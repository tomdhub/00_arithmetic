package Recursion;

/**
 * @author tomable
 * @create 2021-08-31-15:54
 */
public class Recursion {
    public static void main(String[] args) {
//        test(4);
        int res = factorial(3);
        System.out.println("res=" + res);
    }

    //打印问题
    public static void test(int n){
        if (n > 2){
            test(n - 1);
        } else {
          System.out.println("n=" + n);
        }
    }

    //阶乘问题
    public static int factorial(int n){
        if (n == 1){
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }

}
