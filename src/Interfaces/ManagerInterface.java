/**
 * Mark Angelot
 * April 28, 2020
 */

package Interfaces;

import OtherClasses.Details.RentalDetails;
import OtherClasses.Details.ReservationDetails;
import OtherClasses.TimeSpan;
import Rates.CarRates;
import Rates.SUVRates;
import Rates.TruckRates;
import Resources.promptUser;
import java.util.Scanner;

public class ManagerInterface implements UserInterface{
// no constructor needed, calls static methods of the SystemInterface
// starts a “command loop” that repeatedly: (a) displays a menu of options, (b) gets the selected
// option from the user, and (c) executes the corresponding command.

    boolean quit=false;

    @Override
    public void start(Scanner input){
        int selection;
        // command loop
        while(!quit){
            displayMenu();
            selection=getSelection(input);
            execute(selection, input);
        }
    }

    private void execute(int selection, Scanner input){
        switch(selection){
            case 1:
                displayResults(viewAndUpdateRates(input));
                break;
            case 2:
                displayResults(viewAllVehicles(input));
                break;
            case 3:
                displayResults(addAccount(input));
                break;
            case 4:
                displayResults(viewReservations(input));
                break;
            case 5:
                displayResults(viewAccounts(input));
                break;
            case 6:
                displayResults(viewTransactions(input));
                break;
            case 7:
                quit=true;
                break;
        }
    }
    // ------- private methods

    /**
     * Displays the menu of options
     */
    private void displayMenu(){
        System.out.println("\nMAIN MENU – MANAGER");
        System.out.println("1 – View/Update Rates      ...  allows updating of rental and insurance rates");
        System.out.println("2 – View All Vehicles      ...  displays all vehicles of the agency");
        System.out.println("3 – Add Account            ...  allows entry of a new customer account");
        System.out.println("4 – View All Reservations  ...  displays all current reservations");
        System.out.println("5 – View All Accounts      ...  displays all customer accounts");
        System.out.println("6 – View Transactions      ...  displays all transactions");
        System.out.println("7 - Quit");
    }

    /**
     * Prompts user for selection from menu (continues to prompt if selection
     * &lt; 1 or selection &gt; 8)
     * @param input a Scanner object that will be used to input the selection
     *          from the user
     * @return the user's selection
     */
    private int getSelection(Scanner input){
        return promptUser.promptUserForInt("Enter your option",
                true,
                new int[]{1,2,3,4,5,6,7});
    }

    /**
     * Prompts user to enter VIN for a given vehicle (does not do any error checking on the input) { }
     * @param input
     * @return 
     */
    private String getVIN(Scanner input){ // 
        return promptUser.promptUser("Enter the VIN");
    }

    /**
     * Prompts user to enter 1, 2, or 3, and returns (continues to prompt user
     * if invalid input given)
     * @param input a Scanner object that will be used to input the selection
     *          from the user
     * @return a response of either 1, 2, or 3 that represents a type of vehicle
     */
    private int getVehicleType(Scanner input){
        return promptUser.promptUserForInt("Enter the vehicle type (1-car, 2-SUV, 3-Truck)",
                                           true,
                                           new int[]{1,2,3});
    }

    /**
     * Prompts user to enter required information for an estimated rental cost
     * (vehicle type, estimated number of miles expected to be driven, rental
     * period (number of days, weeks or months), and insurance option),
     * returning the result packaged as a RentalDetails object (to be passed in
     * method calls to the SystemInterface)
     * @param input a Scanner object that will be used to input the selection
     *          from the user
     * @return a RentalDetails object that holds details about a rental
     */
    private RentalDetails getRentalDetails(Scanner input){
        int vehicleType=getVehicleType(input);
        double estimatedMiles=promptUser.promptUserForDouble("Enter an estimated number of miles",
                        true);
        TimeSpan rentalPeriod=TimeSpan.promptUser();
        boolean insuranceOption=promptUser.promptUserForBoolean("Is insurance selected?",
                SystemInterface.YES,
                SystemInterface.NO);
        boolean isPrimeCustomer=promptUser.promptUserForBoolean("Is the customer a prime customer?",
                SystemInterface.YES,
                SystemInterface.NO);

        return new RentalDetails(vehicleType,rentalPeriod,estimatedMiles,insuranceOption,isPrimeCustomer);
    }


