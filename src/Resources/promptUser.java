package Resources;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Mark Angelot
 * @date February 13, 2020
 */
public class promptUser{    
    /**
     * Displays a message and prompts the user for a response
     * @param message the message to be printed to the screen
     * @return the user's response
     * @since February 13, 2020
     */
    public static String promptUser(String message){
        Scanner input=new Scanner(System.in);
        System.out.print(message+"\n\t");
        return input.nextLine();
    }
    /**
     * Displays a message and prompts the user for a response
     * @param message the message to be printed to the screen
     * @return the user's response<br>Note: if the user's response is longer than one
     *                        character, this method will return the first character of
     *                        the user's response
     * @since March 16, 2020
     */
    public static char promptUserForChar(String message){
        return promptUser(message).charAt(0);
    }
    /**
     * Displays a message and prompts the user for a response
     * @param message the message to be printed to the screen
     * @param repeatIfInvalid
     * @param acceptableResponses
     * @return the user's response<br>Note: if the user's response is longer than one
     *         character, this method will return the first character of the user's
     *         user's response
     * @since May 14, 2020
     */
    public static char promptUserForChar(String message,
                                       boolean repeatIfInvalid,
                                       char[] acceptableResponses){
        boolean responseIsNotInArray=true;
        char response='0';
        while(responseIsNotInArray){
            response=promptUser(message).charAt(0);
            for(int i:acceptableResponses){
                if(i==response){
                    responseIsNotInArray=false;
                    break;
                }
            }
            if(responseIsNotInArray){
                if(repeatIfInvalid){
                    System.err.println("Acceptable inputs are "+
                            listifyChars(acceptableResponses,"and"));
                }
                else{
                    throw new IllegalArgumentException("Response "+
                            response+" was not found in the array");
                }
            }
        }
        return response;
    }
    /**
     * Displays a message and prompts the user for a response
     * @param message the message to be printed to the screen
     * @param repeatIfInvalid if the parameter is true, the method will reprompt the user for
     *                        a response if the response is invalid; otherwise, it'll throw
     *                        an IllegalArgumentException
     * @return the user's response as a double
     * @throws IllegalArgumentException if the user's input is invalid and {@code repeatIfInvalid}
     *         is true
     * @since February 13, 2020
     */
    public static double promptUserForDouble(String message, boolean repeatIfInvalid){
        String response;
        boolean isInvalid=true;
        double output=-1;
        
        while(isInvalid){
            response=promptUser(message);
            try{
                output=Double.parseDouble(response);
                isInvalid=false;
            }
            catch(NumberFormatException e){
                if(!repeatIfInvalid){
                    throw new IllegalArgumentException();
                }
                else{
                    System.err.println("Error: invalid input");
                }
            }
        }
        return output;
    }
    /**
     * Displays a message and prompts the user for a response
     * @param message the message to be printed to the screen
     * @param repeatIfInvalid if the parameter is true, the method will reprompt the user for
     *                        a response if the response is invalid; otherwise, it'll throw
     *                        an IllegalArgumentException
     * @return the user's response as an int
     * @throws IllegalArgumentException if the user's input is invalid and {@code repeatIfInvalid}
     *         is true
     * @since February 13, 2020
     */
    public static int promptUserForInt(String message, boolean repeatIfInvalid){
        String response;
        boolean isInvalid=true;
        int output=-1;
        
        while(isInvalid){
            response=promptUser(message);
            try{
                output=Integer.parseInt(response);
                isInvalid=false;
            }
            catch(NumberFormatException e){
                if(!repeatIfInvalid){
                    throw new IllegalArgumentException();
                }
                else{
                    System.err.println("Error: invalid input");
                }
            }
        }
        return output;
    }
    /**
     * Displays a message and prompts the user for a response
     * @param message the message to be printed to the screen
     * @param repeatIfInvalid if the parameter is true, the method will reprompt
     *          the user for a response if the response is invalid; otherwise,
     *          it'll throw an {@link IllegalArgumentException}
     * @param acceptableResponses an array of acceptable responses. If the user
     *          enters a repose that isn't in the array, the method will either
     *          reprompt the user if {@code repeatIfInvalid} is true, or throw
     *          an {@link IllegalArgumentException} if {@code repeatIfInvalid}
     *          is false
     * @return the user's response as an int
     * @throws IllegalArgumentException if the user's input is invalid and
     *         {@code repeatIfInvalid} is true
     * @since May 11, 2020
     */
    public static int promptUserForInt(String message,
                                       boolean repeatIfInvalid,
                                       int[] acceptableResponses){
        boolean responseIsNotInArray=true;
        int response=0;
        while(responseIsNotInArray){
            response=promptUserForInt(message,repeatIfInvalid);
            for(int i:acceptableResponses){
                if(i==response){
                    responseIsNotInArray=false;
                    break;
                }
            }
            if(responseIsNotInArray){
                if(repeatIfInvalid){
                    System.err.println("Acceptable inputs are "+
                            listifyInts(acceptableResponses,"and"));
                }
                else{
                    throw new IllegalArgumentException("Response "+
                            response+" was not found in the array");
                }
            }
        }
        return response;
    }
    /**
     * Prompts the user for a character to represent a yes/no response and
     * returns the boolean value that corresponds to the response
     * @param message the message to be printed to the screen
     * @param yesResponses an array of responses that will count as a "yes"
     * @param noResponses an array of responses that will count as a "no"
     * @return true if the user entered a "yes" response, and false if the user
     *      entered a "no" response
     */
    public static boolean promptUserForBoolean(String message,
                                               char[] yesResponses,
                                               char[] noResponses){
        int fullLength=(yesResponses.length+noResponses.length);
        char[] validInputs=new char[fullLength];
        
        for(int i=0;i<fullLength;i++){
            if(i<yesResponses.length){
                validInputs[i]=yesResponses[i];
            }
            else{
                validInputs[i]=noResponses[(i-yesResponses.length)];
            }
        }
        
        char input=promptUserForChar(message+
                    "\n For yes, enter "+listifyChars(yesResponses,"or")+
                    "\n For no, enter  "+listifyChars(noResponses,"or"),
                true,
                validInputs);
        
        String yes=new String(yesResponses);
        String no=new String(noResponses);
        
        if(yes.indexOf(input)!=-1){
            return true;
        }
        else if(no.indexOf(input)!=-1){
            return false;
        }
        
        return false;
    }

