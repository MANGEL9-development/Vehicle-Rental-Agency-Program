package Interfaces;

import Aggregators.Aggregator;
import Aggregators.Accounts;
import Aggregators.Transactions;
import Aggregators.Vehicles;
import Exceptions.CreditCardNumberNotFoundException;
import Exceptions.ReservedVehiclesException;
import OtherClasses.Details.RentalDetails;
import Rates.CurrentRates;
import Exceptions.UninitializedSystemInterfaceException;
import Exceptions.UnreservedVehicleException;
import Exceptions.VINNotFoundException;
import OtherClasses.Account;
import OtherClasses.Details.ReservationDetails;
import OtherClasses.Reservation;
import OtherClasses.Transaction;
import Rates.CarRates;
import Rates.SUVRates;
import Rates.TruckRates;
import Rates.VehicleRates;
import Resources.numbers;
import Resources.promptUser;
import Vehicles.Car;
import Vehicles.SUV;
import Vehicles.Truck;
import Vehicles.Vehicle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * The system interface contains all static methods, one for each use case,
 * providing all of the functionality needed in the program (and a set of
 * private static variables of type CurrentRates, Vehicles, Accounts and
 * Transactions). They are the only methods that the user interfaces directly
 * call, each returning an array of strings which the user interfaces simply 
 * display.
 * @author Mark Angelot
 * @date April 21, 2020
 */
public class SystemInterface{
    private static boolean isInitialized=false;
    
    private static CurrentRates agency_rates;
    private static Vehicles agency_vehicles;
    private static Accounts accounts;
    private static Transactions transactions_history;
    
    public final static char[] YES=new char[]{'Y','y'};
    public final static char[] NO=new char[]{'N','n'};

    /**
     * This method is used to init static variables (in place of a constructor).
     * This method is called by the main method and is passed constructed and
     * populated CurrentRates and Vehicles objects. It is also passed (and
     * initially empty) {@link Aggregators.Accounts Accounts} and
     * {@link Aggregators.Transactions Transactions} objects. This method
     * assigns the four static variables of the System Interface class to the
     * four aggregation objects passed.
     * @param r a {@link Rates.CurrentRates CurrentRates} object containing the
     *          current rates
     * @param v a {@link Aggregators.Vehicles Vehicles} object that holds the
     *          collection of {@link Vehicles.Vehicle Vehicles} in the program
     * @param a an {@link Aggregators.Accounts Accounts} object that holds the
     *          collection of {@link OtherClasses.Account Accounts} in the
     *          program
     * @param t a {@link Aggregators.Transactions Transactions} object that
     *          holds the transactions in the program
     */
    public static void initSystem(CurrentRates r, Vehicles v, Accounts a, Transactions t){
        isInitialized=true;
        agency_rates=r;
        agency_vehicles=v;
        accounts=a;
        transactions_history=t;
    }

    /* Note that methods makeReservation, cancelReservation, addAccount, and
     * updateXXXRates return an acknowledgement of successful completion of the
     * requested action (e.g. “Vehicle QK3FL4273ME successfully reserved”).
     * Method processReturnedVehicle returns the final cost for the returned
     * vehicle (e.g., “Total charge for VIN QK3FL4273ME for 3 days,
     * 233 miles @ 0.15/mile and daily insurance @ 14.95/day (with 100 miles
     * credit as Prime customer) = $xxx.xx.)
     */
    
    private static String[] carRates(){
        checkInitialization();
        
        String[] lines=new String[5];
        
        lines[0]="Daily: $"+agency_rates.getCarRates().getDailyRate();
        lines[1]="Daily (with insurance): $"+agency_rates.getCarRates().dailyInsurRate();
        lines[2]="Weekly: $"+agency_rates.getCarRates().weeklyRate();
        lines[3]="Monthly: $"+agency_rates.getCarRates().monthlyRate();
        lines[4]="Mileage Charge: $"+agency_rates.getCarRates().mileageCharge();
        
        return lines;
    }
    public static String[] getCarRates(){
        checkInitialization();
        return createListFromArray(carRates(),false);
    }

    private static String[] suvRates(){
        checkInitialization();
        
        String[] lines=new String[5];
        
        lines[0]="Daily: $"+agency_rates.getSUVRates().getDailyRate();
        lines[1]="Daily (with insurance): $"+agency_rates.getSUVRates().dailyInsurRate();
        lines[2]="Weekly: $"+agency_rates.getSUVRates().weeklyRate();
        lines[3]="Monthly: $"+agency_rates.getSUVRates().monthlyRate();
        lines[4]="Mileage Charge: $"+agency_rates.getSUVRates().mileageCharge();
        
        return lines;
    }
    public static String[] getSUVRates(){
        checkInitialization();
        return createListFromArray(suvRates(),false);
    }

