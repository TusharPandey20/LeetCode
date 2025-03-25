// 3394. Check if Grid can be Cut into Sections

    /*You are given an integer n representing the dimensions of an n x n grid, with the origin at the bottom-left corner of the grid. You are also given a 2D array of coordinates rectangles, where rectangles[i] is in the form [startx, starty, endx, endy], representing a rectangle on the grid. Each rectangle is defined as follows:

    (startx, starty): The bottom-left corner of the rectangle.
    (endx, endy): The top-right corner of the rectangle.
    Note that the rectangles do not overlap. Your task is to determine if it is possible to make either two horizontal or two vertical cuts on the grid such that:

    Each of the three resulting sections formed by the cuts contains at least one rectangle.
    Every rectangle belongs to exactly one section.
    Return true if such cuts can be made; otherwise, return false.

    

    Example 1:

    Input: n = 5, rectangles = [[1,0,5,2],[0,2,2,4],[3,2,5,3],[0,4,4,5]]

    Output: true

    Explanation:



    The grid is shown in the diagram. We can make horizontal cuts at y = 2 and y = 4. Hence, output is true.

    Example 2:

    Input: n = 4, rectangles = [[0,0,1,1],[2,0,3,4],[0,2,2,3],[3,0,4,3]]

    Output: true

    Explanation:



    We can make vertical cuts at x = 2 and x = 3. Hence, output is true.

    Example 3:

    Input: n = 4, rectangles = [[0,2,2,4],[1,0,3,2],[2,2,3,4],[3,0,4,2],[3,2,4,4]]

    Output: false

    Explanation:

    We cannot make two horizontal or two vertical cuts that satisfy the conditions. Hence, output is false.

    

    Constraints:

    3 <= n <= 109
    3 <= rectangles.length <= 105
    0 <= rectangles[i][0] < rectangles[i][2] <= n
    0 <= rectangles[i][1] < rectangles[i][3] <= n
    No two rectangles overlap. */




/* ################################ Solution ################################ */

import java.util.Arrays;

class Solution {

    public boolean checkValidCuts(int n, int[][] rectangles) {
        // Try both horizontal and vertical cuts
        return checkCuts(rectangles, 0) || checkCuts(rectangles, 1);
    }

    // Check if valid cuts can be made in a specific dimension
    private boolean checkCuts(int[][] rectangles, int dim) {
        int gapCount = 0;

        // Sort rectangles by their starting coordinate in the given dimension
        Arrays.sort(rectangles, (a, b) -> Integer.compare(a[dim], b[dim]));

        // Track the furthest ending coordinate seen so far
        int furthestEnd = rectangles[0][dim + 2];

        for (int i = 1; i < rectangles.length; i++) {
            int[] rect = rectangles[i];

            // If current rectangle starts after the furthest end we've seen,
            // we found a gap where a cut can be made
            if (furthestEnd <= rect[dim]) {
                gapCount++;
            }

            // Update the furthest ending coordinate
            furthestEnd = Math.max(furthestEnd, rect[dim + 2]);
        }

        // We need at least 2 gaps to create 3 sections
        return gapCount >= 2;
    }
}
