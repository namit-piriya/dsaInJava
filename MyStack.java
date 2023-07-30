import java.util.ArrayDeque;

class MyStack {

    private ArrayDeque<Integer> secondQ;

    private ArrayDeque<Integer> currQ;

    public MyStack() {
        ArrayDeque<Integer> firstQ = new ArrayDeque<Integer>();
        ArrayDeque<Integer> secQ = new ArrayDeque<Integer>();
        this.currQ = firstQ;
        this.secondQ = secQ;
    }

    public void push(int x) {
        this.currQ.offer(x);
        if (!this.secondQ.isEmpty()) {
            for (Integer i : this.secondQ) {
                this.currQ.offer(i);
                this.secondQ.poll();
            }
        }
        var temp = this.currQ;
        this.currQ = this.secondQ;
        this.secondQ = temp;
    }

    public int pop() {
        var num = this.secondQ.peek();
        if (num != null)
            return this.secondQ.poll();
        return 0;
    }

    public int top() {
        var num = this.secondQ.peek();
        if (num != null)
            return this.secondQ.peek();

        return 0;
    }

    public boolean empty() {
        return this.secondQ.isEmpty();
    }
}