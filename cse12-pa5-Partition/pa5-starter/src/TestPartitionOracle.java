import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This is an example of how to implement the Partitioner interface to implement
 * a concrete Partitioner. You can use this bad implementation to test your PartitionOracle,
 * to ensure that it works in detecting a bad Partitioner. You should add a correct implementation
 * of a Partitioner here, maybe one from class, to verify that your PartitionOracle also works
 * correctly on good implementations. Once you implement part 2, you can also test those Partitioner
 * implementations here as well.
 * 
 */
class CopyFirstElementPartition implements Partitioner {
    public int partition(String[] strs, int low, int high) {
        if (high - low < 1)
            return 0;
        for (int i = 0; i < strs.length; i += 1) {
            strs[i] = strs[0];
        }
        return 0;
    }
}

class LastElePartition implements Partitioner {
    public int partition(String[] strs, int low, int high) {

        int pivotIndex = high -1;
        int lowerBound = low;

        if(lowerBound == high - 1) {return pivotIndex;}

        int upperBound = high - 2;
        String temp;

        while(lowerBound < upperBound){
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

class RandomElePartition implements Partitioner {
    public int partition(String[] strs, int low, int high) {

        int pivotIndex = (int)(Math.random()*(high-low-1))+low;
        int lowerBound = low;
        int upperBound = high - 1;
        if(lowerBound == upperBound) {return pivotIndex;}

        String temp;

        while(lowerBound < upperBound){
            if(lowerBound == pivotIndex) {lowerBound += 1;}
            else if(upperBound == pivotIndex) {upperBound -= 1;}
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
        temp = strs[upperBound];
        strs[upperBound] = strs[pivotIndex];
        strs[pivotIndex] = temp;
        pivotIndex = upperBound;

        return pivotIndex;
    }
}

public class TestPartitionOracle {
    @Test
    public void testCopyFirstElementPartition() {
        CounterExample ce = PartitionOracle.findCounterExample(new CopyFirstElementPartition());
        System.out.println(ce);
        assertNotNull(ce);
    }

    @Test
    public void testLastElePartition() {
        CounterExample ce = PartitionOracle.findCounterExample(new LastElePartition());
        System.out.println(ce);
        assertNotNull(ce);
    }

    @Test
    public void testFirstElePivotPartition() {
        CounterExample ce = PartitionOracle.findCounterExample(new FirstElePivotPartitioner());
        System.out.println(ce);
        assertNull(ce);
    }

    @Test
    public void testRandomElePartition() {
        CounterExample ce = PartitionOracle.findCounterExample(new RandomElePartition());
        System.out.println(ce);
        assertNotNull(ce);
    }

    @Test
    public void testCentralPivotPartition() {
        CounterExample ce = PartitionOracle.findCounterExample(new CentralPivotPartitioner());
        System.out.println(ce);
        assertNull(ce);
    } 

    @Test
    public void testWebPivotPartition() {
        CounterExample ce = PartitionOracle.findCounterExample(new WebPartitioner());
        System.out.println(ce);
        assertNull(ce);
    } 
}
