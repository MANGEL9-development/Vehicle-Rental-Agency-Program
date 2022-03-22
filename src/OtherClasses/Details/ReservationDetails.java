/**
 * Mark Angelot
 * May 1, 2020
 */

package OtherClasses.Details;

import OtherClasses.TimeSpan;

/**
 * Contains the information for making a reservation (VIN, creditcard_num,
 * rental period, daily insurance option). Only used to pass this reservation
 * information from the user interface to the system interface. Thus, these
 * classes contain only a constructor and getter methods, and are not stored
 * anywhere in the system.
 */
public class ReservationDetails{
    private final String vin_;
    private final String credit_card_number;
    private final TimeSpan rental_period;
    private final boolean daily_insurance;
    
    /**
     * Constructs a ReservationDetails object with the specified properties
     * @param vin the VIN associated with this Reservation
     * @param creditCardNumber the credit card number associated with this
     *          Reservation
     * @param rentalPeriod the period for which the Vehicle is reserved
     * @param dailyInsurance set this to true if daily insurance is selected,
     *          and false otherwise
     */
    public ReservationDetails(String vin,
                              String creditCardNumber,
                              TimeSpan rentalPeriod,
                              boolean dailyInsurance){
        vin_=vin;
        credit_card_number=creditCardNumber;
        rental_period=rentalPeriod;
        daily_insurance=dailyInsurance;
    }
    
    /**
     * @return the VIN associated with this Reservation
     */
    public String getVIN(){
        return vin_;
    }
    /**
     * @return the credit card number associated with this Reservation
     */
    public String creditCardNumber(){
        return credit_card_number;
    }
    /**
     * @return the period for which the Vehicle is reserved
     */
    public TimeSpan rentalPeriod(){
        return rental_period;
    }
    /**
     * @return true if daily insurance is selected, and false otherwise
     */
    public boolean dailyInsurance(){
        return daily_insurance;
    }
}
