import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntervalProblems {


    /*You are given a sorted unique integer array nums.
    A range [a,b] is the set of all integers from a to b (inclusive).
    Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.
    Each range [a,b] in the list should be output as:
    "a->b" if a != b
    "a" if a == b
    Example 1:
    Input: nums = [0,1,2,4,5,7]
    Output: ["0->2","4->5","7"]
    Explanation: The ranges are:
    [0,2] --> "0->2"
    [4,5] --> "4->5"
    [7,7] --> "7"
    * */

    public List<String> summaryRanges(int[] nums) {
        if (nums.length <= 1) return List.of(String.valueOf(nums[0]));
        int i = 0;
        List<String> ans = new ArrayList<>();
        int rangeStart;
        int rangeLast;
        while (i < nums.length) {
            rangeStart = nums[i];
            rangeLast = nums[i];
            while (i + 1 < nums.length && nums[i + 1] == nums[i] + 1) {
                rangeLast = nums[i + 1];
                i++;
            }
            String resString = rangeStart == rangeLast ? rangeStart + "" : rangeStart + "->" + rangeLast;
            ans.add(resString);
            i++;
        }
        return ans;
    }
}
