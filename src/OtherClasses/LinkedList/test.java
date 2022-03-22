package OtherClasses.LinkedList;

import Vehicles.Car;
import Vehicles.SUV;
import Vehicles.Truck;
import Vehicles.Vehicle;

/**
 * @author Mark Angelot
 * @date April 9, 2020
 */
public class test{
    public static void main(String[] args){
        final VehicleList VEHICLES_LIST=new VehicleList();
        VEHICLES_LIST.append(new Car("Chevrolet Camaro - 2018",30,2,"HK4GM4564GD"));
        VEHICLES_LIST.append(new Car("Ford Fusion - 2018",34,4,"AB4EG5689GM"));
        VEHICLES_LIST.append(new Car("Ford Fusion Hybrid - 2017",32,4,"KU4EG3245RW"));
        VEHICLES_LIST.append(new Car("Chevrolet Impala - 2019",30,4,"RK3BM4356YH"));

        VEHICLES_LIST.append(new SUV("Honda Odyssey - 2020",28,7,6,"VM9RE2635TD"));
        VEHICLES_LIST.append(new SUV("Dodge Caravan - 2019",25,5,4,"QK3FL4273ME"));
        VEHICLES_LIST.append(new SUV("Ford Expedition - 2018",20,5,3,"JK2RT9264HY"));

        VEHICLES_LIST.append(new Truck("Ten-Foot",12,2810,"EJ5KU2435BC"));
        VEHICLES_LIST.append(new Truck("Eighteenteen-Foot",10,5930,"KG4DM5472RK"));
        VEHICLES_LIST.append(new Truck("Twenty-Four-Foot",8,6500,"EB2WR3082QB"));
        VEHICLES_LIST.append(new Truck("Twenty-Four-Foot",8,6500,"TV3GH4290EK"));
        
        Vehicle[] v=VEHICLES_LIST.asArray();
        for(int i=0;i<v.length;i++){
            System.out.println(VEHICLES_LIST.vehicleAtIndex(i).getDescription());
        }
    }
}
