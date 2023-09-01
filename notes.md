## Note for Quick Revision

### Stack Using Single Queue (Recursive Push)

**Description:**

- **Concept:** Implementing a stack using a single queue with recursive push operations.
- **Key Methods:** `recursivePush(x)`, `push(x)`, `pop()`, `top()`, and `empty()`.
- **Time Complexity:** Push - O(n), Pop/Top/Empty - O(1).
- **Space Complexity:** O(n).

#### Critical Logic

- **Recursive Push Operation:**
    - In the `recursivePush(int x)` method, we ensure that the new element `x` is added to the front of the queue to maintain the Last-In-First-Out (LIFO) behavior of a stack.
    - The steps involved in `recursivePush`:
        1. If the queue is empty, we directly enqueue `x` into the queue.
        2. If the queue is not empty, we dequeue the front element and then recursively call `recursivePush(x)` to push the new element `x` onto the stack.
        3. After the recursive call, we enqueue the previously dequeued front element back into the queue. This step is crucial for preserving the LIFO order.
    - This logic rearranges the elements in the queue, ensuring that the newly pushed element is always at the front, effectively simulating a stack's behavior.

### Alternative Approach

- **Using Two Queues with Optimized Pop Operation:**
    - In this approach, we maintain two queues: `queue1` and `queue2`.
    - For pushing, we enqueue elements into `queue1` as usual.
    - For popping, we perform the following steps:
        1. Dequeue all elements from `queue1` except the last one and enqueue them into `queue2`.
        2. Dequeue the last element from `queue1` and return it as the popped element.
        3. Swap the names of `queue1` and `queue2`.
    - This optimized approach avoids moving all elements for each push operation, resulting in better time complexity.

#### Alternative Approach Notes

- The alternative approach uses two queues and optimizes the pop operation to achieve a better time complexity for push.
- Pushing elements into `queue1` remains the same as the primary approach.
- Popping elements involves transferring elements between `queue1` and `queue2` while ensuring the last element in `queue1` is returned as the popped element.
- This approach maintains the LIFO behavior of a stack while improving the efficiency of the push operation.

---
# Maximum Product Subarray - Revision Note

## Problem Statement

Given an integer array `nums`, find a subarray that has the largest product and return the product.

### Example

**Input:**


**Explanation:** [2, 3] has the largest product 6.

## Approach

To find the maximum product subarray, 
we can use dynamic programming to keep track of both the maximum and minimum products ending at each position in the array. This approach is designed to handle negative numbers correctly.
```
for (int i = 1; i < nums.length; i++) {
    int temp = maxProduct;
    maxProduct = Math.max(nums[i], Math.max(nums[i] * maxProduct, nums[i] * minProduct));
    minProduct = Math.min(nums[i], Math.min(nums[i] * temp, nums[i] * minProduct));
    result = Math.max(result, maxProduct);
}
```





### Algorithm Logic

1. Initialize three variables: `maxProduct`, `minProduct`, and `result` to keep track of the maximum product, minimum product, and the final result.
2. Iterate through the array from the second element (index 1) to the end.
3. At each iteration, update `maxProduct` and `minProduct` based on the current element and the products ending at the previous position.
4. Update `result` with the maximum product encountered so far.
5. Return the `result` as the maximum product subarray.

### Alternative Approach

An alternative approach to solve this problem is to use a brute-force method, which involves nested loops to compute the product of all possible subarrays and keep track of the maximum product. However, this approach is less efficient than the dynamic programming approach, as it has a time complexity of O(n^2), whereas the dynamic programming approach has a time complexity of O(n).

## Edge Cases

1. **Empty Input:**
   - If the input array `nums` is empty, return 0 as there are no elements to form a subarray.

2. **Single Element:**
   - If `nums` contains only one element, the maximum product subarray is that element itself.

3. **Zeroes:**
   - If `nums` contains zeroes, the product will always be zero. However, the subarray should not be empty; it should contain at least one element (the zero itself).

4. **Negative Numbers:**
   - The algorithm handles negative numbers correctly by keeping track of both the maximum and minimum products.

5. **Large Numbers:**
   - The algorithm is designed to work with large integers and ensures that the result fits in a 32-bit integer.

## Summary

The dynamic programming approach efficiently solves the "Maximum Product Subarray" problem by keeping track of maximum and minimum products, allowing it to handle negative numbers and efficiently compute the maximum product subarray. Remember to consider edge cases and handle them gracefully in your implementation.

# Group Anagrams - Revision Note

## Problem Statement

Given an array of strings `strs`, group the anagrams together and return the answer in any order.

### Example

**Input:**


```strs = ["eat", "tea", "tan", "ate", "nat", "bat"]```


**Output:**

