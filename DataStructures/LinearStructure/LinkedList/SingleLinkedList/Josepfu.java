package LinearStructure.LinkedList.SingleLinkedList;

/**
 * @author tomable
 * @create 2021-08-28-19:36
 */
public class Josepfu {
    public static void main(String[] args) {
        //测试
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.countBoy(1,2,5);
    }
}

//创建一个环形单向链表
class CircleSingleLinkedList{
    //创建first节点
    private Boy first = null;

    //添加一个节点，构建成一个环形的链表
    public void addBoy(int nums){
        if (nums < 1){
            System.out.println("nums值不正确");
            return;
        }
        Boy curBoy = null; //辅助指针
        for (int i = 1; i <= nums; i++){
            Boy boy = new Boy(i);
            if (i == 1){
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历
    public void showBoy(){
        if (first == null){
            System.out.println("没有小孩");
            return;
        }
        Boy curBoy = first; //辅助指针
        while (true){
            System.out.printf("小孩的编号为 %d \n",curBoy.getNo());
            if (curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext(); //后移
        }
    }

    //出圈
    /**
     *
     * @param starNo 表示从第几个小孩开始数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少小孩在圈中
     */
    public void countBoy(int starNo,int countNum,int nums){
        if (first == null || starNo < 1 || starNo > nums){
            System.out.println("参数有误！！！");
            return;
        }
        Boy helper = first; //辅助指针
        while (true){
            if (helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }
        //报数前，先移动 K - 1 次
        for (int j = 0; j < starNo - 1; j++){
            first = first.getNext();
            helper = helper.getNext();
        }
        //报数时，移动 countNum - 1 次
        while (true){
            if (helper == first){
                break;
            }
            for (int j = 0; j < countNum - 1; j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.println(first.getNo() + "小孩出圈");
            //出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("小孩" + first.getNo() +"最后留在圈中");
    }
}

//创建一个Boy类，表示一个节点
class Boy{
    private int no; //编号
    private Boy next; //指向下一个节点

    //构造器
    public Boy(int no){
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
