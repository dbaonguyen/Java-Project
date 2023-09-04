import Entities.*;
import Users.Admin;
import Users.PortManager;
import Users.User;
import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class  Main {
    //data load
    private static List<User> userList = readListFromFile("userList.ser");
    private static List<Port> portList = readListFromFile("portList.ser");
    private static List<Container> containerList = readListFromFile("containerList.ser");
    private static List<Ship> shipList = readListFromFile("shipList.ser");
    private static List<Truck> truckList = readListFromFile("truckList.ser");
    private static List<ReeferTruck> reeferTruckList = readListFromFile("reeferTruckList.ser");
    private static List<TankerTruck> tankerTruckList = readListFromFile("tankerTruckList.ser");
    private static List<Type> typeList = readListFromFile("typeList.ser");
    private static final String DEFAULT_DIRECTORY = "Data"; // Change this to your default directory path

    public static <T> void writeListToFile(List<T> list, String fileName) {
        String filePath = DEFAULT_DIRECTORY + File.separator + fileName;
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> readListFromFile(String fileName) {
        String filePath = DEFAULT_DIRECTORY + File.separator + fileName;
        List<T> deserializedList = null;
        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            deserializedList = (List<T>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return deserializedList;
    }

    public static void main(String[] args) {
        EmRuy.emruy();
    }

    public static String loginValidation (String enteredUsername, String enteredPassword) {
        // Search for a user with the provided username in the list of registered users.
        User currentLoginUser = null;
        for (User user : userList) {
            if (user.getUsername().equals(enteredUsername)) {
                currentLoginUser = user;
                break; // Exit the loop once a matching username is found.
            }
        }
        if (currentLoginUser != null) {
            // If a matching username is found, validate the password.
            if (currentLoginUser.getPassword().equals(enteredPassword)) {
                // Check the user's type (role).
                if (currentLoginUser instanceof Admin) {
                    return "admin";
                } else if (currentLoginUser instanceof PortManager) {
                    return "manager";
                } else {
                    return "user";
                }
            }
        }

        // If no matching username or invalid password, return "invalid" as a flag.
        return "invalid";
    }
}