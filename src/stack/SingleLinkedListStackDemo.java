package stack;

import java.util.Scanner;

public class SingleLinkedListStackDemo {
    public static void main(String[] args) {
        //测试一下SingleLinkedListStack 是否正确
        //先创建一个SingleLinkedListStack对象->表示栈
        SingleLinkedListStack stack = new SingleLinkedListStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("show：表示显示栈");
            System.out.println("exit：退出程序");
            System.out.println("push：表示添加数据到栈(入栈)");
            System.out.println("pop：表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    try {
                        stack.list();
                        break;
                    } catch (Exception e) {
                        e.getMessage();
                    }
                case "exit":
                    loop = false;
                    break;
                case "push":
                    System.out.println("请输入一个数：");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int data = stack.pop();
                        System.out.println("出栈的数据为：" + data);
                        break;
                    } catch (Exception e) {
                        e.getMessage();
                    }
            }
        }
        System.out.println("程序结束~~");
    }
}

class SingleLinkedListStack {
    private int maxSize;//栈的大小
    private SingleLinkedList stack;//数组，数组模拟栈，数据就放在数组

    //构造器
    public SingleLinkedListStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new SingleLinkedList();
    }

    //判断栈空
    public boolean isEmpty() {
        return stack.getLength() == 0;
    }

    //判断栈满
    public boolean isFull() {
        return stack.getLength() == maxSize;
    }

    //将数据压入栈
    public void push(int value) {
        //判断栈满
        if (isFull()) {
            System.out.println("栈满了，塞不下了！");
            return;
        }
        //判断若栈不为空，进行反转
        if (!isEmpty()){
            stack.reverseList();
        }
        //创建对象
        Number number = new Number(value);
        //将数据添加
        stack.add(number);
        //若链表长度大于1，将单链表反转
        if (stack.getLength() > 1){
            stack.reverseList();
        }
    }

    //将栈顶数据取出
    public int pop() {
        //判断栈空
        if (isEmpty()) {
            System.out.println("栈空，没有数据!");
            throw new RuntimeException("栈空，没有数据");
        }
        int value = stack.pop().num;

        return value;
    }

    //显示栈的情况（遍历栈），遍历时，从栈顶开始
    public void list() {
        //判断栈空
        if (isEmpty()) {
            System.out.println("栈空，没有数据!");
            return;
        }

        stack.list();
    }

}

//创建单链表
class SingleLinkedList {
    //初始化头结点
    private Number head = new Number(0);

    public Number getHead() {
        return head;
    }

    //添加节点到单向链表
    //1、找到当前链表的最后节点
    //2、将最后这个节点的next指向新的节点
    public void add(Number number) {
        //因为head不能动，因此使用一个辅助节点temp
        Number temp = head;
        //遍历链表找到链表的最后
        while (true) {
            if (temp.next == null) {
                break;
            }
            //若没有找到最后，将temp后移，重复上面步骤
            temp = temp.next;
        }
        //当while循环结束时，temp指向链表的最后
        //让新的节点被最后节点的next指向
        temp.next = number;
    }

    //显示链表
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
        }
        //通过辅助节点来帮助遍历
        Number temp = head.next;
        while (true) {
            //判断是否已到链表尾部
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println("数字："+temp.num);
            //将temp节点后移
            temp = temp.next;
        }
    }

    //取出最后加入的节点
    public Number pop() {
        //判断链表是否为空
        if (head.next == null) {
            throw new RuntimeException("链表为空！！");
        }
        //由于在模拟栈的add()方法中已经将链表进行反转，所以只需取顶部数据
        Number data = head.next;
        //由于是取出，让head指向head的下下个节点
        head.next = head.next.next;

        return data;
    }

    //获取单链表中有效节点的个数
    public int getLength() {
        //判断是否为空
        if (head.next == null) {
            return 0;
        }
        //创建一个辅助节点
        Number temp = head.next;
        //创建一个变量表示链表长度
        int length = 0;
        //遍历
        while (true) {
            //判断是否已到链表尾部
            if (temp == null) {
                break;
            }
            length++;
            //将temp后移
            temp = temp.next;
        }
        return length;
    }

    //将单链表反转
    public void reverseList() {
        //判断是否为空或只有一个节点，若是，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义一个辅助变量，帮助遍历
        Number temp = head.next;
        //定义一个用来存放当前变量[temp]后的变量
        Number next = null;
        Number reverseHead = new Number(0);
        //遍历单链表
        while (temp != null) {
            next = temp.next;
            //使后一个变量指向前一个变量
            temp.next = reverseHead.next;
            reverseHead.next = temp;
            //将temp后移
            temp = next;
        }
        //让head.next指向reverseHead.next来实现链表的反转
        head.next = reverseHead.next;
    }

}

//定义数据对象
class Number {
    public int num;
    public Number next;

    //构造器
    public Number(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Number{" +
                "num=" + num +
                '}';
    }
}