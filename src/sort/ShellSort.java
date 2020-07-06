package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {
    public static void main(String[] args) {
//        int[] array = {2, 3, 1, 4, 5, 7, 9, 0, 6};
//        System.out.println("排序前");
//        System.out.println(Arrays.toString(array));
//        System.out.println("排序后");
//        shellSort2(array);
//        System.out.println(Arrays.toString(array));

        //速度测试
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        System.out.println("排序前的时间:" + simpleDateFormat.format(date1));
        shellSort2(arr);
        Date date2 = new Date();
        System.out.println("排序后的时间" + simpleDateFormat.format(date2));
    }

    //希尔排序(交换型)
    public static void shellSort1(int[] arr) {
        //创建交换用的中间变量
        int temp = 0;
        //希尔排序所需分的组数，每轮/2
        int groupNum = arr.length / 2;
        while (groupNum > 0) {
            //将数据分为(groupNum)组
            for (int i = groupNum; i < arr.length; i++) {
                //遍历各组中的元素,共groupNum组，步长为groupNum
                for (int j = i - groupNum; j >= 0; j -= groupNum) {
                    //两两进行比较，较大的排后
                    if (arr[j] > arr[j + groupNum]) {
                        temp = arr[j];
                        arr[j] = arr[j + groupNum];
                        arr[j + groupNum] = temp;
                    }
                }
            }
            groupNum /= 2;
        }
    }

    //希尔排序(移位型)
    public static void shellSort2(int[] arr) {
        //用于存放待插入数据
        int insertValue = 0;
        //在待插入数据位置前数据的下标
        int insertIndex = 0;
        //要分的组数
        int groupNum = arr.length / 2;
        while (groupNum > 0) {
            for (int i = groupNum; i < arr.length; i++) {//将数据分为(groupNum)组
                //待插入数据
                insertValue = arr[i];
                //各组待插入数据前一个位置数据下标
                insertIndex = i - groupNum;
                while (insertIndex >= 0 && insertValue < arr[insertIndex]) {//判断数据大小
                    arr[insertIndex + groupNum] = arr[insertIndex];
                    insertIndex -= groupNum;
                }
                //如果待插入数据大于前一个数据，则代表找到位置，则将待插入数据插入在该数据后面
                arr[insertIndex + groupNum] = insertValue;
            }
            //组数除以2
            groupNum /= 2;
        }
    }
}
