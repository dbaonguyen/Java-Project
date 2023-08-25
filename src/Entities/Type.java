package Entities;

public class Type {
    private final String typeName;
    private final double truckConsume;
    private final double shipConsume;
//create variables
    Type(){
        typeName = "default";
        truckConsume = 0;
        shipConsume = 0;
    }

    Type(String typeName, double truckConsume, double shipConsume){
        this.typeName = typeName;
        this.truckConsume = truckConsume;
        this.shipConsume = shipConsume;
    }
//define elements in the class
    public String getType(){
        return this.typeName;
    }
    //method to get container type
    public double getTruckConsume(){
        return this.truckConsume;
    }
    //method to get fuel consumption on trucks
    public double getShipConsume(){
        return this.shipConsume;
    }
    //method to get fuel consumption on ships

    public static void main(String[] args){
        Type dryStorage = new Type("Dry storage",3.5, 4.6);
        Type openTop = new Type("Open top", 2.8, 3.2);
        Type openSide = new Type("Open side", 2.7, 3.2);
        Type refrigerated = new Type("Refrigerated", 4.5, 5.4);
        Type liquid = new Type("Liquid", 4.8, 5.3);
    }
}




