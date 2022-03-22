package OtherClasses;

import Exceptions.UnreservedVehicleException;
import Vehicles.Vehicle;

/**
 * @author Mark Angelot
 * @date April 21, 2020
 */
public class Transaction{
    private final String creditcard_num;
    private final String company_name;
    private final byte vehicle_type;  // car, SUV or Truck
    private final TimeSpan rental_period; // days, week, months
    private final String rental_cost;
    
    public Transaction(String creditcard_num,
                       String company_name,
                       String vehicle_type,
                       TimeSpan rental_period,
                       String rental_cost){
        this.creditcard_num=creditcard_num;
        this.company_name=company_name;
        this.vehicle_type=Byte.parseByte(vehicle_type);
        this.rental_period=rental_period;
        this.rental_cost=rental_cost;
    }
    
    /**
     * Constructs a Transaction using information from a Vehicles object and an
     * Account object, and a passed cost
     * @param vehicle the Vehicle that was reserved. This parameter is used to
     *          get the credit card number (through the Vehicle's reservation)
     * @param account the Account under which the transaction was made
     * @param cost the cost of the returned vehicle
     * @throws Exceptions.UnreservedVehicleException if the vehicle isn't
     *          already reserved
     */
    public Transaction(Vehicle vehicle,Account account,double cost) throws UnreservedVehicleException{
        Reservation reservation=vehicle.getReservation();
        creditcard_num=reservation.getCreditCardNum();
        company_name=account.getCompanyName();
        rental_period=reservation.getRentalPeriod();
        rental_cost="$"+cost;
        /*if(vehicle instanceof Car){
            vehicle_type="1";
        }
        else if(vehicle instanceof SUV){
            vehicle_type="2";
        }
        else{ // Truck
            vehicle_type="3";
        }*/
        vehicle_type=vehicle.vehicleType();
    }
    
    /**
     * Returns a string with all the information about this Transaction. The
     * returned String includes the name of the company, the company’s credit
     * card number, the type and model of vehicle rented, the number of days
     * had, and the total cost; displayed as follows:
     * <blockquote>Baltimore Environmental Company (card #3212546453245879),
     * Car, 3 day rental, $120.54</blockquote>
     * @return a String representation of this Transaction
     */
    @Override
    public String toString(){
        String period=rental_period.toString();
        if(period.charAt(period.length()-1)=='s'){
            period=period.substring(0,(period.length()-1));
        }
        return company_name+
                " (card #"+creditcard_num+"), "+
                vehicle_type+", "+
                period+" rental, "+rental_cost;
    }
}
