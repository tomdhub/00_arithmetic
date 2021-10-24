package LinearStructure.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author tomable
 * @create 2021-08-30-20:19
 * 下列逆波兰计算器均不支持小数点
 */
public class PolandNotation {
    public static void main(String[] args) {
        //----------------------  逆波兰计算器  -------------------------
        //直接输入后缀表达式
        //(3+4)*5-6  >>>  3 4 + 5 * 6 -
//        String suffixExpression = "30 4 + 5 * 6 -";
//
//        List<String> List = getListString(suffixExpression);
//        System.out.println(List);
//
//        int res = calculate(List);
//        System.out.println("计算结果是" + res);

        //----------------------  逆波兰计算器  -------------------------
        //输入中缀表达式表达式
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式：" + infixExpressionList);

        //转换过程
        List<String> suffixExpressionList = parseSuffixExpreesionList(infixExpressionList);
        System.out.println("后缀表达式：" + suffixExpressionList);

        //计算
        System.out.println(expression + " 结果为：" + calculate(suffixExpressionList));
    }

    //将中缀表达式转换为对应的LIst，重点
    public static List<String> toInfixExpressionList(String s){
        //定义一个List，存放中缀表达式 对应的内容
        List<String> ls = new ArrayList<String>();
        int i = 0; //指针，用来遍历 中缀表达式字符串
        String str; //对多位数的拼接
        char c; //每遍历到一个字符，就放入到c
        do{
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57){ //字符
                ls.add("" + c);
                i++; //i需要后移
            }else { //数字，需要考虑多位数
                str = ""; //置""
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57){
                    str += c; //拼接
                    i++;
                }
                ls.add(str);
            }
        }while (i < s.length());
        return ls;
    }

    //中转后缀，重点
    public static List<String> parseSuffixExpreesionList(List<String> ls){
        //定义两个栈
        Stack<String> s1 = new Stack<String>(); //符号栈

//        Stack<String> s2 = new Stack<String>(); //存储中间结果栈
        List<String> s2 = new ArrayList<>(); //存储中间结果栈

        //遍历ls
        for (String item: ls){
            if (item.matches("\\d+")){//数字直接入栈
                s2.add(item);
            } else if (item.equals("(")){
                s1.push(item);
            } else if (item.equals(")")){
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }

        while (s1.size() != 0){
            s2.add(s1.pop());
        }

        return s2;
    }

    //将逆波兰表达式，放入到 ArrayList 中
    public static List<String>getListString(String suffixExpression){
        //分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele: split){
            list.add(ele);
        }
        return list;
    }

    //逆波兰表达式的运算
    public static int calculate(List<String>ls){
        //创建栈
        Stack<String> stack = new Stack<String>();
        //遍历 ls
        for (String item: ls){
            //使用正则表达式来取出数
            if (item.matches("\\d+")){ //匹配的是多位数
                //入栈
                stack.push(item);
            }else {
                //pop 出两个数
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")){
                    res = num1 + num2;
                }else if (item.equals("-")){
                    res = num1 - num2;
                }else if (item.equals("*")){
                    res = num1 * num2;
                }else if (item.equals("/")){
                    res = num1 / num2;
                }else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

//Operation 返回一个运算符 对应的优先级
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 1;
    private static int DIV = 1;

    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("符号输入错误");
                break;
        }
        return result;
    }
}
