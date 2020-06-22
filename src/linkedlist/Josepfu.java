package linkedlist;

public class Josepfu {
    public static void main(String[] args) {
        //测试构建环形链表和遍历
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);//加入5个节点
        //遍历
        circleSingleLinkedList.showBoy();

        //测试小孩出圈是否正确
        circleSingleLinkedList.countBoy(1, 2, 5);
    }
}

//创建一个环形链表
class CircleSingleLinkedList {
    //创建一个first节点，当前没有编号
    private Boy first = null;

    //添加节点，构成环形链表
    public void addBoy(int nums) {
        //判断输入的数据
        if (nums < 0) {
            System.out.println("输入的nums有问题！");
        }
        //创建一个辅助指针
        Boy curBoy = null;
        //通过遍历来创建环形链表
        for (int i = 1; i <= nums; i++) {
            //创建节点
            Boy boy = new Boy(i);
            //第一个节点
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成一个环
                curBoy = first;//辅助指针指向第一个
            } else {
                curBoy.setNext(boy);//让上一个节点指向新节点
                boy.setNext(first);//让新添加的指向第一个，构成一个环
                curBoy = boy;//curBoy后移
            }
        }
    }

    //遍历
    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("链表为空！");
            return;
        }
        //创建辅助节点
        Boy curBoy = first;
        //遍历
        while (true) {
            //输出
            System.out.println("第" + curBoy.getNo() + "个小孩的编号为:" + curBoy.getNo());
            //判断是否到队尾（环形链表尾部指向头部）
            if (curBoy.getNext() == first) {
                break;
            }
            //curBoy后移
            curBoy = curBoy.getNext();
        }
    }

    //根据用户输入，确定小孩出圈顺序

    /**
     * startNum 开始的编号
     * countNum 一次数几下
     * nums 最初有多少个小孩在圈中
     */

    public void countBoy(int startNum, int countNum, int nums) {
        //先对数据进行检验
        if (first == null || startNum < 1 || startNum > nums || countNum < 0) {
            System.out.println("输入的数据有误！");
        }
        //创建一个helper节点，并让其指向链表尾部
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            //helper节点后移
            helper = helper.getNext();
        }
        //先让first和helper移动(startNum-1)个位置，让first到达开始的位置
        for (int i = 0; i < startNum - 1; i++) {
            //first后移
            first = first.getNext();
            //helper后移
            helper = helper.getNext();
        }
        //开始数数，出圈
        while (true) {
            //如果first和helper同时指向同一个节点，则链表内只剩最后一个节点
            if (first == helper) {
                break;
            }
            //first和helper移动countNum-1个位置
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //将first指向的节点拿出
            System.out.println("编号为" + first.getNo() + "的小朋友出圈");
            first = first.getNext();
            helper.setNext(first);
        }
        //将最后一个节点拿出
        System.out.println("编号" + first.getNo() + "的小朋友是最后留在圈中的");
    }
}

//创建一个Boy类，表示一个节点
class Boy {
    private int no;//编号
    private Boy next;//下一个节点，默认null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}