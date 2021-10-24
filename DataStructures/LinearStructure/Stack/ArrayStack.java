package LinearStructure.Stack;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author tomable
 * @create 2021-08-29-19:23
 */
public class ArrayStack {
    public static void main(String[] args) {
        //测试
        ArrStack stack = new ArrStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop){
            System.out.println("S：显示栈");
            System.out.println("E：退出程序");
            System.out.println("Push：入栈");
            System.out.println("Pop：出栈");

            key = scanner.next();
            switch (key){
                case "S":
                    stack.list();
                    break;
                case "push":
                    System.out.println("输入数字");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.println("出栈的数据是" + res);
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                case "E":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }
}


//定义一个类 ArrStack ,表示栈
class ArrStack {
    private int maxSize; //栈的大小
    private int[] stack; //数组，模拟栈
    private int top = -1; //栈顶

    //构造器
    public ArrStack(int maxSize){
         this.maxSize = maxSize;
         stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈
    public void push(int value){
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空，没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历
    public void list(){
        if (isEmpty()){
            throw new RuntimeException("栈空，没有数据");
        }
        for (int i = top; i >= 0; i--){
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }


}
