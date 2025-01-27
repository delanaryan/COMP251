import java.io.*;
import java.util.*;

/****************************
*
* COMP251 template file
*
* Assignment 1, Question 1
*
*****************************/

public class Open_Addressing {
     public int m; // number of SLOTS AVAILABLE
     public int A; // the default random number
     int w;
     int r;
     public int[] Table;

     protected Open_Addressing(int w, int seed, int A) {

         this.w = w;
         this.r = (int) (w-1)/2 +1;
         this.m = power2(r);
         if (A==-1){
            this.A = generateRandom((int) power2(w-1), (int) power2(w),seed);
         }
        else{
            this.A = A;
        }
         this.Table = new int[m];
         for (int i =0; i<m; i++) {
             Table[i] = -1;
         }

     }

                 /** Calculate 2^w*/
     public static int power2(int w) {
         return (int) Math.pow(2, w);
     }
     public static int generateRandom(int min, int max, int seed) {
         Random generator = new Random();
                 if(seed>=0){
                    generator.setSeed(seed);
                 }
         int i = generator.nextInt(max-min-1);
         return i+min+1;
     }
        /**Implements the hash function g(k)*/
         public int probe(int key, int i) {
            //TODO: implement this function and change the return statement.
            int h_k = ((A * key) % power2(w)) >> (w - r);
            int g_k_i = (h_k + i) % m;
        
            return g_k_i;
     }


     /**Inserts key k into hash table. Returns the number of collisions encountered*/
        public int insertKey(int key){
            //TODO : implement this and change the return statement.
            int collisions = 0; // Initialize collision counter
    
            for (int i = 0; i < m; i++) { // Probe up to m slots
                int hash = probe(key, i); // Compute the hash value for the i-th probe
            
                if (Table[hash] == -1 || Table[hash] == -2) { 
                    Table[hash] = key; 
                    return collisions;
                } else {
                    collisions++;
                }
            }
            return collisions;
        }

        /**Sequentially inserts a list of keys into the HashTable. Outputs total number of collisions */
        public int insertKeyArray (int[] keyArray){
            int collision = 0;
            for (int key: keyArray) {
                collision += insertKey(key);
            }
            return collision;
        }

         /**Inserts key k into hash table. Returns the number of collisions encountered*/
        public int removeKey(int key){
            //TODO: implement this and change the return statement
            int collisions = 0;

            for (int i = 0; i < m; i++) { 
                int hash = probe(key, i); 
                
                if (Table[hash] == -1) { 
                    return collisions + 1;
                }

                if (Table[hash] == key) { 
                    Table[hash] = -2; 
                    return collisions;
                }
                collisions++;
            }
            return collisions;
        }
}