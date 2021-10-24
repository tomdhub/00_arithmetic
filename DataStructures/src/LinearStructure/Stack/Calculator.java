package LinearStructure.Stack;

/**
 * @author tomable
 * @create 2021-08-30-18:54
 */
public class Calculator {
    public static void main(String[] args) {
        String expression = "70+2*6-2";

        ArrStack2 numStack = new ArrStack2(10);
        ArrStack2 operStack = new ArrStack2(10);
        int index = 0; //用于扫描
        int num1 = 0, num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' '; //将扫描得到char保存到ch
        String keepNum = ""; //用于拼接多位数

        while (true){
            //依次得到 expression 的每一个字符
            ch = expression.substring(index,index+1).charAt(0);

            if (operStack.isOper(ch)){ //如果是运算符
                if (!operStack.isEmpty()){ //符号栈是否为空
                    //如果符号栈有操作符，就进行比较,
                    //如果当前的操作符的优先级小于或者等于栈中的操作符,就需要从数栈中pop出两个数
                    //在从符号楫中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();

                        res = numStack.cal(num1, num2, oper);

                        numStack.push(res);
                        operStack.push(ch);
                    }else {
                        operStack.push(ch);
                    }
                }else {
                    //如果符号栈为空，则直接入栈
                    operStack.push(ch);
                }
            }else { //为数字
//                numStack.push(ch - 48);
                keepNum += ch;

                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));

                        keepNum = "";
                    }
                }
            }

            index++;
            if (index >= expression.length()){
                break;
            }
        }

        while (true){
            if (operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        System.out.println(expression + " 最终的结果：" + numStack.pop());
    }
}


class ArrStack2 {
    private int maxSize; //栈的大小
    private int[] stack; //数组，模拟栈
    private int top = -1; //栈顶

    //构造器
    public ArrStack2(int maxSize){
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

    //返回栈顶
    public int peek(){
        return stack[top];
    }

    //返回顺序的预先级，数字越大优先级越高
    public int priority(int oper){
        if (oper == '*' || oper == '/'){
            return 1;
        }else if (oper == '+' || oper == '-'){
            return 0;
        }else {
            return -1;
        }
    }

    //判断是否为运算符
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper){
        int res = 0;
        switch (oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}




