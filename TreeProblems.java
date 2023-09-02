import java.util.ArrayList;
import java.util.List;

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
            if(!pTraversal.get(i).equals(qTraversal.get(i))){
                return false;
            }
        }
        return true;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p== null && q == null){
            return true;
        }

        if(p == null || q == null || p.val != q.val){
            return false;
        }

        return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }


    public void flatten(TreeNode root) {
        if(root == null){return;}
        var leftTree = root.left;
        var rightTree = root.right;
        flatten(leftTree);
        flatten(rightTree);
        root.left = null;
        root.right = leftTree;
        var curr = root;
        while(curr.right != null){
            curr = curr.right;
        }
        curr.right = rightTree;
    }

}
