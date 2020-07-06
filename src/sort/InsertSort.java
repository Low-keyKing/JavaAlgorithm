package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsertSort {
    public static void main(String[] args) {
        //测试
//        int[] array = {101,34,25,534};
//        System.out.println("排序前");
//        System.out.println(Arrays.toString(array));
//        System.out.println("排序后");
//        insertSort(array);

        //速度测试
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        System.out.println("排序前的时间:" + simpleDateFormat.format(date1));
        insertSort(arr);
        Date date2 = new Date();
        System.out.println("排序后的时间" + simpleDateFormat.format(date2));
    }

    //插入排序
    public static void insertSort(int[] arr) {
        int insertValue = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {//需要插入数组长度-1轮
            //待插入的数
            insertValue = arr[i];
            //前一个位置的下标
            insertIndex = i - 1;
            //遍历，进行比较
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];//将数据后移
                insertIndex--;//将数据与再前一个数据比较
            }
            //如果待插入数据大于前一个数据，则代表找到位置，则将待插入数据插入在该数据后面
            arr[insertIndex + 1] = insertValue;

//            System.out.println("第"+i+"轮");
//            System.out.println(Arrays.toString(arr));
        }
    }
}
