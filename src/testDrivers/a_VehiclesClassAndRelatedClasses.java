/**
 * Mark Angelot
 * May 6, 2020
 */

package testDrivers;


import Aggregators.Vehicles;
import Exceptions.ReservedVehiclesException;
import Exceptions.VINAlreadyExistsException;
import Exceptions.VINNotFoundException;
import OtherClasses.Reservation;
import OtherClasses.TimeSpan;
import Vehicles.Car;
import Vehicles.SUV;
import Vehicles.Truck;
import Vehicles.Vehicle;

public class a_VehiclesClassAndRelatedClasses{
    private static final Vehicles VEHICLES_LIST=new Vehicles();
    public static void main(String[] args){
        fillList();
        
        System.out.println("This is the list of vehicles: ");
        displayVehicles();
        
        System.out.println("\nAdding new vehicles to the list...");
        addExampleVehicles();
        
        System.out.println("\nNew list of vehicles:");
        displayVehicles();
        
        System.out.println("\nRemoving some vehicles from the list...");
        removeSelectVehicles();
        
        System.out.println("\nNew list of vehicles:");
        displayVehicles();
        
        System.out.println("\nReserving a vehicle...");
        Reservation exampleReservation=addExampleReservation();
        
        System.out.println("\nReservation details:  "+exampleReservation);
    }
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
            
        }
    }
    
    private static void displayVehicles(){
        VEHICLES_LIST.reset();
        while(VEHICLES_LIST.hasNext()){
            System.out.println("\t"+VEHICLES_LIST.getNext());
        }
    }

    private static void addExampleVehicles(){
        try{
            VEHICLES_LIST.add(new Car("Ford Crown Victoria - 2007",40,5,"A1B2C3D4E5F"));
            VEHICLES_LIST.add(new SUV("Kia Soul - 2012",30,5,19,"A1B2C3D4E5F"));
            VEHICLES_LIST.add(new Truck("Tesla Cybertruck - 2019",0,3500,"A1B2C3D4E5F"));
        }
        catch(VINAlreadyExistsException e){
            
        }
    }

    private static void removeSelectVehicles(){
        try{
            VEHICLES_LIST.remove("AB4EG5689GM");
            VEHICLES_LIST.remove("RK3BM4356YH");
            VEHICLES_LIST.remove("QK3FL4273ME");
            VEHICLES_LIST.remove("EJ5KU2435BC");
            VEHICLES_LIST.remove("EB2WR3082QB");
            VEHICLES_LIST.remove("A1B2C3D4E5F");
        }
        catch(VINNotFoundException e){
            
        }
    }

    private static Reservation addExampleReservation(){
        Vehicle reservedVehicle;
        TimeSpan rentalPeriod=new TimeSpan('D',4);
        try{
            reservedVehicle=VEHICLES_LIST.getVehicle("KG4DM5472RK");
        }
        catch(VINNotFoundException ex){
            return null;
        }
        Reservation r=new Reservation("1920314253647586",
                                      3,
                                      reservedVehicle.getDescription(),
                                      rentalPeriod,
                                      true);
        try{
            reservedVehicle.setReservation(r);
        }
        catch(ReservedVehiclesException ex){
            
        }
        return r;
    }
}