    private static String[] truckRates(){
        checkInitialization();
        
        String[] lines=new String[5];
        
        
        lines[0]="Daily: $"+agency_rates.getTruckRates().getDailyRate();
        lines[1]="Daily (with insurance): $"+agency_rates.getTruckRates().dailyInsurRate();
        lines[2]="Weekly: $"+agency_rates.getTruckRates().weeklyRate();
        lines[3]="Monthly: $"+agency_rates.getTruckRates().monthlyRate();
        lines[4]="Mileage Charge: $"+agency_rates.getTruckRates().mileageCharge();
        
        return lines;
    }
    public static String[] getTruckRates(){
        checkInitialization();
        return createListFromArray(truckRates(),false);
    }
    
    public static String[] getAllRates(){
        String[][] lines=new String[3][6];
        String[] rateNames={"Car Rates","SUV Rates","Truck"};
        String[][] rates=new String[][]{
            carRates(),
            suvRates(),
            truckRates()
        };
        
        for(int x=0;x<lines.length;x++){
            lines[x][0]=rateNames[x];
            for(int y=0;y<rates[x].length;y++){
                lines[x][(y+1)]=rates[x][y];
            }
        }
        
        return createListFromArray(lines);
    }

    public static String[] updateCarRates(CarRates r){
        checkInitialization();
        agency_rates.updateRates(r);
        return new String[]{"\tCar rates successfully updated"};
    }

    public static String[] updateSUVRates(SUVRates r){
        checkInitialization();
        agency_rates.updateRates(r);
        return new String[]{"\tSUV rates successfully updated"};
    }

    public static String[] updateTruckRates(TruckRates r){
        checkInitialization();
        agency_rates.updateRates(r);
        return new String[]{"\tTruck rates successfully updated"};
    }

    public static String[] estimatedRentalCost(RentalDetails details){
        checkInitialization();
        double estimatedRentalCost=agency_rates.calcEstimatedCost(details.getVehicleType(),
                details.getEstimatedRentalPeriod(),
                details.getEstimatedMiles(),
                details.getInsuranceOption(),
                details.isPrimeCustomer());
        estimatedRentalCost=numbers.round(estimatedRentalCost, 0.01);
        return new String[]{"\tEstimated Rental Cost: $"+estimatedRentalCost};
    }

    public static String[] processReturnedVehicle(String vin, int num_days_used, int num_miles_driven){
        checkInitialization();
        String[] lines=new String[1];
        Vehicle returnedVehicle;
        try{
            returnedVehicle=agency_vehicles.getVehicle(vin);
        }
        catch(VINNotFoundException e){
            return new String[]{"\tError: No vehicle found with the VIN \""+vin+"\""};
        }
        Reservation reservation;
        try{
            reservation=returnedVehicle.getReservation();
        }
        catch(UnreservedVehicleException e){
            return new String[]{"\tNo reservation was found with vehicle "+vin};
        }
        Account associatedAccount;
        try{
            associatedAccount=accounts.getAccountByCreditCardNumber(reservation.getCreditCardNum());
        }
        catch(CreditCardNumberNotFoundException e){
            return new String[]{"\tNo account found with the credit "+
                    "card number associated with the reservation associated "+
                    "with the vehicle with the VIN \""+vin+"\""};
        }
        //Calculate Cost
        double cost=agency_rates.calcActualCost(returnedVehicle.getQuotedRates(),
                num_days_used,
                num_miles_driven,
                reservation.insuranceSelected(),
                associatedAccount.primeCustomer());
        
        //Lift reservation and record transaction
        Transaction newTransaction;
        try{
            newTransaction=new Transaction(returnedVehicle,
                    associatedAccount,
                    cost);
        }
        catch(UnreservedVehicleException e){
            return new String[]{"\tNo reservation found on the returned vehicle vehicle"};
        }
        transactions_history.addTransaction(newTransaction);
        lines[0]="\t"+newTransaction.toString();
        
        return lines;
    }
    
    /* Note that the rates to be used are retrieved from the VehicleRates object
     * stored in the specific rented vehicle object, the daily insurance option
     * is retrieved from the Reservation object of the rented vehicle, and
     * whether they are a Prime customer is retrived from the Customer Account
     * object vehicle associated with the Reservation object associated with the
     * specific rented vehicle.
     */

