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
		HashMap<String, ArrayList<String>> users = new HashMap<String, ArrayList<String>>();

		for (String strings : posts) {
			String[] messages =  strings.split(" ");
			String userName = messages[0];

			for (String word : messages){
				if (users.containsKey(userName)){
					users.get(username).add(word);
				} else {
					users.put(userName, word);
				}
			}

		}
        return null;
	}

}