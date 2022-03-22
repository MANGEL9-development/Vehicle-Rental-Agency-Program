package $_Driver;
/*
-------------------------------------------------------------------
        The main class for Program 5 is AgencyRentalProgram
-------------------------------------------------------------------
*/
import Exceptions.CreditCardNumberNotFoundException;
import Exceptions.ReservedVehiclesException;
import Exceptions.UnreservedVehicleException;
import Exceptions.VINAlreadyExistsException;
import Exceptions.VINNotFoundException;
import OtherClasses.Reservation;
import OtherClasses.TimeSpan;
import Aggregators.Vehicles;
import Resources.promptUser;
import Vehicles.Car;
import Vehicles.SUV;
import Vehicles.Truck;
import Vehicles.Vehicle;

/**
 * This is the main class for this project. This class can be used to manipulate
 * the {@link Vehicles.Vehicle Vehicles} in a {@link Aggregators.Vehicles Vehicles}
 * object
 * @author Mark Angelot
 * @date March 15, 2020
 * @deprecated use {@link AgencyRentalProgram} instead
 */
@Deprecated
public class vehicles_driver{
    /**
     * This {@link Aggregators.Vehicles Vehicles} object holds the
     * {@link Vehicles.Vehicle Vehicles} that will be used in this class
     */
    private static final Vehicles VEHICLES_LIST=new Vehicles();
    
    /**
     * When this method is run, the user will be given eight options:
     * <ol>
            <li>Display all vehicles</li>
            <li>Display available vehicles</li>
            <li>Reserve a vehicle</li>
            <li>Display a reservation</li>
            <li>Cancel a reservation</li>
            <li>Add a vehicle</li>
            <li>Remove a vehicle</li>
            <li>Quit</li>
     * </ol>
     * If the user chooses 8, the program will terminate. After the user chooses
     * an option, the corresponding action will be performed and then the user
     * will be reprompted with the same options.
     * @param args this parameter is not used in this method
     */
    public static void main(String[] args){
        fillList();
        
        boolean keepGoing=true;
        System.out.println("Welcome to the Vehicles Collection program");
        while(keepGoing){
            System.out.println("\n");
            System.out.println("1 - Display all vehicles");
            System.out.println("2 - Display available vehicles");
            System.out.println("3 - Reserve a vehicle");
            System.out.println("4 - Display a reservation");
            System.out.println("5 - Cancel a reservation");
            System.out.println("6 - Add a vehicle");
            System.out.println("7 - Remove a vehicle");
            System.out.println("8 - Quit");
            System.out.println();

            int response=promptUser.promptUserForInt("Enter your option",true);
            switch(response){
                case 1:
                    displayAllVehicles();
                    break;
                case 2:
                    displayAvailableVehicles();
                    break;
                case 3:
                    reserveAVehicle();
                    break;
                case 4:
                    displayAReservation();
                    break;
                case 5:
                    cancelAReservation();
                    break;
                case 6:
                    addAVehicle();
                    break;
                case 7:
                    removeAVehicle();
                    break;
                case 8:
                    System.out.println("Terminating Program...");
                    keepGoing=false;
                    break;
                default:
                    System.out.println("Error: invalid input ("+response+")");
                    break;
            }
        }
    }
    
    /**
     * This method fills the {@link VEHICLES_LIST} with
     * {@link Vehicles.Vehicle Vehicles} from the rubric
     */
    private static void fillList(){
        try{
            VEHICLES_LIST.add(new Car("Chevrolet Camaro - 2018",30,2,"HK4GM4564GD"));
            VEHICLES_LIST.add(new Car("Ford Fusion - 2018",34,4,"AB4EG5689GM"));
            VEHICLES_LIST.add(new Car("Ford Fusion Hybrid - 2017",32,4,"KU4EG3245RW"));
            VEHICLES_LIST.add(new Car("Chevrolet Impala - 2019",30,4,"RK3BM4356YH"));

            VEHICLES_LIST.add(new SUV("Honda Odyssey - 2020",28,7,6,"VM9RE2635TD"));
            VEHICLES_LIST.add(new SUV("Dodge Caravan - 2019",25,5,4,"QK3FL4273ME"));
            VEHICLES_LIST.add(new SUV("Ford Expedition - 2018",20,5,3,"JK2RT9264HY"));

            VEHICLES_LIST.add(new Truck("Ten-Foot",12,2810,"EJ5KU2435BC"));
            VEHICLES_LIST.add(new Truck("Eighteenteen-Foot",10,5930,"KG4DM5472RK"));
            VEHICLES_LIST.add(new Truck("Twenty-Four-Foot",8,6500,"EB2WR3082QB"));
            VEHICLES_LIST.add(new Truck("Twenty-Four-Foot",8,6500,"TV3GH4290EK"));
        }
        catch(VINAlreadyExistsException e){
            // this shouldn't happen since none of the Vehicles have the same VIN
        }
    }

