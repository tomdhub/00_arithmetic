package NonlinearStructure.Table;


import java.util.Scanner;

/**
 * @author tomable
 * @create 2021-09-27-16:57
 */
public class HashTab {
    public static void main(String[] args) {
        //创建哈希表
        HashTable hashTable = new HashTable(7);

        //交互界面
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();

            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTable.add(emp);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "find":
                    System.out.println("需要查找的id：");
                    id = scanner.nextInt();
                    hashTable.findById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

//雇员类
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

//创建EmpLinkedList,表示链表
class EmpLinkedList {
    //头指针，执行第一个Emp，因此我们这个链表的head是指向第一个Emp
    private Emp head; //默认为空

    //添加雇员到链表
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }

        Emp curEmp = head;  //辅助指针,定位到最后
        while (true) {
            if (curEmp.next == null) {    //说明到链表最后
                break;
            }
            curEmp = curEmp.next; //后移
        }

        //加入链表
        curEmp.next = emp;
    }

    //遍历链表的雇员信息
    public void list(int no) {
        if (head == null) {    //空链表
            System.out.println("第" + (no + 1) + "链表为空");
            return;
        }
        System.out.println("第" + (no + 1) + "链表的信息为：");

        Emp curEmp = head;
        while (true) {
            System.out.printf("=> id: %d name: %s\t\n", curEmp.id, curEmp.name);

            if (curEmp.next == null) { //说已经到最后
                break;
            }
            curEmp = curEmp.next; //后移
        }
        System.out.println();
    }

    //根据id查找雇员
    public Emp findById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }

        Emp curEmp = head;  //辅助指针
        while (true) {
            if (curEmp.id == id) { //找到
                break;  //此时curEmp 指向查找雇员
            }
            if (curEmp.next == null) { //没有找到该雇员
                curEmp = null;
            }
            curEmp = curEmp.next;   //后移
        }
        return curEmp;
    }
}

//创建HashTab 管理多条链表
class HashTable {
    private EmpLinkedList[] empLinkedListArray;
    private int size;   //表示有多少条链表

    //构造器
    public HashTable(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size]; //初始化empLinkedListArray

        //分别初始化每个链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //散列函数
    public int hashFun(int id) {
        return id % size;
    }

    //添加雇员
    public void add( Emp emp) {
        int empLinkedListNO = hashFun(emp.id);
        empLinkedListArray[empLinkedListNO].add(emp);
    }

    //遍历所有链表
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    //根据id查找雇员
    public void findById(int id) {
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findById(id);
        if (emp != null) { //找到
            System.out.printf("在第 %d 条链表中找到雇员 id= %d", (empLinkedListNO + 1), id);
        } else {
            System.out.println("在哈希表中没有找到该雇员");
        }
    }


}