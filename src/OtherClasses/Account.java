package OtherClasses;

/**
 * @author Mark Angelot
 * @date April 24, 2020
 */
public class Account{
    private final String creditcard_num;
    private final String company_name;
    private final boolean prime_customer;
    
    /**
     * Constructs an Account object with the specified information
     * @param creditcard_num the company's credit card number
     * @param company_name the company's name
     * @param prime_customer set this to true for a prime customer, and false
     *          for anyone else
     */
    public Account(String creditcard_num,String company_name,boolean prime_customer){
        this.creditcard_num=creditcard_num;
        this.company_name=company_name;
        this.prime_customer=prime_customer;
    }
    
    /**
     * @return the company's credit card number
     */
    public String getCreditCardNum(){
        return creditcard_num;
    }
    /**
     * @return the company's name
     */
    public String getCompanyName(){
        return company_name;
    }
    /**
     * @return whether or not the company is a prime customer
     */
    public boolean primeCustomer(){
        return prime_customer;
    }
    
    /**
     * @return a String that represents this Account, displayed as follows:
     * <blockquote>Baltimore Environmental Company (prime)</blockquote>
     */
    @Override
    public String toString(){
        String isPrime=(prime_customer)?"(prime customer)":"(not prime)";
        return company_name+" "+isPrime;
    }
}
