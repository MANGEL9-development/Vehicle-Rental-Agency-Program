package Vehicles;

/**
 * This is one of three vehicle types used in this project
 * @author Mark Angelot
 * @date March 15, 2020
 */
public class Truck extends Vehicle{
    private final int cargo;
    
    /**
     * Constructs a Truck with specifications
     * @param description a String describing the truck e.g. "Ford F-150 2013"
     * @param milage the truck's mileage (in mpg)
     * @param loadCapacity the truck's load capacity (in pounds)
     * @param vin the truck's Vehicle Identification Number
     */
    public Truck(String description,int milage,int loadCapacity,String vin){
        super(description,milage,vin);
        cargo=loadCapacity;
    }
    
    /**
     * Returns a String representation of the Truck in the form:
     * <blockquote>Seventeen-Foot (Truck) MPG: 10 Load Capacity: 5930 lbs. VIN: KG4DM5472RK</blockquote>
     * @return a String with all the Truck's information
     */
    @Override
    public String toString(){
        return super.getDescription()+" (Truck)"+
                "   MPG: "+super.getMPG()+
                "   Storage: "+cargo+" lbs."+
                "   VIN: "+super.getVIN();
    }

    /**
     * Throughout this program, a vehicle's type is needed to perform certain
     * methods (mostly for calculating rates). So each subclass of Vehicle is
     * assigned a number to represent its type (1=Car, 2=SUV, 3=Truck). So this
     * method can be used to find the vehicle type.
     * @return 3
     */
    @Override
    public byte vehicleType(){
        return 3;
    }
}
