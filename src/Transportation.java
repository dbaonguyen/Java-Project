import Entities.Port;
import Entities.Trip;
import Entities.Vehicle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Transportation {

    private Vehicle vehicle;
    private Port destinationPort;
    private Date departureDate;
    private Date arrivalDate;
    public Transportation(Vehicle vehicle, Port destinationPort, Date departureDate, Date arrivalDate) {
        this.vehicle = vehicle;
        this.destinationPort = destinationPort;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }
    public Trip run() {
        // Departure action
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        // Print a line at the departure time
        AdminInterface.notificationList.add(vehicle.getName() + " is departing from " + vehicle.getPort().getName() + " at " + dateFormat.format(departureDate));

        // Departure action
        Trip trip = vehicle.moveToPort1(destinationPort, departureDate, arrivalDate);
        return trip;
    }

    public void run2(Trip trip) {
        // Departure action
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        // Print a line at the departure time
        vehicle.hasArrived(trip);
        // Print a line at the arrival time
        AdminInterface.notificationList.add(vehicle.getName() + " has arrived at " + destinationPort.getName() + " at " + dateFormat.format(arrivalDate));
        // Call the "has arrived" method
    }
}