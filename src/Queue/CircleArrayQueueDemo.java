package Queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        // 测试
        //创建一个环形队列
        CircleArray circleArray = new CircleArray(4);//数组最大空间设置为4，最大有效数据数量为3
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        // 输出一个菜单
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    circleArray.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数据");
                    int value = scanner.nextInt();
                    circleArray.addQueue(value);
                    break;
                case 'g':
                    try {
                        System.out.println("取出的数据为：" + circleArray.getQueue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        System.out.println("队列头数据为：" + circleArray.headQueue());
                    } catch (Exception e) {
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
class CircleArray{
    private int maxSize;//最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;

    public CircleArray(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0;//头指针指向队列头，初始值为0
        rear = 0;//指向队列尾后的一个空位,初始值为0
    }

    //判断队列是否是满的
    public boolean isFull() {
        return (rear+1) % maxSize == front;
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
        arr[rear] = data;
        //尾指针加1和最大容量取模
        rear = (rear+1) % maxSize;
    }

    //获取队列中的数据
    public int getQueue() {
        //判断队列是否为空
        if(isEmpty()) {
            //抛出异常
            throw new RuntimeException("队列为空");
        }
        //将数据存入临时变量中
        int data = arr[front];
        //对头指针进行操作
        front = (front+1) % maxSize;
        //返回临时变量中存放的数据
        return data;
    }

    //显示队列中的数据
    public void showQueue() {
        //判断队列是否为空
        if(isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        //遍历
        for (int i = front; i < front + size(); i++) {
            System.out.println("队列数据为：" + arr[i % maxSize]);
        }
    }

    //队列中存放的有效数据个数
    public int size() {
        return (rear-front+maxSize) % maxSize;
    }

    //显示队列头数据，非取出
    public int headQueue() {
        //判断是否为空
        if(isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return arr[front];
    }

}