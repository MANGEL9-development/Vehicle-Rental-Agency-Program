package Rates;

/**This is an abstract that can be used to construct an object with specified
 * rates. Subclasses are {@link CarRates}, {@link SUVRates}, and {@link TruckRates}
 * @author Mark Angelot
 * @date April 21, 2020
 */
public abstract class VehicleRates{
    private final double dailyRate;
    private final double weeklyRate;
    private final double monthlyRate;
    private final double mileageCharge;
    private final double dailyInsurRate;
    
    /**
     * Constructs a VehicleRates object with the specified rates
     * @param daily_rate cost per day
     * @param weekly_rate cost per week
     * @param monthly_rate cost per month
     * @param mileage_chrg cost per mile
     * @param daily_insur_rate insurance cost (per day)
     */
    public VehicleRates(double daily_rate,
                        double weekly_rate,
                        double monthly_rate,
                        double mileage_chrg,
                        double daily_insur_rate){
        dailyRate=daily_rate;
        weeklyRate=weekly_rate;
        monthlyRate=monthly_rate;
        mileageCharge=mileage_chrg;
        dailyInsurRate=daily_insur_rate;
    }
    
    public VehicleRates(VehicleRates otherRates){
        dailyRate=otherRates.dailyRate;
        weeklyRate=otherRates.weeklyRate;
        monthlyRate=otherRates.monthlyRate;
        mileageCharge=otherRates.mileageCharge;
        dailyInsurRate=otherRates.dailyInsurRate;
    }
    
    /**
     * @return cost per day
     */
    public double getDailyRate(){
        return dailyRate;
    }
    /**
     * @return cost per week
     */
    public double weeklyRate(){
        return weeklyRate;
    }
    /**
     * @return cost per month
     */
    public double monthlyRate(){
        return monthlyRate;
    }
    /**
     * @return cost per mile, based on vehicle type
     */
    public double mileageCharge(){
        return mileageCharge;
    }
    /**
     * @return insurance cost (per day)
     */
    public double dailyInsurRate(){
        return dailyInsurRate;
    }
    /**
     * forces subclasses to provide an appropriate toString method
     */
    @Override
    public abstract String toString();
}
