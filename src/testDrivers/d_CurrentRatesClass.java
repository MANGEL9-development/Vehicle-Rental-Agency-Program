/**
 * Mark Angelot
 * May 6, 2020
 */

package testDrivers;

import Aggregators.Transactions;
import Exceptions.ReservedVehiclesException;
import Exceptions.UnreservedVehicleException;
import OtherClasses.Account;
import OtherClasses.Reservation;
import OtherClasses.TimeSpan;
import OtherClasses.Transaction;
import Rates.CarRates;
import Rates.CurrentRates;
import Rates.SUVRates;
import Rates.TruckRates;
import Resources.numbers;
import Vehicles.SUV;


public class d_CurrentRatesClass{
    private static CurrentRates RATES;
    private static Transactions transactions;

    public static void main(String[] args){
        setup();
        
        System.out.println("Current Rates:");
        displayRates();
        
        System.out.println("\nCreating a hypothetical scenario");
        createHypotheticalScenario();
        
        System.out.println("\nUpdating Rates...");
        updateRates();
        
        System.out.println("\nNew Current Rates:");
        displayRates();
        
        System.out.println("\nContinuing the hypothetical scenario");
        continueHypotheticalScenario();
    }

    /**
     * used to initialize fields that will be used throughout the program
     */
    public static void setup(){
        RATES=new CurrentRates(new CarRates(24.95, 159.95, 514.95, 0.15, 14.95),
                               new SUVRates(29.95, 189.95, 679.95, 0.15, 14.95),
                             new TruckRates(35.95, 224.95, 787.95, 0.26, 22.95));
        transactions=new Transactions();
    }

    private static void displayRates(){
        System.out.println("\tCar Rates:"+
                "\n\t\tDaily: $"+RATES.getCarRates().getDailyRate()+
                "\n\t\tDaily (with insurance): $"+RATES.getCarRates().dailyInsurRate()+
                "\n\t\tWeekly: $"+RATES.getCarRates().weeklyRate()+
                "\n\t\tMonthly: $"+RATES.getCarRates().monthlyRate()+
                "\n\t\tMileage Charge: $"+RATES.getCarRates().mileageCharge());
        System.out.println("\tSUV Rates:"+
                "\n\t\tDaily: $"+RATES.getSUVRates().getDailyRate()+
                "\n\t\tDaily (with insurance): $"+RATES.getSUVRates().dailyInsurRate()+
                "\n\t\tWeekly: $"+RATES.getSUVRates().weeklyRate()+
                "\n\t\tMonthly: $"+RATES.getSUVRates().monthlyRate()+
                "\n\t\tMileage Charge: $"+RATES.getSUVRates().mileageCharge());
        System.out.println("\tTruck Rates:"+
                "\n\t\tDaily: $"+RATES.getTruckRates().getDailyRate()+
                "\n\t\tDaily (with insurance): $"+RATES.getTruckRates().dailyInsurRate()+
                "\n\t\tWeekly: $"+RATES.getTruckRates().weeklyRate()+
                "\n\t\tMonthly: $"+RATES.getTruckRates().monthlyRate()+
                "\n\t\tMileage Charge: $"+RATES.getTruckRates().mileageCharge());
    }

    private static void updateRates(){
        RATES.updateRates(new CarRates(numbers.round(randomNumber( 15.00, 30.00),0.01),
                                       numbers.round(randomNumber(120.00,150.00),0.01),
                                       numbers.round(randomNumber(500.00,600.00),0.01),
                                       numbers.round(randomNumber(  0.05,  0.20),0.01),
                                       numbers.round(randomNumber( 10.00, 20.00),0.01)));
        RATES.updateRates(new SUVRates(numbers.round(randomNumber( 15.00, 30.00),0.01),
                                       numbers.round(randomNumber(120.00,150.00),0.01),
                                       numbers.round(randomNumber(500.00,600.00),0.01),
                                       numbers.round(randomNumber(  0.05,  0.20),0.01),
                                       numbers.round(randomNumber( 10.00, 20.00),0.01)));
      RATES.updateRates(new TruckRates(numbers.round(randomNumber( 15.00, 30.00),0.01),
                                       numbers.round(randomNumber(120.00,150.00),0.01),
                                       numbers.round(randomNumber(500.00,600.00),0.01),
                                       numbers.round(randomNumber(  0.05,  0.20),0.01),
                                       numbers.round(randomNumber( 10.00, 20.00),0.01)));
    }

    /**
     * Generates a random number within a range
     * @param min the smallest possible number
     * @param max the largest possible number
     * @return a random number between min and max
     */
    private static double randomNumber(double min, double max){
        double range=(max-min);
        double randomNumber=(Math.random()*range)+min;
        randomNumber=numbers.round(randomNumber,0.01);
        return randomNumber;
    }

    private static Account exampleAccount;
    private static String description;
    private static SUV reservedVehicle;
    private static TimeSpan rentalPeriod;
    private static Reservation exampleReservation;
    
    private static void createHypotheticalScenario(){
        exampleAccount=new Account("3212546453245879",
                                   "Baltimore Environmental Company",
                                   true);
        description="Honda Odyssey - 2020";
        reservedVehicle=new SUV(description,28,7,6,"VM9RE2635TD");
        rentalPeriod=new TimeSpan('W',7);
        int expectedMiles=199;
        
        System.out.println("\t"+exampleAccount.getCompanyName()+" is reserving a vehicle:");
        
        
        System.out.println("\t\tVehicle: "+description);
        System.out.println("\t\tTime Period: "+rentalPeriod);
        
        exampleReservation=new Reservation(description,
                                           2,
                                           description,
                                           rentalPeriod,
                                           false);
        
        System.out.println("\t\tInsurance Selection: "+((exampleReservation.insuranceSelected())?"Yes":"No"));
        
        try{
            reservedVehicle.setReservation(exampleReservation);
        }
        catch(ReservedVehiclesException e){
            
        }
        double estimatedCost=RATES.calcEstimatedCost(2,rentalPeriod,expectedMiles,true,false);
        reservedVehicle.setQuotedRates(RATES.getSUVRates());
        
        System.out.println("\tEstimated Cost (driving "+expectedMiles+" miles): $"+estimatedCost);
    }

    private static void continueHypotheticalScenario(){
        int milesDriven=201;
        TimeSpan timeUsed=new TimeSpan('D',57);
        double cost=RATES.calcActualCost(reservedVehicle.getQuotedRates(),
                                         timeUsed.days(),
                                         milesDriven,
                                         exampleReservation.insuranceSelected(),
                                         exampleAccount.primeCustomer());


        System.out.println("\t"+exampleAccount.getCompanyName()+" is returning the "+description);
        System.out.println("\t\tVehicle was used for "+timeUsed);
        System.out.println("\t\tMiles Driven: "+milesDriven);
        System.out.println("\tCost: $"+cost);
        
        Transaction thisTransaction;
        try{
            thisTransaction=new Transaction(reservedVehicle,
                    exampleAccount,
                    cost);
        }
        catch(UnreservedVehicleException e){
            return;
        }
        
        transactions.addTransaction(thisTransaction);
        
        System.out.println("\n\tTransaction Details: "+thisTransaction);
    }
}
