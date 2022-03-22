package Vehicles;

/**
 * This is one of three vehicle types used in this project
 * @author Mark Angelot
 * @date March 15, 2020
 */
public class Car extends Vehicle{
    private final int seating;
    public final byte vehicleType=1;
    
    /**
     * Constructs a Car with specifications
     * @param description a String describing the car e.g. "Ford Crown Victoria - 2007"
     * @param mileage the car's mileage (in mpg)
     * @param seatingCapacity the amount of seats the car has
     * @param vin the car's Vehicle Identification Number
     */
    public Car(String description,int mileage,int seatingCapacity,String vin){
        super(description,mileage,vin);
        seating=seatingCapacity;
    }
    
    /**
     * Returns a String representation of the Car in the form:
     * <blockquote>Ford Fusion - 2019 (Car) MPG: 34 Seating: 4 VIN: AB4FG5689GM</blockquote>
     * @return a String with all the Car's information
     */
    @Override
    public String toString(){
        return super.getDescription()+" (Car)"+
                "   MPG: "+super.getMPG()+
                "   Seating: "+seating+
                "   VIN: "+super.getVIN();
    }
    
    /**
     * Throughout this program, a vehicle's type is needed to perform certain
     * methods (mostly for calculating rates). So each subclass of Vehicle is
     * assigned a number to represent its type (1=Car, 2=SUV, 3=Truck). So this
     * method can be used to find the vehicle type.
     * @return 1
     */
    @Override
    public byte vehicleType(){
        return 1;
    }
}
