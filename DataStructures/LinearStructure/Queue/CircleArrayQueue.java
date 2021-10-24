package LinearStructure.Queue;

import java.util.Queue;
import java.util.Scanner;

/**
 * @author tomable
 * @create 2021-08-27-19:43
 */
public class CircleArrayQueue {
    public static void main(String[] args) {
        //测试
        CircleArray circleArray = new CircleArray(3);//其实算两个空间
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("Show:显示队列");
            System.out.println("Exit:退出程序");
            System.out.println("Add:入队列");
            System.out.println("Get:出队列");
            System.out.println("Head:查看队列头");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key){
                case 's':
                    circleArray.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    circleArray.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = circleArray.getQueue();
                        System.out.printf("取出的数据为：%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = circleArray.headQueue();
                        System.out.printf("队列头数据为：%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;

            }
        }
        System.out.println("程序退出");
    }
}

class CircleArray {
    private int maxSize;//最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//用于存放数据，模拟队列

    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列，入队列
    public void addQueue(int n){
        if(isFull()){
            System.out.println("队列已满，不能添加数据");
            return;
        }
        //先加数据
        arr[rear] = n;
        //再后移
        rear = (rear + 1) % maxSize;
    }

    //获取队列得数据，出队列
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，取出失败");
        }
        //注意：这里要先把front对应的值保留到一个临时变量，然后后移front，最后返回临时变量
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //求出当前队列有效数据的个数
    public int size(){
        return (rear + maxSize-front) % maxSize;
    }

    //显示队列的所有数据
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空，没有数据");
            return;
        }
        for (int i = front; i < (front + size()); i++){
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //显示头数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front];
    }
}
