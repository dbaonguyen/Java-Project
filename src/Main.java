import Entities.*;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Ports
        List<Port> ports = new ArrayList<>();
        Port port1 = new Port("p-1", "Harbor Bay", 34.0522, -118.2437, 1500.0, 500.0, true);
        Port port2 = new Port("p-2", "Marine City", 40.7128, -74.0060, 1800.0,700.0 , false);
        Port port3 = new Port("p-3", "Ocean View", -33.8688, 151.2093, 2000.0,800.0 , true);
        Port port4 = new Port("p-4", "Seaside Haven", 37.7749, -122.4194, 1200.0,400.0 , true);
        Port port5 = new Port("p-5", "Island Port", 21.3069, -157.8583, 1600.0,600.0 , false);
        ports.add(port1);
        ports.add(port2);
        ports.add(port3);
        ports.add(port4);
        ports.add(port5);

        Type openTop = new Type("Open top", 2.8, 3.2);
        Type openSide = new Type("Open side", 2.7, 3.2);
        Type refrigerated = new Type("Refrigerated", 4.5, 5.4);
        Type liquid = new Type("Liquid", 4.8, 5.3);
        Type dryStorage = new Type("Dry storage",3.5, 4.6);
        List<Container> containerList = new ArrayList<>();

        port2.addContainer(new Container("C1", 5.0, dryStorage));
        port1.addContainer(new Container("C2", 7.2, openTop));
        port3.addContainer(new Container("C3", 4.3, openSide));
        port4.addContainer(new Container("C4", 6.1, refrigerated));
        port5.addContainer(new Container("C5", 3.9, liquid));
        port1.addContainer(new Container("C6", 8.6, dryStorage));
        port2.addContainer(new Container("C7", 2.5, openTop));
        port3.addContainer(new Container("C8", 4.8, openSide));
        port4.addContainer(new Container("C9", 5.7, refrigerated));
        port5.addContainer(new Container("C10", 6.9, liquid));

        Ship ship1 = new Ship("S1", "Ship 1", 10000.0, 2000.0, 20000.0, 4000.0, port1);
        Ship ship2 = new Ship("S2", "Ship 2", 15000.0, 3000.0, 25000.0, 5000.0, port2);
        Ship ship3 = new Ship("S3", "Ship 3", 12000.0, 2500.0, 22000.0, 4500.0, port3);

        Truck truck1 = new Truck("T1", "Truck 1", 5000.0, 500.0, 10000.0, 1000.0, port1);
        Truck truck2 = new Truck("T2", "Truck 2", 6000.0, 600.0, 11000.0, 1100.0, port2);
        Truck truck3 = new Truck("T3", "Truck 3", 7000.0, 700.0, 12000.0, 1200.0, port3);

        ReeferTruck reeferTruck1 = new ReeferTruck("RT1", "Reefer Truck 1", 6000.0, 600.0, 12000.0, 1200.0, port1);
        ReeferTruck reeferTruck2 = new ReeferTruck("RT2", "Reefer Truck 2", 7000.0, 700.0, 13000.0, 1300.0, port2);
        ReeferTruck reeferTruck3 = new ReeferTruck("RT3", "Reefer Truck 3", 8000.0, 800.0, 14000.0, 1400.0, port3);

        TankerTruck tankerTruck1 = new TankerTruck("TT1", "Tanker Truck 1", 7000.0, 700.0, 14000.0, 1400.0, port1);
        TankerTruck tankerTruck2 = new TankerTruck("TT2", "Tanker Truck 2", 8000.0, 800.0, 15000.0, 1500.0, port2);
        TankerTruck tankerTruck3 = new TankerTruck("TT3", "Tanker Truck 3", 9000.0, 900.0, 16000.0, 1600.0, port3);

        HashMap<String, String> userCredentials = new HashMap<>();
        userCredentials.put("1", "1");
        userCredentials.put("2", "2");
        userCredentials.put("3", "3");

        Scanner scanner = new Scanner(System.in);

        //Menu
        boolean running = true;
        while (running) {
            System.out.println("Please choose your option");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            String option = scanner.next();

            //Login
            if (option.equals("1")) {
                //Usernames and Password
                while (true) {
                    System.out.print("Enter your username: ");
                    String username = scanner.next();

                    System.out.print("Enter your password: ");
                    String password = scanner.next();
                    System.out.println();

                    //Correct Username and password
                    if (userCredentials.containsKey(username) && userCredentials.containsValue(password)) {
                        String expectedPassword = userCredentials.get(username);
                        if (username.equals("1") && password.equals(expectedPassword)) {
                            System.out.println("Welcome Admin " + username);
                        while (true) {
                            System.out.println("1. Choose Port");
                            System.out.println("2. Add Port");
                            System.out.println("3. Go Back");
                            System.out.print("Your option: ");
                            System.out.println();
                            String option2 = scanner.next();

                            //Admin Option (Choose ports)
                            if (option2.equals("1")) {

                                // Print the ports in the ArrayList for Options
                                List<String> portIDs = new ArrayList<>();

                                //Choose Port
                                while (true) {
                                    for (Port port : ports) {
                                        System.out.println(port.getPortID() + ". " + port.getName());
                                        portIDs.add(port.getPortID());
                                    }
                                    System.out.println("0. Go back");
                                    System.out.println("Enter the ID of the port above that you want to modify: ");
                                    String portOption = scanner.next();

                                    if (portOption.equals("0")) {
                                        break;
                                    }

                                    if (portIDs.contains(portOption)) {
                                        boolean shouldContinue = true;
                                        while (shouldContinue) {
                                            System.out.println("1. Calculate distance");
                                            System.out.println("2. Add Container");
                                            System.out.println("3. Remove Container");
                                            System.out.println("4. Add Vehicle");
                                            System.out.println("5. Remove Vehicle");
                                            System.out.println("6. Search Vehicle");
                                            System.out.println("7. Add Trips");
                                            System.out.println("8. Display Trips");
                                            System.out.println("9. Display Vehicles");
                                            System.out.println("10. Display Containers");
                                            System.out.println("11. Go Back");

                                            String option3 = scanner.next();
                                            switch (option3) {
                                                case "1":
                                                    List<String> portIDs2 = new ArrayList<>();
                                                    for (Port port : ports) {
                                                        System.out.println(port.getPortID() + ". " + port.getName());
                                                        portIDs2.add(port.getPortID());
                                                    }

                                                    while (true) {
                                                        System.out.println("Please enter the port you want to calculate the distance:");
                                                        String portOption2 = scanner.next();
                                                        if (portIDs2.contains(portOption2)) {
                                                            System.out.println("The distance between 2 ports is: " + ports.get(portIDs.indexOf(portOption)).calculateDistance(ports.get(portIDs2.indexOf(portOption2))) + "km");
                                                            System.out.println();
                                                            break;
                                                        } else {
                                                            System.out.println("Please choose an valid option");
                                                            System.out.println();
                                                        }
                                                    }
                                                    break;

                                                case "2":
                                                case "3":
                                                case "4":
                                                case "5":
                                                case "6":
                                                case "7":
                                                case "8":
                                                case "9":
                                                case "10":
                                                    for (Container container : ports.get(portIDs.indexOf(portOption)).getContainers()) {
                                                        System.out.println(container);
                                                    }
                                                    break;

                                                case "11":
                                                    shouldContinue = false;
                                                    break;

                                                default:
                                                    System.out.println("Please choose an valid option: ");
                                                    break;
                                            }
                                        }

                                    } else {
                                        System.out.println("Please choose an valid option");
                                    }
                                }
                            }

                            //Add Port2
                            else if (option2.equals("2")) {
                                System.out.println("Please enter port id:");
                                String portID = scanner.next();
                                System.out.println("Please enter port name:");
                                String portName = scanner.next();
                                System.out.println("Please enter port latitude:");
                                double portLatitude = scanner.nextDouble();
                                System.out.println("Please enter port longtitude:");
                                double portLongtitude = scanner.nextDouble();
                                System.out.println("Please enter port capacity:");
                                double portCapacity = scanner.nextDouble();
                                System.out.println("Please enter port current weight:");
                                double portWeight = scanner.nextDouble();
                                System.out.println("Please enter port landing ability:");
                                boolean portLandingAbility = scanner.nextBoolean();

                                ports.add(new Port(portID, portName, portLatitude, portLongtitude, portCapacity, portWeight, portLandingAbility));
                                System.out.println("New port added successfully!");
                            }

                            //Go Back
                            else if (option2.equals("3")) {
                                break;
                            } else {
                                System.out.println("Please enter a valid option");
                            }
                            }
                        }

                        //Port Manager Option
                        else if (password.equals(expectedPassword)) {
                            System.out.println("Welcome Port Manager " + username);
                            break;
                        }
                    }

                    //Wrong Username or Password
                    else {
                        System.out.println("Username or Password is incorrect. Please try again.");
                        System.out.println();
                    }
                    break;
                }
            } else if (option.equals("2")) {
                System.out.println("logged out");
                break;
            } else {
                System.out.println("Please enter a valid option");
            }
        }
    }
}