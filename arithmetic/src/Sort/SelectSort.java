package Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author tomable
 * @create 2021-09-02-18:58
 */
public class SelectSort {
    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1};
//        System.out.println("排序前：" + Arrays.toString(arr));
//        selectSort(arr);

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 800000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前" + date1Str);

        selectSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后" + date2Str);
    }

    //选择排序
    public static void selectSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i; //最小值的数组坐标索引
            int min = arr[i]; //最小数
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]){
                    min = arr[j];
                    minIndex = j;
                }
            }
            //将最小值交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
//            System.out.println("第" + (i + 1) + "次转换：" + Arrays.toString(arr));
        }
    }

}
