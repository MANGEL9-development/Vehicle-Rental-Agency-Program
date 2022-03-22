/**
 * Mark Angelot
 * May 11, 2020
 */

import Resources.promptUser;

public class prompting{
    public static void main(String[] args){
        int x=promptUser.promptUserForInt("Enter a number from 1-10", true, new int[]{1,2,3,4,5,6,7,8,9,10});
        System.out.println(x);
    }
}
