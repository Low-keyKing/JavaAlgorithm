package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {

        String expression = "1+((2+3)*4)-5";
        List<String> list1 = toInfixExpression(expression);
        System.out.println(list1);
        List<String> list2 = parseSuffixExpressionList(list1);
        System.out.println(list2);
        System.out.println(expression + "的结果为" + calculate(list2));

        /*
        //先定义给逆波兰表达式
        //(3+4)x5-6 => 3 4 + 5 x 6 - => 29
        //说明为了方便，逆波兰表达式的数字和符号使用空格隔开
        String suffixExpression = "3 4 + 5 * 6 -";
        //思路
        //1、先将逆波兰表达式放入ArrayList中
        //2、将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算
        List<String> list = getListString(suffixExpression);
        int result = calculate(list);
        System.out.println("计算的结果为" + result);
        */
    }

    //将中缀表达式转化成后缀表达式
    public static List<String> parseSuffixExpressionList(List<String> list) {
        //定义两个栈
        Stack<String> s1 = new Stack<>();//符号栈
        //由于s2这个栈没有pop操作，后面还要倒序输出
        //比较麻烦，故用List代替
        //Stack s2 = new Stack();
        List<String> s2 = new ArrayList<>();

        //遍历
        for (String item : list) {
            //如果是一个数就加s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {//如果是左括号，直接压入s1
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号，就循环查找，将不是左括号的符号加入s2，直到找到左括号，将括号去除
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {
                //当item的优先级小于等于s1栈顶运算符，将s1栈顶的运算符加入s2，然后接着和下一个栈顶比较
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //将符号加入s1
                s1.push(item);
            }
        }
        //将s1剩余的符号依次压入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;//由于s2是list，所以直接输出就可以
    }

    //将中缀表达式放入List中
    public static List<String> toInfixExpression(String expression) {
        //创建List用于存放
        List<String> list = new ArrayList<>();
        int i = 0;//模拟指针，用来遍历字符串
        char c = ' ';//用来放遍历到的每一个字符
        String str = "";//用于存放多位数
        while (i < expression.length()) {
            //如果是字符
            if ((c = expression.charAt(i)) < 48 || (c = expression.charAt(i)) > 57) {
                list.add(c + "");//加入list中
                i++;//指针后移
            } else {//如果是数字
                str = "";//先将str重置
                //遍历，判断是否是多位数
                while (i < expression.length() && (c = expression.charAt(i)) >= 48 && (c = expression.charAt(i)) <= 57) {
                    str += c;//拼接
                    i++;
                }
                list.add(str);
            }
        }
        return list;
    }

    //将逆波兰表达式放入List中
    public static List<String> getListString(String suffixException) {
        //将字符串分隔加入数组
        String[] split = suffixException.split(" ");
        List<String> list = new ArrayList<>();
        //遍历，将字符加入list中
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //计算方法
    public static int calculate(List<String> list) {
        //创建栈
        Stack<String> stack = new Stack();
        //遍历list
        for (String ls : list) {
            //利用正则表达式判断是数字还是符号
            if (ls.matches("\\d+")) {//匹配的是数字
                //入栈
                stack.push(ls);
            } else {
                //pop出两个数，再运算
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                //创建临时变量
                int result = 0;
                if (ls.equals("+")) {
                    result = num1 + num2;
                } else if (ls.equals("-")) {
                    result = num2 - num1;
                } else if (ls.equals("*")) {
                    result = num1 * num2;
                } else if (ls.equals("/")) {
                    result = num2 / num1;
                } else {
                    throw new RuntimeException("运算符有误！");
                }
                //将结果入栈
                stack.push(result + "");
            }
        }
        int res = Integer.parseInt(stack.pop());
        return res;
    }
}

class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回优先级
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("运算符不符合规定！");
                break;
        }
        return result;
    }
}