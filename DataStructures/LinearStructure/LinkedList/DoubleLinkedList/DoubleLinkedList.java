package LinearStructure.LinkedList.DoubleLinkedList;

/**
 * @author tomable
 * @create 2021-08-28-18:52
 */
public class DoubleLinkedList {
    public static void main(String[] args) {
        //创建节点
        HeroNode2 hero1 = new HeroNode2(1,"aa","AA");
        HeroNode2 hero2 = new HeroNode2(2,"bb","BB");
        HeroNode2 hero3 = new HeroNode2(3,"cc","CC");
        HeroNode2 hero4 = new HeroNode2(4,"dd","DD");
        //创建一个双向链表
        DoubleLinked doubleLinked = new DoubleLinked();
        //加入
        doubleLinked.add(hero1);
        doubleLinked.add(hero2);
        doubleLinked.add(hero3);
        doubleLinked.add(hero4);
        doubleLinked.list();

        //修改
        HeroNode2 newHeroNode = new HeroNode2(2,"BB","bb");
        doubleLinked.update(newHeroNode);
        doubleLinked.list();

        //删除
        doubleLinked.deldate(2);
        doubleLinked.list();

    }
}


//创建一个双向链表的类
class DoubleLinked{
    //先初始化一个头节点（不能动）
    private HeroNode2 head = new HeroNode2(0,"","");

    public HeroNode2 getHead() {
        return head;
    }

    //显示链表
    public void list(){
        if (head.next == null){
            System.out.println("链表为空");
        }
        HeroNode2 temp = head.next;
        while (true){
            if (temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
        System.out.println();
    }

    //修改节点的信息，根据no编号修改
    public void update(HeroNode2 heroNode){
        if (head.next == null){ //判断链表是否为空
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;//标识是否找到该节点
        while (true){
            if (temp == null){
                break; //已经遍历完链表
            }
            if (temp.no == heroNode.no){ //说明添加的编号已经存在
                flag = true; //找到
                break;
            }
            temp = temp.next; //后移
        }
        //判断flag的值
        if (flag){
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        }else {
            System.out.printf("没有找到编号 %d 的节点，修改失败\n",heroNode.no);
        }

    }

    //添加节点，没有要求
    public void add(HeroNode2 heroNode){
        HeroNode2 temp = head;
        //1.找到当前链表的最后节点
        while (true){
            if (temp.next == null){
                break;
            }
            temp = temp.next;
        }
        //2.将这个节点的 next 指向新的节点
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //删除节点的信息，根据no编号修改
    public void deldate(int no){
        if (head.next == null){ //判断链表是否为空
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;//标识是否找到该节点
        while (true){
            if (temp == null){
                break; //已经遍历完链表
            }
            if (temp.no == no){ //说明添加的编号已经存在
                flag = true; //找到
                break;
            }
            temp = temp.next; //后移
        }
        //判断flag的值
        if (flag){
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.printf("没有找到编号 %d 的节点，删除失败\n",no);
        }

    }

}


//定义 HeroNode2，每一个 HeroNode 对象就是一个节点
class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;//指向下一个节点
    public HeroNode2 pre; //指向前一个节点

    //构造器
    public HeroNode2(int no, String name, String nickname){
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\''+
                '}';
    }
}
