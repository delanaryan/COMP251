import java.util.*;

public class A2_Q3 {
    public static String directions(int[] distances) {
        int steps = distances.length;
        int maxH = 1000; // Mt Royal has at most 1000 stairs
        boolean[][] dp = new boolean[steps + 1][maxH + 1];
        char[][] path = new char[steps + 1][maxH + 1];

        dp[0][0] = true; // Start at street level

        // DP Table Filling
        for (int i = 0; i < steps; i++) {
            for (int j = 0; j <= maxH; j++) {
                if (!dp[i][j]) continue; // Skip invalid states

                int down = j - distances[i];
                int up = j + distances[i];

                // Move down if within bounds
                if (down >= 0 && !dp[i + 1][down]) {
                    dp[i + 1][down] = true;
                    path[i + 1][down] = 'D';
                }
                // Move up if within bounds
                if (up <= maxH && !dp[i + 1][up]) {
                    dp[i + 1][up] = true;
                    path[i + 1][up] = 'U';
                }
            }
        }

        if (!dp[steps][0]) return "IMPOSSIBLE";

        // Backtrack to reconstruct the path
        char[] result = new char[steps];
        int h = 0;

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
