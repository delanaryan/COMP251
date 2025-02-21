import java.util.*;
import java.math.BigInteger;

/*
 * For this exercise, your task is to find out how the number of swaps required to sort the student by their ID in 
 * an ascending order as described in the pdf file. Please complete the function num_swaps which receive a 1-D array 
 * of integers representing the queue of students. The function num_swaps must return a number representing the 
 * total number of successively swapping pairs of consecutive students in the queue to guarantee that the queue 
 * appear in increasing order based on their student ID.

You will be graded on both accuracy and efficiency in this problem. 
 */

public class A2_Q2 {
    public static BigInteger num_swaps(int[] numbers){
        return null;
    }

    public static void mergeSort(int[] A, int p, int r){
        if (p < r) {
            int q = (int) Math.floor((p+r)/2);

            mergeSort(A, p, q);
            mergeSort(A, p, q+1);

            merge(A,p,q,r);
        }
        return;
    }

    public static void merge(int[] A, int p, int q, int r){
        int n1 = q - p + 1;
        int n2 = r - q;

        int[] L = new int[A.length];
        int[] R = new int[A.length];

        for (int i = 1; i < n1; i++){
            L[i] = A[p + 1 - 1];
        }

        for (int j = 1; j < n1; j++){
            R[j] = A[q+j];
        }

        L[n1] = Integer.MAX_VALUE;
        L[n2] = Integer.MAX_VALUE;

        int i = 1;
        int j = 1;

        for (int k = p; k < r; k++){
            if (L[i] <= R[j]){
                A[k] = L[i];
                i = i + 1;
            } else {
                A[k] = R[j];
                j = j + 1;
            }
        }
        return;
    }
}
