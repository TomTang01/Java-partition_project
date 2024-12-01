// You can (and should) add "implements Partitioner" below once you have the implementation ready

import java.util.Arrays;

public class CentralPivotPartitioner implements Partitioner{
    @Override
    public int partition(String[] strs, int low, int high) {

        int pivotIndex = (high-1 - low) / 2 + low;
        int lowerBound = low;
        int upperBound = high-1;
        String temp;
        
        while(lowerBound <= upperBound){
            if(strs[lowerBound].compareTo(strs[pivotIndex])==0){
                lowerBound+=1;
            }
            else if(strs[upperBound].compareTo(strs[pivotIndex])==0){
                upperBound-=1;
            }
            else if(strs[lowerBound].compareTo(strs[pivotIndex])<=0){
                lowerBound+=1;
            }
            else{
                temp = strs[lowerBound];
                strs[lowerBound] = strs[upperBound];
                strs[upperBound] = temp;
                upperBound-=1;
            }
        }
        if(upperBound>=pivotIndex){
            temp = strs[upperBound];
            strs[upperBound] = strs[pivotIndex];
            strs[pivotIndex] = temp;
            pivotIndex = upperBound;
        }
        else{
            temp = strs[lowerBound];
            strs[lowerBound] = strs[pivotIndex];
            strs[pivotIndex] = temp;
            pivotIndex = lowerBound;
        }
        return pivotIndex;
    }
}
