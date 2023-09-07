public class  Main {
    public static void main(String[] args) {
        int choice = AdminInterface.login();
        String indicator = AdminInterface.loginValidation();
        while (indicator.equals("invalid")) {
            System.out.println("Invalid credentials, please enter again!");
            choice = AdminInterface.login();
            indicator = AdminInterface.loginValidation();
        }
        if (indicator.equals("admin")) {
            AdminInterface.run(choice, indicator);
        } else {
            PortManagerInterface.run(choice, indicator);
        }
    }
}