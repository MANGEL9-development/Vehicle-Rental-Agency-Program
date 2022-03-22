package OtherClasses.LinkedList;

import Vehicles.Vehicle;

/**
 * @author Mark Angelot
 * @date April 9, 2020
 * @deprecated use {@link OtherClasses.LinkedList.LinkedList LinkedList} instead
 */
@Deprecated
public class VehicleList{
    /**
     * This holds the first node of this VehicleList. Every subsequent
     * element is found by searching from this node with the use of its
     * {@code getNext()} method
     */
    VehicleNode head;
    
    /**
     * Constructs an empty VehicleList
     */
    public VehicleList(){
        head=null;
    }
    
    /**
     * @return how many {@link VehicleNode VehicleNodes} are in this VehicleList
     */
    public int length(){
        if(head==null){
            return 0;
        }
        int l=0;
        for(VehicleNode i=head;i!=null;i=i.getNext()){
            l++;
        }
        return l;
    }
    
    /**
     * @return whether or not this VehicleList is empty (meaning that the head
     *         is null)
     */
    public boolean isEmpty(){
        return (head==null);
    }
    
    /**
     * @return the head node in this VehicleList
     */
    public VehicleNode getHead(){
        return head;
    }
    /**
     * @return the last node in this VehicleList
     */
    public VehicleNode findLastNode(){
        if(head==null){
            System.err.println("\tnull");
            return null;
        }
        VehicleNode currentNode=head;
        while(true){
            if(currentNode.isLastNode()){
                return currentNode;
            }
            currentNode=currentNode.getNext();
        }
    }
    
    /**
     * Adds a {@link VehicleNode} to the end of this VehicleList
     * @param newVehicle the {@link VehicleNode} to be added to the end of this
     *        VehicleList
     */
    public void append(Vehicle newVehicle){
        if(head==null){
            head=new VehicleNode(newVehicle);
        }
        else{
            VehicleNode lastNode=findLastNode();
            lastNode.setNext(new VehicleNode(newVehicle,lastNode));
        }
    }
    /**
     * Adds a {@link VehicleNode} to the beginning of this VehicleList
     * @param newVehicle the {@link VehicleNode} to be added to the beginning of
     *        this VehicleList
     */
    public void prepend(Vehicle newVehicle){
        if(head==null){
            head=new VehicleNode(newVehicle);
        }
        head.setPrevious(new VehicleNode(newVehicle));
        head=head.getPrevious();
    }
    
    /**
     * Removes the {@link VehicleNode} at the specified index
     * @param index the index at which a {@link VehicleNode} will be removed
     * @throws IndexOutOfBoundsException if index exceeds the length of this
     *         VehicleList
     */
    public void removeAtIndex(int index){
        if(index>=length()){
            throw new IndexOutOfBoundsException("Index "+index+" out of bounds for length "+length());
        }
        if(index==0){
            if(head.getNext()!=null){
                head.getNext().setPrevious(null);
                head=head.getNext();
            }
            else{
                head=null;
            }
        }
        else{
            VehicleNode removedVehicle=vehicleNodeAtIndex(index);
            removedVehicle.getPrevious().setNext(removedVehicle.getNext());
        }
    }
    
    /**
     * Empties this VehicleList by setting the head node to null
     */
    public void clearList(){
        head=null;
    }
    
    /**
     * @return this VehicleList in the form of an array
     */
    public Vehicle[] asArray(){
        if(head==null){
            return new Vehicle[0];
        }
        Vehicle[] array=new Vehicle[length()];
        int i=0;
        for(VehicleNode current=head;i<length();current=current.getNext(),i++){
            array[i]=current.getValue();
        }
        return array;
    }
    
    /**
     * Finds the {@link Vehicles.Vehicle Vehicle} at the specified index
     * @param index the index at which to return a {@link Vehicles.Vehicle Vehicle}
     * @return the {@link Vehicles.Vehicle Vehicle} at the specified index
     */
    public Vehicle vehicleAtIndex(int index){
        return vehicleNodeAtIndex(index).getValue();
    }
    /**
     * Finds the {@link VehicleNode} at the specified index
     * @param index the index at which to return a {@link VehicleNode}
     * @return the {@link VehicleNode} at the specified index
     */
    public VehicleNode vehicleNodeAtIndex(int index){
        if(head==null){
            throw new IndexOutOfBoundsException("Index "+index+" out of bounds for length 0");
        }
        VehicleNode v=head;
        for(int i=0;i<index;i++){
            if(v.isLastNode()){
                throw new IndexOutOfBoundsException("Index "+index+" out of bounds for length "+i);
            }
            v=v.getNext();
        }
        return v;
    }
}
