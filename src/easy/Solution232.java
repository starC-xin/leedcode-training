package easy;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 2023/6/17
 *
 * @author x.z
 */
public class Solution232 {
    List<Integer> list = new LinkedList<>();

    public Solution232() {}

    public void push(int x) {
        list.add(x);
    }

    public int pop() {
        return list.remove(0);
    }

    public int peek() {
        return list.get(0);
    }

    public boolean empty() {
        return list.isEmpty();
    }


    /**
     * 示例代码
     */
    static class MyQueue {

        Stack<Integer> stack;

        public MyQueue() {
            stack = new Stack<>();
        }

        public void push(int x) {
            stack.add(x);
        }

        public int pop() {
            Stack<Integer> newStack = new Stack<>();
            while (stack.size() > 1) {
                newStack.add(stack.pop());
            }
            int ret = stack.pop();

            while (newStack.size() > 0) {
                stack.add(newStack.pop());
            }

            return ret;
        }

        public int peek() {
            Stack<Integer> newStack = new Stack<>();
            while (stack.size() > 1) {
                newStack.add(stack.pop());
            }
            int ret = stack.pop();

            stack.add(ret);

            while (newStack.size() > 0) {
                stack.add(newStack.pop());
            }

            return ret;
        }

        public boolean empty() {
            return stack.isEmpty();
        }
    }
}
