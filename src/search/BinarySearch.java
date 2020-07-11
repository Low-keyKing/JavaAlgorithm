package search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        int array[] = {1, 8, 89, 1000, 1000, 1000, 1000, 1234};
        List<Integer> indexList = binarySearch(array, 0, array.length - 1, 1000);
        System.out.println("查找的数的索引为：" + indexList);
    }

    //二分查找法，在有序数列中找到要找的数，然后返回其索引
    //arr为带查找数列，left为左索引，right为右索引，findVal为要查找的数
    public static List<Integer> binarySearch(int[] arr, int left, int right, int findVal) {
        //如果left大于right，则代表已经查找完毕且没有查找到对应的数
        if (left > right) {
            return new ArrayList<Integer>();
        }
        //有序数组的中位数序列
        int mid = (left + right) / 2;
        //若带查找数据小于中位数，则向左递归；反之，向右递归
        if (findVal < arr[mid]) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else if (findVal > arr[mid]) {
            return binarySearch(arr, mid + 1, right, findVal);
        } else {//相等，则代表mid为要查询数的索引之一
            //创建ArrayList
            List<Integer> indexList = new ArrayList<>();
            //向左右查询，将所有符合条件的索引都放入ArrayList中返回
            int temp = mid - 1;//向左
            while (temp >= 0 && arr[temp] == findVal) {
                indexList.add(temp);
                temp--;
            }
            indexList.add(mid);//将中位数序列加入
            temp = mid + 1;//向右
            while (temp < arr.length && arr[temp] == findVal) {
                indexList.add(temp);
                temp++;
            }
            return indexList;
        }
    }
}
