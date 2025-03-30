import java.util.*;
import java.lang.*;

public class A3Q3 {
    public static double arduino(double[][] locations){
        int n = locations.length;
        boolean[] inMST = new boolean[n]; // Tracks whether a node is in the MST
        double[] minEdge = new double[n]; // Tracks the minimum edge weight to connect a node to the MST
        Arrays.fill(minEdge, Double.MAX_VALUE); // Initialize all distances to infinity
        minEdge[0] = 0.0; // Start from the first point
        double totalWireLength = 0.0;

        for (int i = 0; i < n; i++) {
            int u = -1;
            double min = Double.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (!inMST[j] && minEdge[j] < min) {
                    min = minEdge[j];
                    u = j;
                }
            }

            totalWireLength += minEdge[u];
            inMST[u] = true; // Mark this node as added to the MST

            for (int v = 0; v < n; v++) {
                if (!inMST[v]) {
                    double dx = locations[u][0] - locations[v][0];
                    double dy = locations[u][1] - locations[v][1];
                    double distance = Math.sqrt(dx * dx + dy * dy);
                    
                    minEdge[v] = Math.min(minEdge[v], distance);
                }
            }
        }

        return Math.round(totalWireLength * 100.0) / 100.0;
    }
    
}
