package Recursion;

/**
 * @author tomable
 * @create 2021-08-31-19:50
 */
public class Queue8 {
    int max = 8; //表示有多少个皇后
    //定义数组array，保存皇后放置位置的结果。比如anr = {0 ,4,7,5,2,6,1,3}
    int[] array = new int[max];

    static int count= 0; //解法数量
    static int judgeCount =0;   //判断冲突标志


    public static void main(String[] args){
        //测试一把﹐8皇后
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d解法", count);
        System.out.println();
        System.out.printf("一共判断冲突的次数%d次", judgeCount); //1.5w
    }

    //放置方法
    private void check(int n){
        if (n == max) {
            print();
            count++;
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            array[n] = i;
            if (judge(n)){ //不冲突
                check(n + 1);
            }
            //冲突
            judgeCount++;
        }
    }

    //检查是否与前面已经摆放的皇后冲突
    private boolean judge(int n){
        for (int i = 0; i < n; i++) {
            //判断是否为同一列              是否为同一斜线
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }

    //方法，可以将皇后摆放的位置输出
    private void print(){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

}