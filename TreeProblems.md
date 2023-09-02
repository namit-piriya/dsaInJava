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

## Code Explanation

- The provided code implements the above approach in Java, efficiently flattening a binary tree into a linked list in preorder traversal order.
- It handles base cases, stores subtrees, and appropriately attaches the flattened parts of the tree.
- The code works in place and is efficient.

This approach ensures that the flattened linked list maintains the original order of nodes as seen in a preorder traversal of the binary tree.
