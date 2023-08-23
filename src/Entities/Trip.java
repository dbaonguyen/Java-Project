package Entities;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Trip {
    private Vehicle vehicle;
    private SimpleDateFormat departDate;
    private SimpleDateFormat arrivalDate;
    private Port departFrom;
    private Port arriveTo;
    private boolean status;

    public Trip(Vehicle vehicle, SimpleDateFormat departDate, SimpleDateFormat arrivalDate, Port departFrom, Port arriveTo, boolean status) {
        this.vehicle = vehicle;
        this.departDate = departDate;
        this.arrivalDate = arrivalDate;
        this.departFrom = departFrom;
        this.arriveTo = arriveTo;
        this.status = status;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public SimpleDateFormat getDepartDate() {
        return departDate;
    }

    public void setDepartDate(SimpleDateFormat departDate) {
        this.departDate = departDate;
    }

    public SimpleDateFormat getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(SimpleDateFormat arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Port getDepartFrom() {
        return departFrom;
    }

    public void setDepartFrom(Port departFrom) {
        this.departFrom = departFrom;
    }

    public Port getArriveTo() {
        return arriveTo;
    }

    public void setArriveTo(Port arriveTo) {
        this.arriveTo = arriveTo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "vehicle=" + vehicle +
                ", departDate=" + departDate +
                ", arrivalDate=" + arrivalDate +
                ", departFrom=" + departFrom +
                ", arriveTo=" + arriveTo +
                ", status=" + status +
                '}';
    }
}
