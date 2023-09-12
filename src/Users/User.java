package Users;

import Entities.Port;
import Source.AdminInterface;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class User implements Serializable {
    private String username;
    private String password;
    private static Scanner scanner = new Scanner(System.in);

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
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
    public static void addUser(List<String> userNameList, List<String> usedPortsID){
        String userName;
        do {
            AdminInterface.decorativeLine();
            System.out.println();
            try {
                System.out.println("Please enter a new user name: ");
                userName = scanner.nextLine();
                if (!userNameList.contains(userName)) {
                    userNameList.add(userName);
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

        do {
            for (Port port : AdminInterface.portList) {
                System.out.println(port.getPortID() + ". " + port.getName());
            }
            System.out.println("0. Go back");
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
                                PortManager newPortManager = new PortManager(userName, password, port);
                                AdminInterface.userList.add(newPortManager);
                                AdminInterface.usedUsername.add(userName);
                                AdminInterface.usedPortID.add(portOption);
                                System.out.println("A new port manager has been created");
                            } else {
                                PortManager newPortManager = new PortManager(userName, password, port);
                                newPortManager.setPortManaged(null);
                                AdminInterface.userList.add(newPortManager);
                                System.out.println("Failed to add a new manager to this port. The port for this user will be set to null");
                            }
                        }
                    }
                    break;
                }
            }
        } while (true);
    }
}
