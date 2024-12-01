// You can (and should) add "implements Partitioner" below once you have the implementation ready

import java.util.Arrays;

public class FirstElePivotPartitioner implements Partitioner{
    @Override
    public int partition(String[] strs, int low, int high) {

        int pivotIndex = low;
        int lowerBound = low + 1;

        if(lowerBound == high) {return pivotIndex;}

        int upperBound = high - 1;
        String temp;

        while(lowerBound <= upperBound){
            if(strs[lowerBound].compareTo(strs[pivotIndex])<=0){
                lowerBound+=1;
            }
            else{
                temp = strs[lowerBound];
                strs[lowerBound] = strs[upperBound];
                strs[upperBound] = temp;
                upperBound-=1;
            }
        }
        temp = strs[upperBound];
        strs[upperBound] = strs[pivotIndex];
        strs[pivotIndex] = temp;
        pivotIndex = upperBound;

        return pivotIndex;
    }
}
