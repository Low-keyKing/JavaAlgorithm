package tree;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class HeapSort {
    public static void main(String[] args) {
//        int[] arr = {4, 6, 8, 5, 9, -1, 90, 89, -555, 99};
//        heapSort(arr);
//        System.out.println(Arrays.toString(arr));
        //速度测试
        int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 800000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss:SSS");
        System.out.println("排序前的时间:" + simpleDateFormat.format(date1));
        heapSort(arr);
        Date date2 = new Date();
        System.out.println("排序后的时间" + simpleDateFormat.format(date2));
    }

    public static void heapSort(int[] arr) {
        //创建进行交换的临时变量
        int temp = 0;
        //将无序序列构成一个堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        //进行交换，将堆顶的数放到底部，然后接着将符合规则的数放置堆顶
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }

    }

    //arr为待排序数列，i为非叶子节点在数组的下标，length为当前尚未确定位置的数的数量
    public static void adjustHeap(int[] arr, int i, int length) {//大顶堆
        //将当前数据存入临时变量中，用于后面进行交换
        int temp = arr[i];
        //左子节点
        int left = 0;
        //右子节点
        int right = 0;
        //创建一个存储较大值的变量
        int largest = 0;
        while (i < length) {
            //左子节点
            left = i * 2 + 1;
            //右子节点
            right = i * 2 + 2;
            //初始化为左子节点
            largest = left;
            if (right < length && arr[right] > arr[left]) {
                largest = right;
            }
            //若父节点较小，则进行交换
            if (largest < length && arr[largest] > arr[i]) {
                arr[i] = arr[largest];
                arr[largest] = temp;
            }
            //i指向进行交换的节点
            i = largest;
        }
    }
}
