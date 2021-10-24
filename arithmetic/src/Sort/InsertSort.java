package Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author tomable
 * @create 2021-09-02-19:23
 */
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1};
//        System.out.println(Arrays.toString(arr));
//        insertSort(arr);
//        System.out.println(Arrays.toString(arr));

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 800000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前" + date1Str);

        insertSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后" + date2Str);
    }

    //插入排序
    public static void insertSort(int[] arr){
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {

            insertVal = arr[i];
            insertIndex = i - 1; //

            //找到要 插入的位置
            while (insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //插入
            if (insertVal + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
    }
    
}
