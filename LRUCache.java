import java.util.HashMap;
import java.util.Map;

class LRUCache {
    private final int size;
    private final int capacity;
    Map<Integer, DoublyLinkedList> cache;
    DoublyLinkedList head = new DoublyLinkedList(null, null, -1);
    DoublyLinkedList tail = new DoublyLinkedList(null, null, -1);

    static class DoublyLinkedList {
        DoublyLinkedList prev;
        DoublyLinkedList next;
        int data;
        int key;

        public DoublyLinkedList(DoublyLinkedList prev, DoublyLinkedList next, int data) {
            this.prev = prev;
            this.next = next;
            this.data = data;
        }
    }

    public LRUCache(int capacity) {
        head.next = tail;
        tail.prev = head;
        cache = new HashMap<>();
        this.capacity = capacity;
        size = 0;
    }

    public int get(int key) {
        DoublyLinkedList ans = cache.getOrDefault(key, null);
        if (ans != null) {
            putKeyFirst(ans, false);
        }
        return ans == null ? -1 : ans.data;
    }

    private void putKeyFirst(DoublyLinkedList key, boolean firstKey) {
        if (firstKey) {
            key.prev.next = key.next;
            key.next.prev = key.prev;
        }
        key.prev = head;
        key.next = head.next;
        head.next = key;
    }

    private void addKey(int key, int value) {
        DoublyLinkedList curr = new DoublyLinkedList(null, null, value);
        curr.key = key;
        putKeyFirst(curr, true);
        cache.put(key, curr);
    }

    public void put(int key, int value) {
        DoublyLinkedList ans = cache.getOrDefault(key, null);
        if (ans != null) {
            putKeyFirst(ans, false);
            ans.data = value;
        } else {
            if (size > capacity) {
                removeKey();
                addKey(key, value);
            }
            addKey(key, value);
        }
    }

    private void removeKey() {
        tail.prev.next = null;
        tail.prev = tail.prev.prev;
        cache.remove(tail.prev.key);
    }
}