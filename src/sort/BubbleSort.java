package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BubbleSort {
    public static void main(String[] args) {
        //先创建一个待排序的数组
        int[] array = {1, 0, 5, 4, 3};

        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        System.out.println("排序前的时间:" + simpleDateFormat.format(date1));
        bubbleSort(arr);
        Date date2 = new Date();
        System.out.println("排序后的时间" + simpleDateFormat.format(date2));

//        System.out.println("排序前");
//        System.out.println(Arrays.toString(array));
//        bubbleSort(array);
//        System.out.println("排序后");
//        System.out.println(Arrays.toString(array));

    }

    //冒泡排序
    public static void bubbleSort(int[] array) {
        //创建一个临时变量，用于存放数据
        int temp = 0;
        //创建一个进行优化的判断变量
        boolean flag = false;
        //冒泡排序，两个数依次比较，较大的数靠后排，较小的数靠前排，每一轮比较都能将最大的数排在最后
        for (int i = 0; i < array.length - 1; i++) {//最多需要比较数组长度-1轮
            //两两依次比较,每一轮所需要比较的数都会少一个，因为每一轮都会"沉底"一个这一轮中最大的数
            for (int j = 0; j < array.length - 1 - i; j++) {
                //将两个数进行比较，如果前一个数比后一个数大，则交换
                if (array[j] > array[j + 1]) {
                    flag = true;
                    temp = array[j];//将数据存入临时变量里
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            //判断，若在某一轮排序中，一次数据位置交换都没有，则表明数组已排序完成
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }
}