    /**
     * This is option 1. This method displays all the
     * {@link Vehicles.Vehicle Vehicles} in {@link VEHICLES_LIST}
     */
    private static void displayAllVehicles(){
        VEHICLES_LIST.reset();
        if(VEHICLES_LIST.length()==0){
            System.out.println("No vehicles found");
        }
        else{
            while(VEHICLES_LIST.hasNext()){
                System.out.println(VEHICLES_LIST.getNext());
            }
            System.out.println(VEHICLES_LIST.getNext());
        }
    }

    /**
     * This is option 2. This method displays all the unreserved
     * {@link Vehicles.Vehicle Vehicles} in {@link VEHICLES_LIST}
     */
    private static void displayAvailableVehicles(){
        VEHICLES_LIST.reset();
        if(VEHICLES_LIST.length()==0){
            System.out.println("No vehicles found");
        }
        else{
            boolean availableVehicleFound=false;
            while(VEHICLES_LIST.hasNext()){
                Vehicle nextVehicle=VEHICLES_LIST.getNext();
                if(!nextVehicle.isReserved()){
                    System.out.println(nextVehicle);
                    availableVehicleFound=true;
                }
            }
            if(!availableVehicleFound){
                System.out.println("No available vehicles found");
            }
        }
    }

    /**
     * This is option 3. This method asks the user for their credit card number,
     * the amount of time for which they're reserving the vehicle, whether or
     * not they want insurance, and the VIN for the vehicle they're reserving
     */
    private static void reserveAVehicle(){
        String creditCardNumber=promptUser.promptUser("Enter your credit card");
        String timeSpan=promptUser.promptUser("For how long are you reserving this vehicle? (Enter your response in the form \"4 d\" for four days)");
        
        int amountOfTime;
        try{
            amountOfTime=Integer.parseInt(timeSpan.split(" ")[0]);
        }
        catch(NumberFormatException e){
            System.err.println("\tError: Input must begin with a number");
            return;
        }
        if(!timeSpan.contains(" ")){
            System.err.println("\tError: Expected input in form \"[int] [char]\", but got \""+timeSpan+"\"");
            return;
        }
        char unitOfTime=timeSpan.split(" ")[1].charAt(0);
        /*if((unitOfTime!='d') && (unitOfTime!='w') && (unitOfTime!='m')){
            System.err.println("Error: unit of time can only be 'd', 'w', or 'm'");
            return;
        }*/
        TimeSpan reservationTime;
        try{
            reservationTime=new TimeSpan(unitOfTime,amountOfTime);
        }
        catch(IllegalArgumentException e){
            System.err.println("\tError: "+e.getMessage());
            return;
        }
        
        boolean withInsurance=(promptUser.promptUserForChar("Do you want insurance? (Enter 'Y' for yes, and 'N' for no)")!='N');
        Reservation r=new Reservation(creditCardNumber,reservationTime,withInsurance);
        
        String VIN=promptUser.promptUser("Enter the car's VIN");
        
        try{
            Vehicle v=VEHICLES_LIST.getVehicle(VIN);
            try{
                v.setReservation(r);
                try{
                    System.out.println(v.getDescription()+" has successfully been reserved for "+v.getReservation().getRentalPeriod()+".");
                }
                catch(UnreservedVehicleException e){
                    // this is impossible
                }
            }
            catch(ReservedVehiclesException e){
                System.err.println("\t"+v.getDescription()+" is already reserved");
                System.err.println("\tVehicle could not be reserved");
            }
        }
        catch(VINNotFoundException e){
            System.err.println("\tNo vehicle found with the VIN \""+VIN+"\"");
            System.err.println("\tVehicle could not be reserved");
        }
        
    }

