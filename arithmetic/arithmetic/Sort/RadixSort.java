package Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author tomable
 * @create 2021-09-22-21:58
 */
public class RadixSort {
    public static void main(String[] args) {
//        int arr[] = {53, 3, 542, 748, 14, 214};
//        radixSort(arr);
//        System.out.println(Arrays.toString(arr));

        int[] arr = new int[800000];
        for (int i = 0; i < 800000; i++) {
            arr[i] = (int)(Math.random() * 800000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前" + date1Str);

        int temp[] = new int[arr.length]; //归并排序需要一个额外空间
        radixSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后" + date2Str);
    }

    //基数排序方法
    public static void radixSort(int[] arr) {
        //定义一个二维数组，表示十个桶
        int[][] bucket = new int[10][arr.length];

        //定义一个一维数组，记录各个桶的每次放入的数据个数
        //eg: bucketElementCounts[0]，就是bucket[0]桶放入数据的个数
        int[] bucketElementCounts = new int[10];

        //得到数组中最大的数的位数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //得到最大数是几位数
        int maxLength = (max + "").length();

        //排序
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                //取个位数上的值
                int digitOfElement = arr[j] / n % 10;
                //放入对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }

            //按照桶的顺序，放入原来的数组中
            int index = 0;
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据，才放入
                if (bucketElementCounts[k] != 0) {//放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                }
                //放入完成后需要将bucketElementCounts[k] 清零
                bucketElementCounts[k] = 0;
            }
        }

    }
}
