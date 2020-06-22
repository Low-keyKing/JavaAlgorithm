package recursion;

public class Queen8 {
    //定义一个max变量表示有多少个皇后
    int max = 8;
    //创建一个放置皇后位置的数组,行数依次下去，然后值代表列数
    int[] array = new int[max];
    //解法的总数
    static int count = 0;

    public static void main(String[] args) {
        //测试
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.println("共有" + count + "种解法");
    }

    //编写一个放置第n个皇后的方法
    private void check(int n) {
        if (n == max) {//代表已经满了8个皇后了
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //将当前皇后n放在第i列
            array[n] = i;
            //判断是否冲突
            if (judge(n)) {
                //若不冲突，则放置下一个皇后
                check(n + 1);
            }
            //若冲突，则继续进行循环
        }
    }

    //判断是否符合规则
    private boolean judge(int n) {//n表示第n个皇后
        for (int i = 0; i < n; i++) {
            //1、如果array[i] = array[n],则表示第n个皇后和前面的皇后在同一列
            //2、如果两个皇后的斜率等于1，则他们在同一条斜线上
            //即Math.abs(n-i) = Math.abs(array[n]-array[i])
            //3、由于数列依次排序关系，则不需要判断同一行
            if (array[n] == array[i] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //输出数组的方法
    private void print() {
        count++;
        for (int arr : array) {
            System.out.print(arr + " ");
        }
        System.out.println();
    }
}


