import java.util.Stack;

class MinStack {


    /*
    Push pop and top can be done in O(1) but the getMin will take time.

     */

    private final Stack<Integer> stack;

    private final Stack<Integer> minStack;

    public MinStack() {

        this.minStack = new Stack<>();
        this.stack = new Stack<>();
    }

    public void push(int val) {
        Integer currMin = this.stack.isEmpty() ? null : this.minStack.peek();
        this.stack.push(val);
        if(currMin == null || val < currMin){
            this.minStack.push(val);
        }
        else this.minStack.push(currMin);
    }

    public void pop() {
        this.stack.pop();
        this.minStack.pop();
    }

    public int top() {
        return this.stack.peek();
    }

    public int getMin() {
        return this.minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */


// linked list whatever the stack is maintain that if you find any min then maintain min in a linked list
//
