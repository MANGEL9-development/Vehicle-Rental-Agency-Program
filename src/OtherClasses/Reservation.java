package OtherClasses;

/**
 * This class is used to represent reservations made on vehicles
 * @author Mark Angelot
 * @date March 15, 2020
 */
public class Reservation{
    private final String creditCardNum;       // credit card number on file for company account
    private final int vehicleType;            // 1-car, 2-SUV, 3-Truck
    private final String description;         // e.g., Honda Odyssey
    private final TimeSpan rentalPeriod;      // e.g., TimeSpan type stores, e.g, 3, ‘D’ (3 days)
    private final boolean insuranceSelected;  // set to true if optional daily insurance wanted


    /**
     * Constructs a Reservation under a credit card.The reservation is set for
     * a certain amount of time (determined by a {@link TimeSpan} object). And
     * insurance can be selected.
     * @param c the customer's credit card
     * @param v the vehicle type (1-car, 2-SUV, 3-Truck)
     * @param d the {@link Vehicles.Vehicle Vehicle}'s description e.g., Honda Odyssey
     * @param r a {@link TimeSpan} object representing the amount of time the vehicle is
     *          set to be reserved
     * @param i if this is true, insurance will be selected; otherwise, insurance will not
     *          be selected
     */
    public Reservation(String c, int v, String d, TimeSpan r, boolean i){
        creditCardNum=c;
        vehicleType=v;
        description=d;
        rentalPeriod=r;
        insuranceSelected=i;
    }

    public Reservation(String creditCardNumber, TimeSpan reservationTime, boolean withInsurance){
        throw new UnsupportedOperationException("This method is no longer used, instead use the other constructor");
    }
    
    /**
     * @return the credit card that was used to make a reservation
     */
    public String getCreditCardNum(){
        return creditCardNum;
    }
    /**
     * @return a {@link TimeSpan} object representing the amount of time the vehicle is
     */
    public TimeSpan getRentalPeriod(){
        return rentalPeriod;
    }
    /**
     * @return true if insurance was selected, false otherwise
     */
    public boolean insuranceSelected(){
        return insuranceSelected;
    }
    /**
     * @return the vehicle type (1-car, 2-SUV, 3-Truck)
     */
    public int getVehicleType(){
        return vehicleType;
    }
    
    /**
     * Returns a String representation of this Reservation in the form:
     * <blockquote>Reservation for [<i>rental period</i>] [<i>with/without</i>]
     * insurance</blockquote>
     * @return a String representation of this Reservation
     */
    @Override
    public String toString(){
        Object obj;
        return "Reservation for "+rentalPeriod+" with"+(insuranceSelected?"":"out")+" insurance";
    }
}
