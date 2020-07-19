package linkedlist;

import java.util.*;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        //创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode hero5 = new HeroNode(3, "小江", "小雨");
        HeroNode hero6 = new HeroNode(3, "老卢", "老麒麟");
        HeroNode hero7 = new HeroNode(4, "小吴", "小星星");
        HeroNode hero8 = new HeroNode(8, "冲哥", "包子头");
        //创建初始链表
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        //加入
        singleLinkedList1.add(hero1);
        singleLinkedList1.add(hero2);
        singleLinkedList1.add(hero3);
        singleLinkedList1.add(hero5);
        singleLinkedList1.add(hero6);
        singleLinkedList1.add(hero7);
        singleLinkedList1.add(hero4);
        singleLinkedList1.list();

//        //按照编号加入加入
//        singleLinkedList1.addByOrder(hero3);
//        singleLinkedList1.addByOrder(hero5);
//        singleLinkedList1.addByOrder(hero7);
//        singleLinkedList1.addByOrder(hero4);
//        //显示
//        System.out.println("---链表1---");
//        singleLinkedList1.list();

//        //按照编号加入加入
//        singleLinkedList2.addByOrder(hero2);
//        singleLinkedList2.addByOrder(hero6);
//        singleLinkedList2.addByOrder(hero1);
//        singleLinkedList2.addByOrder(hero8);
//        //显示
//        System.out.println("---链表2---");
//        singleLinkedList2.list();

        //删除重复节点
        deleteRepeatNode(singleLinkedList1.getHead());
        System.out.println("---删除对应节点后的链表---");
        singleLinkedList1.list();

//        //合并两个有序单链表
//        System.out.println("---合并之后的链表---");
//        HeroNode heroNode = combineLists(singleLinkedList1.getHead(), singleLinkedList2.getHead()).next;
//        while (heroNode != null) {
//            System.out.println(heroNode);
//            heroNode = heroNode.next;
//        }

//        //反向输出链表
//        System.out.println("---将链表反向输出为(没有改变链表结构)---");
//        reversePrint(singleLinkedList1.getHead());

//        //将链表反转
//        reverseList(singleLinkedList1.getHead());
//        System.out.println("---反转后的链表---");
//        singleLinkedList1.list();
//
//        //测试修改节点的代码
//        HeroNode newHeroNode = new HeroNode(2, "卢哥", "小麒麟");
//        singleLinkedList1.update(newHeroNode);
//        //修改后显示
//        System.out.println("---显示修改后链表数据---");
//        singleLinkedList1.list();
//
//
//        //删除节点
//        singleLinkedList1.delete(2);
//        //删除后显示
//        System.out.println("---删除对应节点后的链表---");
//        singleLinkedList1.list();
//
//        //测试获取单链表有效节点个数的方法
//        System.out.println("链表有效节点的个数为：" + getLength(singleLinkedList1.getHead()));
//
//        //测试获取链表倒数第k个节点数据的方法
//        int k = 3;
//        System.out.println("倒数第" + k + "的值为：" + findLastIndexNode(singleLinkedList1.getHead(), k));

    }

    //在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
    // 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
    public static void deleteRepeatNode(HeroNode head) {
        Set<Integer> num = new HashSet<>();
        HeroNode Stemp = head.next;
        HeroNode temp = null;
        while (Stemp != null) {
            temp = Stemp;
            while (temp.next != null) {
                if (Stemp.no == temp.next.no) {
                    num.add(Stemp.no);
                }
                temp = temp.next;
            }
            Stemp = Stemp.next;
        }
        for (Integer i : num) {
            temp = head;
            while (temp.next != null) {
                if (temp.next.no == i) {
                    temp.next = temp.next.next;
                } else {
                    temp = temp.next;
                }
            }
        }
    }


    //有序合并两个有序单链表
    public static HeroNode combineLists(HeroNode head1, HeroNode head2) {
        //创建新链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        //分别判断两个链表是否为空
        if (head1.next == null) {//链表1为空
            singleLinkedList.getHead().next = head2.next;
            return head2;
        }
        if (head2.next == null) {//链表2为空
            singleLinkedList.getHead().next = head1.next;
            return head1;
        }
        if (head1.next == null && head2.next == null) {//两表均为空
            return null;
        }
        //若链表1的head.next的编号小于链表2的head.next的编号
        if (head1.next.no < head2.next.no) {
            singleLinkedList.getHead().next = head1.next;
            //创建辅助变量
            HeroNode temp2 = head2.next;
            //创建暂存变量
            HeroNode next = null;
            //遍历
            while (temp2 != null) {
                next = temp2.next;
                singleLinkedList.addByOrder(temp2);
                //temp2后移
                temp2 = next;
            }
        } else {
            singleLinkedList.getHead().next = head2.next;
            //创建辅助变量
            HeroNode temp1 = head1.next;
            //创建暂存变量
            HeroNode next = null;
            //遍历
            while (temp1 != null) {
                next = temp1.next;
                singleLinkedList.addByOrder(temp1);
                //temp1后移
                temp1 = next;
            }
        }

        return singleLinkedList.getHead();

    }


    //将单链表反向输出
    public static void reversePrint(HeroNode head) {
        //判断单链表是否为空
        if (head.next == null) {
            return;
        }
        //定义一个辅助变量，帮助遍历
        HeroNode temp = head.next;
        //创建一个栈
        Stack<HeroNode> stack = new Stack<>();
        //遍历，将单链表数据压入栈内
        while (temp != null) {
            stack.push(temp);
            //将temp后移
            temp = temp.next;
        }
        //遍历栈并出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());//栈的特点是先进后出
        }
    }

    //将单链表反转
    public static void reverseList(HeroNode head) {
        //判断是否为空或只有一个节点，若是，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义一个辅助变量，帮助遍历
        HeroNode temp = head.next;
        //定义一个用来存放当前变量[temp]后的变量
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0, "", "");
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


    //获取倒数第K个节点的数据
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //判断链表是否为空
        if (head.next == null) {
            return null;
        }
        //获得链表的长度
        int size = getLength(head);
        //检查index
        if (index < 0 || index > size) {
            return null;
        }
        //创建辅助节点
        HeroNode temp = head.next;
        //遍历
        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }

        return temp;
    }

    //获取单链表中有效节点的个数
    public static int getLength(HeroNode head) {
        //判断是否为空
        if (head.next == null) {
            return 0;
        }
        //创建一个辅助节点
        HeroNode temp = head.next;
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

}

