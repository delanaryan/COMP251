package A1.Q3;

import java.util.*;

/****************************
*
* COMP251 template file
*
* Assignment 1, Question 3
*
*****************************/

public class A1_Q3 {
	
	public static ArrayList<String> Discussion_Board(String[] posts){
		HashMap<String, ArrayList<String>> users = new HashMap<>();
		HashMap<String, Integer> wordCount = new HashMap<>();
		HashMap<String, HashSet<String>> wordUsers = new HashMap<>(); // Tracks which users used each word
        HashSet<String> uniqueUsers = new HashSet<>(); // Stores unique user names

		for (String post : posts) {
			String[] words = post.split(" ");
			String userName = words[0];
			uniqueUsers.add(userName);

			users.putIfAbsent(userName, new ArrayList<>());

			HashSet<String> seenWords = new HashSet<>(); // Track words seen in this post

			for (int i = 1; i < words.length; i++) { // Start from index 1 (skip username)
				String word = words[i];

				// Add word to the user's list
				users.get(userName).add(word);

				// Count total occurrences of each word
				wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);

				// If this word hasn't been seen in this post, count it for the user
				if (seenWords.add(word)) {
					wordUsers.putIfAbsent(word, new HashSet<>());
					wordUsers.get(word).add(userName);
				}
			}
		}

		int totalUsers = uniqueUsers.size();
		ArrayList<String> commonWords = new ArrayList<>();

		System.out.println("Word usage by users: " + wordUsers);

		// Find words that were used by all users
		for (Map.Entry<String, HashSet<String>> entry : wordUsers.entrySet()) {
			if (entry.getValue().size() == totalUsers) { // Check if word was used by every unique user
				commonWords.add(entry.getKey());
			}
		}

		// Sort by frequency, then alphabetically
		commonWords.sort((a, b) -> {
            int freqCompare = wordCount.get(b) - wordCount.get(a); 
            return (freqCompare != 0) ? freqCompare : a.compareTo(b); 
        });

        return commonWords;
	}

    public static void main(String[] args) {
        String[] posts = {
            "David no no no no nobody never",
			"Alexia why ever not",
			"Parham no not never nobody",
			"Brian no never know nobody",
			"Jeremy why no nobody",
			"Jeremy nobody never know why nobody",
			"David never no nobody",
			"Alexia never never nobody no"
        };

        ArrayList<String> commonWords = Discussion_Board(posts);
        System.out.println("Common words: " + commonWords);
    }
}
