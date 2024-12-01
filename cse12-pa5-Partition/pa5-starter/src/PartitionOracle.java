
// These are some imports that the course staff found useful, feel free to use them
// along with other imports from java.util.
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PartitionOracle {

    /**
     * Feel free to use this method to call partition. It catches any exceptions or
     * errors and returns a definitely-invalid pivot (-1) to turn errors into
     * potential failures. For example, in testPartition, you may use
     * 
     * runPartition(p, someArray, someLow, someHigh)
     * 
     * instead of
     * 
     * p.partition(someArray, someLow, someHigh)
     * 
     * @param p
     * @param strs
     * @param low
     * @param high
     * @return
     */
    public static int runPartition(Partitioner p, String[] strs, int low, int high) {
        try {
            return p.partition(strs, low, high);
        } catch (Throwable t) {
            return -1;
        }
    }

    // The three methods below are for you to fill in according to the PA writeup.
    // Feel free to make other helper methods as needed.

    public static String isValidPartitionResult(String[] before, int low, int high, int pivot, String[] after) {
        if(pivot>=high){
            return"Pivot is larger than the upper bound";
        }
        else if(pivot<low){
            return"Pivot is smaller than the lower bound";
        }

        if(before.length > after.length){
            return "after array is is shorter than before array";
        }
        else if(before.length < after.length){
            return "after array is is longer than before array";
        }

        for(int i=0; i<low; i++){
            if(before[i] != after[i]){
                return "unexpected change outside of bound at index " + i;
            }
        }
        for(int i=high; i<before.length; i++){
            if(before[i] != after[i]){
                return "unexpected change outside of bound at index " + i;
            }
        }

        for(int i=0; i<before.length; i++){
            for(int j=0; j<after.length; j++){
                if(before[i].compareTo(after[j])==0){
                    break;
                }
                else if(j == after.length-1){
                    return ("after array missing the element at index " + i + "of before array");
                }
            }
        }
        for(int i=0; i<after.length; i++){
            for(int j=0; j<before.length; j++){
                if(after[i].compareTo(before[j])==0){
                    break;
                }
                else if(j == before.length-1){
                    return ("after array has extra the element at index " + i);
                }
            }
        }

        for(int i=low; i<pivot; i++){
            if(after[i].compareTo(after[pivot])>0){
                return "element at index " + i + " should be on the right side of the pivot";
            }
        }
        for(int i=pivot+1; i<high; i++){
            if(after[i].compareTo(after[pivot])<0){
                return "element at index " + i + " should be on the left side of the pivot";
            }
        }
        return null;  
    }

    public static String[] generateInput(int n) {
        String[] toReturn = new String[n];
        for (int i=0; i<n; i++){
            int strLength =(int)(Math.random() * 10 + 1);
            String finalStr = "", strToAdd;
            for (int j=0; j<strLength; j++){
                strToAdd = Character.toString((int)(Math.random() * 70 + 60));
                finalStr = finalStr + strToAdd;
            }
            toReturn[i] = finalStr;
        }
        return toReturn;
    }

    public static CounterExample findCounterExample(Partitioner p) {
        String[] testArray, afterArray;
        for(int i=8; i<18; i++){
            int arrLength = i;
            testArray = generateInput(arrLength);
            afterArray = Arrays.copyOf(testArray, arrLength);
            int pivot = p.partition(afterArray, 2, afterArray.length-2);
            String reason = isValidPartitionResult(testArray, 2, afterArray.length-2, pivot, afterArray);
            if(reason != null){
                return new CounterExample(testArray, 2, afterArray.length-2, pivot, afterArray, reason);
            }
        }
        return null;
    }

}
