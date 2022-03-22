package $_Driver;

import Aggregators.Accounts;
import Aggregators.Transactions;
import Exceptions.VINAlreadyExistsException;
import Interfaces.SystemInterface;
import Interfaces.UserInterface;
import Aggregators.Vehicles;
import Interfaces.EmployeeUI;
import Interfaces.ManagerInterface;
import Rates.CarRates;
import Rates.CurrentRates;
import Rates.SUVRates;
import Rates.TruckRates;
import Resources.promptUser;
import Vehicles.Car;
import Vehicles.SUV;
import Vehicles.Truck;
import java.util.Scanner;

/**
 * 
 * @author Mark Angelot
 * @date April 21, 2020
 */
public class AgencyRentalProgram{

    public static void main(String[] args){
        // Provide Hard-coded Current Agency Rates
        CurrentRates agency_rates=new CurrentRates(new CarRates(24.95, 159.95, 514.95, 0.15, 14.95),
                new SUVRates(29.95, 189.95, 679.95, 0.15, 14.95),
                new TruckRates(35.95, 224.95, 787.95, 0.26, 22.95));
        // Create an Initially Empty Vehicles Collection, and Populate
        Vehicles agency_vehicles=new Vehicles();

        populate(agency_vehicles);
        // Create Initially Empty Accounts and Transaction Objects
        Accounts accounts=new Accounts();
        Transactions transactions=new Transactions();
        // Establish User Interface
        Scanner input=new Scanner(System.in);
        UserInterface ui;
        boolean quit=false;
        // Create Requested UI and Begin Execution
        while(!quit){ // (allows switching between Employee and Manager user interfaces while running)
            ui=getUI(input);
            if(ui==null){
                quit=true;
            }else{
                // Init System Interface with Agency Data (if not already initialized)
                if(!SystemInterface.initialized()){
                    SystemInterface.initSystem(agency_rates, agency_vehicles, accounts, transactions);
                }
                // Start User Interface
                ui.start(input);
            }
        }
    }

    private static void populate(Vehicles agency_vehicles){
        try{
            agency_vehicles.add(new Car("Chevrolet Camaro - 2018",30,2,"HK4GM4564GD"));
            agency_vehicles.add(new Car("Ford Fusion - 2018",34,4,"AB4EG5689GM"));
            agency_vehicles.add(new Car("Ford Fusion Hybrid - 2017",32,4,"KU4EG3245RW"));
            agency_vehicles.add(new Car("Chevrolet Impala - 2019",30,4,"RK3BM4356YH"));

            agency_vehicles.add(new SUV("Honda Odyssey - 2020",28,7,6,"VM9RE2635TD"));
            agency_vehicles.add(new SUV("Dodge Caravan - 2019",25,5,4,"QK3FL4273ME"));
            agency_vehicles.add(new SUV("Ford Expedition - 2018",20,5,3,"JK2RT9264HY"));

            agency_vehicles.add(new Truck("Ten-Foot",12,2810,"EJ5KU2435BC"));
            agency_vehicles.add(new Truck("Eighteenteen-Foot",10,5930,"KG4DM5472RK"));
            agency_vehicles.add(new Truck("Twenty-Four-Foot",8,6500,"EB2WR3082QB"));
            agency_vehicles.add(new Truck("Twenty-Four-Foot",8,6500,"TV3GH4290EK"));
        }
        catch(VINAlreadyExistsException e){
            // this shouldn't happen since none of the Vehicles have the same VIN
        }
    }

    public static UserInterface getUI(Scanner input){
        boolean valid_selection=false;
        while(!valid_selection){
            int selection=promptUser.promptUserForInt("1 – Employee, 2 – Manager, 3 – quit:",
                    true,
                    new int[]{1,2,3});
            UserInterface ui;
            if(selection==1){
                ui=new EmployeeUI();
                valid_selection=true;
            }
            else if(selection==2){
                ui=new ManagerInterface();
                valid_selection=true;
            }
            else if(selection==3){
                ui=null;
                valid_selection=true;
            }
            else{
                System.out.println("Invalid selection – please reenter");
                break;
            }
            return ui;
        }
        throw new RuntimeException("There is no error"); // to get rid of the "missing return statement" error
    }
}
