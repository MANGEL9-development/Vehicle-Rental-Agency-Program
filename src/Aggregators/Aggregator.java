/**
 * Mark Angelot
 * May 18, 2020
 */

package Aggregators;

import OtherClasses.LinkedList.LinkedList;

/**
 * The aggregators in this program work very similarly, so this class
 * essentially groups them together so they all fall under the same type.
 * Methods like {@link Interfaces.SystemInterface#createListFromAggregator(Aggregators.Aggregator)}
 * make use of this class and the iterator methods ({@link #hasNext()} and
 * {@link #getNext()}) that all of this class's subclasses have since they work
 * the same way in all the subclasses.<br>
 * Subclasses include {@link Aggregators.Accounts Accounts}, 
 * {@link Aggregators.Transactions Transactions}, and
 * {@link Aggregators.Vehicles Vehicles}. 
 * @param <Type> the type of elements the aggregator hold. It's typical that any
 *          subclass of this class is named by the plural of the type of element
 *          it holds. For example, an Aggregator that holds elements of type
 *          Vehicle would be called Vehicles.
 */
public abstract class Aggregator<Type>{
    protected final LinkedList<Type> list;
    private LinkedList<Type>.Node current;
    
    protected Aggregator(){
        list=new LinkedList();
    }
    
    /**
     * resets to first vehicle in list
     */
    public void reset(){
        current=list.getHead();
    }
    /**
     * @return true if more vehicles in list to retrieve
     */
    public boolean hasNext(){
        return !current.isLastNode();
    }
    /**
     * @return next vehicle in list
     * @throws IndexOutOfBoundsException if the iterator is at the end of the
     *         list
     */
    public Type getNext(){
        if(current==null){
            throw new IndexOutOfBoundsException("No more elements");
        }
        LinkedList<Type>.Node returnedNode=current;
        current=current.getNext();
        return returnedNode.getValue();
    }
    
    /**
     * @return how many vehicles are in this collection
     */
    public int length(){
        return list.length();
    }
    
    /**
     * @return true if this collection is empty, and false otherwise
     */
    public boolean isEmpty(){
        return list.isEmpty();
    }
    
    /**
     * Adds an element to the list
     * @param element the transaction that will be added
     */
    protected void addElement(Type element){
        list.append(element);
    }
}
