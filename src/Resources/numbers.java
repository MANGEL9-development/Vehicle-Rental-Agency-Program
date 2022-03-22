/**
 * Mark Angelot
 * May 7, 2020
 */

package Resources;

public class numbers{
    /**
     * Rounds a number to a specified interval
     * @param number the number to be rounded
     * @param interval the interval to which the number is to be rounded
     * @return a number to the specified interval
     */
    public static double round(double number,double interval){
        interval=Math.abs(interval);
        number/=interval;
        number=Math.round(number);
        number*=interval;
        return number;
    }
}
