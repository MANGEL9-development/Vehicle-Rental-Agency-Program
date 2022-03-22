/**
 * Mark Angelot
 * April 24, 2020
 */

package OtherClasses.Details;

import OtherClasses.TimeSpan;

/**
 * A RentalDetails class contains the information related to a given vehicle
 * rental for the sake of computing the rental charges - vehicle type, rental
 * period, estimated number of miles to be driven, insurance option, and whether
 * a prime customer. There are no objects of type RentalDetails stored anywhere
 * in the program (and thus not part of the UML class diagram). These objects
 * are only used for passing information between methods. Thus, these classes
 * contain only a constructor and getter methods, and are not stored anywhere in
 * the system.
 */
public class RentalDetails{
    private final int vehicle_type;
    private final TimeSpan rental_period;
    private final double estimated_miles;
    private final boolean insurance_option;
    private final boolean is_prime_customer;
    
    /**
     * Constructs a RentalDetails with the specified properties
     * @param vehicleType the type of vehicle ("1" - car, "2" - SUV, "3" - Truck)
     * @param rentalPeriod the period for which the associated Vehicle is being
     *          rented
     * @param estimatedMiles an estimate of how many miles will be driven by the
     *          customer
     * @param insuranceOption set this to true if insurance is selected, and
     *          false otherwise
     * @param isPrimeCustomer set this to true if the customer is a prime
     *          customer, and false otherwise
     */
    public RentalDetails(int vehicleType,TimeSpan rentalPeriod,double estimatedMiles,boolean insuranceOption,boolean isPrimeCustomer){
        vehicle_type=vehicleType;
        rental_period=rentalPeriod;
        estimated_miles=estimatedMiles;
        insurance_option=insuranceOption;
        is_prime_customer=isPrimeCustomer;
    }
    
    /**
     * @return the type of vehicle (1 - car, 2 - SUV, 3 - Truck)
     */
    public int getVehicleType(){
        return vehicle_type;
    }
    /**
     * @return the period for which the associated Vehicle is being rented
     */
    public TimeSpan getEstimatedRentalPeriod(){
        return rental_period;
    }
    /**
     * @return an estimate of how many miles will be driven by the customer
     */
    public double getEstimatedMiles(){
        return estimated_miles;
    }
    /**
     * @return true if insurance is selected, and false otherwise
     */
    public boolean getInsuranceOption(){
        return insurance_option;
    }
    /**
     * @return true if the customer is a prime customer, and false otherwise
     */
    public boolean isPrimeCustomer(){
        return is_prime_customer;
    }
}
