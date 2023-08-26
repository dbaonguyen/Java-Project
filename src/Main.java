import Entities.Port;

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

        //Menu Loop
        while (true) {
            System.out.println("Please choose your option");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            int option = scanner.nextInt();

            //Login
            if (option == 1) {
                scanner.nextLine();
                while (true) {
                    System.out.print("Enter your username: ");
                    String username = scanner.nextLine();

                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();
                    System.out.println();

                    if (userCredentials.containsKey(username) && userCredentials.containsValue(password)) {
                        String expectedPassword = userCredentials.get(username);
                        if (username.equals("1") && password.equals(expectedPassword)) {
                            System.out.println("Welcome Admin " + username);
                            System.out.println("1. Choose Port");
                            System.out.println("2. Go Back");
                            System.out.print("Your option: ");
                            int option2 = scanner.nextInt();
                            if (option2 == 1)
                            break;
                        } else if (password.equals(expectedPassword)) {
                            System.out.println("Welcome Port Manager " + username);
                            break;
                        }
                    } else {
                        System.out.println("Username or Password is incorrect. Please try again.");
                        System.out.println();
                    }
                }

                //Exit
            } else if (option == 2) {
                System.out.println("logged out");
                break;
            } else {
                System.out.println("Please choose a valid option");
                continue;
            }
            break;
        }
    }
}
