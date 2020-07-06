package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RadixSort {
    public static void main(String[] args) {
//        //测试
//        int[] array = {4, 356, 5, 74, 65, 14, 2, 33};
//        System.out.println("排序前");
//        System.out.println(Arrays.toString(array));
//        System.out.println("排序后");
//        radixSort(array);
//        System.out.println(Arrays.toString(array));
        //速度测试
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        System.out.println("排序前的时间:" + simpleDateFormat.format(date1));
        radixSort(arr);
        Date date2 = new Date();
        System.out.println("排序后的时间" + simpleDateFormat.format(date2));
    }

    //基数排序
    public static void radixSort(int[] arr) {
        //找到数组中最大的数,并获得它的位数
        //默认最大的数为第一个
        int max = arr[0];
        for (int d = 1; d < arr.length; d++) {
            //若找到比max大的值，则将其赋值给max
            if (arr[d] > max) {
                max = arr[d];
            }
        }
        //最大数的位数
        int maxLength = (max + "").length();

        //创建10个由于排序的数组
        int[][] bucket = new int[10][arr.length];
        //创建一个用于计数10个数组分别有多少数据的一维数组
        int[] bucketElementCount = new int[10];

        //循环
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //针对各个元素的位(个，十，百)放入对应的数组里
            for (int j = 0; j < arr.length; j++) {
                //取出相应位的值
                int digitOfElement = arr[j] / n % 10;
                //将arr中的数放入对应数组
                bucket[digitOfElement][bucketElementCount[digitOfElement]++] = arr[j];
            }
            //将各个数组中的数依次放回arr中
            int index = 0;
            //遍历每一个数组(桶)
            for (int k = 0; k < bucketElementCount.length; k++) {
                //若数组内有数据，则遍历数组，取出数据，放入原数组arr
                if (bucketElementCount[k] != 0) {
                    for (int l = 0; l < bucketElementCount[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                }
                //处理后，要将每个对应bucket的bucketElementCount的数据清空
                bucketElementCount[k] = 0;
            }
        }
    }
}