//定义SingleLinkedList管理我们的英雄
class SingleLinkedList {
    //先初始化一个头结点
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //1、找到当前链表的最后节点
    //2、将最后这个节点的next指向新的节点
    public void add(HeroNode heroNode) {
        //因为head不能动，因此使用一个辅助节点temp
        HeroNode temp = head;
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
        temp.next = heroNode;
    }

    //通过编号顺序添加
    public void addByOrder(HeroNode heroNode) {
        //加入辅助节点
        HeroNode temp = head;
        //flag判断添加的编号是否存在，默认为false
        boolean flag = false;
        while (true) {
            //判断是否到链表尾部
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {//判断temp.next的编号是否大于待插入数据的编号,若是，咋找到位置
                break;
            } else if (temp.next.no == heroNode.no) {//判断该编号是否已存在
                flag = true;
                break;
            }
            //将temp节点后移
            temp = temp.next;
        }
        //判断flag的值
        if (flag) {
            System.out.println("该编号" + heroNode.no + "已存在");
        } else {
            //插入链表中temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //根据编号修改链表中数据的信息
    public void update(HeroNode newHeroNode) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
        }
        //创建一个辅助变量
        HeroNode temp = head.next;
        //添加一个布尔变量表示是否找到对应节点
        boolean found = false;
        //遍历链表
        while (true) {
            //已到链表尾部
            if (temp == null) {
                break;
            }
            //找到要更改的数据
            if (temp.no == newHeroNode.no) {
                found = true;
                break;
            }
            //temp指针后移
            temp = temp.next;
        }
        //判断是否找到对应节点
        if (found) {
            //将数据换新
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.println("没有找到编号为" + newHeroNode.no + "节点");
        }
    }

    //显示链表
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
        }
        //通过辅助节点来帮助遍历
        HeroNode temp = head.next;
        while (true) {
            //判断是否已到链表尾部
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp节点后移
            temp = temp.next;
        }
    }

    //删除节点
    public void delete(int no) {
        //创建辅助节点
        HeroNode temp = head;
        //创建用来标志是否找到对应节点的变量
        boolean found = false;
        //遍历链表
        while (true) {
            //链表已达尾部
            if (temp.next == null) {
                break;
            }
            //找到对应节点
            if (temp.next.no == no) {
                found = true;
                break;
            }
            //辅助节点temp后移
            temp = temp.next;
        }
        //判断是否找到要删除节点
        if (found) {
            temp.next = temp.next.next;
        } else {
            System.out.println("未找到编号为" + no + "的节点");
        }
    }

}

//定义HeroNode,每个HeroNode就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    //构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
