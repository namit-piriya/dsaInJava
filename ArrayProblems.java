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

    public static int[] twoSumHash(int[] nums, int target){
        HashMap<Integer,Integer> indexes = new HashMap<>();
        int i = 0;
        int [] ans = new int[2];
        for(int num : nums){
            indexes.put(num,i++);
        }
        i = 0;
        for(int num : nums){
            int currTarget = target - num;

             if(indexes.containsKey(currTarget) && indexes.get(currTarget) != i){
                 ans[0] = indexes.get(currTarget);
                 ans[1] = i;
             }
             i++;
        }
        return ans;
    }

}
