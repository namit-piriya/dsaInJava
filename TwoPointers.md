# Finding Maximum Water Container

## Problem Statement

Given an integer array `height` of length `n`, where each element represents the height of a vertical line, you need to find two lines that, together with the x-axis, form a container containing the most water. Return the maximum amount of water the container can store.

## Approach: Two-Pointer Approach

### Intuition

- Use two pointers, one starting from the beginning (left pointer) and the other from the end (right pointer) of the `height` array.

- Calculate the area formed by the two vertical lines (heights) and the width between them (the difference in indices of the two lines).

- To maximize the area, you need to find taller lines. Move the pointers inward by comparing the heights of the lines at the current positions. If the pointer pointing to the shorter line is moved, there's a chance of finding a taller line that can increase the area.

- Keep track of the maximum area found so far and update it whenever a larger area is encountered.

### Why the Two-Pointer Approach?

- The two-pointer approach allows you to efficiently explore the entire array once, as you move the pointers inward, focusing on potentially taller lines.

- By comparing and updating the maximum area, you ensure that you find the optimal pair of lines that forms the largest container.

### Time Complexity

- The time complexity is O(n), where `n` is the number of lines (heights). You iterate through the array once.

### Space Complexity

- The space complexity is O(1) because the algorithm uses a constant amount of extra space.

## Java Code

```java
class MaxWaterContainer {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int h1 = height[left];
            int h2 = height[right];
            int width = right - left;

            // Calculate the area formed by the two lines.
            int currentArea = Math.min(h1, h2) * width;
            
            // Update maxArea if the current area is larger.
            maxArea = Math.max(maxArea, currentArea);

            // Move the pointers towards each other.
            if (h1 < h2) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}
