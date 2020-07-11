package search;

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < 100; i++) {
            array[i] = i + 1;
        }
        int index = insertValueSearch(array, 0, array.length - 1, 7);
        System.out.println("查找数序列为：" + index);
    }

    //插值查找法
    //arr待查找有序数组，left左边序列，right右边序列，findVal为要查找值
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        System.out.println("插值查找---");
        //findVal < arr[0] || findVal > arr[arr.length-1]必须加入，否则mid可能越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        //求出mid
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        if (findVal < arr[mid]) {//向左递归
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else if (findVal > arr[mid]) {//向右递归
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else {
            return mid;
        }
    }
}
