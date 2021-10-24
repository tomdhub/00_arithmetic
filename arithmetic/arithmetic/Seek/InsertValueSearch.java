package Seek;

import java.util.Arrays;

/**
 * @author tomable
 * @create 2021-09-25-22:25
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
//        System.out.println(Arrays.toString(arr));
        int index = insertValueSearch(arr,0,arr.length-1,1);
        System.out.println(index);
    }

    /**
     * 查找算法
     * findVal < arr[0] || findVal > arr[arr.length - 1] 必须有，否则 mid 可能越界
     * @param arr     数组
     * @param left    左索引
     * @param right   右索引
     * @param findVal 查找值
     * @return
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }

        //求mid,自适应
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];

        if (findVal > midVal) {    //向右递归
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {  //向左递归
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }

    }

}
