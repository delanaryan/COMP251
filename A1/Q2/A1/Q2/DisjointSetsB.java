

/****************************
*
* COMP251 template file
*
* Assignment 1, Question 2b
*
*****************************/

/* contructor: creates a partition of n elements. */
/* Each element is in a separate disjoint set */
public class DisjointSetsB {

    private int[] par;
    private int[] rank;

    /* contructor: creates a partition of n elements. */
    /* Each element is in a separate disjoint set */
    DisjointSetsB(int n) {
        n += 1;
        if (n>=0) {
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

    /* move i to the set containing j */
    public void move(int i, int j) {
        int repI = find(i); 
        int repJ = find(j); 
    
        if (repI == repJ) return; // Already in the same set
    
        if (par[i] == i) { // i is a root node
            int newRoot = -1;
            for (int k = 0; k < par.length; k++) {
                if (find(k) == repI && k != i) { // Find a new root
                    newRoot = k;
                    break;
                }
            }
    
            if (newRoot != -1) {
                for (int k = 0; k < par.length; k++) {
                    if (find(k) == repI && k != i) { 
                        par[k] = newRoot; // Update all nodes in i's set
                    }
                }
            }
        }
    
        par[i] = i; // Temporarily isolate i
        union(i, j); // Attach i to j's set
    }

    /* return the sum of elements in the set of i */
    public int sum_elements(int i) {
        int sum = 0;

        int root = find(i);

        for (int j=0; j < par.length; j++){
            if (find(j) == root){
                sum += j;
            }
        }

        /* Fill this method */
        return sum;
    }

    public static void main(String[] args) {

        DisjointSetsB myset = new DisjointSetsB(6);
        System.out.println(myset);
        System.out.println("-> Union 1 and 2");
        myset.union(1,2);
        System.out.println(myset);
        System.out.println("-> Move 3 and 4");
        myset.move(3,4);
        System.out.println(myset);
        System.out.println("-> Union 3 and 5 ");
        myset.union(3,5);
        System.out.println(myset);
        System.out.println("-> Sum 4");
        System.out.println(myset.sum_elements(4));
        System.out.println(myset);
        System.out.println("->Move 4 and 1");
        myset.move(4,1);
        System.out.println(myset);
        System.out.println("-> Sum 4");
        System.out.println(myset.sum_elements(4));
        System.out.println(myset);

    }

}