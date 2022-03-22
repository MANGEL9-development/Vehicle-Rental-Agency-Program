/**
 * Mark Angelot
 * May 6, 2020
 */

package testDrivers;

import Aggregators.Transactions;
import OtherClasses.TimeSpan;
import OtherClasses.Transaction;

public class c_TransactionsClass{
    private final static Transactions TRANSACTIONS_LIST=new Transactions();

    public static void main(String[] args){
        System.out.println("Adding transactions...");
        addExampleTransactions();
        
        System.out.println("Transactions list:");
        displayTransactions();
    }
    
    private static void displayTransactions(){
        TRANSACTIONS_LIST.reset();
        while(TRANSACTIONS_LIST.hasNext()){
            System.out.println("\t"+TRANSACTIONS_LIST.getNext());
        }
    }

    private static void addExampleTransactions(){
        TRANSACTIONS_LIST.addTransaction(new Transaction("3212546453245879",
                                         "Baltimore Environmental Company",
                                         "3",
                                         new TimeSpan('D',1),
                                         "$321.25"));
        TRANSACTIONS_LIST.addTransaction(new Transaction("1234567890123456",
                                         "Zoom Video Communications",
                                         "2",
                                         new TimeSpan('W',2),
                                         "$123.45"));
        TRANSACTIONS_LIST.addTransaction(new Transaction("2345678901234567",
                                         "Lysol Sanitation",
                                         "1",
                                         new TimeSpan('M',3),
                                         "$234.56"));
        TRANSACTIONS_LIST.addTransaction(new Transaction("3456789012345678",
                                         "Bounty Paper Towels and Napkins",
                                         "2",
                                         new TimeSpan('D',4),
                                         "$345.67"));
        TRANSACTIONS_LIST.addTransaction(new Transaction("4567890123456789",
                                         "Costco Wholesale",
                                         "3",
                                         new TimeSpan('W',5),
                                         "$456.78"));
    }
}
