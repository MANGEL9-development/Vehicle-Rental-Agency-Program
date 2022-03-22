package Aggregators;

import OtherClasses.LinkedList.LinkedList;
import OtherClasses.Transaction;

/**
 * This class holds a collection of {@link OtherClasses.Transaction
 * Transactions} and has methods to manipulate the list
 * @author Mark Angelot
 * @date April 24, 2020
 */
public class Transactions extends Aggregator<Transaction>{
    
    /**
     * Constructs an empty aggregation of Transactions
     */
    public Transactions(){
        super();
    }
    
    /**
     * Adds a transaction to this Transactions object
     * @param transaction the transaction that will be added
     */
    public void addTransaction(Transaction transaction){
        super.addElement(transaction);
    }
}
