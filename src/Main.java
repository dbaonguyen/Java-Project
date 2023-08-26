import Entities.Container;
import Entities.Port;
import Entities.ReeferTruck;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HashMap<String, String> userCredentials = new HashMap<>();
        userCredentials.put("1", "1");
        userCredentials.put("2", "2");
        userCredentials.put("3", "3");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();

            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            if (userCredentials.containsKey(username) && userCredentials.containsValue(password)) {
                String expectedPassword = userCredentials.get(username);
                if (username.equals("1") && password.equals(expectedPassword)) {
                    System.out.println("Welcome Admin " + username);
                    break;
                } else if (password.equals(expectedPassword)) {
                    System.out.println("Welcome Port Manager " + username);
                    break;
                }
            } else {
                System.out.println("Username or Password is incorrect. Please try again.");
            }
        }
    }
}
