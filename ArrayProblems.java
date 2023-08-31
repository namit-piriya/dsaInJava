import java.util.*;

public class ArrayProblems {
    public static int[] twoSumTwoPointers(int[] nums, int target) {
        HashMap<Integer, Integer> indexes = new HashMap<>();
        List<Integer> arrayList = new ArrayList<>();
        int ind = 0;
        for (int num : nums) {
            arrayList.add(num);
            indexes.put(num, ind);
            ind++;
        }
        int[] sol = new int[2];
        Collections.sort(arrayList);
        int i = 0;
        int j = arrayList.size() - 1;
        while (i < j) {
            var currSum = arrayList.get(i) + arrayList.get(j);
            if (currSum > target) {
                j--;
            } else if (currSum < target) {
                i++;
            } else {
//                 this will not work for [3,3]
                sol[0] = indexes.get(arrayList.get(i));
                sol[1] = indexes.get(arrayList.get(j));
                return sol;
            }
        }
        return sol;
    }

    public static int[] twoSumHash(int[] nums, int target) {
        HashMap<Integer, Integer> indexes = new HashMap<>();
        int i = 0;
        int[] ans = new int[2];
        for (int num : nums) {
            indexes.put(num, i++);
        }
        i = 0;
        for (int num : nums) {
            int currTarget = target - num;

            if (indexes.containsKey(currTarget) && indexes.get(currTarget) != i) {
                ans[0] = indexes.get(currTarget);
                ans[1] = i;
            }
            i++;
        }
        return ans;
    }

    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
    public static int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int ans = 0;
        for (int price : prices) {
            if (min > price) {
                min = price;
            }
            ans = Math.max(ans, price - min);
        }
        return ans;
    }


    // https://leetcode.com/problems/contains-duplicate/
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> e = new HashSet<Integer>();

        for (int num : nums) {
            if (e.contains(num)) {
                return true;
            } else
                e.add(num);
        }
        return false;
    }

    //https://leetcode.com/problems/product-of-array-except-self/
    public static int[] productExceptSelf(int[] nums) {
        int [] ans = new int[nums.length];
        ans[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            ans[i] = nums[i-1] * ans[i-1];
        }
        int r = nums[nums.length-1];
        for (int i = nums.length - 2; i >= 0; i--) {
            ans[i] = r * ans[i];
            r = r * nums[i];
        }
        return ans;
    }

    //https://leetcode.com/problems/maximum-subarray/
    public static int maxSubArray(int[] nums) {
        // corner case [-2,1]
        // corner case [-2]
        int maxSum = Integer.MIN_VALUE;
        int currSum = 0;
        for (int num : nums) {
            currSum += num;
            if (num > currSum) {
                currSum = num;
            }
            if (currSum > maxSum) {
                maxSum = currSum;
            }
        }
        return maxSum;
    }


}
