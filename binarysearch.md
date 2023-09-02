# Finding Minimum Element in a Sorted Rotated Array

## Problem Statement

Suppose an array of length `n` sorted in ascending order is rotated between 1 and `n` times. Given a sorted rotated array `nums` of unique elements, return the minimum element of this array.

The algorithm must run in O(log n) time.

## Approach: Binary Search

### Intuition

To find the minimum element in a rotated sorted array, we can use a binary search algorithm. Here's the intuition:

1. Divide the array into two halves and compare the middle element (`mid`) with the first element (`nums[0]`).

2. If `mid` is greater than `nums[0]`, it means the minimum element is in the right half. This is because in a sorted array, elements on the left side are greater than elements on the right side.

3. If `mid` is less than `nums[0]`, it means the minimum element is in the left half, including `mid`. This is because the minimum element in a rotated array will always be part of the unsorted portion, which includes the left half.

4. Repeat the process, halving the search range each time, until we find the minimum element.

### Why Go to the Right Half?

The key insight here is that when `mid` is greater than `nums[0]`, it implies that the entire left half of the array, including `mid`, is sorted and does not contain the minimum element. Therefore, we can confidently eliminate this part of the array from consideration.

Going to the right half allows us to focus our search on the unsorted portion, where the minimum element is guaranteed to be. By repeatedly halving the search range in this manner, we efficiently pinpoint the minimum element without examining unnecessary elements.

### Time Complexity

- In each step, we halve the search range, so the algorithm runs in O(log n) time.

### Space Complexity

- The algorithm uses only a constant amount of extra space, so the space complexity is O(1).

## Java Code

```java
class FindMinimumInRotatedArray {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                // Minimum is in the right half
                left = mid + 1;
            } else {
                // Minimum is in the left half, including mid
                right = mid;
            }
        }

        return nums[left];
    }
}
