package LinearStructure.LinkedList.SingleLinkedList;

import java.awt.event.AdjustmentEvent;

/**
 * @author tomable
 * @create 2021-08-27-20:31
 */
public class SingleLinkedList {
    public static void main(String[] args) {
        //测试
        //创建节点
        HeroNode hero1 = new HeroNode(1,"aa","AA");
        HeroNode hero2 = new HeroNode(2,"bb","BB");
        HeroNode hero3 = new HeroNode(3,"cc","CC");
        HeroNode hero4 = new HeroNode(4,"dd","DD");

        //创建链表
        SingleLinked singleLinked = new SingleLinked();

        //不按顺序加入
//        singleLinked.add(hero1);
//        singleLinked.add(hero4);
//        singleLinked.add(hero3);
//        singleLinked.add(hero2);

        //按顺序加入
        singleLinked.addByOrder(hero1);
        singleLinked.addByOrder(hero4);
        singleLinked.addByOrder(hero3);
        singleLinked.addByOrder(hero2);

        reversetList(singleLinked.getHead());
//        //修改节点
//        singleLinked.update(new HeroNode(2,"BB","bb"));
//
//        //删除节点
//        singleLinked.deldate(2);

        //显示
        singleLinked.list();
    }


    //翻转单链表
    public static void reversetList(HeroNode head){
        if (head.next == null || head.next.next == null){
            return;
        }
        HeroNode cur = head.next;
        HeroNode tmep = null;
        HeroNode reversetHead = new HeroNode(0,"","");
        while (cur != null){
            tmep = cur.next;
            cur.next = reversetHead.next;
            reversetHead.next = cur;
            cur = tmep;
        }
        head.next = reversetHead.next;
    }
}



//定义 SingleLinkedList 管理英雄
class SingleLinked{
    //先初始化一个头节点（不能动）
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    //添加节点，没有要求
    public void add(HeroNode heroNode){
        HeroNode temp = head;
        //1.找到当前链表的最后节点
        while (true){
            if (temp.next == null){
                break;
            }
            temp = temp.next;
        }
        //2.将这个节点的 next 指向新的节点
        temp.next = heroNode;
    }

    //添加节点，按顺序添加
    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        boolean flag = false;//标识添加的编号是否存在
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.no > heroNode.no){ //位置找到，就在temp的后面插入
                break;
            }else if (temp.next.no == heroNode.no){ //说明添加的编号已经存在
                flag = true; //说明编号存在
                break;
            }
            temp = temp.next; //后移
        }
        //判断flag的值
        if (flag){
            System.out.printf("%d编号的的英雄已经存在，不能继续添加\n", heroNode.no);
        }else {
            //2.将这个节点的 next 指向新的节点
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点的信息，根据no编号修改
    public void update(HeroNode heroNode){
        if (head.next == null){ //判断链表是否为空
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
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

    //删除节点的信息，根据no编号修改
    public void deldate(int no){
        if (head.next == null){ //判断链表是否为空
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head;
        boolean flag = false;//标识是否找到该节点
        while (true){
            if (temp.next == null){
                break; //已经遍历完链表
            }
            if (temp.next.no == no){ //说明添加的编号已经存在
                flag = true; //找到
                break;
            }
            temp = temp.next; //后移
        }
        //判断flag的值
        if (flag){
            temp.next = temp.next.next;
        }else {
            System.out.printf("没有找到编号 %d 的节点，删除失败\n",no);
        }

    }

    //显示链表
    public void list(){
        if (head.next == null){
            System.out.println("链表为空");
        }
        HeroNode temp = head.next;
        while (true){
            if (temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

//定义 HeroNode，每一个 HeroNode 对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点

    //构造器
    public HeroNode(int no, String name, String nickname){
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
