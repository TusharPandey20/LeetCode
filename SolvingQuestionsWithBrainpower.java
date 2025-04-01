//2140. Solving Questions With Brainpower

/* */


//########################################## Solution #############################################



class Solution {
    public long mostPoints(int[][] questions) {
     int n = questions.length;
        long[] dp = new long[n + 1]; // DP array (initialized to 0)

        for (int i = n - 1; i >= 0; i--) {
            int points = questions[i][0];
            int brainpower = questions[i][1];

            // Solve this question
            long solve = points;
            if (i + brainpower + 1 < n) {
                solve += dp[i + brainpower + 1];
            }

            // Either solve or skip
            dp[i] = Math.max(solve, dp[i + 1]);
        }

        return dp[0];   
    }
}