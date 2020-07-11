package hashTable;

import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTable hashTable = new HashTable(7);
        //菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        //创建循环判断值
        boolean loop = true;
        while (loop) {
            System.out.println("add：添加雇员");
            System.out.println("list：显示雇员");
            System.out.println("find：查找雇员");
            System.out.println("delete：删除雇员");
            System.out.println("exit：退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("请输入id");
                    int id = scanner.nextInt();
                    System.out.println("请输入名字");
                    String name = scanner.next();
                    Employee employee = new Employee(id, name);
                    hashTable.addByOrder(employee);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "find":
                    System.out.println("请输入id");
                    int fid = scanner.nextInt();
                    hashTable.findById(fid);
                    break;
                case "delete":
                    System.out.println("请输入id");
                    int did = scanner.nextInt();
                    hashTable.deleteById(did);
                    break;
                case "exit":
                    loop = false;
                    scanner.close();
                    break;
                default:
                    break;
            }
        }
    }

}

//创建HashTable,管理多条链表
class HashTable {
    //创建一个数组，存放链表
    private EmpLinkedList[] empLinkedListArray;
    private int size;//链表的数量

    public HashTable(int size) {
        this.size = size;
        //初始化empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
        //同时需要初始化数组中的每一条链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Employee employee) {
        //根据员工id，判断员工该去哪条链表
        int empLinkedListNum = hashFun(employee.id);
        //添加进链表中
        empLinkedListArray[empLinkedListNum].add(employee);
    }

    //按顺序添加雇员
    public void addByOrder(Employee employee) {
        //根据员工id，判断员工该去哪条链表
        int empLinkedListNum = hashFun(employee.id);
        //添加进链表中
        empLinkedListArray[empLinkedListNum].addByOrder(employee);
    }

    //遍历所有链表
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    //根据id查找雇员
    public void findById(int id) {
        //根据雇员id，判断员工在哪条链表
        int empLinkedListNum = hashFun(id);
        //从链表中查找
        Employee employee = empLinkedListArray[empLinkedListNum].findById(id);

        if (employee != null) {
            System.out.print("在第" + (empLinkedListNum + 1) + "条链表中找到雇员：");
            System.out.println("=>雇员id为：" + employee.id + " 雇员姓名为：" + employee.name);
        } else {
            System.out.println("没有找到相应雇员！");
        }
    }

    //根据id删除雇员
    public void deleteById(int id) {
        //根据雇员id，判断员工在哪条链表
        int empLinkedListNum = hashFun(id);
        //从链表中查找并删除
        Employee employee = empLinkedListArray[empLinkedListNum].deleteById(id);
        //判断表中是否有该数据
        if (employee == null) {
            System.out.println("表中没有待删除雇员！");
        } else {
            System.out.println("删除成功！");
        }
    }

    //编写散列函数，取模法
    public int hashFun(int id) {
        return id % size;
    }
}

//添加雇员类
class Employee {
    public int id;
    public String name;
    public Employee next;//next,默认为null

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//创建EmpLinkedList，表示链表
class EmpLinkedList {
    //头指针，默认为null
    Employee head = null;

    //添加
    //默认id自增长，那么添加进来的雇员，直接放在链表最后即可
    public void add(Employee employee) {
        //如果添加的是第一个雇员
        if (head == null) {
            head = employee;
            return;
        }
        //添加辅助指针
        Employee temp = head;
        //循环，让temp指向最后
        while (temp.next != null) {
            temp = temp.next;
        }
        //将新数据加入链表尾部
        temp.next = employee;
    }

    //按顺序添加
    public void addByOrder(Employee employee) {
        //如果添加的是第一个雇员
        if (head == null) {
            head = employee;
            return;
        }
        //添加辅助指针
        Employee temp = head;
        //循环，让temp.next的id大于待添加雇员的id
        while (temp.next != null) {
            if (temp.next.id > employee.id){
                employee.next = temp.next;
                break;
            }
            //temp后移
            temp = temp.next;
        }
        //将新数据加入链表尾部
        temp.next = employee;
    }

    //遍历
    public void list(int num) {
        //判断链表是否为空
        if (head == null) {
            System.out.println("第" + (num + 1) + "条链表无数据！！");
            return;
        }
        //创建辅助节点，帮助遍历链表
        Employee temp = head;
        //循环遍历
        System.out.print("第" + (num + 1) + "条链表的信息：");
        while (temp != null) {
            System.out.print(" =>雇员id为：" + temp.id + " 雇员姓名为：" + temp.name);
            //指针后移
            temp = temp.next;
        }
        System.out.println();
    }

    public Employee findById(int id) {
        //判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        //创建辅助节点
        Employee temp = head;

        Employee employee = null;
        //遍历链表
        while (temp != null) {
            //找到对应id雇员
            if (temp.id == id) {
                employee = new Employee(temp.id, temp.name);
            }
            //temp后移
            temp = temp.next;
        }
        return employee;
    }

    public Employee deleteById(int id) {
        //判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
        }
        //创建用于存储删除节点信息的临时变量
        Employee next = null;
        //如果表中只有一个数据
        if (head.next == null && head.id == id) {
            next = head;
            head = null;
        } else {
            //创建辅助节点
            Employee temp = head;
            //遍历链表
            while (temp.next != null) {
                //找到对应id雇员,进行删除
                if (temp.next.id == id) {
                    next = temp.next;
                    temp.next = next.next;
                    break;
                }
                //temp后移
                temp = temp.next;
            }
        }
        return next;
    }


}
