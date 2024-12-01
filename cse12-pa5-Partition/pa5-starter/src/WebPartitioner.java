// You can (and should) add "implements Partitioner" below once you have the implementation ready

import java.util.Arrays;
import java.util.Random;

public class WebPartitioner implements Partitioner{

    /*
     * From https://github.com/joeyajames/Java/blob/master/QuickSort.java
     * 
     * License: https://github.com/joeyajames/Java/blob/master/LICENSE
     * 
     * Was originally a partition for integer arrays, I changed it into String[] 
     * and replaced the < operation with .compareTo()<0. 
     * Also change conditional statement of the for loop to i<high because 
     * I want high to be not included in the partition.
     * 
     * After these modify, it is not buggy.
     * 
     * The worst case would be having a array of length N, low = 0, and high = length of array as the imputs.
     * This will make the for loop iterate through the whole array and thus O(n)
     */
    public int partition(String[] A, int low, int high) {
		swap(A, low, getPivot(low, high));
		int border = low + 1;
		for (int i = border; i < high; i++) {
			if (A[i].compareTo(A[low])<0) {
				swap(A, i, border++);
			}
		}
		swap(A, low, border-1);
		return border-1;
	}

    /*
     * From https://github.com/joeyajames/Java/blob/master/QuickSort.java
     * 
     * License: https://github.com/joeyajames/Java/blob/master/LICENSE
     * 
     * Minus an extra 1 to high because I do not want high to be included.
     * 
     * After this modify, it is not buggy.
     * 
     * There is no worst case or best case. There is only a constant number of steps
     * thus O(1)
     */
    private int getPivot(int low, int high) {
		Random rand = new Random();
		return rand.nextInt((high-1 - low) + 1) + low;
	}

    /*
     * From https://github.com/joeyajames/Java/blob/master/QuickSort.java
     * 
     * License: https://github.com/joeyajames/Java/blob/master/LICENSE
     * 
     * Was originally a swap method for integer arrays. I changed it into String[] 
     * and made temp a String variable.
     * 
     * After these modify, it is not buggy.
     * 
     * There is no worst case or best case. There is only a constant number of steps
     * thus O(1)
     */
    private void swap(String[] A, int index1, int index2) {
		String temp = A[index1];
		A[index1] = A[index2];
		A[index2] = temp;		
	}
}