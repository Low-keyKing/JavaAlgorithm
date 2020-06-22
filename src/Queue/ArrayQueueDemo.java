package Queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        //测试
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        //输出一个菜单
        boolean loop = true;
        while(loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);
            switch(key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数据");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        System.out.println("取出的数据为："+arrayQueue.getQueue());
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        System.out.println("队列头数据为："+arrayQueue.headQueue());
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
            }

        }
    }
}
//利用数组模拟队列
class ArrayQueue{
    private int maxSize;//最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;

    public ArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;//头指针指向队列头的前一个位置
        rear = -1;//指向队列尾，指向队列尾的数据
    }

    //判断队列是否是满的
    public boolean isFull() {
        return rear == maxSize-1;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    //添加数据到队列
    public void addQueue(int data) {
        //判断队列是否已满
        if(isFull()) {
            System.out.println("队列已满");
        }
        //尾指针后移
        rear++;
        arr[rear] = data;
    }

    //获取队列中的数据
    public int getQueue() {
        //判断队列是否为空
        if(isEmpty()) {
            //抛出异常
            throw new RuntimeException("队列为空");
        }
        //头指针后移
        front++;
        return arr[front];
    }

    //显示队列中的数据
    public void showQueue() {
        //判断队列是否为空
        if(isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        //遍历
        for (int i = 0; i < arr.length; i++) {
            System.out.println("队列数据为：" + arr[i]);
        }
    }

    //显示队列头数据，非取出
    public int headQueue() {
        //判断是否为空
        if(isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return arr[front+1];
    }

}
