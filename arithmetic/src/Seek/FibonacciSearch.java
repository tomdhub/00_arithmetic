package Seek;

import java.util.Arrays;

/**
 * @author tomable
 * @create 2021-09-26-20:12
 */
public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1234};

        System.out.println("下标："+fibSearch(arr,1234));
    }

    //因为后面我们mid=low+F(k-1)-1 ,需要使用到斐波那契数列，因此我们需要先获取一个斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 斐波那契算法
     *
     * @param a   数组
     * @param key 查找的关键值
     * @return
     */
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0;   //表示斐波那契分割值的下标
        int mid = 0; //存放mid值
        int f[] = fib(); //获取到斐波那契数列

        //获取斐波那契分割值的下标
        while (high > f[k] - 1) {
            k++;
        }

        //因为f[k] 值可能大于a的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向temp[]
        int[] temp = Arrays.copyOf(a, f[k]); //该方法，不足的部分使用0填充
        for (int i = high + 1; i < temp.length; i++) {    //需要改为最后的数填充temp
            temp[i] = a[high];
        }

        //使用while 循环，找到数key
        while (low <= high) { //满足此条件，就可以查找
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) { //继续向数组的前面查找（左）
                high = mid - 1;
                k--;
            } else if (key > temp[mid]) {    //继续向数组的后面查找（右）
                low = mid + 1;
                k -= 2;
            } else { //找到
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
