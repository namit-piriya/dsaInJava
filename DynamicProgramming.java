import java.util.Arrays;
import java.util.HashMap;

public class DynamicProgramming {
//    https://leetcode.com/problems/unique-paths/

    public int uniquePaths2d(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public int uniquePaths1d(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    public int combinationSum4(int[] nums, int target) {
        int curr = 0;
        var targetMap = new HashMap<Integer, Integer>();
        return combSumHelper(nums, target, curr, targetMap);
    }

    private int combSumHelper(int[] nums, int target, int curr, HashMap<Integer, Integer> targetMap) {
        if (curr > target) return 0;
        if (curr == target) return 1;
        var totalSum = 0;
        for (var num : nums) {
            if (targetMap.containsKey(curr + num)) {
                totalSum += targetMap.get(curr + num);
            } else {
                var currResult = combSumHelper(nums, target, curr + num, targetMap);
                totalSum += currResult;
                targetMap.put(curr + num, currResult);
            }
        }
        return totalSum;
    }

}
