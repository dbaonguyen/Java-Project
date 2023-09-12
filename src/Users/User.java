package Users;

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

        int portID = -1;
        do {
            for (int i = 0; i < AdminInterface.portList.size(); i++) {
                System.out.println(i + ". " + AdminInterface.portList.get(i).getName());
            }
            try {
                System.out.println("Please choose the port to assign this user to: ");
                portID = Integer.parseInt(scanner.nextLine());

                if (portID >= 0 && portID < AdminInterface.portList.size() && !usedPortsID.contains(AdminInterface.portList.get(portID).getPortID())) {
                    usedPortsID.add(AdminInterface.portList.get(portID).getPortID());
                    break;
                } else {
                    System.out.println("Please enter a valid option");
                }
            } catch (Exception e) {
                System.out.println("Invalid value");
            }
        } while (true);

        AdminInterface.userList.add(new PortManager(userName, password, AdminInterface.portList.get(portID)));
        AdminInterface.usedUsername.add(userName);
    }
}
