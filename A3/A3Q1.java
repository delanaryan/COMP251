import java.util.*;

public class A3Q1 {
    private static final int[][] DIRECTIONS = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };

    public static int[] saving_frogs(String[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int totalFood = 0;
		int reachableFood = 0; 
		int froggies = 0;
        List<int[]> entrances = new ArrayList<>();

        // Identify entrances (A-W only) and count total food
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char ch = grid[i][j].charAt(0);
                if (grid[i][j].equals(".")) {
                    totalFood++;
                } else if (ch >= 'A' && ch <= 'W') { // Only A-W are entrances
                    entrances.add(new int[]{i, j});
                }
            }
        }

        //System.out.println("Total food: " + totalFood);
        //System.out.println("Valid entrances (A-W): " + entrances.size());

        // BFS from each entrance
        for (int[] entrance : entrances) {
            int sx = entrance[0], sy = entrance[1];

            if (visited[sx][sy]) continue; // Skip already visited areas

            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{sx, sy});
            visited[sx][sy] = true;
            boolean foundFood = false;

            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                int x = cell[0], y = cell[1];

                if (grid[x][y].equals(".")) {
                    reachableFood++;
                    foundFood = true;
                }

                for (int[] dir : DIRECTIONS) {
                    int nx = x + dir[0], ny = y + dir[1];
                    if (nx >= 0 && nx < rows && ny >= 0 && ny < cols &&
                        !visited[nx][ny] &&
                        (grid[nx][ny].equals(".") || grid[nx][ny].equals(" "))) {
                        queue.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }

            if (foundFood) {
                froggies++; // Count a frog only if food was found
                //System.out.println("New frog assigned, total frogs: " + froggies);
            }
        }

        int unreachableFood = totalFood - reachableFood;
        //System.out.println("Reachable food: " + reachableFood);
        //System.out.println("Unreachable food: " + unreachableFood);

        return new int[]{froggies, unreachableFood};
    }

}