```[["bat"], ["nat", "tan"], ["ate", "eat", "tea"]]```




## Approach

To group anagrams together efficiently, we can use a hashmap to map each sorted string (anagram) to a list of original strings that match the sorted string. Here's how the algorithm works:

1. Initialize a hashmap `anagramMap`, where the keys will be sorted strings, and the values will be lists of original strings.
2. Iterate through the input array `strs`.
3. For each string in `strs`, sort its characters and use the sorted string as the key.
4. If the sorted string is not already in `anagramMap`, create a new entry with an empty list as the value.
5. Add the original string to the list corresponding to the sorted string in `anagramMap`.
6. Finally, return a list of lists containing the values of `anagramMap`, which groups anagrams together.

```
Map<String, List<String>> anagramMap = new HashMap<>();    
for (String str : strs) {
    char[] chars = str.toCharArray();
    Arrays.sort(chars);
    String sortedStr = new String(chars);
    
    if (!anagramMap.containsKey(sortedStr)) {
        anagramMap.put(sortedStr, new ArrayList<>());
    }
    
    anagramMap.get(sortedStr).add(str);
}
```

## Edge Cases

1. **Empty Input:**
    - If the input array `strs` is empty, return an empty list as there are no anagrams to group.

2. **Single Element:**
    - If `strs` contains only one element, return a list with that element as a single group.

3. **Anagrams and Non-Anagrams:**
    - The algorithm correctly handles cases where there are both anagrams and non-anagrams in the input array. Anagrams are grouped together, and non-anagrams are treated as individual groups.

4. **Empty Strings:**
    - The algorithm handles empty strings correctly and includes them as individual groups.

## Summary

The algorithm efficiently groups anagrams together using a hashmap to store and organize the anagrams based on their sorted representations. Edge cases such as empty input, single elements, and empty strings are handled gracefully.


# Permutations of Distinct Integers - Revision Note

## Problem Statement

Given an array `nums` of distinct integers, return all the possible permutations. You can return the answer in any order.

### Example

**Input:**
```nums = [1, 2, 3]```

**Output:**
```[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]```


## Approach

### DFS Approach

To generate all possible permutations of distinct integers efficiently, we can use a depth-first search (DFS) algorithm. Here's how the revised DFS approach works:

1. Initialize an empty result list `result`, an empty list `currentPermutation` to track the current permutation being built, and a boolean array `used` to track which elements have been used in the current permutation.
2. Call a helper function `generatePermutations` to perform the DFS.
3. In the `generatePermutations` function:
    - If the size of `currentPermutation` equals the length of `nums`, it means a valid permutation is found, so add a copy of `currentPermutation` to `result`.
    - Iterate through the elements of `nums`.
    - If an element has not been used (`!used[i]`), add it to `currentPermutation`, mark it as used (`used[i] = true`), and recursively call `generatePermutations` with the updated state.
    - After the recursive call, remove the last element from `currentPermutation` and mark the element as not used (`used[i] = false`).
4. Return the `result` containing all valid permutations.

### Alternative Approach (Using Heap's Algorithm)

Another approach to generating permutations is to use Heap's Algorithm, which generates permutations in a more iterative manner without the need for recursion. Here's a high-level overview:

1. Initialize an empty result list `result`, and a variable `n` to store the length of `nums`.
2. Create an array `indexes` containing values from 0 to `n-1`.
3. While `indexes` is not empty:
    - Add a copy of `nums` with elements rearranged based on the values in `indexes` to the `result`.
    - Implement the Heap's Algorithm to generate the next permutation by swapping elements in `indexes`.
4. Return the `result` containing all valid permutations.

## Intuition

### DFS Approach Intuition

- We start with an empty permutation and explore different permutations by adding elements to it.
- We use the `used` array to keep track of which elements have been used in the current permutation to ensure distinct integers in each permutation.
- By recursively exploring all possible choices of elements to add to the permutation, we find all valid permutations.

### Alternative Approach (Heap's Algorithm) Intuition

- Heap's Algorithm is an iterative method to generate permutations.
- It maintains an array of indexes that corresponds to the positions of elements in the current permutation.
- The algorithm generates permutations by repeatedly swapping elements in the `indexes` array.
- This approach avoids recursion and generates permutations in a more iterative manner.

## Time and Space Complexity Analysis

### Time Complexity

The time complexity of this approach is O(N * N!), where N is the length of the input array `nums`. In the worst case, we generate N! permutations, and for each permutation, we iterate through N elements to perform swaps.

### Space Complexity

The space complexity is O(N * N!) as well. This is because we store all valid permutations in the `result` list, and there are N! permutations, each containing N elements.


## Edge Cases

1. **Empty Input:**
    - If the input array `nums` is empty, the result is an empty list as there are no integers to permute.

