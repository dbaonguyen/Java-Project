package Source;

import Entities.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Transportation {

    private Vehicle vehicle;
    private Port destinationPort;
    private Date departureDate;
    private Date arrivalDate;
    private static final Scanner scanner = new Scanner(System.in);
    public Transportation(Vehicle vehicle, Port destinationPort, Date departureDate, Date arrivalDate) {
        this.vehicle = vehicle;
        this.destinationPort = destinationPort;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }
    public Trip run() {
        // Departure action
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        // Print a line at the departure time
        AdminInterface.notificationList.add(vehicle.getName() + " is departing from " + vehicle.getPort().getName() + " at " + dateFormat.format(departureDate));
        // Departure action
        Trip trip = vehicle.moveToPort(destinationPort, departureDate, arrivalDate);
        return trip;
    }

    public void run2(Trip trip) {
        // Departure action
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        // Print a line at the departure time
        vehicle.hasArrived(trip);
        // Print a line at the arrival time
        AdminInterface.notificationList.add(vehicle.getName() + " has arrived at " + destinationPort.getName() + " at " + dateFormat.format(arrivalDate));
        // Call the "has arrived" method

    }
    public static void setTransportShip(List<String> shipIDs, String shipID) {
        if (shipIDs.contains(shipID)) {
            for (Ship ship : AdminInterface.shipList) {
                if (shipID.equals(ship.getVehicleID())) {
                    if (!ship.isStatus()) {
                        System.out.println("This ship is unavailable!");
                    } else {
                        do {
                            List<String> portCanMoveToIDs = new ArrayList<>();
                            for (Port port : AdminInterface.portList) {
                                if (port != ship.getPort()) {
                                    System.out.println(port.getPortID() + ". " + port.getName());
                                    portCanMoveToIDs.add(port.getPortID());
                                }
                            }
                            System.out.println("0. Go back");
                            System.out.println("Please enter the ID of the port that you want the ship to move to:");
                            String portCanMoveToID = scanner.nextLine();

                            if (portCanMoveToID.equals("0")) {
                                break;
                            } else {
                                if (portCanMoveToIDs.contains(portCanMoveToID)) {
                                    for (Port port : AdminInterface.portList) {
                                        if (portCanMoveToID.equals(port.getPortID())) {
                                            if (ship.canMoveToPort(port)){
                                                transportShip(ship, port);
                                            }
                                        }
                                    }
                                    break;
                                } else {
                                    System.out.println("Port does not exist");
                                }
                            }
                        } while (true);
                    }
                }
            }
        } else {
            System.out.println("The ship does not exist");
        }
    }
    public static void transportShip(Ship ship, Port port) {
        do {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date currentTime = new Date(); // Get the current time

                System.out.println("Please enter the departure date (dd/MM/yyyy HH:mm:ss)");
                String departureDateStr = scanner.nextLine();

                System.out.println("Please enter the arrival date (dd/MM/yyyy HH:mm:ss)");
                String arrivalDateStr = scanner.nextLine();


                if (isValidDate(departureDateStr) && isValidDate(arrivalDateStr)) {
                    Date departureDate = dateFormat.parse(departureDateStr);
                    Date arrivalDate = dateFormat.parse(arrivalDateStr);

                    if (departureDate.compareTo(currentTime) >= 0 && arrivalDate.compareTo(departureDate) > 0) {
                        do {
                            System.out.print("Do you want to confirm this transportation?(y/n): ");
                            String option = scanner.nextLine();
                            if (option.equals("y")) {
                                System.out.println("The transportation procedure is completed!");
                                AdminInterface.decorativeLine();
                                Trip newTrip = new Trip(ship, ship.getPort(), departureDate, arrivalDate, port, false);
                                AdminInterface.tripList.add(newTrip);
                                long departureDelayMillis = departureDate.getTime() - System.currentTimeMillis();
                                long arrivalDelayMillis = arrivalDate.getTime() - System.currentTimeMillis();

                                ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                                //make a new trip without adding to any port
                                CompletableFuture<Trip> tripFuture = new CompletableFuture<>();
                                // Schedule departure action
                                Transportation departureTask = new Transportation(ship, port, departureDate, arrivalDate);
                                ScheduledFuture<?> departureFuture = scheduler.schedule(() -> {
                                    AdminInterface.tripList.remove(newTrip);
                                    Trip trip = departureTask.run();
                                    tripFuture.complete(trip); // Store the Trip object in the CompletableFuture
                                }, departureDelayMillis, TimeUnit.MILLISECONDS);
                                ScheduledFuture<?> arrivalFuture = scheduler.schedule(() -> {
                                    try {
                                        // Wait for the departure task to complete and retrieve the Trip object
                                        Trip trip = tripFuture.get();
                                        // Now you have the Trip object, you can use it in the arrival task
                                        Transportation arrivalTask = new Transportation(ship, port, departureDate, arrivalDate);
                                        arrivalTask.run2(trip); // Pass the Trip object as a parameter to run2
                                    } catch (InterruptedException | ExecutionException e) {
                                        // Handle exceptions if needed
                                    }
                                }, arrivalDelayMillis, TimeUnit.MILLISECONDS);
                                break;
                            } else if (option.equals("n")) {
                                System.out.println("The procedure is cancelled");
                                AdminInterface.decorativeLine();
                                System.out.println();
                                break;
                            }
                        } while (true);
                        break;
                    } else {
                        System.out.println("Invalid dates. Departure date must be >= current time, and arrival date must be > departure date.");
                    }
                } else {
                    System.out.println("Invalid date format. Try again.");
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format. Try again.");
            }
        } while (true);
        ship.setStatus(false);
        System.out.println();
    }
    public static void transportationShip() {
        do {
            List<String> shipIDs = new ArrayList<>();
            for (Ship ship : AdminInterface.shipList) {
                if (ship.getPort() == null) {
                    System.out.println(ship.getVehicleID() + " | status: is arriving...");
                } else {
                    System.out.println(ship.getVehicleID() +". " + ship.getName() + " " + ship.getPort().getName() + " (" + ship.getStatus() + ")"  + " (" + ship.getCurrentFuel() + "/" + ship.getFuelCapacity() + ")");
                }
                shipIDs.add(ship.getVehicleID());
            }
            System.out.println("0. Go back");
            System.out.println("Please enter the ID of the ship that you want to do the transportation:");
            String shipID = scanner.nextLine();

            if (shipID.equals("0")) {
                break;
            } else {
                setTransportShip(shipIDs, shipID);
            }
        } while (true);
    }
    public static void setTransportTruck(List<String> truckIDs, String truckID) {
        if (truckIDs.contains(truckID)) {
            for (Truck truck : AdminInterface.truckList) {
                if (truckID.equals(truck.getVehicleID())) {
                    if (!truck.isStatus()) {
                        System.out.println("This ship is unavailable!");
                    } else {
                        do {
                            List<String> portCanMoveToIDs = new ArrayList<>();
                            for (Port port : AdminInterface.portList) {
                                if (port != truck.getPort()) {
                                    System.out.println(port.getPortID() + ". " + port.getName());
                                    portCanMoveToIDs.add(port.getPortID());
                                }
                            }
                            System.out.println("0. Go back");
                            System.out.println("Please enter the ID of the port that you want the truck to move to:");
                            String portCanMoveToID = scanner.nextLine();

                            if (portCanMoveToID.equals("0")) {
                                break;
                            } else {
                                if (portCanMoveToIDs.contains(portCanMoveToID)) {
                                    for (Port port : AdminInterface.portList) {
                                        if (portCanMoveToID.equals(port.getPortID())) {
                                            if (truck.canMoveToPort(port)){
                                                transportTruck(truck, port);
                                            }
                                        }
                                    }
                                    break;
                                } else {
                                    System.out.println("Port does not exist");
                                }
                            }
                        } while (true);
                    }
                }
            }
        } else {
            System.out.println("The truck does not exist");
        }
    }
    public static void transportTruck(Truck truck, Port port) {
        do {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date currentTime = new Date(); // Get the current time

                System.out.println("Please enter the departure date (dd/MM/yyyy HH:mm:ss)");
                String departureDateStr = scanner.nextLine();

                System.out.println("Please enter the arrival date (dd/MM/yyyy HH:mm:ss)");
                String arrivalDateStr = scanner.nextLine();


                if (isValidDate(departureDateStr) && isValidDate(arrivalDateStr)) {
                    Date departureDate = dateFormat.parse(departureDateStr);
                    Date arrivalDate = dateFormat.parse(arrivalDateStr);

                    if (departureDate.compareTo(currentTime) >= 0 && arrivalDate.compareTo(departureDate) > 0) {
                        do {
                            System.out.print("Do you want to confirm this transportation?(y/n): ");
                            String option = scanner.nextLine();
                            if (option.equals("y")) {
                                System.out.println("The transportation procedure is completed!");
                                AdminInterface.decorativeLine();
                                Trip newTrip = new Trip(truck, truck.getPort(), departureDate, arrivalDate, port, false);
                                AdminInterface.tripList.add(newTrip);
                                long departureDelayMillis = departureDate.getTime() - System.currentTimeMillis();
                                long arrivalDelayMillis = arrivalDate.getTime() - System.currentTimeMillis();

                                ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                                //make a new trip without adding to any port
                                CompletableFuture<Trip> tripFuture = new CompletableFuture<>();
                                // Schedule departure action
                                Transportation departureTask = new Transportation(truck, port, departureDate, arrivalDate);
                                ScheduledFuture<?> departureFuture = scheduler.schedule(() -> {
                                    AdminInterface.tripList.remove(newTrip);
                                    Trip trip = departureTask.run();
                                    tripFuture.complete(trip); // Store the Trip object in the CompletableFuture
                                }, departureDelayMillis, TimeUnit.MILLISECONDS);
                                ScheduledFuture<?> arrivalFuture = scheduler.schedule(() -> {
                                    try {
                                        // Wait for the departure task to complete and retrieve the Trip object
                                        Trip trip = tripFuture.get();
                                        // Now you have the Trip object, you can use it in the arrival task
                                        Transportation arrivalTask = new Transportation(truck, port, departureDate, arrivalDate);
                                        arrivalTask.run2(trip); // Pass the Trip object as a parameter to run2
                                    } catch (InterruptedException | ExecutionException e) {
                                        // Handle exceptions if needed
                                    }
                                }, arrivalDelayMillis, TimeUnit.MILLISECONDS);
                                break;
                            } else if (option.equals("n")) {
                                System.out.println("The procedure is cancelled");
                                AdminInterface.decorativeLine();
                                System.out.println();
                                break;
                            }
                        } while (true);
                        break;
                    } else {
                        System.out.println("Invalid dates. Departure date must be >= current time, and arrival date must be > departure date.");
                    }
                } else {
                    System.out.println("Invalid date format. Try again.");
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format. Try again.");
            }
        } while (true);
        truck.setStatus(false);
        System.out.println();
    }
    public static void transportationTruck() {
        do {
            List<String> truckIDs = new ArrayList<>();
            for (Truck truck : AdminInterface.truckList) {
                if (truck.getPort() == null) {
                    System.out.println(truck.getVehicleID() +". " + truck.getPort() + " | status: is arriving...");
                } else {
                    System.out.println(truck.getVehicleID() +". " + truck.getName() + " " + truck.getPort().getName() + " (" + truck.getStatus() + ")"  + " (" + truck.getCurrentFuel() + "/" + truck.getFuelCapacity() + ")");
                }
                truckIDs.add(truck.getVehicleID());
            }
            System.out.println("0. Go back");
            System.out.println("Please enter the ID of the truck that you want to do the transportation:");
            String truckID = scanner.nextLine();

            if (truckID.equals("0")) {
                break;
            } else {
                setTransportTruck(truckIDs, truckID);
            }
        } while (true);
    }
    public static void setTransportReeferTruck(List<String> reeferTruckIDs, String reeferTruckID) {
        if (reeferTruckIDs.contains(reeferTruckID)) {
            for (ReeferTruck reeferTruck : AdminInterface.reeferTruckList) {
                if (reeferTruckID.equals(reeferTruck.getVehicleID())) {
                    if (!reeferTruck.isStatus()) {
                        System.out.println("This ship is unavailable!");
                    } else {
                        do {
                            List<String> portCanMoveToIDs = new ArrayList<>();
                            for (Port port : AdminInterface.portList) {
                                if (port != reeferTruck.getPort()) {
                                    System.out.println(port.getPortID() + ". " + port.getName());
                                    portCanMoveToIDs.add(port.getPortID());
                                }
                            }
                            System.out.println("0. Go back");
                            System.out.println("Please enter the ID of the port that you want the reefer truck to move to:");
                            String portCanMoveToID = scanner.nextLine();

                            if (portCanMoveToID.equals("0")) {
                                break;
                            } else {
                                if (portCanMoveToIDs.contains(portCanMoveToID)) {
                                    for (Port port : AdminInterface.portList) {
                                        if (portCanMoveToID.equals(port.getPortID())) {
                                            if (reeferTruck.canMoveToPort(port)){
                                                transportReeferTruck(reeferTruck, port);
                                            }
                                        }
                                    }
                                    break;
                                } else {
                                    System.out.println("Port does not exist");
                                }
                            }
                        } while (true);
                    }
                }
            }
        } else {
            System.out.println("The reefer truck does not exist");
        }
    }
    public static void transportationReeferTruck() {
        do {
            List<String> reeferTruckIDs = new ArrayList<>();
            for (ReeferTruck reeferTruck : AdminInterface.reeferTruckList) {
                if (reeferTruck.getPort() == null) {
                    System.out.println(reeferTruck.getVehicleID() + ". " + reeferTruck.getPort() + " | status: is arriving...");
                } else {
                    System.out.println(reeferTruck.getVehicleID() +". " + reeferTruck.getName() + " " + reeferTruck.getPort().getName() + " (" + reeferTruck.getStatus() + ")"  + " (" + reeferTruck.getCurrentFuel() + "/" + reeferTruck.getFuelCapacity() + ")");
                }
                reeferTruckIDs.add(reeferTruck.getVehicleID());
            }
            System.out.println("0. Go back");
            System.out.println("Please enter the ID of the reefer truck that you want to do the transportation:");
            String reeferTruckID = scanner.nextLine();

            if (reeferTruckID.equals("0")) {
                break;
            } else {
                setTransportReeferTruck(reeferTruckIDs, reeferTruckID);
            }
        } while (true);
    }
    public static void transportReeferTruck(ReeferTruck reeferTruck, Port port) {
        do {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date currentTime = new Date(); // Get the current time

                System.out.println("Please enter the departure date (dd/MM/yyyy HH:mm:ss)");
                String departureDateStr = scanner.nextLine();

                System.out.println("Please enter the arrival date (dd/MM/yyyy HH:mm:ss)");
                String arrivalDateStr = scanner.nextLine();


                if (isValidDate(departureDateStr) && isValidDate(arrivalDateStr)) {
                    Date departureDate = dateFormat.parse(departureDateStr);
                    Date arrivalDate = dateFormat.parse(arrivalDateStr);

                    if (departureDate.compareTo(currentTime) >= 0 && arrivalDate.compareTo(departureDate) > 0) {
                        do {
                            System.out.print("Do you want to confirm this transportation?(y/n): ");
                            String option = scanner.nextLine();
                            if (option.equals("y")) {
                                System.out.println("The transportation procedure is completed!");
                                AdminInterface.decorativeLine();
                                Trip newTrip = new Trip(reeferTruck, reeferTruck.getPort(), departureDate, arrivalDate, port, false);
                                AdminInterface.tripList.add(newTrip);
                                long departureDelayMillis = departureDate.getTime() - System.currentTimeMillis();
                                long arrivalDelayMillis = arrivalDate.getTime() - System.currentTimeMillis();

                                ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                                //make a new trip without adding to any port
                                CompletableFuture<Trip> tripFuture = new CompletableFuture<>();
                                // Schedule departure action
                                Transportation departureTask = new Transportation(reeferTruck, port, departureDate, arrivalDate);
                                ScheduledFuture<?> departureFuture = scheduler.schedule(() -> {
                                    AdminInterface.tripList.remove(newTrip);
                                    Trip trip = departureTask.run();
                                    tripFuture.complete(trip); // Store the Trip object in the CompletableFuture
                                }, departureDelayMillis, TimeUnit.MILLISECONDS);
                                ScheduledFuture<?> arrivalFuture = scheduler.schedule(() -> {
                                    try {
                                        // Wait for the departure task to complete and retrieve the Trip object
                                        Trip trip = tripFuture.get();
                                        // Now you have the Trip object, you can use it in the arrival task
                                        Transportation arrivalTask = new Transportation(reeferTruck, port, departureDate, arrivalDate);
                                        arrivalTask.run2(trip); // Pass the Trip object as a parameter to run2
                                    } catch (InterruptedException | ExecutionException e) {
                                        // Handle exceptions if needed
                                    }
                                }, arrivalDelayMillis, TimeUnit.MILLISECONDS);
                                break;
                            } else if (option.equals("n")) {
                                System.out.println("The procedure is cancelled");
                                AdminInterface.decorativeLine();
                                System.out.println();
                                break;
                            }
                        } while (true);
                        break;
                    } else {
                        System.out.println("Invalid dates. Departure date must be >= current time, and arrival date must be > departure date.");
                    }
                } else {
                    System.out.println("Invalid date format. Try again.");
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format. Try again.");
            }
        } while (true);
        reeferTruck.setStatus(false);
        System.out.println();
    }
    public static void setTransportTankerTruck(List<String> tankerTruckIDs, String tankerTruckID) {
        if (tankerTruckIDs.contains(tankerTruckID)) {
            for (TankerTruck tankerTruck : AdminInterface.tankerTruckList) {
                if (tankerTruckID.equals(tankerTruck.getVehicleID())) {
                    if (!tankerTruck.isStatus()) {
                        System.out.println("This ship is unavailable!");
                    } else {
                        do {
                            List<String> portCanMoveToIDs = new ArrayList<>();
                            for (Port port : AdminInterface.portList) {
                                if (port != tankerTruck.getPort()) {
                                    System.out.println(port.getPortID() + ". " + port.getName());
                                    portCanMoveToIDs.add(port.getPortID());
                                }
                            }
                            System.out.println("0. Go back");
                            System.out.println("Please enter the ID of the port that you want the reefer truck to move to:");
                            String portCanMoveToID = scanner.nextLine();

                            if (portCanMoveToID.equals("0")) {
                                break;
                            } else {
                                if (portCanMoveToIDs.contains(portCanMoveToID)) {
                                    for (Port port : AdminInterface.portList) {
                                        if (portCanMoveToID.equals(port.getPortID())) {
                                            if (tankerTruck.canMoveToPort(port)){
                                                transportTankerTruck(tankerTruck, port);
                                            }
                                        }
                                    }
                                    break;
                                } else {
                                    System.out.println("Port does not exist");
                                }
                            }
                        } while (true);
                    }
                }
            }
        } else {
            System.out.println("The reefer truck does not exist");
        }
    }
    public static void transportTankerTruck(TankerTruck tankerTruck, Port port) {
        do {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date currentTime = new Date(); // Get the current time

                System.out.println("Please enter the departure date (dd/MM/yyyy HH:mm:ss)");
                String departureDateStr = scanner.nextLine();

                System.out.println("Please enter the arrival date (dd/MM/yyyy HH:mm:ss)");
                String arrivalDateStr = scanner.nextLine();


                if (isValidDate(departureDateStr) && isValidDate(arrivalDateStr)) {
                    Date departureDate = dateFormat.parse(departureDateStr);
                    Date arrivalDate = dateFormat.parse(arrivalDateStr);

                    if (departureDate.compareTo(currentTime) >= 0 && arrivalDate.compareTo(departureDate) > 0) {
                        do {
                            System.out.print("Do you want to confirm this transportation?(y/n): ");
                            String option = scanner.nextLine();
                            if (option.equals("y")) {
                                System.out.println("The transportation procedure is completed!");
                                AdminInterface.decorativeLine();
                                System.out.println("The transportation procedure is completed!");
                                Trip newTrip = new Trip(tankerTruck, tankerTruck.getPort(), departureDate, arrivalDate, port, false);
                                AdminInterface.tripList.add(newTrip);
                                long departureDelayMillis = departureDate.getTime() - System.currentTimeMillis();
                                long arrivalDelayMillis = arrivalDate.getTime() - System.currentTimeMillis();

                                ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                                //make a new trip without adding to any port
                                CompletableFuture<Trip> tripFuture = new CompletableFuture<>();
                                // Schedule departure action
                                Transportation departureTask = new Transportation(tankerTruck, port, departureDate, arrivalDate);
                                ScheduledFuture<?> departureFuture = scheduler.schedule(() -> {
                                    AdminInterface.tripList.remove(newTrip);
                                    Trip trip = departureTask.run();
                                    tripFuture.complete(trip); // Store the Trip object in the CompletableFuture
                                }, departureDelayMillis, TimeUnit.MILLISECONDS);
                                ScheduledFuture<?> arrivalFuture = scheduler.schedule(() -> {
                                    try {
                                        // Wait for the departure task to complete and retrieve the Trip object
                                        Trip trip = tripFuture.get();
                                        // Now you have the Trip object, you can use it in the arrival task
                                        Transportation arrivalTask = new Transportation(tankerTruck, port, departureDate, arrivalDate);
                                        arrivalTask.run2(trip); // Pass the Trip object as a parameter to run2
                                    } catch (InterruptedException | ExecutionException e) {
                                        // Handle exceptions if needed
                                    }
                                }, arrivalDelayMillis, TimeUnit.MILLISECONDS);
                                break;
                            } else if (option.equals("n")) {
                                System.out.println("The procedure is cancelled");
                                AdminInterface.decorativeLine();
                                System.out.println();
                                break;
                            }
                        } while (true);
                        break;
                    } else {
                        System.out.println("Invalid dates. Departure date must be >= current time, and arrival date must be > departure date.");
                    }
                } else {
                    System.out.println("Invalid date format. Try again.");
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format. Try again.");
            }
        } while (true);
        tankerTruck.setStatus(false);
        System.out.println();
    }
    public static void transportationTankerTruck() {
        do {
            List<String> tankerTruckIDs = new ArrayList<>();
            for (TankerTruck tankerTruck : AdminInterface.tankerTruckList) {
                if (tankerTruck.getPort() == null) {
                    System.out.println(tankerTruck.getVehicleID() +". " + tankerTruck.getPort() + " | status: is arriving...");
                } else {
                    System.out.println(tankerTruck.getVehicleID() +". " + tankerTruck.getName() + " " + tankerTruck.getPort().getName() + " (" + tankerTruck.getStatus() + ")"  + " (" + tankerTruck.getCurrentFuel() + "/" + tankerTruck.getFuelCapacity() + ")");
                }
                tankerTruckIDs.add(tankerTruck.getVehicleID());
            }
            System.out.println("0. Go back");
            System.out.println("Please enter the ID of the tanker truck that you want to do the transportation:");
            String tankerTruckID = scanner.nextLine();

            if (tankerTruckID.equals("0")) {
                break;
            } else {
                setTransportTankerTruck(tankerTruckIDs, tankerTruckID);
            }
        } while (true);
    }
    public static boolean isValidDate(String input) {
        // Define a regular expression pattern to match a valid date format
        String dateFormatPattern = "\\d{1,2}/\\d{1,2}/\\d{4} \\d{2}:\\d{2}:\\d{2}";

        // Check if the input matches the pattern
        if (!input.matches(dateFormatPattern)) {
            return false;
        }

        // If it matches the pattern, try to parse it as a date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        sdf.setLenient(false); // Disable leniency to enforce strict date parsing

        try {
            Date parsedDate = sdf.parse(input);
            // If parsing succeeds without exceptions, it's a valid date
            return true;
        } catch (ParseException e) {
            // Parsing failed, so it's an invalid date
            return false;
        }
    }
    public static boolean isValidDay(String input) {
        // Define a regular expression pattern to match a valid date format
        String dateFormatPattern = "\\d{1,2}/\\d{1,2}/\\d{4}";

        // Check if the input matches the pattern
        if (!input.matches(dateFormatPattern)) {
            return false;
        }

        // If it matches the pattern, try to parse it as a date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // Disable leniency to enforce strict date parsing

        try {
            Date parsedDate = sdf.parse(input);
            // If parsing succeeds without exceptions, it's a valid date
            return true;
        } catch (ParseException e) {
            // Parsing failed, so it's an invalid date
            return false;
        }
    }
}