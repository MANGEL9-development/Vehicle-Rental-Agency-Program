package OtherClasses.LinkedList;

import Vehicles.Vehicle;

/**
 * @author Mark Angelot
 * @date April 9, 2020
 * @deprecated use {@link OtherClasses.LinkedList.LinkedList.Node LinkedList.Node} instead
 */
@Deprecated
public class VehicleNode{
    private final Vehicle value;
    private VehicleNode previous;
    private VehicleNode next;
    
    /**
     * Constructs a head VehicleNode
     * @param vehicle the {@link Vehicles.Vehicle Vehicle} set as this
     *        VehicleNode's value
     */
    public VehicleNode(Vehicle vehicle){
        //for head
        value=vehicle;
        previous=null;
        next=null;
    }
    /**
     * Constructs a VehicleNode
     * @param vehicle the {@link Vehicles.Vehicle Vehicle} to be set as this
     *        VehicleNode's value
     * @param previous the VehicleNode that comes before this VehicleNode in a
     *        {@link VehicleList}
     */
    public VehicleNode(Vehicle vehicle,VehicleNode previous){
        //for not head
        value=vehicle;
        this.previous=previous;
        next=null;
    }
    
    /**
     * @return this VehicleNode's value
     */
    public Vehicle getValue(){
        return value;
    }
    /**
     * @return the VehicleNode that comes before this VehicleNode in a
     *         {@link VehicleList}
     */
    public VehicleNode getPrevious(){
        return previous;
    }
    /**
     * @return the VehicleNode that comes after this VehicleNode in a
     *         {@link VehicleList}
     */
    public VehicleNode getNext(){
        return next;
    }
    
    /**
     * Sets which VehicleNode comes before this one
     * @param previous the VehicleNode that will be set as this VehicleNode's
     *        previous
     */
    public void setPrevious(VehicleNode previous){
        this.previous=previous;
    }
    /**
     * Sets which VehicleNode comes after this one
     * @param next the VehicleNode that will be set as this VehicleNode's
     *        next
     */
    public void setNext(VehicleNode next){
        this.next=next;
    }
    
    /**
     * @return whether or not this is the head node of a {@link VehicleList}
     */
    public boolean isHeadNode(){
        return (previous==null);
    }
    /**
     * @return whether or not this is neither the head node nor the last node of
     *         a {@link VehicleList}
     */
    public boolean isMiddleNode(){
        return (previous!=null && next!=null);
    }
    /**
     * @return whether or not this is the last node of a {@link VehicleList}
     */
    public boolean isLastNode(){
        return (next==null);
    }
    
}
