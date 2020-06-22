package linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        System.out.println("双向链表的测试");
        //创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        //创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        //添加
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero4);
        //有序添加
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero4);

        //遍历
        doubleLinkedList.list();

        //修改
        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("---修改后遍历---");
        doubleLinkedList.list();

        //删除
        doubleLinkedList.delete(4);
        System.out.println("---删除后遍历---");
        doubleLinkedList.list();
    }
}

//双向链表
class DoubleLinkedList {
    //先初始化一个头结点
    private HeroNode2 head = new HeroNode2(0, "", "");

    //添加一个节点到双向链表最后
    public void add(HeroNode2 heroNode) {
        //因为head不能动，因此使用一个辅助节点temp
        HeroNode2 temp = head;
        //遍历链表找到链表的最后
        while (true) {
            if (temp.next == null) {
                break;
            }
            //若没有找到最后，将temp后移，重复上面步骤
            temp = temp.next;
        }
        //当while循环结束时，te mp指向链表的最后
        //让新的节点被最后节点的next指向,且让新节点的pre指向最后节点
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //通过编号顺序添加
    public void addByOrder(HeroNode2 heroNode) {
        //加入辅助节点
        HeroNode2 temp = head;
        //flag判断添加的编号是否存在，默认为false
        boolean flag = false;
        while (true) {
            //判断是否到链表尾部
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {//判断temp.next的编号是否大于待插入数据的编号,若是，则找到位置
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
            if (temp.next != null) {//判断是否在尾部
                temp.next.pre = heroNode;
                heroNode.pre = temp;
            }
        }
    }

    //根据编号修改链表中数据的信息
    public void update(HeroNode2 newHeroNode) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
        }
        //创建一个辅助变量
        HeroNode2 temp = head.next;
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
        HeroNode2 temp = head.next;
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
    //1、对于双向链表，我们可以直接找到要删除的这个节点
    //2、找到后，自我删除即可
    public void delete(int no) {
        //判断链表是否为空
        if (head.next == null) {
            return;
        }
        //创建辅助节点
        HeroNode2 temp = head.next;
        //创建用来标志是否找到对应节点的变量
        boolean found = false;
        //遍历链表
        while (true) {
            //链表已达尾部
            if (temp == null) {
                break;
            }
            //找到对应节点
            if (temp.no == no) {
                found = true;
                break;
            }
            //辅助节点temp后移
            temp = temp.next;
        }
        //判断是否找到要删除节点
        if (found) {
            //temp.next = temp.next.next; [单链表]
            temp.pre.next = temp.next;
            //如果是最后一个节点，就不需要执行下面这句话，否则出现空指针异常
            if (temp.next != null) {//判断删除的是最后一个节点
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.println("未找到编号为" + no + "的节点");
        }
    }

}

//定义HeroNode2,每个HeroNode就是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    //构造器
    public HeroNode2(int no, String name, String nickname) {
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