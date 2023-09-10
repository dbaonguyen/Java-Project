//import Entities.Port;
//import Entities.Trip;
//import Entities.Truck;
//import Entities.Vehicle;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Scanner;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.ScheduledFuture;
//import java.util.concurrent.TimeUnit;
//
//public class Transportation {
//    private Vehicle vehicle;
//    private Port destinationPort;
//    private Date departureDate;
//    private Date arrivalDate;
//    private Trip trip;
//
//    public Transportation(Vehicle vehicle, Port destinationPort, Date departureDate, Date arrivalDate) {
//        this.vehicle = vehicle;
//        this.destinationPort = destinationPort;
//        this.departureDate = departureDate;
//        this.arrivalDate = arrivalDate;
//    }
//
//
//    public void run() {
//        // Departure action
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        // Print a line at the departure time
//        emruy(dateFormat);
//    }
//    public void emruy(SimpleDateFormat dateFormat) {
//        System.out.println(vehicle.getName() + " is departing from " + destinationPort.getName() + " at " + dateFormat.format(departureDate));
//
//        // Departure action
//        trip = vehicle.moveToPort(destinationPort, departureDate, arrivalDate);
//        System.out.println(trip);
//    }
//
//    public void run2() {
//        // Departure action
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        // Print a line at the departure time
//
//        emruy2(dateFormat);
//    }
//
//    public void emruy2(SimpleDateFormat dateFormat) {
//        // Print a line at the arrival time
//        System.out.println(vehicle.getName() + " has arrived at " + destinationPort.getName() + " at " + dateFormat.format(arrivalDate));
//
//
//        // Call the "has arrived" method
//        vehicle.hasArrived(trip);
//        System.out.println(trip);
//    }
//    public static boolean isValidDate(String input) {
//        // Define a regular expression pattern to match a valid date format
//        String dateFormatPattern = "\\d{1,2}/\\d{1,2}/\\d{4} \\d{2}:\\d{2}:\\d{2}";
//
//        // Check if the input matches the pattern
//        if (!input.matches(dateFormatPattern)) {
//            return false;
//        }
//
//        // If it matches the pattern, try to parse it as a date
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        sdf.setLenient(false); // Disable leniency to enforce strict date parsing
//
//        try {
//            Date parsedDate = sdf.parse(input);
//            // If parsing succeeds without exceptions, it's a valid date
//            return true;
//        } catch (ParseException e) {
//            // Parsing failed, so it's an invalid date
//            return false;
//        }
//    }
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        Port newPort = new Port("p-003", "New Port", 40.7128, -74.0060, 5000, 1500, true);
//        Truck newVehicle = new Truck("tr-001", "Truck 1", 10000, 200, newPort);
//
//        do {
//            try {
//                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//                Date currentTime = new Date(); // Get the current time
//
//                System.out.println("Please enter the departure date (dd/MM/yyyy HH:mm:ss)");
//                String departureDateStr = scanner.nextLine();
//
//                System.out.println("Please enter the arrival date (dd/MM/yyyy HH:mm:ss)");
//                String arrivalDateStr = scanner.nextLine();
//
//                if (isValidDate(departureDateStr) && isValidDate(arrivalDateStr)) {
//                    Date departureDate = dateFormat.parse(departureDateStr);
//                    Date arrivalDate = dateFormat.parse(arrivalDateStr);
//
//                    if (departureDate.compareTo(currentTime) >= 0 && arrivalDate.compareTo(departureDate) > 0) {
//                        long departureDelayMillis = departureDate.getTime() - System.currentTimeMillis();
//                        long arrivalDelayMillis = arrivalDate.getTime() - System.currentTimeMillis();
//
//                        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//                        //make a new trip without adding to any port
//
//                        // Schedule departure action
//                        //run moveToPort
//                        Transportation departureTask = new Transportation(newVehicle, newPort, departureDate, arrivalDate);
//                        ScheduledFuture<?> departureFuture = scheduler.schedule(departureTask::run, departureDelayMillis, TimeUnit.MILLISECONDS);
//
//                        // Schedule arrival action
//                        //complete trip and add it 2 ports with hasArrived
//                        Transportation arrivalTask = new Transportation(newVehicle, newPort, departureDate, arrivalDate);
//                        ScheduledFuture<?> arrivalFuture = scheduler.schedule(arrivalTask::run2, arrivalDelayMillis, TimeUnit.MILLISECONDS);
//                        break;
//                    } else {
//                        System.out.println("Invalid dates. Departure date must be >= current time, and arrival date must be > departure date.");
//                    }
//                } else {
//                    System.out.println("Invalid date format. Try again.");
//                }
//            } catch (ParseException e) {
//                System.out.println("Invalid date format. Try again.");
//            }
//        } while (true);
//    }
//}
