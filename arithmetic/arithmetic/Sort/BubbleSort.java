package Sort;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author tomable
 * @create 2021-09-02-17:46
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int arr[] = {3, 9, -1, 10, -2};
//        System.out.println("排序前的数组：" + Arrays.toString(arr));
//        bubbleSort(arr);
//        System.out.println("排序后的数组：" + Arrays.toString(arr));

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 800000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前" + date1Str);

        bubbleSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后" + date2Str);

    }

    public static void bubbleSort(int[] arr){
        int temp = 0; //临时变量,交换用
        boolean flag = false; //标识变量，表示是否进行过交换

        for (int i = 0; i < arr.length - 1; i++) {  //
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]){ //后面的数大，则交换
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (flag == false) { //说明没有进行交换
                break;
            }else {
                flag = false;
            }
        }
    }
}
