package Rates;

/**Subclass of {@link VehicleRates}. This class holds different rates for rented
 * SUVs
 * @author Mark Angelot
 * @date April 21, 2020
 * 
 * @see CarRates
 * @see TruckRates
 */
public class SUVRates extends VehicleRates{
    /**
     * Constructs a SUVRates object with the specified rates
     * @param daily_rate cost per day
     * @param weekly_rate cost per week
     * @param monthly_rate cost per month
     * @param mileage_chrg cost per mile
     * @param daily_insur_rate insurance cost (per day)
     */
    public SUVRates(double daily_rate,
                    double weekly_rate,
                    double monthly_rate,
                    double mileage_chrg,
                    double daily_insur_rate){
        super(daily_rate,weekly_rate,monthly_rate,mileage_chrg,daily_insur_rate);
    }
    
    /**
     * Creates a new SUVRates object with the rates from another SUVRates object
     * @param otherRates a SUVRates object containing the rates to be added to
     *          this SUVRates object
     */
    public SUVRates(SUVRates otherRates){
        super(otherRates);
    }

    @Override
    public String toString(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
