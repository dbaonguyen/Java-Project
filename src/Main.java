import Entities.Port;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Ports
        List<Port> ports = new ArrayList<>();
        Port port1 = new Port("p-1", "Harbor Bay", 34.0522, -118.2437, 500.0, 1500.0, true);
        Port port2 = new Port("p-2", "Marine City", 40.7128, -74.0060, 700.0, 1800.0, false);
        Port port3 = new Port("p-3", "Ocean View", -33.8688, 151.2093, 800.0, 2000.0, true);
        Port port4 = new Port("p-4", "Seaside Haven", 37.7749, -122.4194, 400.0, 1200.0, true);
        Port port5 = new Port("p-5", "Island Port", 21.3069, -157.8583, 600.0, 1600.0, false);
        ports.add(port1);
        ports.add(port2);
        ports.add(port3);
        ports.add(port4);
        ports.add(port5);

        HashMap<String, String> userCredentials = new HashMap<>();
        userCredentials.put("1", "1");
        userCredentials.put("2", "2");
        userCredentials.put("3", "3");

        Scanner scanner = new Scanner(System.in);

        //Menu Loop
        while (true) {
            System.out.println("Please choose your option");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            int option = scanner.nextInt();

            //Login
            if (option == 1) {
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
                            System.out.println("1. Choose Port");
                            System.out.println("2. Go Back");
                            System.out.print("Your option: ");
                            int option2 = scanner.nextInt();

                            //Admin Option (Choose ports)
                            if (option2 == 1) {

                                // Print the ports in the ArrayList for Options
                                List<String> portIDs = new ArrayList<>();
                                for (Port port : ports) {
                                    System.out.println(port.getPortID() + ". " + port.getName());
                                    portIDs.add(port.getPortID());
                                }

                                //Choose Port
                                System.out.println("Enter the ID of the port you want to modify: ");
                                while (true) {
                                    String portOption = scanner.next();
                                    if (portIDs.contains(portOption)) {
                                        System.out.println(ports.get(portIDs.indexOf(portOption)));
                                    } else {
                                        System.out.println("Please choose an valid option");
                                    }
                                }
                            }

                            //Go Back
                            else if (option2 == 2) {
                                continue;
                            }
                            break;
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
                }
            }

            //Exit
            else if (option == 2) {
                System.out.println("logged out");
                break;
            }

            //Invalid Option
            else {
                System.out.println("Please choose a valid option");
                continue;
            }
            break;
        }
    }
}
