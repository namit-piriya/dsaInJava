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

}
