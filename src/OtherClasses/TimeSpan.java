package OtherClasses;

import Resources.promptUser;

/**
 * This class represents a span of time (primarily to be used in a {@link Reservation}
 * @author Mark Angelot
 * @date March 15, 2020
 */
public class TimeSpan{
    private final char timeUnit;  // ‘D’ (day), ‘W’ (week), ‘M’ (month)
    private final int numUnits;   // num of days, weeks or months

    /**
     * Constructs a TimeSpan with a specified unit of time and duration
     * @param units a unit of time ('D' for day, 'W' for week, and ‘M’ for month)
     * @param time the duration of time; i.e. if t is 'W' and n is 3, this TimeSpan would
     *          be for 3 weeks
     */
    public TimeSpan(char units,int time){
        units=Character.toUpperCase(units);
        if((units!='D' && units!='W' && units!='M')){
            throw new IllegalArgumentException("Expected 'd', 'D', 'm', 'M', 'w', or 'W'. Found "+units);
        }
        if(time<0){
            throw new IllegalArgumentException("Time cannot be negative");
        }
        
        timeUnit=units;
        numUnits=time;
    }
    
    /**
     * @return the character that represents the unit used in this TimeSpan (where 'D' is
     *         for day, 'W' is for week, and ‘M’ is for month)
     */
    public char getTimeUnit(){
        return timeUnit;
    }
    /**
     * @return the number of units for which this TimeSpan lasts (e.g. if the TimeSpan is
     *         for 8 days, this will return 8
     */
    public int getNumUnits(){
        return numUnits;
    }
    
    /**
     * Returns a String that represents this TimeSpan (e.g. 5 months, 1 week, etc.)
     * @return a String representation of this TimeSpan
     */
    @Override
    public String toString(){
        return numUnits+" "+englishize(timeUnit,numUnits);
    }

    public String asString(){
        return timeUnit+""+numUnits;
    }

    public int days(){
        switch(timeUnit){
            case 'D':
                return numUnits;
            case 'W':
                return (numUnits*7);
            case 'M':
                return (numUnits*30);
        }
        return numUnits;
    }
    
    /**
     * Uses methods from {@link Resources.promptUser} to prompt the user for
     * information with which to make a TimeSpan object. First, the user will
     * be prompted to enter which units the TimeSpan is for. Then the user will
     * be prompted to enter how many units the TimeSpan is for.<br><br>
     * Example execution (+ = output, - = input):<br>
     * <blockquote>
     *  +Enter the units the time is for ('D' for days, 'W' for weeks, or 'M' for months)<br>
     *  -D<br>
     *  +Enter how many days<br>
     *  -3<br>
     * </blockquote>
     * @return a TimeSpan object with information that was input from the user
     */
    public static TimeSpan promptUser(){
        char units=promptUser.promptUserForChar("Enter the units the time is for ('D' for days, 'W' for weeks, or 'M' for months)",
                true,
                new char[]{'D','d','W','w','M','m'});
        int numUnits=promptUser.promptUserForInt("Enter how many "+englishize(units,2), true);
        return new TimeSpan(units,numUnits);
    }
    
    /**
     * Uses grammar rules of plurality to return a word that represents the
     * time unit. For example, {@code englishize('W',5)} would return "weeks",
     * and {@code englishize('m',1)} would return "month"
     * @param units a unit of time ('D' for day, 'W' for week, and ‘M’ for month)
     * @param numUnits the duration of time; if this is not 1, then this method
     *          will return the plural form of the word
     * @return the time unit as a word
     */
    private static String englishize(char units, int numUnits){
        String unitAsWord="";
        switch(units){
            case 'D':
            case 'd':
                unitAsWord="day";
                break;
            case 'W':
            case 'w':
                unitAsWord="week";
                break;
            case 'M':
            case 'm':
                unitAsWord="month";
                break;
            default:
                throw new IllegalArgumentException();
        }
        
        return unitAsWord+((numUnits!=1)?"s":"");
    }
}
