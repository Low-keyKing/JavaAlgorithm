package stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试一下ArrayStack 是否正确
        //先创建一个ArrayStack对象->表示栈
        ArrayStack2 stack = new ArrayStack2(4);
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

class ArrayStack {
    private int maxSize;//栈的大小
    private int[] stack;//数组，数组模拟栈，数据就放在数组
    private int top = -1;//栈顶，初始值为-1

    //构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //判断栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //将数据压入栈
    public void push(int value) {
        //判断栈满
        if (isFull()) {
            System.out.println("栈满了，塞不下了！");
            return;
        }
        //将数据压入栈顶
        top++;
        stack[top] = value;
    }

    //将栈顶数据取出
    public int pop() {
        //判断栈空
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有数据");
        }
        int value = stack[top];
        top--;

        return value;
    }

    //显示栈的情况（遍历栈），遍历时，从栈顶开始
    public void list() {
        //判断栈空
        if (isEmpty()) {
            System.out.println("栈空，没有数据!");
            return;
        }
        //遍历
        for (int i = top; i >= 0; i--) {
            //打印
            System.out.println("stack[" + i + "]：" + stack[i]);

        }
    }
}
