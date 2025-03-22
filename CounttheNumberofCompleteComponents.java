//2685. Count the Number of Complete Components

/*You are given an integer n. There is an undirected graph with n vertices, numbered from 0 to n - 1. You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting vertices ai and bi.

Return the number of complete connected components of the graph.

A connected component is a subgraph of a graph in which there exists a path between any two vertices, and no vertex of the subgraph shares an edge with a vertex outside of the subgraph.

A connected component is said to be complete if there exists an edge between every pair of its vertices.

 

Example 1:



Input: n = 6, edges = [[0,1],[0,2],[1,2],[3,4]]
Output: 3
Explanation: From the picture above, one can see that all of the components of this graph are complete.
Example 2:



Input: n = 6, edges = [[0,1],[0,2],[1,2],[3,4],[3,5]]
Output: 1
Explanation: The component containing vertices 0, 1, and 2 is complete since there is an edge between every pair of two vertices. On the other hand, the component containing vertices 3, 4, and 5 is not complete since there is no edge between vertices 4 and 5. Thus, the number of complete components in this graph is 1.
 

Constraints:

1 <= n <= 50
0 <= edges.length <= n * (n - 1) / 2
edges[i].length == 2
0 <= ai, bi <= n - 1
ai != bi
There are no repeated edges.

Hint 1
Find the connected components of an undirected graph using depth-first search (DFS) or breadth-first search (BFS).
Hint 2
For each connected component, count the number of nodes and edges in the component.
Hint 3
A connected component is complete if and only if the number of edges in the component is equal to m*(m-1)/2, where m is the number of nodes in the component. */


/* ################################ Solution ################################ */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
      // Adjacency list to represent the graph
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        
        // Initialize graph with empty sets for each node
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<>());
        }
        
        // Build adjacency list representation of the graph
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        // Set to track visited nodes
        boolean[] visited = new boolean[n];
        int completeComponents = 0; // To count complete components

        // Explore all nodes using DFS
        for (int i = 0; i < n; i++) {
            if (!visited[i]) { // If node `i` is not visited, start DFS for a new component
                List<Integer> componentNodes = new ArrayList<>();
                int edgeCount = dfs(graph, visited, i, componentNodes);

                // Number of nodes in this connected component
                int nodeCount = componentNodes.size();

                // Formula for a complete graph: nodes * (nodes - 1) / 2
                if (edgeCount == (nodeCount * (nodeCount - 1)) / 2) {
                    completeComponents++; // If condition is met, it's a complete component
                }
            }
        }

        return completeComponents;
    }

    // Depth-First Search (DFS) to find connected components and count edges
    private int dfs(Map<Integer, Set<Integer>> graph, boolean[] visited, int node, List<Integer> componentNodes) {
        Stack<Integer> stack = new Stack<>();
        stack.push(node);
        visited[node] = true;
        
        int edgeCount = 0;
        
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            componentNodes.add(curr); // Track nodes in the component
            
            for (int neighbor : graph.get(curr)) {
                edgeCount++; // Each edge is counted twice due to undirected nature

                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    stack.push(neighbor);
                }
            }
        }
        
        return edgeCount / 2; // Since each edge was counted twice, divide by 2  
    }
}