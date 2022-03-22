package Exceptions;

/**
 * Thrown when a {@link Vehicles.Vehicle Vehicle} with a certain VIN already exists
 * @author Mark Angelot
 * @date April 2, 2020
 */
public class VINAlreadyExistsException extends Exception{
    public String vin;
    public VINAlreadyExistsException(String VIN){
        vin=VIN;
    }
}
