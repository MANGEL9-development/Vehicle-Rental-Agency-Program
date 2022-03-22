package Aggregators;

import Exceptions.CreditCardNumberNotFoundException;
import OtherClasses.Account;
import OtherClasses.LinkedList.LinkedList;

/**
 * Th
 * @author Mark Angelot
 * @date April 21, 2020
 */
public class Accounts extends Aggregator<Account>{
    
    /**
     * Constructs an empty aggregation of Accounts
     */
    public Accounts(){
        super();
    }
    
    /**
     * Looks through this list of accounts and finds an account with the
     * specified credit card number
     * @param creditCardNumber the credit card number to look for
     * @return the account with the specified credit card number
     * @throws CreditCardNumberNotFoundException if no account was found with
     *         the specified credit card number
     */
    public Account getAccountByCreditCardNumber(String creditCardNumber) throws CreditCardNumberNotFoundException{
        for(LinkedList<Account>.Node i=super.list.getHead();i!=null;i=i.getNext()){
            if(i.getValue().getCreditCardNum().equals(creditCardNumber)){
                return i.getValue();
            }
        }
        throw new CreditCardNumberNotFoundException();
    }
    
    /**
     * Adds an account to this Accounts object
     * @param account the account that will be added
     */
    public void addAccount(Account account){
        super.addElement(account);
    }
}
