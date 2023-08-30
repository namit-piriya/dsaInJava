import java.util.List;

public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }

     // have three variables to swap the pointers and return the last node;
     public ListNode reverseList(ListNode head) {
         if(head == null){
             return null;
         }
         ListNode curr, next, previous;
         previous = null;
         curr = head;
         while(curr != null){
             next = curr.next;
             curr.next = previous;
             previous = curr;
             curr = next;
         }
         return previous;
     }

     ListNode helper(ListNode node, ListNode ans){
          if(node == null){
              return null;
          }
          ListNode next = node.next;
          if(next == null){
              ans = node;
              return node;
          }
          ListNode ptr = helper(next,ans);
          ptr.next = node;
          return node;
     }

     public ListNode reverseListRecursive(ListNode head){
          ListNode ans = new ListNode();
          this.helper(head,ans);
          return ans;
     }

  }

