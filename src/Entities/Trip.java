package Entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Trip implements Serializable {
    private Vehicle vehicle;
    private Date departDate;
    private Date arrivalDate;
    private Port departFrom;
    private Port arriveTo;
    private boolean status;

    public Trip(Vehicle vehicle, Port departFrom, Date departDate, Date arrivalDate, Port arriveTo, boolean status) {
        this.vehicle = vehicle;
        this.departFrom = departFrom;
        this.arriveTo = arriveTo;
        this.status = status;
        this.departDate = departDate;
        this.arrivalDate = arrivalDate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getDepartDate() {
        return departDate;
    }

    public void setDepartDate(Date departDate) {
        this.departDate = departDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return "Trip{" +
                "vehicle=" + vehicle.getVehicleID() +
                ", departDate=" + dateFormat.format(departDate) +
                ", arrivalDate=" + dateFormat.format(arrivalDate) +
                ", departFrom=" + departFrom.getPortID() +
                ", arriveTo=" + arriveTo.getPortID() +
                ", status=" + status +
                '}';
    }
}


