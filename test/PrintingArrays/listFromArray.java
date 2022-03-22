/**
 * Mark Angelot
 * May 19, 2020
 */

package PrintingArrays;

public class listFromArray{
    public static void main(String[] args){
        String[][] cities={
            {"Maryland","Salisbury","Baltimore","Ocean City"},
            {"Delaware","Dover","Georgetown","Delmar","Rehoboth Beach"},
            {"New York","New York","Albany"}
        };
        printArray(createListFromArray(cities));
    }
    private static String[] createListFromArray(Object[][] array){
        int totalLength=totalLength(array);
        int level1_length=array.length;
        String[] lines=new String[totalLength];
        for(int x=1,a=0;x<=level1_length;x++){
            int level2_length=array[(x-1)].length;
            lines[a]="\t"+(((x<10) && (level1_length>10))?" ":"")+x+". "+array[(x-1)][0];
            a++;
            for(int y=1;y<array[(x-1)].length;y++,a++){
                char letter=((char)(y+64));
                lines[a]="\t\t"+(((x<10) && (level2_length>10))?" ":"")+letter+". "+array[(x-1)][y];
            }
        }
        return lines;
    }
    
    private static void printArray(String[] array){
        for(String line:array){
            System.out.println(line);
        }
    }
    
    private static int totalLength(Object[][] array){
        int total=0;
        for(int a=0;a<array.length;a++){
            for(int b=0;b<array[a].length;b++){
                total++;
            }
        }
        return total;
    }
}
