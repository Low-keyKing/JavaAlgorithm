package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SelectSort {
    public static void main(String[] args) {
//        int[] array = {4, 3, 5, 7, 6, 1, 2};
//        System.out.println("排序前");
//        System.out.println(Arrays.toString(array));
//        System.out.println("排序后");
//        selectSort(array);
//        System.out.println(Arrays.toString(array));

        //速度测试
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        System.out.println("排序前的时间:" + simpleDateFormat.format(date1));
        selectSort(arr);
        Date date2 = new Date();
        System.out.println("排序后的时间" + simpleDateFormat.format(date2));
    }

    //选择排序
    //每一次循环找到最小的数，然后将该数与最前面的未确定的数进行交换，然后每轮确定一个该轮最小数的排序，以此完成从小到大排序
    public static void selectSort(int[] arr) {
        //遍历
        for (int i = 0; i < arr.length - 1; i++) {//遍历的轮数为数组长度-1轮
            //创建一个用于存放最小数下标的变量
            int minIndex = i;
            //创建一个用于存放最小值的临时变量
            int min = arr[i];
            for (int j = i+1; j < arr.length; j++) {//每轮遍历都会确定一个最小数的位置
                if (min > arr[j]) {//两两判断大小，将较小的一方的下标赋给minIndex,且将较小的数赋予min
                    min = arr[j];
                    minIndex = j;
                }
            }
            //若最小值和要交换的数刚好是一个数，则不需要交换
            if (minIndex != i) {
                //将最小的数和当前未确定位置的第一个数进行交换
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
//            System.out.println("第"+(i+1)+"轮");
//            System.out.println(Arrays.toString(arr));
        }
    }
}
