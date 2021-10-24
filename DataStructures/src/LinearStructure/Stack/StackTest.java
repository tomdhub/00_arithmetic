package LinearStructure.Stack;

import java.util.Stack;

/**
 * @author tomable
 * @create 2021-08-28-17:22
 */
public class StackTest {
    public static void main(String[] args) {
        //演示栈的基本使用
        Stack<String> stack = new Stack();
        //入栈
        stack.add("1");
        stack.add("2");
        stack.add("3");
        stack.add("4");

        //出栈
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }

    }
}
