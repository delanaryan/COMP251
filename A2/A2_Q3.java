import java.util.*;

public class A2_Q3 {
    public static String directions(int[] distances) {
        int steps = distances.length;
        int sum = 0;

        // Calculate the total sum of steps
        for (int d : distances) {
            sum += d;
        }

        int maxH = 2 * sum; // Maximum possible height to handle positive & negative values
        boolean[][] dp = new boolean[steps + 1][maxH + 1];
        dp[0][sum] = true; // Offset sum to handle negative heights

        char[][] path = new char[steps + 1][maxH + 1];

        // DP Table Filling
        for (int i = 0; i < steps; i++) {
            for (int j = 0; j <= maxH; j++) {
                if (!dp[i][j]) continue;

                // Try moving DOWN first (minimizing height deviation)
                if (j - distances[i] >= 0) {
                    dp[i + 1][j - distances[i]] = true;
                    path[i + 1][j - distances[i]] = 'D';
                }
                // Then try moving UP
                if (j + distances[i] <= maxH) {
                    dp[i + 1][j + distances[i]] = true;
                    path[i + 1][j + distances[i]] = 'U';
                }
            }
        }

        // If we can't return to the start height, return "IMPOSSIBLE"
        if (!dp[steps][sum]) return "IMPOSSIBLE";

        // Backtrack to reconstruct the path
        char[] result = new char[steps];
        int h = sum;

        for (int i = steps; i > 0; i--) {
            result[i - 1] = path[i][h];
            if (path[i][h] == 'U') h -= distances[i - 1];  // Reverse 'U'
            else h += distances[i - 1];  // Reverse 'D'
        }

        return new String(result);
    }

    // Test cases
    public static void main(String[] args) {
        System.out.println(directions(new int[]{20, 20, 20, 20})); // Expected: "UDUD"
        System.out.println(directions(new int[]{3, 2, 5, 3, 1, 2})); // Expected: "UUDUDD"
        System.out.println(directions(new int[]{3, 4, 2, 1, 6, 4, 5})); // Expected: "IMPOSSIBLE"
    }
}