2. **Single Element:**
    - If `nums` contains only one element, the result is a list containing that element as the only permutation.

3. **Distinct Integers:**
    - Both the DFS approach and the alternative approach handle cases where all integers in `nums` are distinct and generate all possible permutations.

4. **Alternative Approach Validity:**
    - The alternative approach using Heap's Algorithm is a valid method for generating permutations and can be used as an alternative to the DFS approach.

## Summary

The revised DFS approach efficiently generates all possible permutations of distinct integers using a depth-first search (DFS) approach, while the alternative approach using Heap's Algorithm iteratively generates permutations without recursion. Both approaches are valid and produce the correct results. Edge cases such as empty input and single-element input are gracefully handled. The result is a list of lists containing the valid permutations.

### DFS Approach Summary

- **Efficiency**: The DFS approach has a time complexity of O(N * N!), where N is the length of the input array `nums`. This is because there are N! possible permutations, and for each permutation, we iterate through N elements.

### Alternative Approach (Heap's Algorithm) Summary

- **Efficiency**: The alternative approach using Heap's Algorithm is also efficient with a time complexity of O(N!), but it generates permutations


```
Level 0: []
  |
  |-- Level 1: [1]
  |     |
  |     |-- Level 2: [1, 2]
  |     |     |
  |     |     |-- Level 3: [1, 2, 3] (Valid permutation)
  |     |     
  |     |-- Level 2: [1, 3]
  |           |
  |           |-- Level 3: [1, 3, 2] (Valid permutation)
  |
  |-- Level 1: [2]
  |     |
  |     |-- Level 2: [2, 1]
  |     |     |
  |     |     |-- Level 3: [2, 1, 3] (Valid permutation)
  |     |     
  |     |-- Level 2: [2, 3]
  |           |
  |           |-- Level 3: [2, 3, 1] (Valid permutation)
  |
  |-- Level 1: [3]
        |
        |-- Level 2: [3, 1]
        |     |
        |     |-- Level 3: [3, 1, 2] (Valid permutation)
        |     
        |-- Level 2: [3, 2]
              |
              |-- Level 3: [3, 2, 1] (Valid permutation)


```


# Searching in a Sorted Array - Comprehensive Explanation

## Problem Statement

You have a sorted list of numbers, like [1, 3, 5, 7, 9]. Your task is to find a specific number in the list. If it's there, return its index. If it's not, return -1.

The challenge is to do this quickly, especially for long lists, without checking every number one by one.

## Approach

### Binary Search

We'll use a smart method called "Binary Search." Here's how it works:

1. You start with the entire list of numbers.
2. Pick the middle number.
3. If the middle number is the one you're looking for, you're done! You found it!
4. If it's too big, you know the number must be on the left side, so you ignore the right side and repeat.
5. If it's too small, you know the number must be on the right side, so you ignore the left side and repeat.
6. Keep doing this, narrowing down the possibilities each time, until you either find the number or you're sure it's not in the list.

## Intuition

Think of Binary Search as a smart way to divide and conquer. By repeatedly comparing the middle element, you eliminate half of the remaining possibilities. It's like flipping through a book to find a page, starting in the middle and deciding whether to go forward or backward.

## Alternative Approaches

### Linear Search

You could check each number from the beginning to the end of the list. It works, but it's slower, especially for long lists. The time complexity is O(N), where N is the number of items in the list.

### Hash Table

You could create a hash table to store numbers and their indices. Then, you could quickly look up the number. This works well, but it uses more memory, and the setup takes extra time. The time complexity is O(N) for setup and O(1) for searching.

## Edge Cases

1. **Empty List:**
    - If the list is empty, you can't find anything, so return -1.

2. **Not in the List:**
    - If the number you're looking for isn't in the list, return -1.

3. **Single Element:**
    - If the list has only one element, you can immediately tell if it's the number you're looking for or not.

4. **First or Last Element:**
    - If the number you're looking for is the first or last element, you can find it quickly without many comparisons.

## Time Complexity (Big O: O(log N))

Binary Search is super fast! You can find the answer in about log₂(N) tries, where N is the number of items in the list. This is really quick and is represented as O(log N) time complexity.

## Space Complexity (Big O: O(1))

Binary Search doesn't use much memory. It just needs a few variables to keep track of things, so the space it uses is very small. We say it has O(1) space complexity, which means it uses a constant amount of memory, regardless of the list's size.

## Why It Takes That Much Time

Binary Search is efficient because it exploits the property of the sorted array. It continually reduces the search space by half, ensuring that you can quickly find the target element or determine its absence.

To understand why it takes O(log N) time:

