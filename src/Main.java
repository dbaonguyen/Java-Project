import Entities.Port;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HashMap<String, String> userCredentials = new HashMap<>();
        userCredentials.put("s3978690", "Lacloi@12345");
        userCredentials.put("trungbuoito", "trungngu123");
        userCredentials.put("user3", "password3");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();

            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            if (!userCredentials.containsKey(username) || !userCredentials.containsValue(password)) {
                System.out.println("Username or Password is incorrect. Please try again.");
            } else {
                System.out.println("Welcome " + username);
                break;
            }
        }

    }
}
