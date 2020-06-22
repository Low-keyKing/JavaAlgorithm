package stack;

public class Calculator {
    public static void main(String[] args) {
        String equation = "100+7*4-2";
        //创建两个数组栈，一个数字栈，一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //创建用于遍历的变量
        int index = 0;
        //创建数字变量
        int num1 = 0;
        int num2 = 0;
        //创建运算符变量
        int oper = 0;
        //创建结果变量
        int result = 0;
        //用于保存每次扫描得到的字符
        char ch = ' ';
        //用于保存多位数的变量
        String keepNum = "";
        //开始while循环扫描算式字符串
        while (true) {
            //依次得到equation中的每一个字符
            ch = equation.substring(index, index + 1).charAt(0);
            //判断获取的字符是符号还是数字
            if (operStack.isOper(ch)) {//运算符
                //判断栈是否为空
                if (!operStack.isEmpty()) {
                    //判断符号的优先级,若新符号优先级小于等于栈顶符号，则从数字栈pop两个数字，从符号栈pop出一个符号
                    //然后进行运算，将得到的结果压入数字栈内,将新扫描到的运算符压入符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        result = numStack.cal(num1, num2, oper);
                        numStack.push(result);
                        operStack.push(ch);
                    } else {//若优先级大于栈顶符号，则直接压入符号栈
                        operStack.push(ch);
                    }

                } else {
                    //直接压入栈
                    operStack.push(ch);
                }
            } else {//数字
                //numStack.push(ch - 48);
                //1、不能立即入栈，有可能是多位数
                //2、当处理数，需要再扫描算式后一个字符，若为数就进行扫描，若为符号就入栈
                //将数字加入同一变量
                keepNum += ch;
                //如果ch是最后一个字符，则直接入栈
                if (index == equation.length() - 1) {
                    //入栈keepNum
                    numStack.push(Integer.valueOf(keepNum));
                } else {
                    //判断下一个字符是符号还是数字，若为符号，则将数字压入数栈
                    if (operStack.isOper(equation.substring(index + 1, index + 2).charAt(0))) {
                        //入栈keepNum
                        numStack.push(Integer.valueOf(keepNum));
                        //将keepNum清空
                        keepNum = "";
                    }
                }
            }
            //让index + 1， 并判断是否扫描到算式的最后
            index++;
            if (index >= equation.length()) {
                break;
            }
        }

        //当算是扫描完毕，就顺序的从数字栈和符号栈中pop出相应的数和符号，并运行
        while (true) {
            //如果符号栈为空，则数字栈中只剩最后一个数，即结果
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            result = numStack.cal(num1, num2, oper);
            numStack.push(result);//入栈
        }
        System.out.println("表达式" + equation + "=" + numStack.pop());
    }
}

class ArrayStack2 {
    private int maxSize;//栈的大小
    private int[] stack;//数组，数组模拟栈，数据就放在数组
    private int top = -1;//栈顶，初始值为-1

    //构造器
    public ArrayStack2(int maxSize) {
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

    //显示栈顶的数据
    public int peek() {
        //判断栈空
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有数据");
        }
        int value = stack[top];

        return value;
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

    //判断是否为运算符
    public boolean isOper(char oper) {
        //创建判断的变量
        boolean op = false;
        //判断运算符
        if (oper == '+' || oper == '-' || oper == '*' || oper == '/') {
            op = true;
        }
        return op;
    }

    //判断优先级
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //运算
    public int cal(int num1, int num2, int oper) {
        int reslut = 0;
        switch (oper) {
            case '+':
                reslut = num1 + num2;
                break;
            case '-':
                reslut = num2 - num1;
                break;
            case '*':
                reslut = num1 * num2;
                break;
            case '/':
                reslut = num2 / num1;
                break;
        }
        return reslut;
    }
}