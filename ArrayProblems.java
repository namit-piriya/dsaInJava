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
        int[] ans = new int[nums.length];
        ans[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            ans[i] = nums[i - 1] * ans[i - 1];
        }
        int r = nums[nums.length - 1];
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
            // handles the negative number case
            //see is the num is greater than the sum of previous sum and the number
            // which means we have something negative in bw
            if (num > currSum) {
                currSum = num;
            }
            if (currSum > maxSum) {
                maxSum = currSum;
            }
        }
        return maxSum;
    }


    public static int removeDuplicates(int[] nums) {
        int curr = 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[curr - 2]) { // curr is the array index without duplicates more than 2
                nums[curr] = nums[i];
                curr++;
            }
        }
        return curr;
    }

    public static void reverse(int[] nums, int s, int e) {
        while (s < e) {
            int t = s;
            s = e;
            e = t;
            s++;
            e--;
        }
    }

    public static void rotate(int[] nums, int k) {
        k %= (nums.length - 1);
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    //    You can sell the stock every day if the price is higher
//    anyways you can only hold one share
//    1,3,5
//    1,3 and 3,5 is same as 1,5
    public int maxProfit2(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }

    //    pascal triangle
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        if (numRows <= 0) return ans;
        var list = new Integer[]{1};
        ans.add(Arrays.asList(list));
        for (int i = 1; i < numRows; i++) {
            var previousRow = ans.get(i - 1);
            var currentRow = new ArrayList<Integer>();
            currentRow.add(1);
            for (int j = 1; j < previousRow.size(); j++) {
                int sum = previousRow.get(j) + previousRow.get(j - 1);
                currentRow.add(sum);
            }
            currentRow.add(1);
            ans.add(currentRow);
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        int n = removeDuplicates(nums);
        System.out.println("reflected size is " + n);
        System.out.println(Arrays.toString(nums));
    }

    //    https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        Map<Integer, List<Integer>> m = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            var num = groupSizes[i];
            var numList = m.getOrDefault(num, new ArrayList<Integer>());
            numList.add(i);
            m.put(num, numList);
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (var entries : m.entrySet()) {
            var size = entries.getKey();
            var list = entries.getValue();
            var listSize = list.size();
            while (listSize > 0) {
                var ptr = 0;
                var subAns = new ArrayList<Integer>();
                var currSize = size;
                while (currSize-- > 0) {
                    var val = list.get(ptr++);
                    subAns.add(val);
                }
                ans.add(subAns);
                listSize -= size;
            }

        }
        return ans;
    }

    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            var currSum = numbers[i] + numbers[j];
            if (currSum == target) {
                return new int[]{i + 1, j + 1};
            }
            if (currSum > target) {
                j--;
            } else {
                i++;
            }
        }
        return new int[]{1, 1};
    }

}