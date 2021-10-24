package Sort;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 堆排序
 *
 * @author tomable
 * @create 2021-10-15-18:29
 */
public class HeapSort {
    public static void main(String[] args) {
//        int arr[] = {4, 6, 8, 5, 9, -1, 90, 89};
//        heapSort(arr);

        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int)(Math.random() * 800000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前" + date1Str);

        heapSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后" + date2Str);
    }

    //堆排序的方法
    public static void heapSort(int arr[]) {
        int temp = 0;
        //1. 将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        System.out.println("堆排序！");
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        //2. 将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
        //3. 反复执行调整+交换步骤，直到整个序列有序。
        for (int j = arr.length - 1; j > 0; j--) {
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
    }

    /**
     * 功能： 将数组（二叉树），调整成一个大顶堆
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子结点在数组中索引
     * @param lenght 表示对多少个元素进行调整
     */
    public static void adjustHeap(int arr[], int i, int lenght) {
        int temp = arr[i];  //先取出当前元素的值，保存在临时变量
        //开始调整
        for (int k = i * 2 + 1; k < lenght; k = k * 2 + 1) {
            if (k + 1 < lenght && arr[k] < arr[k + 1]) {   //说明左子结点的值小于右子结点的值
                k++;    //k指向右子结点
            }
            if (arr[k] > temp) {   //如果子结点大于父结点
                arr[i] = arr[k];  //把较大的值赋给当前结点
                i = k;    //!!!i指向k,继续循环比较
            } else {
                break;
            }
        }

        //for循环结束后，已经将以i为父节点的树的最大值，放在了最顶上
        arr[i] = temp;    //将temp值放到调整后的位置
    }
}