    /**
     * This is option 4. This method asks the user for their credit card number,
     * and displays the reservation associated with the credit card (if there's
     * a reservation)
     */
    private static void displayAReservation(){
        String creditCardNumber=promptUser.promptUser("Enter your credit card number");
        Vehicle v;
        try{
            v=VEHICLES_LIST.getVehicleByCreditCard(creditCardNumber);
        }
        catch(CreditCardNumberNotFoundException e){
            System.err.println("\tNo vehicle found with the credit card number \""+creditCardNumber+"\"");
            System.err.println("\tReservation could not be displayed");
            return;
        }
        try{
            System.out.println(v.getDescription() +
                    " reserved for "+
                    v.getReservation().getRentalPeriod()+
                    " with"+(v.getReservation().insuranceSelected()?"":"out")+" insurance");
        }
        catch(UnreservedVehicleException e){
            System.err.println("\tNo reservation found for this vehicle");
            System.err.println("\tReservation could not be displayed");
        }
    }

    /**
     * This is option 5. This method asks the user for a VIN and cancels the
     * corresponding vehicle's reservation (if it's reserved)
     */
    private static void cancelAReservation(){
        String creditCardNumber=promptUser.promptUser("Enter your credit card number");
        Vehicle v;
        try{
            v=VEHICLES_LIST.getVehicleByCreditCard(creditCardNumber);
        }
        catch(CreditCardNumberNotFoundException e){
            System.err.println("\tNo vehicle found with the credit card number \""+creditCardNumber+"\"");
            System.err.println("\tReservation could not be displayed");
            return;
        }
        try{
            v.cancelReservation();
            System.out.println("Reservation was successfully canceled");
        }
        catch(UnreservedVehicleException e){
            System.err.println("\tNo reservation found for this vehicle");
            System.err.println("\tReservation could not be displayed");
        }
    }

    /**
     * This is option 6. This method lets a user add a custom vehicle to
     * {@link VEHICLES_LIST}
     */
    private static void addAVehicle(){
        int typeOfVehicle=promptUser.promptUserForInt("Which type of vehicle do you want to add?\n"+
                "  1 - Car\n"+
                "  2 - SUV\n"+
                "  3 - Truck",true);
        if(typeOfVehicle<1 || typeOfVehicle>3){
            System.err.println("\tError: entered input ("+typeOfVehicle+") was not an option");
            return;
        }
        
        String description=promptUser.promptUser("Enter the car's description (eg. \"Tesla Cybertruck - 2019\")");
        int milage=promptUser.promptUserForInt("Enter the car's milage in mpg", true);
        String VIN=promptUser.promptUser("Enter the car's VIN (Vehicle Identification Number)");
        int seatCapacity, cargoCapacity;
        Vehicle newVehicle=new Car("",-1,-1,"");
        switch(typeOfVehicle){
            case 1:
                seatCapacity=promptUser.promptUserForInt("How many people can this car seat?", true);
                newVehicle=new Car(description,milage,seatCapacity,VIN);
                break;
            case 2:
                seatCapacity=promptUser.promptUserForInt("How many people can this car seat?", true);
                cargoCapacity=promptUser.promptUserForInt("Enter the SUV's cargo capacity (in cubic ft.)", true);
                newVehicle=new SUV(description,milage,seatCapacity,cargoCapacity,VIN);
                break;
            case 3:
                cargoCapacity=promptUser.promptUserForInt("Enter the SUV's cargo capacity (in pounds)", true);
                newVehicle=new Truck(description,milage,cargoCapacity,VIN);
                break;
        }
        try{
            VEHICLES_LIST.add(newVehicle);
            System.out.println(newVehicle.getDescription()+" has been successfully added");
        }catch(VINAlreadyExistsException e){
            System.err.println("\tVehicle already exists with the VIN \""+e.vin+"\"");
        }
    }

    /**
     * This is option 7. This method asks the user for a VIN and removes it from
     * {@link VEHICLES_LIST}
     */
    private static void removeAVehicle(){
        String[] VINs=promptUser.promptUser("Enter the car's VIN").split(",");
        for(String VIN:VINs){
            try{
                Vehicle v=VEHICLES_LIST.remove(VIN);
                System.out.println(v.getDescription()+" has been succesfully removed");
            }
            catch(VINNotFoundException e){
                System.err.println("\tNo vehicle found with the VIN \""+VIN+"\"");
                System.err.println("\tVehicle could not be removed");
            }
        }
    }
}
