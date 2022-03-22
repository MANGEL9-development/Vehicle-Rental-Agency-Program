package Rates;

import OtherClasses.TimeSpan;
import Resources.numbers;



/**This is an aggregation of {@link Rates.VehicleRates VehicleRates} objects. In
 * this class are {@link Rates.CarRates CarRates}, {@link Rates.SUVRates SUVRates},
 * and {@link Rates.TruckRates TruckRates}
 * @author Mark Angelot
 * @date April 21, 2020
 */
public class CurrentRates{

    //private final VehicleRates[] rates=new VehicleRates[3]; // array of size 3 to store rates for three types of vehicles

    private CarRates carRates;
    private SUVRates suvRates;
    private TruckRates truckRates;
    
    private final double DAYS_IN_WEEK=7.0;
    private final double DAYS_IN_MONTH=30.0;
    
    public CurrentRates(CarRates carRates, SUVRates suvRates, TruckRates truckRates){
        this.carRates=carRates;
        this.suvRates=suvRates;
        this.truckRates=truckRates;
    }

    public CarRates getCarRates(){
        return carRates;
    }

    public SUVRates getSUVRates(){
        return suvRates;
    }

    public TruckRates getTruckRates(){
        return truckRates;
    }

    /**
     * Called to calculate a cost quote for a customer given an estimated number
     * of days will use and estimated number of miles will drive, whether the
     * daily insurance wanted, and whether a prime customer or not.<br>
     * <br>
     * Uses the CURRENT standard rates for the vehicle type (car, SUV or truck)
     * <br><br>
     * Passed encoded estimated rental period e.g., D4 (four days), and W2, (two
     * weeks), M1 (one month).
     * @param vehicleType a number indicating the type of vehicle (1 - car, 2 -
     *            SUV, 3 - Truck)
     * @param estimatedRentalPeriod an encoded estimated rental period e.g., D4
     *            (four days), and W2, (two weeks), M1 (one month).
     * @param estimatedNumMiles an estimate of how many miles will be driven
     * @param dailyInsur set this to true if insurance is selected, and false
     *            otherwise
     * @param primeCustomer set this to true if the customer is a prime
     *            customer, and false otherwise
     * @return a calculated cost quote for a customer
     */
    public double calcEstimatedCost(int vehicleType,
                                    TimeSpan estimatedRentalPeriod,
                                    double estimatedNumMiles,
                                    boolean dailyInsur,
                                    boolean primeCustomer){
        /*char units=Character.toUpperCase(estimatedRentalPeriod.charAt(0));
        String numRentalUnitsStr=estimatedRentalPeriod.substring(1);
        int numRentalUnits;
        try{
            numRentalUnits=Integer.parseInt(numRentalUnitsStr);
        }
        catch(NumberFormatException e){
            throw new IllegalArgumentException("estimatedRentalPeriod should "+
                    "be in the form <char><int>. Passed value: "+
                    estimatedRentalPeriod);
        }*/
        char units=estimatedRentalPeriod.getTimeUnit();
        
        double rate;
        
        switch(vehicleType){
            case 1: // Car
                switch(units){
                    case 'D': // daily rate
                        rate=(dailyInsur)?carRates.dailyInsurRate():carRates.getDailyRate();
                        break;
                    case 'W': // weekly rate
                        rate=carRates.weeklyRate();
                        break;
                    case 'M': // monthly rate
                        rate=carRates.monthlyRate();
                        break;
                    default: // error
                        throw new IllegalArgumentException("Time units may only "+
                                "be 'D', 'M', or 'W'. Passed value: "+units);
                }
                break;
            case 2: // SUV
                switch(units){
                    case 'D': // daily rate
                        rate=suvRates.getDailyRate();
                        break;
                    case 'W': // daily rate
                        rate=suvRates.weeklyRate();
                        break;
                    case 'M': // monthly rate
                        rate=suvRates.monthlyRate();
                        break;
                    default: // error
                        throw new IllegalArgumentException("Time units may only "+
                                "be 'D', 'M', or 'W'. Passed value: "+units);
                }
                break;
            case 3: // Truck
                switch(units){
                    case 'D': // daily rate
                        rate=truckRates.getDailyRate();
                        break;
                    case 'W': // weekly rate
                        rate=truckRates.weeklyRate();
                        break;
                    case 'M': // monthly rate
                        rate=truckRates.monthlyRate();
                        break;
                    default: // error
                        throw new IllegalArgumentException("Time units may only "+
                                "be 'D', 'M', or 'W'. Passed value: "+units);
                }
                break;
            default: // Error
                throw new IllegalArgumentException("vehicleType can only be 1, 2, or 3 (not "+vehicleType+")");
        }
        
        if(primeCustomer){
            estimatedNumMiles-=100;
            if(estimatedNumMiles<0){
                estimatedNumMiles=0;
            }
        }
        
        return numbers.round((rate*estimatedNumMiles),0.01);
    }

