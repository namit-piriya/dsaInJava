# Finding the Length of Longest Increasing Subsequence (Dynamic Programming)

## Problem Statement

Given an integer array `nums`, you need to find the length of the longest strictly increasing subsequence.

## Approach: Dynamic Programming

### Intuition



- To find the longest increasing subsequence, you start with each number and ask, "What's the longest sequence I can create by adding this number to the end?"

- You compare the current number with the numbers before it. If it's larger, you extend the sequence.

- Keep track of the longest sequence you can create for each number.

- Finally, find the longest sequence among all the sequences you've created.
### Another way of thinking
- Imagine you have a list of numbers, and you want to find the longest sequence where each number is larger than the previous one.

- You start with each number one by one and ask, "What's the longest sequence I can create by adding this number to the end?"

- To answer that question, you look back at the numbers before the current one. If the current number is larger than the previous one, you can extend the sequence.

- As you do this for each number, you keep track of the longest sequence you can create with it.

- Finally, you find the longest sequence among all the sequences you've created.

### Why Dynamic Programming?

- Dynamic programming efficiently computes the length of the longest increasing subsequence for each element and optimally finds the answer.

### Time Complexity

- The time complexity is O(n^2) due to the nested loops. Each element is compared with all elements before it.

### Space Complexity

- The space complexity is O(n) because of the additional array `dp` of length `n`.

## Java Code

```java
class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int maxLength = 1; // Minimum length is 1 for each element

        // Initialize dp array
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        // Calculate the length of the longest increasing subsequence
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }
}
