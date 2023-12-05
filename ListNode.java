import java.util.List;

public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    // have three variables to swap the pointers and return the last node;
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode curr, next, previous;
        previous = null;
        curr = head;
        while (curr != null) {
            next = curr.next;
            curr.next = previous;
            previous = curr;
            curr = next;
        }
        return previous;
    }

//     ListNode helper(ListNode node, ListNode ans){
//          if(node == null){
//              return null;
//          }
//          ListNode next = node.next;
//          if(next == null){
//              ans = node;
//              return node;
//          }
//          ListNode ptr = helper(next,ans);
//          ptr.next = node;
//          return node;
//     }
//
//     public ListNode reverseListRecursive(ListNode head){
//          ListNode ans = new ListNode();
//          this.helper(head,ans);
//          return ans;
//     }

    public ListNode reverseListRec(ListNode head) {
        // Base case: empty list or single node
        if (head == null || head.next == null) {
            return head;
        }
        // 1-2-3-4-5
        // Reverse the rest of the list recursively
        ListNode reversedList = reverseList(head.next);

        // Reverse the current node
        head.next.next = head;
        head.next = null;

        return reversedList;
    }


//    https://leetcode.com/problems/linked-list-cycle-ii/
    public ListNode detectCycle(ListNode head) {
        if (head == null) return head;
        ListNode ans;
        ListNode slow = head;
        ListNode fast = head;
        boolean cycle;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                cycle = true;
                break;
            }
        }
        if(cycle){
//            from the linked list start and the point where slow and fast meet
//            the intersection point is at the same distance
            ListNode curr = head;
            while(curr != slow){
                curr = curr.next;
                slow = slow.next;
            }
            ans = curr;
        }
        return ans;
    }


}

