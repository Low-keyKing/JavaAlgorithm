package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MergeSort {
    public static void main(String[] args) {
//        //测试
//        int[] array = {8, 4, 5, 7, 1, 3, 6, 2, 0, 234};
//        //归并排序需要预留一个空间
//        int[] temp = new int[array.length];
//        System.out.println("排序前");
//        System.out.println(Arrays.toString(array));
//        System.out.println("排序后");
//        mergeSort(array, 0, array.length - 1, temp);
//        System.out.println(Arrays.toString(array));
        //速度测试
        int[] arr = new int[80000];
        //归并排序需要预留一个空间
        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        System.out.println("排序前的时间:" + simpleDateFormat.format(date1));
        mergeSort(arr, 0, arr.length - 1, temp);
        Date date2 = new Date();
        System.out.println("排序后的时间" + simpleDateFormat.format(date2));
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            //计算出左边序列的末尾节点
            int mid = (left + right) / 2;
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, mid, right, temp);
        }
    }

    //合并
    //arr为待排序数组,left为左边有序数列的初始索引，mid为左边有序数列的末尾索引，right为右边有序数列的末尾索引，temp为中间数组
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;//初始化i，左边有序数组的初始索引
        int j = mid + 1;//初始化j，右边有序数组的初始索引
        int t = 0;//temp当前的索引

        //将离两边有序序列按顺序放入temp中,直到两边有一边先遍历完
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        //将有剩余的一边的全部数据放入temp中
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        //将temp中排好序的数据放入arr中
        t = 0;//初始化t
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft++] = temp[t++];
        }
    }
}
