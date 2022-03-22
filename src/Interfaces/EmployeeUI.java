/**
 * Mark Angelot
 * April 28, 2020
 */
package Interfaces;

import Exceptions.CreditCardNumberNotFoundException;
import OtherClasses.Account;
import OtherClasses.Details.RentalDetails;
import OtherClasses.Details.ReservationDetails;
import OtherClasses.TimeSpan;
import Resources.promptUser;
import java.util.Scanner;

/**
 * This is an implementation of the {@link UserInterface} class
 *
 * @see ManagerInterface
 */
public class EmployeeUI implements UserInterface{
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
                displayResults(displayRentalRates(input));
                break;
            case 2:
                displayResults(displayAvailableVehicles(input));
                break;
            case 3:
                displayResults(displayEstimatedRentalCost(input));
                break;
            case 4:
                displayResults(makeAReservation(input));
                break;
            case 5:
                displayResults(cancelAReservation(input));
                break;
            case 6:
                displayResults(viewCorporateAccount(input));
                break;
            case 7:
                displayResults(processReturnedVehicle(input));
                break;
            case 8:
                quit=true;
                break;
        }
    }
    // ------- private methods

    private void displayMenu(){
        System.out.println("MAIN MENU – EMPLOYEE");
        System.out.println("1 – View Current Rates          ...  displays rental (and insurance rates) for one of cars, SUVs, or trucks");
        System.out.println("2 – View Available Vehicles     ...  displays available vehicles (cars, SUVs, or trucks");
        System.out.println("3 – Calc Estimated Rental Cost  ...  displays estimated rental cost for given vehicle type, rental period,");
        System.out.println("                                     expected miles driven, optional daily insurance, and if Prime Customer");
        System.out.println("4 – Make a Reservation          ...  creates a reservation for VIN, credit card num, rental period, and insur");
        System.out.println("                                     option");
        System.out.println("5 - Cancel Reservation          ...  cancels a reservation by VIN");
        System.out.println("6 – View Corporate Account      ...  displays account information for a given account number, including all");
        System.out.println("                                     current reservations");
        System.out.println("7 – Process Returned Vehicle    ...  requests VIN and actual number of miles driven and processes returned");
        System.out.println("                                     vehicle and displays total charge");
        System.out.println("8 - Quit");
    }
// displays the menu of options

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
                new int[]{1,2,3,4,5,6,7,8});
    }

    private String getVIN(Scanner input){ // prompts user to enter VIN for a given vehicle (does not do any error checking on the input) { }
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
        String VIN=promptUser.promptUser("Enter the VIN");
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

    private String[] displayRentalRates(Scanner input){
        String[] display_lines;
        int veh_type=getVehicleType(input);
        switch(veh_type){
            case 1:
                display_lines=SystemInterface.getCarRates();
                break;
            case 2:
                display_lines=SystemInterface.getSUVRates();
                break;
            case 3:
                display_lines=SystemInterface.getTruckRates();
                break;
            default:
                display_lines=new String[]{""};
        }
        return display_lines;
    }

    private String[] displayAvailableVehicles(Scanner input){
        String[] display_lines={};
        int veh_type=getVehicleType(input);
        switch(veh_type){
            case 1:
                display_lines=SystemInterface.getAvailCars();
                break;
            case 2:
                display_lines=SystemInterface.getAvailSUVs();
                break;
            case 3:
                display_lines=SystemInterface.getAvailTrucks();
                break;
        }
        return display_lines;
    }

    private String[] displayEstimatedRentalCost(Scanner input){
        String[] display_lines;
        RentalDetails rental_details=getRentalDetails(input);
        display_lines=SystemInterface.estimatedRentalCost(rental_details);
        return display_lines;
    }

    private String[] makeAReservation(Scanner input){
        String[] display_lines;
        ReservationDetails reserv_details=getReservationDetails(input);
        //check to see if there's an account with the credit card
        String creditCardNumber=reserv_details.creditCardNumber();
        try{
            Account account=SystemInterface.findAccount(creditCardNumber);
        }
        catch(CreditCardNumberNotFoundException e){
            // create a new account for that credit card number
            System.out.println("\tNo account was found with that credit card number. Create a new account:");
            SystemInterface.createAccount(creditCardNumber);
        }
        display_lines=SystemInterface.makeReservation(reserv_details);
        return display_lines;
    }

    private String[] cancelAReservation(Scanner input){
        String creditcard_num=getCreditCardNum(input);
        String vin=getVIN(input);
        return SystemInterface.cancelReservation(creditcard_num, vin);
    }

    private String[] viewCorporateAccount(Scanner input){
        String[] display_lines;
        String creditcard_num=getCreditCardNum(input);
        display_lines=SystemInterface.getAccount(creditcard_num);
        return display_lines;
    }

    private String[] processReturnedVehicle(Scanner input){
        String[] display_lines;
        String creditcard_num=getCreditCardNum(input);
        String vin=getVIN(input);
        int num_days_used=promptUser.promptUserForInt("For how many days was the vehicle used?", true);
        int num_miles_driven=promptUser.promptUserForInt("How many miles were driven?", true);
        display_lines=SystemInterface.processReturnedVehicle(vin,
                num_days_used, num_miles_driven);
        return display_lines;
    }
}
