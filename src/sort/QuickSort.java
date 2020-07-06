package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

class QuickSort {
    public static void main(String[] args) {
//        //测试
//        int[] array = {7, 4, 66, 75, 88, 9, 0, 1, 57, -45, 3};
//        System.out.println("排序前");
//        System.out.println(Arrays.toString(array));
//        System.out.println("排序后");
//        quickSort(array, 0, array.length - 1);
//        System.out.println(Arrays.toString(array));

        //速度测试
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        System.out.println("排序前的时间:" + simpleDateFormat.format(date1));
        quickSort(arr, 0, arr.length - 1);
        Date date2 = new Date();
        System.out.println("排序后的时间" + simpleDateFormat.format(date2));
    }

    //快速排序
    //arr为待排序的数组，l代表左边的起始点，r代表右边的起始点
    public static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            int i = l, j = r;
            //中间值，取最左边的数据
            int pivot = arr[i];
            //循环，当l不小于r时，代表左边已经没有大于中间值的数了，右边已经没有小于中间值的数了
            while (i < j) {
                //从右往左找小于中间值的数
                while (i < j && arr[j] > pivot) {
                    j--;
                }
                //将找到的数arr[j]放入arr[i]中,且代表原来位置留坑,并将i向后移动一个，减少消耗
                if (i < j) {
                    arr[i] = arr[j];
                    i++;
                }
                //从左向右找大于中间值的数
                while (i < j && arr[i] < pivot) {
                    i++;
                }
                //将找到的数arr[i]放入刚刚留下的坑中,且代表原来位置留坑,并将j向前移动一个，减少消耗
                if (i < j) {
                    arr[j] = arr[i];
                    j--;
                }
            }
            //当i==j时，循环结束，将中间值放入这个位置，且代表中间值左边的全是比中间值小的数，右边全是比中间值大的数
            arr[i] = pivot;
            //以中间值的位置为基准，用递归将左右两边分别排序
            quickSort(arr, l, i - 1);
            quickSort(arr, i + 1, r);
        }
    }
}