package Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author tomable
 * @create 2021-09-04-20:48
 */
public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {-9, 78, 0, 23, -567, 70};
//        quickSort(arr, 0, arr.length - 1);
//        System.out.println(Arrays.toString(arr));
        int[] arr = new int[800000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 800000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前" + date1Str);

        quickSort(arr, 0, arr.length - 1);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后" + date2Str);
    }

    //快速排序
    public static void quickSort(int[] arr, int left, int right){
        int l = left;   //左下标
        int r = right;  //右下标
        int privot = arr[(left + right) / 2]; //中轴
        int temp = 0;
        while (l < r){ //
            while (arr[l] < privot){
                l += 1;
            }
            while (arr[r] > privot){
                r -= 1;
            }
            if (l >= r){
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            if (arr[l] == privot){
                r -= 1;
            }
            if (arr[r] == privot){
                l += 1;
            }
        }

        //如果 l==r,必须L++ ,r--.
        if (l == r){
            l += 1;
            r -= 1;
        }
        if (left < r){//左递归
            quickSort(arr, left, r);
        }
        if (right > l){ //右递归
            quickSort(arr, l, right);
        }

    }
}





