# Flattening a Binary Tree to a Linked List - Revision Notes

## Objective

The objective is to flatten a binary tree into a linked list while maintaining the preorder traversal order.

## Approach

1. **Base Case Handling:**
    - If the current root is `null`, return as there's nothing to flatten.

2. **Store Left and Right Subtrees:**
    - Store references to the left and right subtrees of the current root.

3. **Flatten the Left Subtree:**
    - Recursively flatten the left subtree by calling `flatten(leftTree)`.

4. **Finding the Rightmost Node in the Left Subtree:**
    - Traverse the left subtree to find the rightmost node, ensuring we locate the end of the linked list formed by the left subtree.

5. **Flatten the Right Subtree:**
    - Recursively flatten the right subtree by calling `flatten(rightTree)`.

6. **Attach the Right Subtree:**
    - If there was a rightmost node in the flattened left subtree (i.e., `curr` is not `null`), attach the flattened right subtree to it. This step connects the end of the left subtree to the beginning of the right subtree.

7. **Attach the Left Subtree to the Root:**
    - Attach the flattened left subtree to the root's right child. This step connects the root to the beginning of the left subtree, effectively forming the flattened linked list.



```
// approach is first you have to put left tree in the right which will 
// form a linked list then traverse to the end and put the right ll.
var leftTree = root.left;
        var rightTree = root.right;
        flatten(leftTree);
        flatten(rightTree);
        root.left = null;
        root.right = leftTree;
        var curr = root;
        while (curr.right != null) {
            curr = curr.right;
        }
        curr.right = rightTree;

```


## Code Explanation

- The provided code implements the above approach in Java, efficiently flattening a binary tree into a linked list in preorder traversal order.
- It handles base cases, stores subtrees, and appropriately attaches the flattened parts of the tree.
- The code works in place and is efficient.

This approach ensures that the flattened linked list maintains the original order of nodes as seen in a preorder traversal of the binary tree.

## Problem: Finding the Lowest Common Ancestor (LCA) in a Binary Search Tree (BST)

### Intuition
- The LCA is the lowest node that has both `p` and `q` as descendants in a BST.
- We can efficiently find the LCA by comparing values while traversing the BST.
- If the current node's value is greater than both `p` and `q`, we move left.
- If the current node's value is less than both `p` and `q`, we move right.
- When neither of the above conditions is met, the current node is the LCA.

### Approach
1. Start at the root of the BST.
2. Compare values of the current node with `p` and `q`.
3. Move left or right based on comparisons.
4. Find the LCA by following the conditions above.
5. Repeat until the LCA is found.

### Java Code
```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    while (root != null) {
        if (root.val > p.val && root.val > q.val) {
            root = root.left;
        } else if (root.val < p.val && root.val < q.val) {
            root = root.right;
        } else {
            // Current node is the LCA
            return root;
        }
    }
    return null; // LCA not found
}