- In the worst case, you start with N elements.
- After the first comparison, you have N/2 elements left.
- After the second comparison, you have N/4 elements left.
- This process continues until you have either found the target or reduced the search space to a single element.

Mathematically, you can express this as N → N/2 → N/4 → N/8 → ... → 1, which is a geometric progression. The number of steps it takes to reach 1 is roughly log₂(N), hence the O(log N) time complexity.

In summary, Binary Search takes O(log N) time because it systematically reduces the search space by half in each step, making it an efficient algorithm for finding elements in sorted arrays. Additionally, it has a space complexity of O(1), indicating minimal memory usage.


```
public int search(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            return mid; // Target found, return its index.
        } else if (nums[mid] < target) {
            left = mid + 1; // Adjust the left pointer.
        } else {
            right = mid - 1; // Adjust the right pointer.
        }
    }

    return -1; // Target not found in the array.
}

```


# Revision Notes: Three Sum Problem

## Edge Cases to Handle:
1. **Empty Input**: Handle an empty input array (`nums`).
2. **Duplicate Elements**: Account for cases with duplicate elements in the input.
3. **No Triplets Summing to Zero**: Handle scenarios where there are no valid triplets summing to zero.
4. **Negative and Positive Numbers**: Be prepared for both negative and positive numbers in the array.

## Approach with Example and Intuition:

### Approach: Two-Pointer Technique

1. **Sort the Array**: Begin by sorting the given array `nums`. This simplifies the problem by grouping duplicate values together.

2. **Iterate through the Array**: Use a loop to iterate through the sorted array. For each element at index `i`, treat the problem as finding a pair with a target sum of `-nums[i]` in the subarray starting from index `i + 1`.

3. **Use Two Pointers**: Initialize two pointers, `left` and `right`, initially pointing to elements just after `nums[i]` and the last element in the array, respectively.

4. **Check the Sum**: Check if `nums[i] + nums[left] + nums[right]` equals zero.

5. **Move Pointers**: Adjust the pointers based on the sum:
    - If the sum is zero, add the triplet `[nums[i], nums[left], nums[right]]` to the result and move both `left` and `right` pointers.
    - If the sum is less than zero, move the `left` pointer to the right (increment it).
    - If the sum is greater than zero, move the `right` pointer to the left (decrement it).

6. **Avoid Duplicates**: Skip identical elements when moving the pointers to avoid duplicate triplets.

7. **Continue Looping**: Repeat steps 4-6 while the `left` pointer is less than the `right` pointer.

8. **Move to the Next Element**: After processing all possible triplets with `nums[i]`, move to the next element in the loop.

9. **Return the Result**: Finally, return the list of unique triplets that sum up to zero.

```
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int left = i + 1, right = n - 1, target = -nums[i];
                while (left < right) {
                    if (nums[left] + nums[right] == target) {
                        result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    } else if (nums[left] + nums[right] < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return result;```

# Revision Notes: Merge Sorted Arrays with O(m + n) Time Complexity

## Problem Statement:

You are given two integer arrays `nums1` and `nums2`, sorted in non-decreasing order, and two integers `m` and `n`, representing the number of elements in `nums1` and `nums2`, respectively. The task is to merge `nums2` into `nums1` such that the final sorted array is stored inside `nums1`.

## Edge Cases to Handle:

1. **Empty Arrays**: Ensure correct behavior when one or both input arrays (`nums1` and `nums2`) are empty.
2. **Arrays with All Zeroes**: Handle scenarios where either `nums1` or `nums2` consists only of zeroes.
3. **Arrays with All Elements in One Array**: Consider cases when all elements are present in one of the arrays, and the other array is empty.
4. **General Case**: Merging two sorted arrays with non-zero elements.

## Approach with O(m + n) Time Complexity:

1. Initialize pointers `p1`, `p2`, and `p`:
   - `p1`: Points to the last non-zero element in `nums1` (index `m - 1`).
   - `p2`: Points to the last element in `nums2` (index `n - 1`).
   - `p`: Points to the last position in `nums1` (index `m + n - 1`) where we will insert elements.

2. While `p1` and `p2` are within bounds (greater than or equal to 0):
   - Compare `nums1[p1]` and `nums2[p2]`.
   - If `nums1[p1]` is greater, set `nums1[p]` to `nums1[p1]` and decrement both `p1` and `p`.
   - Otherwise, set `nums1[p]` to `nums2[p2]` and decrement `p2` and `p`.

3. If `p2` is still within bounds (greater than or equal to 0), copy the remaining elements from `nums2` to `nums1` in the positions from 0 to `p2`.

4. This approach doesn't involve sorting and runs in O(m + n) time because it goes through each element of both arrays exactly once.

## Java Code:

```java
class MergeSortedArrays {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Implementation code here
    }
}
