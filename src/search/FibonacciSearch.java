package search;

import java.util.Arrays;

public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int array[] = {1, 8, 89, 1000, 1234};
        System.out.println("待查找数据的下标为：" + fibSearch(array, 1000));
    }

    //斐波那契数组
    public static int[] fib() {
        int[] fib = new int[maxSize];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }

    //斐波那契查找
    //arr为待查找数列，findVal为要查找的数
    public static int fibSearch(int[] arr, int findVal) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0;//斐波那契数列的下标值
        int mid = 0;//数列的中间值下标
        int[] f = fib();//斐波那契数组
        //获取斐波那契数列下标
        while (high > f[k]) {//找到斐波那契数列中最早比（数列长度）-1大的数的下标
            k++;
        }
        //因f[k]值可能大于待查询数列的长度，所以创建一个temp数列用于填充至f[k]长
        //填充值为数列最后一个数
        int[] temp = Arrays.copyOf(arr, f[k]);
        //因目前填充值为0，故将其换成数列的最后一个数
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        //循环遍历，找我们要找的数
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            //若寻找值小于中间值，则向左找；
            //反之，向右
            if (findVal < temp[mid]) {
                high = mid - 1;
                k--;
                //因为一开始，总长为f[k]的数组被分割为左边长f[k-1]的数组和右边长f[k-2]的数组
                //因为findVal < temp[mid]，故向左找，于是开始分割长度为f[k-1]的数列
                //即分割为左边长f[k-2]的数组和右边长f[k-3]的数组
                //所以将k减1
            } else if (findVal > temp[mid]) {
                low = mid + 1;
                k -= 2;
                //总长为f[k]的数组被分割为左边长f[k-1]的数组和右边长f[k-2]的数组
                //因为findVal > temp[mid]，故向右找，于是开始分割长度为f[k-2]的数列
                //即分割为左边长f[k-3]的数组和右边长f[k-4]的数组
                //所以将k减2
            } else {//找到
                //判断，若mid大于high，则返回high
                //反之，返回mid
                if (mid > high) {
                    return high;
                } else {
                    return mid;
                }
            }
        }
        return -1;//未找到
    }
}
