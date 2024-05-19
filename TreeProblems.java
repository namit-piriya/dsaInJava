import java.util.*;

public class TreeProblems {
    public void inorder(TreeNode root, List<Integer> traversal) {
        if (root == null) {
            return;
        }
        this.inorder(root.left, traversal);
        traversal.add(root.val);
        this.inorder(root.right, traversal);
    }

    public boolean isSameTreeFailed(TreeNode p, TreeNode q) {
        List<Integer> pTraversal = new ArrayList<>();
        this.inorder(p, pTraversal);
        List<Integer> qTraversal = new ArrayList<>();
        this.inorder(q, qTraversal);
        if (pTraversal.size() != qTraversal.size()) {
            return false;
        }

        for (int i = 0; i < pTraversal.size(); i++) {
            if (!pTraversal.get(i).equals(qTraversal.get(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null || p.val != q.val) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }


    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
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
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root.val == p.val || root.val == q.val) {
            return root;
        }
        var left = lowestCommonAncestor(root.left, p, q);
        var right = lowestCommonAncestor(root.right, p, q);

        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else return root;
    }


    //    https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        if (root == null) return null;
        Queue<TreeNode> q = new ArrayDeque<>();
        List<Integer> ans = new ArrayList<>();
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        q.offer(root);
        while (!q.isEmpty()) {
            var qSize = q.size();
            while (qSize-- > 0) {
                var node = q.poll();
                if (node.left != null) {
                    parent.put(node.left, node);
                    q.offer(node.left);
                }
                if (node.right != null) {
                    parent.put(node.right, node);
                    q.offer(node.right);
                }
            }
        }

        Set<TreeNode> s = new HashSet<>();
        q.offer(root);
        while (!q.isEmpty()) {
            var qSize = q.size();
            while (qSize-- > 0) {
                var node = q.poll();
                if (node.val == target.val) {
                    this.findLevel(node, s, k, 0, ans, parent);
                    return ans;
                }
                if (node.left != null) {
                    parent.put(node.left, node);
                    q.offer(node.left);
                }
                if (node.right != null) {
                    parent.put(node.right, node);
                    q.offer(node.right);
                }
            }
        }
        return ans;
    }

    private void findLevel(TreeNode node, Set<TreeNode> visited,
                           int maxLevel,
                           int level, List<Integer> ans, Map<TreeNode, TreeNode> parentMap) {
        if (level > maxLevel) return;
        if (node == null) return;
        if (visited.contains(node)) {
            return;
        }
        if (level == maxLevel) {
            ans.add(node.val);
        }
        visited.add(node);
        findLevel(node.left, visited, maxLevel, level + 1, ans, parentMap);
        findLevel(node.right, visited, maxLevel, level + 1, ans, parentMap);
        findLevel(parentMap.get(node), visited, maxLevel, level + 1, ans, parentMap);
    }


    //    https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/
    public int maxLevelSum(TreeNode root) {
        if (root == null) return 0;
        int ans = Integer.MIN_VALUE;
        var queue = new ArrayDeque<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            var currSum = 0;
            var queueSize = queue.size();
            while (queueSize-- > 0) {
                var node = queue.poll();
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
                currSum += node.val;
            }
            if (currSum > ans) {
                ans = currSum;
            }
        }
        return ans;
    }


    private int kthSmallestHelper_count = 0;
    private int kthSmallestHelper_result = -1;

    private void kthSmallestHelper(TreeNode root, int k) {
        if (root == null) return;
        kthSmallestHelper(root.left, k);
        kthSmallestHelper_count++;
        if (kthSmallestHelper_count == k) kthSmallestHelper_result = root.val;
        kthSmallestHelper(root.right, k);
    }

    public int kthSmallest(TreeNode root, int k) {
        var currLevel = 0;
        kthSmallestHelper(root, k);
        return kthSmallestHelper_result;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return helper(inorder, postorder, postorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode helper(int[] inorder, int[] postorder, int postStart, int inStart, int inEnd) {
        int i = inStart;
       if(postStart <0 )return null;
       TreeNode root = new TreeNode(postStart);
       if(inStart == inEnd){
           return root;
       }
       for (; i <= inEnd; i++) {
            if (postorder[postStart] == inorder[i]) break;
        }
        int rightSize = inEnd - i;
        root.left = helper(inorder, postorder, postStart-1-rightSize, inStart, i - 1);
        root.right = helper(inorder, postorder, postStart-1, i + 1, inEnd);
        return root;
    }
//https://leetcode.com/problems/sum-root-to-leaf-numbers
    public int sumNumbers(TreeNode root) {
        return sumHelper(root,0,0);
    }

    private int sumHelper(TreeNode root, int i, double val) {
        if(root == null){
            return 0;
        }
        double currSum = val * 10 + root.val;
        if (root.left == null && root.right == null) {
            return (int) currSum;
        };
        return sumHelper(root.left,i+1, currSum) + sumHelper(root.right,i+1, currSum);
    }
}
