package Rates;

/**Subclass of {@link Vehicles}. This class holds different rates for rented
 * Trucks
 * @author Mark Angelot
 * @date April 21, 2020
 * 
 * @see CarRates
 * @see SUVRates
 */
public class TruckRates extends VehicleRates{
    /**
     * Constructs a TruckRates object with the specified rates
     * @param daily_rate cost per day
     * @param weekly_rate cost per week
     * @param monthly_rate cost per month
     * @param mileage_chrg cost per mile
     * @param daily_insur_rate insurance cost (per day)
     */
    public TruckRates(double daily_rate,
                    double weekly_rate,
                    double monthly_rate,
                    double mileage_chrg,
                    double daily_insur_rate){
        super(daily_rate,weekly_rate,monthly_rate,mileage_chrg,daily_insur_rate);
    }
    
    /**
     * Creates a new TruckRates object with the rates from another TruckRates object
     * @param otherRates a TruckRates object containing the rates to be added to
     *          this TruckRates object
     */
    public TruckRates(TruckRates otherRates){
        super(otherRates);
    }

    @Override
    public String toString(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
