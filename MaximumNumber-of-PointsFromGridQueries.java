//2503. Maximum Number of Points From Grid Queries


/*You are given an m x n integer matrix grid and an array queries of size k.

Find an array answer of size k such that for each integer queries[i] you start in the top left cell of the matrix and repeat the following process:

If queries[i] is strictly greater than the value of the current cell that you are in, then you get one point if it is your first time visiting this cell, and you can move to any adjacent cell in all 4 directions: up, down, left, and right.
Otherwise, you do not get any points, and you end this process.
After the process, answer[i] is the maximum number of points you can get. Note that for each query you are allowed to visit the same cell multiple times.

Return the resulting array answer.

 

Example 1:


Input: grid = [[1,2,3],[2,5,7],[3,5,1]], queries = [5,6,2]
Output: [5,8,1]
Explanation: The diagrams above show which cells we visit to get points for each query.
Example 2:


Input: grid = [[5,2,1],[1,1,2]], queries = [3]
Output: [0]
Explanation: We can not get any points because the value of the top left cell is already greater than or equal to 3.
 

Constraints:

m == grid.length
n == grid[i].length
2 <= m, n <= 1000
4 <= m * n <= 105
k == queries.length
1 <= k <= 104
1 <= grid[i][j], queries[i] <= 106 */


/* ################################ Solution ################################ */




import java.util.*;

class Solution {
    public int[] maxPoints(int[][] grid, int[] queries) {
        // Get the dimensions of the grid
        int m = grid.length, n = grid[0].length;
        
        // Result array to store the number of points collected for each query
        int[] result = new int[queries.length];

        // Directions array to move up, down, left, and right in the grid
        int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        
        // Create an array to store queries along with their original indices
        int[][] sortedQueries = new int[queries.length][2];

        // Store queries with their original indices
        for (int i = 0; i < queries.length; i++) {
            sortedQueries[i] = new int[]{queries[i], i};
        }

        // Sort the queries based on their values in ascending order
        Arrays.sort(sortedQueries, Comparator.comparingInt(a -> a[0]));

        // Min-Heap (PriorityQueue) to process the BFS (sorted by grid cell value)
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // Boolean matrix to keep track of visited cells
        boolean[][] visited = new boolean[m][n];

        // Start BFS from the top-left corner (0,0)
        minHeap.offer(new int[]{grid[0][0], 0, 0});
        visited[0][0] = true;
        
        // Variable to keep track of the number of unique cells visited
        int points = 0;

        // Process each query in increasing order
        for (int[] q : sortedQueries) {
            int queryValue = q[0];  // Current query value
            int index = q[1];       // Original index of the query in input array

            // Process the grid cells while the smallest cell in the heap is less than queryValue
            while (!minHeap.isEmpty() && minHeap.peek()[0] < queryValue) {
                // Remove the smallest cell from the heap
                int[] cell = minHeap.poll();
                int r = cell[1], c = cell[2]; // Get row and column of the cell

                // Increase the count of points as we are visiting this cell for the first time
                points++;

                // Explore the 4 possible directions (up, down, left, right)
                for (int[] dir : directions) {
                    int nr = r + dir[0], nc = c + dir[1]; // Calculate new row and column

                    // Check if the new cell is within bounds and not visited yet
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc]) {
                        visited[nr][nc] = true; // Mark the cell as visited
                        minHeap.offer(new int[]{grid[nr][nc], nr, nc}); // Add it to the heap
                    }
                }
            }

            // Store the result for the current query
            result[index] = points;
        }

        // Return the final result array
        return result;
    }
}
