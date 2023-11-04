import java.util.*;
import java.util.stream.Stream;

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
        var s = new StackProblems().simplifyPath("/../home/");
        s = new StackProblems().simplifyPath("//home//foo//");
        System.out.println(s);
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

    //    https://leetcode.com/problems/remove-element
//    [0,1,2,2,3,0,4,2], val = 2
    public int removeElement(int[] nums, int val) {
        int indexToStore = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[indexToStore] = nums[i];
                indexToStore++;
            }
        }
        return indexToStore;
    }

    //    https://leetcode.com/problems/majority-element
    public int majorityElement(int[] nums) {
        int candidate = -1;
        int count = 0;

        for (int n : nums) {
            if (count == 0) {
                candidate = n;
            }
            if (candidate == n) {
                count++;
            } else count--;

        }

        return candidate;

    }

    public boolean canJump(int[] nums) {
        int reachable = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > reachable) return false;
//            adding the current index we are at and the jump we can get from there is new reachable
            reachable = Math.max(reachable, i + nums[i]);
        }
        return true;
    }


    //    Input: s = "the sky is blue"
//    Output: "blue is sky the"
    public String reverseWords(String s) {
        Stack<String> stack = new Stack<>();
        var strSrr = s.split(" ");
        for (String str : strSrr) {
            stack.push(str);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(stack.pop());
        while (!stack.empty()) {
            var str = stack.pop();
            if (!str.trim().isEmpty()) {
                stringBuilder.append(" ");
                stringBuilder.append(str);
            }

        }
        // stringBuilder.setLength(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    int square(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += (digit * digit);
            n = n / 10;
        }
        return sum;
    }

    public boolean isHappy(int n) {

        int slow = square(n);
        int fast = square(square(n));

        while (slow != fast) {
            slow = square(slow);
            fast = square(square(fast));
        }
        return slow == 1;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> s = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            int currValue = s.getOrDefault(nums[i], -1);
            if (currValue != -1 && i - currValue <= k) {
                return true;
            } else {
                s.put(nums[i], i);
            }
        }
        return false;
    }

    public boolean isIsomorphic(String s, String t) {
        var forward = new HashMap<Character, Character>();
        var backward = new HashMap<Character, Character>();

        for (int i = 0; i < s.length(); i++) {
            var sChar = s.charAt(i);
            var tChar = t.charAt(i);
            if (forward.get(sChar) == null && backward.get(tChar) == null) {
                forward.put(sChar, tChar);
                backward.put(tChar, sChar);
            } else if ((forward.get(sChar) == null || backward.get(tChar) == null) ||
                    !(forward.get(sChar) == tChar && backward.get(tChar) == sChar)) {
                return false;
            }
        }
        return true;
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length == 1) return 1;
        int ptr = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[ptr] = nums[i];
                ptr++;
            }
        }
        return ptr;

    }

    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int ans = Integer.MIN_VALUE;
        int skippedZero = 0;
        int i = 0;
        while (i < citations.length && citations[i] == 0) {
            i++;
            skippedZero++;
        }
//        2,2,5
        for (int j = skippedZero; j < citations.length; j++) {
            int curr = Math.min(citations[j], citations.length - j);
            ans = Math.max(curr, ans);
        }
        return ans == Integer.MIN_VALUE ? 0 : ans;
    }


}