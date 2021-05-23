package com.qinh;

import java.util.Scanner;

/**
 * 环形队列
 *
 * @author Qh
 * @version 1.0
 * @date 2021-05-19-23:14
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        //创建一个环形队列,说明设置4，其队列的有效数据最大是3
        CircleArray queue = new CircleArray(4);
        //接收用户输入
        char key;
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数字");
                    int val = scanner.nextInt();
                    queue.addQueue(val);
                    break;
                case 'g':
                    try {
                        int result = queue.getQueue();
                        System.out.printf("取出的数据是%d\n",result);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int head = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n",head);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

/**
 * 使用数组模拟队列--编写一个ArrayQueue类
 */
class CircleArray{
    /** 数组的最大容量 */
    private int maxSize;
    /**
     * front就指向队列的第一个元素，也就是说arr[front]就是队列的第一个元素
     * front的初始值=0
     */
    private int front;
    /**
     * rear指向队列的最后一个元素的后一个位置，因为希望空出一个空间作为约定
     * rear的初始值=0
     */
    private int rear;
    /** 该数据用于存放数据，模拟队列 */
    private int[] arr;

    /**
     * 创建队列的构造器
     *
     * @param maxSize
     */
    public CircleArray(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    /**
     * 判断队列是否已满
     *
     * @return
     */
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty(){
        return rear == front;
    }

    /**
     * 添加数据到队列
     *
     * @param n
     */
    public void addQueue(int n){
        //判断队列是否已满
        if(isFull()){
            System.out.println("队列满，不能加入数据");
            return;
        }
        //直接将数据加入
        arr[rear] = n;
        //将rear后移，这里必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    /**
     * 获取队列的数据，出队列
     *
     * @return
     */
    public int getQueue(){
        //判断队列是否为空
        if(isEmpty()){
            //通过抛出异常
            throw new RuntimeException("队列为空，不能取数据");
        }
        //这里需要分析出front是指向队列的第一个元素
        //1.先把front对应的值保存到一个临时变量
        //2.将front后移,考虑取模
        //3.将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    /**
     * 显示队列的所有数据
     */
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列为空，没有数据");
            return;
        }
        //思路，从front开始遍历，遍历多少个元素
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n",i % maxSize,arr[i % maxSize]);
        }
    }

    /**
     * 当前队列的有效数据的个数
     */
    private int size(){
        return (rear + maxSize - front) % maxSize;
    }

    /**
     * 显示队列的头数据，注意不是取出数据
     */
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front];
    }

}
