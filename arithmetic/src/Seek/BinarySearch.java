package Seek;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tomable
 * @create 2021-09-24-18:07
 */
public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1000, 1000, 1000, 1234};

//        int resIndex = binarySearch(arr, 0, arr.length - 1, 88);
//        System.out.println(resIndex);

        List<Integer> resIndexList = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println(resIndexList);
    }

    /**
     * 二分查找算法
     *
     * @param arr     数组
     * @param left    左边的索引
     * @param right   右边的索引
     * @param findVal 要查找的值
     * @return 如果找到就返回下标，没找到就返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        //当left>right 时，说明递归整个数组都没有找到
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) { //向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { //向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    /**
     * 二分查找算法 ，可以查找多个
     *
     * @param arr     数组
     * @param left    左边的索引
     * @param right   右边的索引
     * @param findVal 要查找的值
     * @return 如果找到就返回下标，没找到就返回-1
     */
    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        //当left>right 时，说明递归整个数组都没有找到
        if (left > right) {
            return new ArrayList<Integer>();
        }

        //查找算法
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) { //向右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { //向左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {    //找到mid值，继续查找
            ArrayList<Integer> resIndexlist = new ArrayList<Integer>();  //存放finfVal的集合
            //向左扫描
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) { //退出
                    break;
                }
                resIndexlist.add(temp); //temp放入数组
                temp -= 1;    //temp左移
            }

            resIndexlist.add(mid);

            //向右扫描
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) { //退出
                    break;
                }
                resIndexlist.add(temp); //temp放入数组
                temp += 1;    //temp左移
            }
            return resIndexlist;
        }
    }

}
