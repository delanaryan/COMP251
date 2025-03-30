import java.util.*;

public class A3Q2 {
    public static String[] time_pass(String[][] itinerary, String[] cities) {
        
        Map<String, List<String>> graph = new HashMap<>();
        for (String[] flight : itinerary) {
            graph.computeIfAbsent(flight[0], k -> new ArrayList<>()).add(flight[1]);
        }

        Set<String> inInfiniteCycle = new HashSet<>();
        for (String city : graph.keySet()) {
            if (canReachCycle(city, graph, new HashSet<>(), new HashSet<>())) {
                inInfiniteCycle.add(city);
            }
        }

        String[] answer = new String[cities.length];
        for (int i = 0; i < cities.length; i++) {
            answer[i] = inInfiniteCycle.contains(cities[i]) ? "succeed" : "failed";
        }
        return answer;
    }

    private static boolean canReachCycle(String city, Map<String, List<String>> graph, Set<String> visited, Set<String> path) {
        if (path.contains(city)) return true; // Found a cycle
        if (visited.contains(city)) return false; // Already checked and no cycle
        
        visited.add(city);
        path.add(city);
        
        for (String neighbor : graph.getOrDefault(city, Collections.emptyList())) {
            if (canReachCycle(neighbor, graph, visited, path)) {
                return true;
            }
        }
        
        path.remove(city);
        return false;
    }

    public static void main(String[] args) {
        String[][] itinerary = {
            {"Arlington", "San_Antonio"},
            {"San_Antonio", "Baltimore"},
            {"Baltimore", "New_York"},
            {"New_York", "Dallas"},
            {"Baltimore", "Arlington"}
        };
        String[] cities = {"San_Antonio", "Baltimore", "New_York"};
        System.out.println(Arrays.toString(time_pass(itinerary, cities)));
    }
}