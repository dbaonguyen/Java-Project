import Entities.Port;
import Entities.Vehicle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Transportation implements Runnable {
    private Vehicle vehicle;
    private Port destinationPort;

    public Transportation(Vehicle vehicle, Port destinationPort) {
        this.vehicle = vehicle;
        this.destinationPort = destinationPort;
    }

    @Override
    public void run() {
        vehicle.moveToPort(destinationPort);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println(vehicle.getName() + " has arrived at " + destinationPort.getName() + " at " + dateFormat.format(new Date()));
    }

    public static boolean isValidDate(String input) {
        // Define a regular expression pattern to match a valid date format
        String dateFormatPattern = "\\d{1,2}/\\d{1,2}/\\d{4} \\d{2}:\\d{2}:\\d{2}";

        // Check if the input matches the pattern
        if (!input.matches(dateFormatPattern)) {
            return false;
        }

        // If it matches the pattern, try to parse it as a date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        sdf.setLenient(false); // Disable leniency to enforce strict date parsing

        try {
            Date parsedDate = sdf.parse(input);
            // If parsing succeeds without exceptions, it's a valid date
            return true;
        } catch (ParseException e) {
            // Parsing failed, so it's an invalid date
            return false;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Port newPort = new Port("p-003", "New Port", 40.7128, -74.0060, 5000, 1500, true);
        Vehicle newVehicle = new Vehicle("tr-003", "New Vehicle", 1000, 500, 8000, 300, newPort);
        do {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                System.out.println("Please enter the arrival date dd/MM/yyyy HH:mm:ss");
                String arrivalDate = scanner.nextLine();

                if (isValidDate(arrivalDate)) {
                    Date scheduledTime = dateFormat.parse(arrivalDate);
                    System.out.println(System.currentTimeMillis());
                    System.out.println(scheduledTime.getTime());

                    long delayMillis = scheduledTime.getTime() - System.currentTimeMillis();
                    System.out.println(delayMillis);
                    if (delayMillis <= 0) {
                        System.out.println("Invalid scheduled time. The program will not run.");
                    } else {
                        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                        Runnable task = new Transportation(newVehicle, newPort);
                        ScheduledFuture<?> future = scheduler.schedule(task::run, delayMillis , TimeUnit.MILLISECONDS);
                        scheduler.shutdown();
                        break;
                    }
                } else {
                    System.out.println("Invalid date. Try again");
                }
            } catch (ParseException e) {
                System.out.println("Invalid value");
            }
        } while (true);
    }
}