    /**
     * Called to calculate charge of a returned rented vehicle (for number of
     * days used, num miles driven, whether daily insurance was selected, and
     * whether a prime customer or not).<br>
     * <br>
     * Uses the QUOTED RATE, the rates as they were at the time of the
     * reservation.<br>
     * <br>
     * If the number of days is less than 7, then charged the daily rate; if
     * number of days is greater than 7 and less than 30, then charged the
     * weekly rate (with remaining days a prorated weekly rate); otherwise, get
     * the monthly rate (with remaining days a prorated monthly rate).
     * @param rates a {@link Rates.VehicleRates VehicleRates} object containing
     *            the rates of the rented vehicle
     * @param numDaysUsed the number of days since the vehicle was rented
     * @param numMilesDriven the number of miles that were driven when the
     *            vehicle was returned
     * @param dailyInsur set this to true if insurance is selected, and false
     *            otherwise
     * @param primeCustomer set this to true if the customer is a prime
     *            customer, and false otherwise
     * @return the charge of a returned rented vehicle
     */
    public double calcActualCost(VehicleRates rates,
                                 int numDaysUsed,
                                 int numMilesDriven,
                                 boolean dailyInsur,
                                 boolean primeCustomer){
        int miles=numMilesDriven;
        if(numMilesDriven<=100){
            miles-=100;
        }
        
        double rate;
        double cost;
        if(numDaysUsed<DAYS_IN_WEEK){ // daily insurance
            if(primeCustomer){
                numMilesDriven-=100;
                if(numMilesDriven<0){
                    numMilesDriven=0;
                }
            }
            if(dailyInsur){
                rate=rates.dailyInsurRate();
            }
            else{
                rate=rates.getDailyRate();
            }
            
            cost=rate*numMilesDriven;
        }
        else if(numDaysUsed<DAYS_IN_MONTH){ // weekly insurance
            double weeks=numDaysUsed/DAYS_IN_WEEK;
            rate=rates.weeklyRate();
            cost=rate*weeks;
        }
        else{
            double months=numDaysUsed/DAYS_IN_WEEK;
            rate=rates.monthlyRate();
            cost=rate*months;
        }
        
        return numbers.round(cost,0.01);
    }
    
    /**
     * Updates the car rates
     * @param carRates the new car rates
     */
    public void updateRates(CarRates carRates){
        this.carRates=carRates;
    }
    /**
     * Updates the SUV rates
     * @param suvRates the new SUV rates
     */
    public void updateRates(SUVRates suvRates){
        this.suvRates=suvRates;
    }
    /**
     * Updates the truck rates
     * @param truckRates the new truck rates
     */
    public void updateRates(TruckRates truckRates){
        this.truckRates=truckRates;
    }
    /**
     * Updates all car rates at once
     * @param carRates the new car rates
     * @param suvRates the new SUV rates
     * @param truckRates the new truck rates
     */
    public void updateRates(CarRates carRates,SUVRates suvRates,TruckRates truckRates){
        this.carRates=carRates;
        this.suvRates=suvRates;
        this.truckRates=truckRates;
    }
}
