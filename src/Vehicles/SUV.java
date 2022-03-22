package Vehicles;

/**
 * This is one of three vehicle types used in this project
 * @author Mark Angelot
 * @date March 15, 2020
 */
public class SUV extends Vehicle{
    private final int seating;
    private final int cargo;
    public final byte vehicleType=2;
    
    /**
     * Constructs an SUV with specifications
     * @param description a String describing the car e.g. "Kia Soul - 2012"
     * @param milage the car's mileage (in mpg)
     * @param seatingCapacity the amount of seats the car has
     * @param cargoCapacity the SUV's cargo capacity (in cubic ft.)
     * @param vin the car's Vehicle Identification Number
     */
    public SUV(String description,int milage,int seatingCapacity, int cargoCapacity,String vin){
        super(description,milage,vin);
        seating=seatingCapacity;
        cargo=cargoCapacity;
    }
    
    /**
     * Returns a String representation of the SUV in the form:
     * <blockquote>Dodge Caravan - 2019 (SUV) MPG: 25 Seating: 5 Storage: 4 cu. ft. VIN: QK3FL4273ME</blockquote>
     * @return a String with all the SUV's information
     */
    @Override
    public String toString(){
        return super.getDescription()+" (SUV)"+
                "   MPG: "+super.getMPG()+
                "   Seating: "+seating+
                "   Storage: "+cargo+" cu ft."+
                "   VIN: "+super.getVIN();
    }
    
    /**
     * Throughout this program, a vehicle's type is needed to perform certain
     * methods (mostly for calculating rates). So each subclass of Vehicle is
     * assigned a number to represent its type (1=Car, 2=SUV, 3=Truck). So this
     * method can be used to find the vehicle type.
     * @return 2
     */
    @Override
    public byte vehicleType(){
        return 2;
    }
}
