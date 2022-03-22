package Vehicles;

import Exceptions.UnreservedVehicleException;
import Exceptions.ReservedVehiclesException;
import OtherClasses.Reservation;
import Rates.VehicleRates;

/**
 * This is an abstract superclass of Car, SUV, and Truck
 * @author Mark Angelot
 * @date March 15, 2020
 */
public abstract class Vehicle{
    private final String description;   // stores make-model-year for cars and SUVs, stores length for trucks
    private final int mpg;              // miles per gallon rating
    private final String vin;           // unique vehicle identification number
    private Reservation resv;           // reservation information (a null value means not reserved)
    private VehicleRates rates;         // only assigned when vehicle reserved

    /**
     * Constructs a vehicle with specifications. At the time of construction,
     * this Vehicle's reservation and rates are set to null.
     * @param d the Vehicle's description
     * @param m the Vehicle's mileage (in mpg)
     * @param v the Vehicle's Vehicle Identification Number
     */
    protected Vehicle(String d,int m,String v){
        description=d;
        mpg=m;
        vin=v;
        resv=null;  // init as “no reservation”
        rates=null; // used to hold rates at the time of the reservation
    }
    
    /**
     * @return the Vehicle's description
     */
    public String getDescription(){
        return description;
    }
    /**
     * @return the Vehicle's mileage (in mpg)
     */
    public int getMPG(){
        return mpg;
    }
    /**
     * @return the Vehicle's Vehicle Identification Number
     */
    public String getVIN(){
        return vin;
    }
    /**
     * @return the Vehicle's {@link OtherClasses.Reservation Reservation}
     * @throws UnreservedVehicleException if the vehicle is unreserved
     */
    public Reservation getReservation() throws UnreservedVehicleException{
        if(isReserved()){
            return resv;
        }
        else{
            throw new UnreservedVehicleException();
        }
    }
    /**
     * @return a String representation of this Vehicle
     */
    @Override
    public abstract String toString(); // ABSTRACT METHOD – implemented in each subclass
    public boolean isReserved(){
        return resv!=null;
    }
    /**
     * Makes a reservation on this Vehicle
     * @param r the reservation that would be made on this Vehicle
     * @throws ReservedVehiclesException if a reservation has already been made on this Vehicle
     */
    public void setReservation(Reservation r) throws ReservedVehiclesException{
        if(isReserved()){
            throw new ReservedVehiclesException();
        }
        resv=r;
    }
    /**
     * Cancels a reservation that has been made on this Vehicle
     * @throws UnreservedVehicleException if reservation doesn't exist
     */
    public void cancelReservation() throws UnreservedVehicleException{
        if(!isReserved()){
            throw new UnreservedVehicleException();
        }
        resv=null;
    }
    
    /**
     * Since rates may change after a customer has reserved a vehicle, we need
     * to ensure that they are not charged a different rate when this Vehicle is
     * returned. Thus, a copy of the appropriate current rates object is made
     * and attached to this Vehicle reserved (by the {@link setQuotedRates}
     * method of this Vehicle class). These are the rates (not the current
     * rates) that are ultimately used to calculate the rental charges when the
     * vehicle is returned.
     * @return the current rates at the time this Vehicle was reserved
     */
    public VehicleRates getQuotedRates(){
        return rates;
    }
    
    /**
     * Sets the rates when the vehicle is reserved
     * @param cost the rates at the time of reservation
     */
    public void setQuotedRates(VehicleRates cost){
        rates=cost;
    }
    
    
    /**
     * Throughout this program, a vehicle's type is needed to perform certain
     * methods (mostly for calculating rates). So each subclass of Vehicle is
     * assigned a number to represent its type (1=Car, 2=SUV, 3=Truck). So this
     * method can be used to find the vehicle type.
     * @return 1 if the Vehicle is a Car, 2 if the Vehicle is an SUV, and 3 if
     * the Vehicle is a Truck,
     */
    public abstract byte vehicleType();
}