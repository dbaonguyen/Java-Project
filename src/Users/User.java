package Users;


import Entities.Port;
import Source.AdminInterface;

import java.io.Serializable;
import java.util.Scanner;

public class User implements Serializable {
    private String username;
    private String password;
    private static final Scanner scanner = new Scanner(System.in);

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User Information:\n" +
                "Username: " + username + "\n" +
                "Password: " + password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public static void addUser(){
        String userName;
        do {
            try {
                System.out.println("Please enter a new user name: ");
                userName = scanner.nextLine();
                if (!AdminInterface.usedUsername.contains(userName)) {
                    break;
                } else {
                    System.out.println("This user name is already existed!");
                }
            } catch (Exception e) {
                System.out.println("Invalid value");
            }
        } while (true);

        String password;
        do{
            try{
                System.out.println("Please enter a password for this user: ");
                password = scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Invalid value");
            }
        } while(true);
        boolean run = true;
        boolean portLoopBreak = false;
        do {
            if (portLoopBreak){
                break;
            }
            for (Port port : AdminInterface.portList) {
                System.out.println(port.getPortID() + ". " + port.getName());
            }

            System.out.println("0. Go Back");
            System.out.print("Enter the ID of the port above that you want to assign the port manager to: ");
            String portOption = scanner.nextLine();
            if (portOption.equals("0")) {
                break;
            } else {
                if (!AdminInterface.portIDs.contains(portOption)) {
                    System.out.println("Port does not exist");
                } else {
                    for (Port port : AdminInterface.portList) {
                        if (portOption.equals(port.getPortID())) {
                            if (!AdminInterface.usedPortID.contains(portOption)) {


                                System.out.print("Do you want to create this port manager account? (y/n): ");
                                String confirmation = scanner.nextLine();
                                if (confirmation.equals("y")) {
                                    PortManager newPortManager = new PortManager(userName, password, port);
                                    AdminInterface.userList.add(newPortManager);
                                    AdminInterface.usedUsername.add(userName);
                                    AdminInterface.usedPortID.add(portOption);
                                    System.out.println("A new port manager has been created");
                                    run = false;
                                    break;
                                } else if (confirmation.equals("n")) {
                                    AdminInterface.usedUsername.remove(userName);
                                    System.out.println("The process is cancelled");
                                    portLoopBreak = true;
                                    break;
                                } else {
                                    System.out.println("Please enter y or n for confirmation!");
                                }
                            } else {
                                System.out.println("This port has been managed by another manager. Please enter another port.");

                            }
                        }
                    }
                }
            }
        } while (run);
    }
}