    // Vehicle Related Methods
    public static String[] getAvailCars(){
        checkInitialization();
        // count the number of available cars
        int num_cars=0;
        agency_vehicles.reset();
        while(agency_vehicles.hasNext()){
            Vehicle currentVehicle=agency_vehicles.getNext();
            if((currentVehicle instanceof Car) && !currentVehicle.isReserved()){
                num_cars=num_cars+1;
            }
        }
        // create appropriate size array
        String[] avail_cars=new String[num_cars];
        // populate the array with available cars
        int i=0;
        Vehicle v;
        agency_vehicles.reset();
        while(agency_vehicles.hasNext()){
            v=agency_vehicles.getNext();
            if(v instanceof Car){
                avail_cars[i++]=v.toString();
            }
        }
        return createListFromArray(avail_cars,true);
    }

    public static String[] getAvailSUVs(){
        checkInitialization();
        // count the number of available suvs
        int num_suvs=0;
        agency_vehicles.reset();
        while(agency_vehicles.hasNext()){
            Vehicle currentVehicle=agency_vehicles.getNext();
            if((currentVehicle instanceof SUV) && !currentVehicle.isReserved()){
                num_suvs=num_suvs+1;
            }
        }
        // create appropriate size array
        String[] avail_suvs=new String[num_suvs];
        // populate the array with available SUVs
        int i=0;
        Vehicle v;
        agency_vehicles.reset();
        while(agency_vehicles.hasNext()){
            v=agency_vehicles.getNext();
            if(v instanceof SUV){
                avail_suvs[i++]=v.toString();
            }
        }
        return createListFromArray(avail_suvs,true);
    }

    public static String[] getAvailTrucks(){
        checkInitialization();
        // count the number of available trucks
        int num_trucks=0;
        agency_vehicles.reset();
        while(agency_vehicles.hasNext()){
            Vehicle currentVehicle=agency_vehicles.getNext();
            if((currentVehicle instanceof SUV) && !currentVehicle.isReserved()){
                num_trucks=num_trucks+1;
            }
        }
        // create appropriate size array
        String[] avail_trucks=new String[num_trucks];
        // populate the array with available trucks
        int i=0;
        Vehicle v;
        agency_vehicles.reset();
        while(agency_vehicles.hasNext()){
            v=agency_vehicles.getNext();
            if(v instanceof Truck){
                avail_trucks[i++]=v.toString();
            }
        }
        return createListFromArray(avail_trucks,true);
    }

    public static String[] getAllVehicles(){
        checkInitialization();
        return createListFromAggregator(agency_vehicles);
    }

    public static String[] makeReservation(ReservationDetails details){
        checkInitialization();
        Vehicle reservedVehicle;
        String vin=details.getVIN();
        try{
            reservedVehicle=agency_vehicles.getVehicle(vin);
        }
        catch(VINNotFoundException e){
            return new String[]{"\tNo vehicle found with the VIN \""+vin+"\"; reservation failed"};
        }
        Reservation reservation=new Reservation(details.creditCardNumber(),
                reservedVehicle.vehicleType(),
                reservedVehicle.getDescription(),
                details.rentalPeriod(),
                details.dailyInsurance());
        try{
            reservedVehicle.setReservation(reservation);
            switch(reservedVehicle.vehicleType()){
                case 1:
                    reservedVehicle.setQuotedRates(new CarRates(agency_rates.getCarRates()));
                    break;
                case 2:
                    reservedVehicle.setQuotedRates(new SUVRates(agency_rates.getSUVRates()));
                    break;
                case 3:
                    reservedVehicle.setQuotedRates(new TruckRates(agency_rates.getTruckRates()));
                    break;
            }
        }
        catch(ReservedVehiclesException e){
            return new String[]{reservedVehicle.getDescription()+" is already reserved; reservation failed"};
        }
        return new String[]{reservedVehicle.getDescription()+" successfully reserved"};
    }

    public static String[] cancelReservation(String creditCardNumber, String vin){
        checkInitialization();
        try{
            Vehicle vehicleWithReservation=agency_vehicles.getVehicleByCreditCard(creditCardNumber);
            try{
                vehicleWithReservation.cancelReservation();
                return new String[]{"\tReservation successfully cancelled on vehicle "+
                        vehicleWithReservation.getVIN()};
            }
            catch(UnreservedVehicleException e){
                return new String[]{"\tNo reservation was found with vehicle "+
                        vehicleWithReservation.getVIN()+"; cancellation failed"};
            }
        }
        catch(CreditCardNumberNotFoundException e){
            return new String[]{"\tNo vehicle found with the credit card number; cancellation failed"};
        }
    }

