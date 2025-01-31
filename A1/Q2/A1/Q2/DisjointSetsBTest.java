public class DisjointSetsBTest {
    public static void main(String[] args) {
        // Test Case 1: Initial state
        DisjointSetsB ds = new DisjointSetsB(7);
        System.out.println(ds); // Should print 7 separate sets

        // Test Case 2: Basic union
        ds.union(1, 2);
        System.out.println(ds); // {1,2} should be one set

        // Test Case 3: Move element
        ds.move(3, 4);
        System.out.println(ds); // {3,4} should be one set

        // Test Case 4: Union two sets
        ds.union(3, 5);
        System.out.println("Expected {3,4,5}");
        System.out.println(ds); // {3,4,5} should be one set

        // Test Case 5: Sum of elements in a set
        System.out.println("Sum of set containing 4: " + ds.sum_elements(4)); // Expected: 3 + 4 + 5 = 12

        // Test Case 6: Move element to another set
        ds.move(4, 1);
        System.out.println("Expected {1,2,4} and {3,5}");
        System.out.println(ds); // {1,2,4} and {3,5}

        // Test Case 7: Sum again
        System.out.println("Sum of set containing 4: " + ds.sum_elements(4)); // Expected: 1 + 2 + 4 = 7
        System.out.println("Sum of set containing 3: " + ds.sum_elements(3)); // Expected: 3 + 5 = 8

        // Test Case 8: Union across moved sets
        ds.union(2, 3);
        System.out.println("Sould merge everything");
        System.out.println(ds); // Should merge everything

        // Test Case 9: Edge cases
        DisjointSetsB ds2 = new DisjointSetsB(1);
        System.out.println("Sum of set containing 0: " + ds2.sum_elements(0)); // Should be 0

        ds2.move(0, 0); // Moving to itself should do nothing
        System.out.println(ds2);
    }
}
