//2843. Count Symmetric Integers


/* You are given two positive integers low and high.

An integer x consisting of 2 * n digits is symmetric if the sum of the first n digits of x is equal to the sum of the last n digits of x. Numbers with an odd number of digits are never symmetric.

Return the number of symmetric integers in the range [low, high].

 

Example 1:

Input: low = 1, high = 100
Output: 9
Explanation: There are 9 symmetric integers between 1 and 100: 11, 22, 33, 44, 55, 66, 77, 88, and 99.
Example 2:

Input: low = 1200, high = 1230
Output: 4
Explanation: There are 4 symmetric integers between 1200 and 1230: 1203, 1212, 1221, and 1230.
 

Constraints:

1 <= low <= high <= 104*/



//############################## Solution ############################## 


class Solution {
    public int countSymmetricIntegers(int low, int high) {
         int count = 0;

        for (int num = low; num <= high; num++) {
            String s = Integer.toString(num);

            if (s.length() % 2 != 0) continue; // Must be even length

            int n = s.length() / 2;
            int sum1 = 0, sum2 = 0;

            for (int i = 0; i < n; i++) {
                sum1 += s.charAt(i) - '0';
            }

            for (int i = n; i < 2 * n; i++) {
                sum2 += s.charAt(i) - '0';
            }

            if (sum1 == sum2) {
                count++;
            }
        }

        return count;
    }
}