    public static String[] getReservation(String vin){
        checkInitialization();
        Vehicle reservedVehicle;
        Reservation reservation;
        try{
            reservedVehicle=agency_vehicles.getVehicle(vin);
        }
        catch(VINNotFoundException e){
            return new String[]{"\tNo vehicle found with the VIN \""+vin+"\"; search failed"};
        }
        try{
            reservation=reservedVehicle.getReservation();
        }
        catch(UnreservedVehicleException e){
            return new String[]{"\tNo reservation was found with vehicle "+vin};
        }
        return new String[]{reservation.toString()};
    }

    /**
     * Finds reservations and returns them as a list of Strings. If there are no
     * reservations, this method returns a one-element array that writes "No
     * reservations found"
     * @return an array in which each element contains details about a
     *     reservation in the system
     */
    public static String[] getAllReservations(){
        checkInitialization();
        ArrayList<String> reservations=new ArrayList();
        agency_vehicles.reset();
        while(agency_vehicles.hasNext()){
            Vehicle currentVehicle=agency_vehicles.getNext();
            try{
                Reservation reservation=currentVehicle.getReservation();
                String reservationDetails="Vehicle: "+currentVehicle.getDescription()+
                        "\nTime: "+reservation.getRentalPeriod()+
                        "\nInsurance: "+(reservation.insuranceSelected()?"Yes":"No");
                try{
                    String companyName=accounts.getAccountByCreditCardNumber(reservation.getCreditCardNum()).getCompanyName();
                    reservationDetails+="\nCompany: "+companyName;
                }
                catch(CreditCardNumberNotFoundException e){
                    // don't add a company name to the reservation details
                }
                reservations.add(reservationDetails);
            }
            catch(UnreservedVehicleException e){
                // skip to next iteration
            }
        }
        
        if(reservations.isEmpty()){
            return new String[]{"\tNo reservations found"};
        }
        
        String[] lines=new String[reservations.size()];
        Iterator<String> iterator=reservations.iterator();
        for(int i=0;iterator.hasNext();i++){
            lines[i]=iterator.next();
        }
        
        return createListFromArray(lines,true);
    }

    // Customer Accounts Related Methods
    public static String[] addAccount(String creditcard, String company_name, boolean prime_cust){
        checkInitialization();
        Account newAccount=new Account(creditcard,company_name,prime_cust);
        accounts.addAccount(newAccount);
        return new String[]{"\tAccount with "+company_name+" successfully added"};
    }
    
    public static void createAccount(String creditCardNumber){
        if(creditCardNumber==null){
            creditCardNumber=promptUser.promptUser("Enter your credit card number");
        }
        String companyName=promptUser.promptUser("Enter the company name");
        boolean isPrimeCustomer=promptUser.promptUserForBoolean("Is this a prime customer?",
                SystemInterface.YES,SystemInterface.NO);
        Account newAccount=new Account(creditCardNumber,companyName,isPrimeCustomer);
        accounts.addAccount(newAccount);
    }

    public static String[] getAccount(String creditcard_num){
        checkInitialization();
        Account account;
        try{
            account=accounts.getAccountByCreditCardNumber(creditcard_num);
        }
        catch(CreditCardNumberNotFoundException e){
            return new String[]{"\tNo vehicle found with the credit card number"};
        }
        return new String[]{account.toString()};
    }

    public static String[] getAllAccounts(){
        checkInitialization();
        if(accounts.isEmpty()){
            return new String[]{"\tNo accounts found"};
        }
        return createListFromAggregator(accounts);
    }
    
    public static Account findAccount(String creditCardNumber) throws CreditCardNumberNotFoundException{
        return accounts.getAccountByCreditCardNumber(creditCardNumber);
    }

    // transactions-related methods
    public static String[] getAllTransactions(){
        checkInitialization();
        if(transactions_history.isEmpty()){
            return new String[]{"\tNo transactions found"};
        }
        return createListFromAggregator(transactions_history);
    }

    public static boolean initialized(){
        return isInitialized;
    }
    
    /**
     * This method is called at the beginning of all the other methods. If this
     * SystemInterface isn't initialized, an
     * {@link Exceptions.UninitializedSystemInterfaceException
     * UninitializedSystemInterfaceException} will be thrown, but won't be
     * caught by the method that called this method, and so will be thrown back
     * to the method that called it. For example, if MethodA() calls
     * getCarRates() before the system is initialized, an
     * UninitializedSystemInterfaceException will be thrown to getCarRates().
     * getCarRates() won't catch the exception, so it'll be thrown back to
     * MethodA().<br /><br />
     * If this SystemInterface is initialized, the program will continue
     * normally.
     * @throws UninitializedSystemInterfaceException if this SystemInterface
     * isn't initialized
     */
    private static void checkInitialization(){
        if(!isInitialized){
            throw new UninitializedSystemInterfaceException();
        }
    }
    