    /**
     * Prompts user to enter required information for making a reservation (VIN
     * of vehicle to reserve, credit card num, rental period, and insurance
     * option), returning the result packaged as a ReservationDetails object (to
     * be passed in method calls to the SystemInterface)
     * @param input a Scanner object that will be used to input the selection
     *          from the user
     * @return a ReservationDetails object that holds details about a reservation
     */
    private ReservationDetails getReservationDetails(Scanner input){
        String VIN=getVIN(input);
        String creditCardNumber=getCreditCardNum(input);
        TimeSpan rentalPeriod=TimeSpan.promptUser();
        boolean insuranceOption=promptUser.promptUserForBoolean("Is insurance selected?",
                SystemInterface.YES,
                SystemInterface.NO);

        return new ReservationDetails(VIN,creditCardNumber,rentalPeriod,insuranceOption);
    }

    /**
     * displays the array of strings passed, one string per screen line
     * @param lines an array of Strings to be printed
     */
    private void displayResults(String[] lines){
        for(String line:lines){
            System.out.println(line);
        }
    }

    /**
     * Prompts the user for a credit card number
     * @param input a Scanner object that will be used to input the selection
     *          from the user
     * @return the credit card that was input from the user
     */
    private String getCreditCardNum(Scanner input){
        return promptUser.promptUser("Enter your credit card number");
    }

    private String[] viewAndUpdateRates(Scanner input){
        System.out.println("\tCurrent Rates:");
        for(String line:SystemInterface.getAllRates()){
            System.out.println(line);
        }
        System.out.println("");
        int vehicleType=promptUser.promptUserForInt("For which type of vehicle would you like to update the rates?"
                + "\n1-Car, 2-SUV, 3-Truck",
                true, new int[]{1,2,3});
        
        double dailyRate=promptUser.promptUserForDouble("Enter the daily rate", true);
        double dailyInsurRate=promptUser.promptUserForDouble("Enter the daily rate with insurance", true);
        double weeklyRate=promptUser.promptUserForDouble("Enter the weekly rate", true);
        double monthlyRate=promptUser.promptUserForDouble("Enter the monthly rate", true);
        double mileageCharge=promptUser.promptUserForDouble("Enter the mileage charge", true);
        
        switch(vehicleType){
            case 1:
                return SystemInterface.viewAndUpdateRates(new CarRates(dailyRate,
                    weeklyRate,
                    monthlyRate,
                    mileageCharge,
                    dailyInsurRate));
            case 2:
                return SystemInterface.viewAndUpdateRates(new SUVRates(dailyRate,
                    weeklyRate,
                    monthlyRate,
                    mileageCharge,
                    dailyInsurRate));
            case 3:
                return SystemInterface.viewAndUpdateRates(new TruckRates(dailyRate,
                    weeklyRate,
                    monthlyRate,
                    mileageCharge,
                    dailyInsurRate));
            default:
                return new String[]{"Rates could not be updated"};
                // but this shouldn't happen
        }
    }

    private String[] viewAllVehicles(Scanner input){
        return SystemInterface.getAllVehicles();
    }

    private String[] addAccount(Scanner input){
        String creditcard_num=getCreditCardNum(input);
        String company_name=promptUser.promptUser("Enter the company name");
        boolean prime_customer=promptUser.promptUserForBoolean("Is this a prime customer?",
                SystemInterface.YES,
                SystemInterface.NO);
        return SystemInterface.addAccount(creditcard_num, company_name, prime_customer);
    }

    private String[] viewReservations(Scanner input){
        return SystemInterface.getAllReservations();
    }

    private String[] viewAccounts(Scanner input){
        return SystemInterface.getAllAccounts();
    }

    private String[] viewTransactions(Scanner input){
        return SystemInterface.getAllTransactions();
    }
}
