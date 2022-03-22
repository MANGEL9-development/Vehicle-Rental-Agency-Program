package Aggregators;

import Exceptions.CreditCardNumberNotFoundException;
import Exceptions.UnreservedVehicleException;
import Exceptions.VINAlreadyExistsException;
import Vehicles.Vehicle;
import Exceptions.VINNotFoundException;
import OtherClasses.LinkedList.LinkedList;
import Vehicles.Car;

/**
 * This class holds a collection of {@link Vehicles.Vehicle Vehicles} and has
 * methods to manipulate the list
 * @author Mark Angelot
 * @date March 15, 2020
 */
public class Vehicles  extends Aggregator<Vehicle>{
    private final LinkedList<Vehicle> agency_vehicles;// array of Vehicle objects (ArrayList type may NOT be used)
    private LinkedList<Vehicle>.Node current;

    /**
     * Constructs a Vehicles object without any {@link Vehicles.Vehicle Vehicles}
     */
    public Vehicles(){
        super();
        agency_vehicles=new LinkedList();
    }
    /**
     * Constructs a Vehicles object with one {@link Vehicles.Vehicle Vehicle}
     * @param vehicle a {@link Vehicles.Vehicle Vehicle} to which the collection
     *                will be initialized
     */
    public Vehicles(Vehicle vehicle){
        agency_vehicles=new LinkedList();
        agency_vehicles.append(vehicle);
    }
    /**
     * Constructs a Vehicles object with an array of {@link Vehicles.Vehicle Vehicles}
     * @param vehicles an array of {@link Vehicles.Vehicle Vehicles} to which
     *                 to initialize the collection
     */
    public Vehicles(Vehicle[] vehicles){
        agency_vehicles=new LinkedList();
        for(Vehicle v:vehicles){
            agency_vehicles.append(v);
        }
    }
    
    /**
     * adds a new vehicle to the collection
     * @param vehicle a {@link Vehicles.Vehicle Vehicle} that will be added to
     *        the collection
     * @throws VINAlreadyExistsException if a {@link Vehicles.Vehicle Vehicle}
     *         with a certain VIN already exists
     */
    public void add(Vehicle vehicle) throws VINAlreadyExistsException{
        /* this will try to find a vehicle that has the same VIN as the passed 
           vehicle. If a vehicle with the VIN could not be found, the program is
           good to continue */
        try{
            Vehicle temp=getVehicle(vehicle.getVIN());
            throw new VINAlreadyExistsException(temp.getVIN());
        }
        catch(VINNotFoundException e){
            agency_vehicles.append(vehicle);
        }
    }
    /**
     * Finds a {@link Vehicles.Vehicle Vehicle} with a specified VIN and
     * removes it from the collection
     * @param VIN the Vehicle Identification Number to search for
     * @return the {@link Vehicles.Vehicle Vehicle} that was removed
     * @throws VINNotFoundException if no vehicle found for provided VIN
     */
    public Vehicle remove(String VIN) throws VINNotFoundException{
        int removalIndex=-1;
        Vehicle removedVehicle=new Car("",-1,-1,"");
        for(int i=0;i<agency_vehicles.length();i++){
            Vehicle currentVehicle=agency_vehicles.elementAtIndex(i);
            if(currentVehicle.getVIN().equals(VIN)){
                removalIndex=i;
                removedVehicle=currentVehicle;
                break;
            }
        }
        if(removalIndex==-1){
            throw new VINNotFoundException();
        }
        agency_vehicles.removeAtIndex(removalIndex);
        return removedVehicle;
    }
    /**
     * Finds a {@link Vehicles.Vehicle Vehicle} with a specified VIN
     * @param VIN the Vehicle Identification Number to search for
     * @return a {@link Vehicles.Vehicle Vehicle} in this collection that has
     *         the specified VIN
     * @throws VINNotFoundException if no vehicle found for provided VIN
     */
    public Vehicle getVehicle(String VIN) throws VINNotFoundException{
        for(LinkedList<Vehicle>.Node currentNode=agency_vehicles.getHead();currentNode!=null;currentNode=currentNode.getNext()){
            if(currentNode.getValue().getVIN().equals(VIN)){
                return currentNode.getValue();
            }
        }
        throw new VINNotFoundException();
    }
    /**
     * Finds a {@link Vehicles.Vehicle Vehicle} with a reservation with a
     * specified credit card number
     * @param creditCardNumber the credit card number to search for
     * @return a {@link Vehicles.Vehicle Vehicle} in this collection that has a
     *         reservation with the specified credit card number
     * @throws CreditCardNumberNotFoundException if no {@link Vehicles.Vehicle Vehicle}
     *         was found with the passed credit card number
     */
    public Vehicle getVehicleByCreditCard(String creditCardNumber) throws CreditCardNumberNotFoundException{
        for(LinkedList<Vehicle>.Node currentNode=agency_vehicles.getHead();currentNode!=null;currentNode=currentNode.getNext()){
            Vehicle v=currentNode.getValue();
            try{
                if(v.getReservation().getCreditCardNum().equals(creditCardNumber)){
                    return v;
                }
            }
            catch(UnreservedVehicleException e){
                //do nothing
            }
        }
        throw new CreditCardNumberNotFoundException();
    }
    
    /**
     * resets to first vehicle in list
     */
    @Override
    public void reset(){
        current=agency_vehicles.getHead();
    }
    /**
     * @return true if more vehicles in list to retrieve
     */
    @Override
    public boolean hasNext(){
        return !current.isLastNode();
    }
    /**
     * @return next vehicle in list
     * @throws IndexOutOfBoundsException if the iterator is at the end of the
     *         list
     */
    @Override
    public Vehicle getNext(){
        if(current==null){
            throw new IndexOutOfBoundsException("No more vehicles");
        }
        LinkedList<Vehicle>.Node returnedNode=current;
        current=current.getNext();
        return returnedNode.getValue();
    }
    
    /**
     * @return how many vehicles are in this collection
     */
    @Override
    public int length(){
        return agency_vehicles.length();
    }
    
    /**
     * @return this collection as an array of vehicles
     */
    public Vehicle[] getArray(){
        Object[] array=agency_vehicles.asArray();
        Vehicle[] vehicles=new Vehicle[array.length];
        for(int i=0;i<array.length;i++){
            vehicles[i]=(Vehicle)array[i];
        }
        return vehicles;
    }
}
