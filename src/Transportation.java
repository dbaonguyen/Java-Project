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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Port newPort = new Port("p-003", "New Port", 40.7128, -74.0060, 5000, 1500, true);
        Vehicle newVehicle = new Vehicle("tr-003", "New Vehicle", 1000, 500, 8000, 300, newPort);
        do {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            try {
                System.out.println("Please enter the arrival date dd/MM/yyyy HH:mm:ss");
                String arrivalDate = scanner.nextLine();
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
                    ScheduledFuture<?> future = scheduler.schedule(task, delayMillis, TimeUnit.MILLISECONDS);
                    scheduler.shutdown();
                    break;
                }
            } catch (ParseException e) {
                System.out.println("Invalid value");
            }
        } while (true);
    }
}
