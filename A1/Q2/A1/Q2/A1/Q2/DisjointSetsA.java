package A1.Q2;

import java.io.*;
import java.util.*;

/****************************
*
* COMP251 template file
*
* Assignment 1, Question 2a
*
*****************************/


public class DisjointSetsA {

    private int[] par;
    private int[] rank;

    /* contructor: creates a partition of n elements. */
    /* Each element is in a separate disjoint set */
    public DisjointSetsA(int n) {
        if (n>0) {
            par = new int[n];
            rank = new int[n];
            for (int i=0; i<this.par.length; i++) {
                par[i] = i;
            }
        }
    }

    public String toString(){
        int pari,countsets=0;
        String output = "";
        String[] setstrings = new String[this.par.length];
        /* build string for each set */
        for (int i=0; i<this.par.length; i++) {
            pari = find(i);
            if (setstrings[pari]==null) {
                setstrings[pari] = String.valueOf(i);
                countsets+=1;
            } else {
                setstrings[pari] += "," + i;
            }
        }
        /* print strings */
        output = countsets + " set(s):\n";
        for (int i=0; i<this.par.length; i++) {
            if (setstrings[i] != null) {
                output += i + " : " + setstrings[i] + "\n";
            }
        }
        return output;
    }

    /* find resentative of element i */
    public int find(int i) {
        int representative = 0;

        if (par[i] == i) {
            representative = i;
        } else {
            par[i] = find(par[i]);
            return par[i];
        }
        /* Fill this method (The statement return 0 is here only to compile) */
        return representative;
    }

    /* merge sets containing elements i and j */
    public int union(int i, int j) {
        /* Fill this method (The statement return 0 is here only to compile) */
        int repI = find(i);
        int repJ = find(j);

        if (repI == repJ) return repI; // Already in the same set

        if (rank[repI] > rank[repJ]) {
            par[repJ] = repI;
            return repI;
        } else if (rank[repI] < rank[repJ]) {
            par[repI] = repJ;
            return repJ;
        } else {
            par[repJ] = repI;
            rank[repI]++;
            return repI;
        }
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
    
        DisjointSetsA myset = new DisjointSetsA(6);
        System.out.println(myset);
    
        System.out.println("-> Union 2 and 3");
        myset.union(2, 3);
        System.out.println(myset);
    
        System.out.println("-> Union 2 and 3");
        myset.union(2, 3);
        System.out.println(myset);
    
        System.out.println("-> Union 2 and 1");
        myset.union(2, 1);
        System.out.println(myset);
    
        System.out.println("-> Union 4 and 5");
        myset.union(4, 5);
        System.out.println(myset);
    
        System.out.println("-> Union 3 and 1");
        myset.union(3, 1);
        System.out.println(myset);
    
        System.out.println("-> Union 2 and 4");
        myset.union(2, 4);
        System.out.println(myset);
    
        long endTime = System.nanoTime();
        double elapsedTime = (endTime - startTime) / 1_000_000.0;
        System.out.println("Total execution time: " + elapsedTime + " ms");
    }

}