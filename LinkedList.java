import java.util.List;

public class LinkedList {
    public ListNode reverseList(ListNode head) {
        // Base case: empty list or single node
        if (head == null || head.next == null) {
            return head;
        }

        // Reverse the rest of the list recursively
        ListNode reversedList = reverseList(head.next);

        // Reverse the current node
        head.next.next = head;
        head.next = null;

        return reversedList;
    }

    public ListNode partitionTest(ListNode head, int x) {
        ListNode dummy = new ListNode(-10, head);
        ListNode temp = head;
        for (int i = 0; i < x; i++) {
            temp = temp.next;
        }
        int val = temp.val;
        ListNode curr = dummy;
        while (curr.next != temp) {
            if (curr.next != null && curr.next.val > val) {
                ListNode neighbor = temp.next;
                temp.next = curr.next;
                curr.next = curr.next.next;
                curr.next.next = neighbor;
            }
            else curr = curr.next;
        }
        ListNode prev = curr;
//        1-> 2 -> 3 -> 4 -> 5
        while (curr.next != null){
            ListNode next = curr.next;
            if(next.val < val){
                ListNode neighbor = prev.next;
                prev.next = next;
                curr.next = next.next;
                next.next = neighbor;
            }
            else  curr = curr.next;
        }
        return dummy.next;
    }

    public ListNode partition(ListNode head, int x) {
        ListNode left = new ListNode(-10);
        ListNode start = left;
        ListNode right = new ListNode(-10);
        ListNode rightStart = right;
        ListNode curr = head;
        while(curr != null){
            if(curr.val < x){
                left.next = curr;
                left = left.next;
            }
            else {

                right.next = curr;
                right = right.next;
            }
            curr = curr.next;
        }
        if(start.next == null) return rightStart.next;
        System.out.println("Hello");
        left.next = rightStart.next;
        right.next = null;
        head = start.next;
        return head;
    }
}
