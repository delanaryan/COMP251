import java.util.*;
import java.math.BigInteger;

public class A2_Q2 {
    private static BigInteger swapCount; 
    
    public static BigInteger num_swaps(int[] numbers) {
        swapCount = BigInteger.ZERO; 
        mergeSort(numbers, 0, numbers.length - 1);
        return swapCount;
    }

    public static void mergeSort(int[] A, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;

            mergeSort(A, p, q);
            mergeSort(A, q + 1, r); 

            merge(A, p, q, r);
        }
    }

    public static void merge(int[] A, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = A[p + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = A[q + 1 + j];
        }

        int i = 0, j = 0, k = p;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                A[k++] = L[i++];
            } else {
                A[k++] = R[j++];
                swapCount = swapCount.add(BigInteger.valueOf(n1 - i));
            }
        }

        while (i < n1) {
            A[k++] = L[i++];
        }
        while (j < n2) {
            A[k++] = R[j++];
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 2};
        System.out.println(num_swaps(arr)); // Expected output: 2
    }

}