    /**
     * Creates and returns a String that is a list of the elements in an array
     * <br>
     * Example:<br>
     * {@code listify(new char[]{'a','e','i','o','u','y'},"and")} returns "a, e,
     * i, o, u, and y"
     * @param characters the characters to be in the list
     * @param conjunction the conjunction to go before the last element
     * @return a String that is a list of the elements in an array
     */
    private static String listifyChars(char[] characters, String conjunction){
        if(characters.length==0){
            return "";
        }
        if(characters.length==1){
            return new String(characters);
        }
        if(characters.length==2){
            return characters[0]+" "+conjunction+" "+characters[1];
        }
        String concatenation="";
        for(int i=0;i<(characters.length-1);i++){
            concatenation+=characters[i]+", ";
        }
        concatenation+=conjunction+" "+characters[(characters.length-1)];
        return concatenation;
    }

    /**
     * Creates and returns a String that is a list of the elements in an array
     * <br>
     * Example:<br>
     * {@code listify(new char[]{1,2,3,5,7,11},"and")} returns "1, 2,
     * 3, 5, 7, and 11"
     * @param numbers the characters to be in the list
     * @param conjunction the conjunction to go before the last element
     * @return a String that is a list of the elements in an array
     */
    private static String listifyInts(int[] numbers, String conjunction){
        if(numbers.length==0){
            return "";
        }
        if(numbers.length==1){
            return ""+numbers[0];
        }
        String concatenation="";
        for(int i=0;i<(numbers.length-1);i++){
            concatenation+=numbers[i]+", ";
        }
        concatenation+=conjunction+" "+numbers[(numbers.length-1)];
        return concatenation;
    }
}
