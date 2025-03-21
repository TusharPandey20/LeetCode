//2115. Find All Possible Recipes from Given Supplies

/*You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients. The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i]. A recipe can also be an ingredient for other recipes, i.e., ingredients[i] may contain a string that is in recipes.

You are also given a string array supplies containing all the ingredients that you initially have, and you have an infinite supply of all of them.

Return a list of all the recipes that you can create. You may return the answer in any order.

Note that two recipes may contain each other in their ingredients.

 

Example 1:

Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
Output: ["bread"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".
Example 2:

Input: recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
Output: ["bread","sandwich"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".
We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
Example 3:

Input: recipes = ["bread","sandwich","burger"], ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
Output: ["bread","sandwich","burger"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".
We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
We can create "burger" since we have the ingredient "meat" and can create the ingredients "bread" and "sandwich".
 

Constraints:

n == recipes.length == ingredients.length
1 <= n <= 100
1 <= ingredients[i].length, supplies.length <= 100
1 <= recipes[i].length, ingredients[i][j].length, supplies[k].length <= 10
recipes[i], ingredients[i][j], and supplies[k] consist only of lowercase English letters.
All the values of recipes and supplies combined are unique.
Each ingredients[i] does not contain any duplicate values. */


/* ################################ Solution ################################ */

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.HashMap;
//import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
//import java.util.Set;

class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        
        // Map to store in-degree (number of missing ingredients) for each recipe
        Map<String, Integer> inDegree = new HashMap<>();
        
        // Graph to store ingredient → list of recipes that depend on it
        Map<String, List<String>> graph = new HashMap<>();
        
        // Set of available ingredients (including initial supplies)
        //Set<String> available = new HashSet<>(Arrays.asList(supplies));
        
        // Queue to process ingredients that are available
        Queue<String> queue = new LinkedList<>();

        // Step 1: Initialize graph and in-degree map
        for (int i = 0; i < recipes.length; i++) {
            String recipe = recipes[i]; // Get the recipe name
            inDegree.put(recipe, ingredients.get(i).size()); // Set missing ingredients count
            
            // Build graph: for each ingredient, add the recipe it helps create
            for (String ingredient : ingredients.get(i)) {
                graph.computeIfAbsent(ingredient, x -> new ArrayList<>()).add(recipe);
            }
        }

        // Step 2: Add all initially available supplies to the queue
        for (String supply : supplies) {
            queue.offer(supply);
        }

        // Result list to store all recipes that can be created
        List<String> result = new ArrayList<>();

        // Step 3: Process the queue using BFS
        while (!queue.isEmpty()) {
            String item = queue.poll(); // Get an available ingredient or recipe
            
            // If the item is a recipe, add it to the result since it can be made
            if (inDegree.containsKey(item)) { 
                result.add(item);
            }

            // Reduce the in-degree for recipes that depend on the current item
            for (String recipe : graph.getOrDefault(item, new ArrayList<>())) {
                inDegree.put(recipe, inDegree.get(recipe) - 1); // Decrease missing ingredients count
                
                // If all ingredients for a recipe are now available, add it to the queue
                if (inDegree.get(recipe) == 0) { 
                    queue.offer(recipe);
                }
            }
        }

        // Return the list of all creatable recipes
        return result;

    }
}