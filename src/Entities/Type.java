package Entities;

public class Type {
    private final String typeName;
    private final double truckConsume;
    private final double shipConsume;
//create variables
    public Type(){
        typeName = "default";
        truckConsume = 0;
        shipConsume = 0;
    }

    public Type(String typeName, double truckConsume, double shipConsume){
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


}