    /**
     * Uses iterator methods from Aggregator to create and return an ordered
     * list in the form of an array.<br>
     * The returned array will be as the following:
     * <blockquote><code>["1. One","2. Two","3. Three"]</code></blockquote>
     * @param aggregator the aggregator from which to retrieve elements
     * @return an array in which every element is a member of an ordered list
     */
    private static String[] createListFromAggregator(Aggregator aggregator){
        int length=aggregator.length();
        String[] lines=new String[length];
        aggregator.reset();
        for(int i=0;aggregator.hasNext();i++){
            Object nextVehicle=aggregator.getNext();
            lines[i]=nextVehicle.toString();
        }
        lines[(length-1)]=aggregator.getNext().toString();
        return createListFromArray(lines,true);
    }
    
    /**
     * Creates and returns a list from an array
     * @param array the array that is to be turned into a list
     * @return an array in which every element is a member of an ordered list
     */
    private static String[] createListFromArray(Object[] array,boolean isNumbered){
        int length=array.length;
        String[] lines=new String[length];
        for(int i=1;i<=length;i++){
            String number=(isNumbered)?
                    (((i<10) && (length>10))?" ":"")+i+". " :
                    "";
            lines[(i-1)]="\t"+number+array[(i-1)];
        }
        return lines;
    }
    
    /**
     * Creates and returns a list from a two-dimensional array
     * @param array the two-dimensional array that is to be turned into a list.
     *          This array should be arranged as so:
     * <blockquote>
     *  {
     *      <blockquote>
     *          {Element 1, Sub 1, Sub 2, Sub 3},
     *      </blockquote>
     *  <blockquote>
     *  {Element 2, Sub 1, Sub 2, Sub 3, Sub 4},
     *      </blockquote>
     *      <blockquote>
     *          {Element 3},
     *      </blockquote>
     *      <blockquote>
     *          {Element 4, Sub 1, Sub 2}
     *      </blockquote>
     *  }
     * </blockquote>
     * And that will produce this:
     * <ol type="1">
     *  <li>Element 1
     *      <ol type="A">
     *          <li>Sub 1</li>
     *          <li>Sub 2</li>
     *          <li>Sub 3</li>
     *      </ol>
     *  </li>
     *  <li>Element 2
     *      <ol type="A">
     *          <li>Sub 1</li>
     *          <li>Sub 2</li>
     *      </ol>
     *  </li>
     *  <li>Element 3</li>
     *  <li>Element 4
     *      <ol type="A">
     *          <li>Sub 1</li>
     *          <li>Sub 2</li>
     *          <li>Sub 3</li>
     *          <li>Sub 4</li>
     *      </ol>
     *  </li>
     * </ol>
     * @return an array in which every element is an element of an ordered,
     * two-level list
     */
    private static String[] createListFromArray(Object[][] array){
        int totalLength=totalLength(array);
        int level1_length=array.length;
        String[] lines=new String[totalLength];
        for(int x=1,a=0;x<=level1_length;x++){
            int level2_length=array[(x-1)].length;
            lines[a]="\t"+(((x<10) && (level1_length>10))?" ":"")+x+". "+array[(x-1)][0];
            a++;
            for(int y=1;y<array[(x-1)].length;y++,a++){
                char letter=((char)(y+64));
                lines[a]="\t\t"+(((x<10) && (level2_length>10))?" ":"")+letter+". "+array[(x-1)][y];
            }
        }
        return lines;
    }
    
    private static int totalLength(Object[][] array){
        int total=0;
        for(Object[] level1:array){
            for(Object level2:level1){
                total++;
            }
        }
        return total;
    }

    public static String[] viewAndUpdateRates(CarRates newRates){
        agency_rates.updateRates(newRates);
        return new String[]{"\tCar rates successfully updated"};
    }

    public static String[] viewAndUpdateRates(SUVRates newRates){
        agency_rates.updateRates(newRates);
        return new String[]{"\tSUV rates successfully updated"};
    }

    public static String[] viewAndUpdateRates(TruckRates newRates){
        agency_rates.updateRates(newRates);
        return new String[]{"\tTruck rates successfully updated"};
    }
}
