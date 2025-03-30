//763. Partition Labels

/*You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part. For example, the string "ababcc" can be partitioned into ["abab", "cc"], but partitions such as ["aba", "bcc"] or ["ab", "ab", "cc"] are invalid.

Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.

Return a list of integers representing the size of these parts.

 

Example 1:

Input: s = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
Example 2:

Input: s = "eccbbbbdec"
Output: [10]
 

Constraints:

1 <= s.length <= 500
s consists of lowercase English letters */


/* ################################ Solution ################################ */

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> partitionLabels(String s) {
        List<Integer> result = new ArrayList<>();
        int[] lastIndex = new int[26]; // Stores the last index of each character
        
        // Step 1: Populate last index of each character
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        
        // Step 2: Partition the string greedily
        int maxEnd = 0, partitionStart = 0;
        for (int i = 0; i < s.length(); i++) {
            maxEnd = Math.max(maxEnd, lastIndex[s.charAt(i) - 'a']);
            
            // If we reach the max end of a partition, finalize it
            if (i == maxEnd) {
                result.add(i - partitionStart + 1);
                partitionStart = i + 1;
            }
        }
        
        return result;
    }
}