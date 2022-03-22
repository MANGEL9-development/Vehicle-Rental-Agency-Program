
import java.util.Arrays;

/**
 * Mark Angelot
 * May 18, 2020
 */

public class arrayTypes{
    public static void main(String[] args){
        Object[] array={"1","2","3"};
        String[] strings=new String[array.length];
        for(int i=0;i<array.length;i++){
            strings[i]=(String)array[i];
        }
        System.out.print(Arrays.toString(strings));
    }
}
