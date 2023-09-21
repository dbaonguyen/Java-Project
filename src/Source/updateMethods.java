package Source;

import Entities.*;
import Users.User;

import java.util.List;
import java.util.Scanner;

public class updateMethods {
    private static final Scanner scanner = new Scanner(System.in);
    public static void decorativeLine() {
        for (int i = 0;i < 50;i++){
            System.out.print("*");
        }
    }
    public static int updateMenu(){
        System.out.println("1. Update Port");
        System.out.println("2. Update Vehicle");
        System.out.println("3. Update Container");
        System.out.println("4. Update Port Manager account");
        System.out.println("0. Go back");
        try {
            System.out.print("Your option: ");
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Please choose a valid option: ");
            return -1;
        }
    }
    public static int portOptionsMenuToUpdate(int choice) {
        //Menu
        decorativeLine();
        System.out.println();
        System.out.println("1. Update Port ID \t\t\t|\t\t2. Update Port name \t\t|\t\t3. Update Port latitude");
        System.out.println("4. Update Port longitude\t|\t\t5. Update Port capacity \t|\t\t6. Update Port landing ability");
        System.out.println("0. Go back");

        try {
            System.out.print("Your option: ");
            choice = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Please choose a valid option: ");
        }
        return choice;
    }
    public static void portOptionsToUpdate(List<String> portIDs, Port port) {
        boolean running3 = true;
        do {
            int choice = -1;
            choice = portOptionsMenuToUpdate(choice);
            switch (choice) {
                //Update portID
                case 1 -> {
                    String portID;
                    String tempID;
                    do {
                        try {
                            System.out.println("Please enter the port ID by the format 'p-portID': ");
                            portID = scanner.nextLine();

                            if (!portID.matches("p-\\d+")) {
                                System.out.println("Invalid ID. The ID must be in the format 'p-<integer>'.");
                            } else {
                                if (!portIDs.contains(portID)) {
                                    tempID = port.getPortID();
                                    portIDs.add(portID);
                                    portIDs.remove(tempID);
                                    portIDs.remove(tempID);
                                    break;
                                } else {
                                    System.out.println("The ID is already existed!");
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid value");
                        }
                    } while (true);
                    port.setPortID(portID);
                    System.out.println("Updated successfully");
                }

                //Update port name
                case 2 -> {
                    System.out.println("Please enter your port name:");
                    String portName = scanner.nextLine();
                    port.setName(portName);
                    System.out.println("Updated successfully");
                }

                //Update port latitude
                case 3 -> {
                    double portLatitude;
                    do {
                        try {
                            System.out.println("Please enter your port latitude:");
                            portLatitude = Double.parseDouble(scanner.nextLine());
                            break;
                        } catch (Exception e) {
                            System.out.println("Please enter a valid value");
                        }
                    } while (true);
                    port.setLatitude(portLatitude);
                    System.out.println("Updated successfully");

                }

                //Update port longitude
                case 4 -> {
                    double portLongitude;
                    do {
                        try {
                            System.out.println("Please enter your port longitude:");
                            portLongitude = Double.parseDouble(scanner.nextLine());
                            break;
                        } catch (Exception e) {
                            System.out.println("Please enter a valid value");
                        }
                    } while (true);
                    port.setLongitude(portLongitude);
                    System.out.println("Updated successfully");
                }

                //Update port capacity
                case 5 -> {
                    double portCapacity;
                    do {
                        try {
                            System.out.println("Please enter your port capacity:");
                            portCapacity = Double.parseDouble(scanner.nextLine());
                            break;
                        } catch (Exception e) {
                            System.out.println("Please enter a valid value");
                        }
                    } while (true);
                    port.setCapacity(portCapacity);
                    System.out.println("Updated successfully");
                }

                //Update port landing ability
                case 6 -> {
                    boolean portLandingAbility;
                    boolean found = false;
                    do {
                        try {
                            System.out.println("Please enter your port landing ability (true/false):");
                            portLandingAbility = scanner.nextBoolean();
                            scanner.nextLine();
                            break;
                        } catch (Exception e) {
                            System.out.println("Please enter a valid value");
                            scanner.nextLine();
                        }
                    } while (true);

                    for (Vehicle vehicle : port.getVehicles()){
                        if (vehicle instanceof Truck || vehicle instanceof ReeferTruck || vehicle instanceof TankerTruck){
                            found = true;
                            System.out.println("The port is containing trucks, please remove all trucks to modify");
                            break;
                        }
                    }

                    if (!found){
                        port.setLandingAbility(portLandingAbility);
                        System.out.println("Updated successfully");
                    }
                }

                //Exit
                case 0 -> running3 = false;
                default -> System.out.println("Please choose from 1-12");
            }
        } while (running3);
    }
    public static void choosePortToUpdate(List<String> portIDs) {

        do {
            //Port IDs
            for (Port port : AdminInterface.portList) {
                System.out.println(port.getPortID() + ". " + port.getName());
                portIDs.add(port.getPortID());
            }

            System.out.println("0. Go back");
            System.out.print("Enter the ID of the port above that you want to modify: ");
            String portOption = scanner.nextLine();

            if (portOption.equals("0")) {
                break;
            } else {
                if (!portIDs.contains(portOption)) {
                    System.out.println("Port does not exist");
                } else {
                    for (Port port : AdminInterface.portList) {
                        if (portOption.equals(port.getPortID())) {
                            portOptionsToUpdate(portIDs, port);
                        }
                    }
                }
            }
        } while (true);
    }
    public static void vehicleOptions(Port port){
        decorativeLine();
        System.out.println();

        do {

            for (Vehicle vehicle : port.getVehicles()) {
                System.out.println(vehicle.getVehicleID() + ". " + vehicle.getName());
            }
            System.out.println("0. Go back");
            System.out.println("Enter the vehicle ID that you want to modify");
            String vehicleChoice = scanner.nextLine();

            if (vehicleChoice.equals("0")) {
                break;
            }


            Vehicle selectedVehicle = null;
            for (Vehicle vehicle : port.getVehicles()) {
                if (vehicleChoice.equals(vehicle.getVehicleID())) {
                    selectedVehicle = vehicle;
                    break;
                }
            }

            if (selectedVehicle != null) {
                portOptionsToUpdateVehicle(port, selectedVehicle);
            } else {
                System.out.println("The vehicle does not exist");
            }
        } while (true);
    }
    public static int portOptionsMenuToUpdateVehicle(int choice) {
        //Menu

        decorativeLine();
        System.out.println();
        System.out.println("1. Update Vehicle ID\t\t\t|\t\t 2. Update Vehicle name \t\t|\t\t 3. Update Vehicle capacity");
        System.out.println("4. Update Vehicle fuel capacity\t|\t\t 0. Go back \t\t|");


        try {
            System.out.print("Your option: ");
            choice = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Please choose a valid option: ");
        }
        return choice;
    }
    public static void portOptionsToUpdateVehicle(Port port, Vehicle vehicle) {
        boolean running3 = true;
        do {
            int choice = -1;
            choice = portOptionsMenuToUpdateVehicle(choice);
            switch (choice) {
                //Update vehicleID
                case 1 -> {
                    String vehicleID;

                    do {
                        try {
                            if (vehicle instanceof Ship) {
                                System.out.println("Please enter the vehicle ID by the format 's-vehicleID': ");
                                vehicleID = scanner.nextLine();

                                if (!vehicleID.matches("s-\\d+")) {
                                    System.out.println("Invalid ID. The ID must be in the format 's-<integer>'.");
                                    continue; // Restart the loop to get a valid ID
                                }
                            } else if (vehicle instanceof Truck || vehicle instanceof ReeferTruck || vehicle instanceof TankerTruck) {
                                System.out.println("Please enter the vehicle ID by the format 'tr-vehicleID': ");
                                vehicleID = scanner.nextLine();

                                if (!vehicleID.matches("tr-\\d+")) {
                                    System.out.println("Invalid ID. The ID must be in the format 'tr-<integer>'.");
                                    continue; // Restart the loop to get a valid ID
                                }
                            } else {

                                break;
                            }

                            boolean idExists = false;
                            for (Vehicle vehicleChoice : port.getVehicles()) {
                                if (vehicleChoice != vehicle && vehicleChoice.getVehicleID().equals(vehicleID)) {
                                    idExists = true;
                                    System.out.println("The ID is already in use!");
                                    break; // No need to check further, the ID already exists
                                }
                            }

                            if (!idExists) {
                                vehicle.setVehicleID(vehicleID);
                                System.out.println("Updated successfully");
                                break; // Exit the loop since a valid, unique ID has been set
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid value");
                        }
                    } while (true);

                }

                //Update vehicle name
                case 2 -> {
                    System.out.println("Please enter the new vehicle name:");
                    String vehicleName = scanner.nextLine();
                    vehicle.setName(vehicleName);
                    // Update the vehicle in the port's list
                    for (Vehicle v : port.getVehicles()) {
                        if (v.getVehicleID().equals(vehicle.getVehicleID())) {
                            v.setName(vehicleName);
                            System.out.println("Updated successfully");
                            break;
                        }
                    }

                }

                //Update vehicle capacity
                case 3 -> {
                    int capacity;
                    do {
                        try {
                            System.out.println("Please enter your vehicle capacity:");
                            capacity = Integer.parseInt(scanner.nextLine());
                            break;
                        } catch (Exception e) {
                            System.out.println("Please enter a valid value");
                        }
                    } while (true);
                    vehicle.setCapacity(capacity);
                    System.out.println("Updated successfully");

                }

                //Update vehicle fuel capacity
                case 4 -> {
                    double fuelCapacity;
                    do {
                        try {
                            System.out.println("Please enter your vehicle capacity:");
                            fuelCapacity = Double.parseDouble(scanner.nextLine());
                            break;
                        } catch (Exception e) {
                            System.out.println("Please enter a valid value");
                        }
                    } while (true);
                    vehicle.setFuelCapacity(fuelCapacity);
                    System.out.println("Updated successfully");
                }


                //Exit
                case 0 -> running3 = false;
                default -> System.out.println("Please choose from 1-4");
            }
        } while (running3);
    }
    public static void choosePortToUpdateVehicle(List<String> portIDs) {
        do {
            //Port IDs
            for (Port port : AdminInterface.portList) {
                System.out.println(port.getPortID() + ". " + port.getName());
                portIDs.add(port.getPortID());
            }

            System.out.println("0. Go back");
            System.out.print("Enter the ID of the port above that you want to modify: ");
            String portOption = scanner.nextLine();

            if (portOption.equals("0")) {
                break;
            } else {
                if (!portIDs.contains(portOption)) {
                    System.out.println("Port does not exist");
                } else {
                    for (Port port : AdminInterface.portList) {
                        if (portOption.equals(port.getPortID())) {
                            vehicleOptions(port);
                        }
                    }
                }
            }
        } while (true);
    }
    public static void containerOptions(Port port){
        decorativeLine();
        System.out.println();


        do {

            for (Container container : port.getContainers()) {
                System.out.println(container.getContainerID() + ".");
            }
            System.out.println("0. Go back");
            System.out.println("Enter the container ID that you want to modify");
            String containerChoice = scanner.nextLine();

            if (containerChoice.equals("0")) {
                break;
            }


            Container selectedContainer = null;
            for (Container container : port.getContainers()) {
                if (containerChoice.equals(container.getContainerID())) {
                    selectedContainer = container;
                    break;
                }
            }

            if (selectedContainer != null) {
                portOptionsToUpdateContainer(port, selectedContainer);
            } else {
                System.out.println("The container does not exist");
            }
        } while (true);
    }
    public static void choosePortToUpdateContainer(List<String> portIDs) {

        do {
            //Port IDs
            for (Port port : AdminInterface.portList) {
                System.out.println(port.getPortID() + ". " + port.getName());
                portIDs.add(port.getPortID());
            }

            System.out.println("0. Go back");
            System.out.print("Enter the ID of the port above that you want to modify: ");
            String portOption = scanner.nextLine();

            if (portOption.equals("0")) {
                break;
            } else {
                if (!portIDs.contains(portOption)) {
                    System.out.println("Port does not exist");
                } else {
                    for (Port port : AdminInterface.portList) {
                        if (portOption.equals(port.getPortID())) {
                            containerOptions(port);
                        }
                    }
                }
            }
        } while (true);
    }
    public static void portOptionsToUpdateContainer(Port port, Container container)     {
        boolean running3 = true;
        do {
            int choice = -1;
            choice = portOptionsMenuToUpdateContainer(choice);
            switch (choice) {
                //Update ContainerID
                case 1 -> {
                    String containerID;

                    do {
                        try {
                            System.out.println("Please enter the vehicle ID by the format 'c-containerID': ");
                            containerID = scanner.nextLine();

                            if (!containerID.matches("c-\\d+")) {
                                System.out.println("Invalid ID. The ID must be in the format 'c-<integer>'.");
                                continue; // Restart the loop to get a valid ID
                            }

                            boolean idExists = false;
                            for (Container containerChoice : port.getContainers()) {
                                if (containerChoice != container && containerChoice.getContainerID().equals(containerID)) {
                                    idExists = true;
                                    System.out.println("The ID is already in use!");
                                    break; // No need to check further, the ID already exists
                                }
                            }

                            if (!idExists) {
                                container.setContainerID(containerID);
                                System.out.println("Updated successfully");
                                break; // Exit the loop since a valid, unique ID has been set
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid value");
                        }
                    } while (true);

                }

                //Update container weight
                case 2 -> {
                    int containerWeight;
                    do {
                        try {
                            System.out.println("Please enter your vehicle capacity:");
                            containerWeight = Integer.parseInt(scanner.nextLine());
                            break;
                        } catch (Exception e) {
                            System.out.println("Please enter a valid value");
                        }
                    } while (true);
                    container.setWeight(containerWeight);
                    System.out.println("Updated successfully");

                }



                //Exit
                case 0 -> running3 = false;
                default -> System.out.println("Please choose from 1-2");
            }
        } while (running3);
    }
    public static int portOptionsMenuToUpdateContainer(int choice) {
        //Menu

        decorativeLine();
        System.out.println();
        System.out.println("1. Update Container ID\t\t|\t\t2. Update Container weight\t\t|\t\t0. Go back");


        try {
            System.out.print("Your option: ");
            choice = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Please choose a valid option: ");
        }
        return choice;
    }
    public static void chooseUserToUpdate(List<String> usedUsername) {
        decorativeLine();
        System.out.println();
        do {
            for (User user : AdminInterface.userList) {
                System.out.println(user.getUsername());
            }
            System.out.println("0. Go back");
            System.out.println("Enter the username of the port manager that you want to modify: ");
            String userChoice = scanner.nextLine();

            if (userChoice.equals("0")) {
                break;
            }
            User selectedUser = null;
            for (User user : AdminInterface.userList) {
                if (userChoice.equals(user.getUsername())) {
                    selectedUser = user;
                    break;
                }
            }
            if (selectedUser != null) {
                userOptionsToUpdate(usedUsername, selectedUser);
            } else {
                System.out.println("The container does not exist");
            }
        } while (true);
    }

    public static void userOptionsToUpdate(List<String> usedUsername, User user){
        boolean running3 = true;
        do {
            int choice = -1;
            choice = userOptionsMenuToUpdate(choice);
            switch (choice) {
                //Update username
                case 1 -> {
                    do{
                        System.out.println("Enter the new username: ");
                        String newUsername = scanner.nextLine();

                        if (!usedUsername.contains(newUsername)){
                            usedUsername.remove(user.getUsername());
                            user.setUsername(newUsername);
                            break;
                        } else{
                            System.out.println("This user name is already existed!");
                        }
                    }while (true);
                }

                //Update password
                case 2 -> {
                    String confirmationPassword;
                    System.out.println("Enter the new password: ");
                    String newPassword = scanner.nextLine();

                    do{
                        System.out.println("Confirm password: ");
                        confirmationPassword = scanner.nextLine();
                        if (confirmationPassword.equals(newPassword)){
                            user.setPassword(newPassword);
                            break;
                        } else{
                            System.out.println("Please reconfirm your password:");
                        }

                    } while (true);
                }

                //Exit
                case 0 -> running3 = false;
                default -> System.out.println("Please choose from 1-12");
            }
        } while (running3);
    }

    public static int userOptionsMenuToUpdate(int choice) {
        //Menu
        decorativeLine();
        System.out.println();
        System.out.println("1. Update username \t|\t 2. Update password");
        System.out.println("0. Go back");

        try {
            System.out.print("Your option: ");
            choice = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Please choose a valid option: ");
        }
        return choice;
    }



}
