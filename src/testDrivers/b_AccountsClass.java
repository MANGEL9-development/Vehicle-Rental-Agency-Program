/**
 * Mark Angelot
 * May 6, 2020
 */

package testDrivers;

import Aggregators.Accounts;
import OtherClasses.Account;

public class b_AccountsClass{
    private static final Accounts ACCOUNTS_LIST=new Accounts();
    
    public static void main(String[] args){
        fillList();
        
        System.out.println("This is the list of accounts: ");
        displayAccounts();
        
        System.out.println("\nAdding a new account to the list...");
        addExampleAccount();
        
        System.out.println("\nNew list of accounts:");
        displayAccounts();
    }
    
    private static void fillList(){
        ACCOUNTS_LIST.addAccount(new Account("3212546453245879",
                                             "Baltimore Environmental Company",
                                             true));
        ACCOUNTS_LIST.addAccount(new Account("1234567890123456",
                                             "Zoom Video Communications",
                                             false));
        ACCOUNTS_LIST.addAccount(new Account("2345678901234567",
                                             "Lysol Sanitation",
                                             true));
        ACCOUNTS_LIST.addAccount(new Account("3456789012345678",
                                             "Bounty Paper Towels and Napkins",
                                             false));
        ACCOUNTS_LIST.addAccount(new Account("4567890123456789",
                                             "Costco Wholesale",
                                             true));
    }
    
    private static void displayAccounts(){
        ACCOUNTS_LIST.reset();
        while(ACCOUNTS_LIST.hasNext()){
            System.out.println("\t"+ACCOUNTS_LIST.getNext());
        }
    }

    private static void addExampleAccount(){
        ACCOUNTS_LIST.addAccount(new Account("5678901234567890",
                                             "Amazon.com, Inc.",
                                             false));
    }
}
