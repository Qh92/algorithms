package com.qinh;

import java.util.Scanner;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-05-18-21:52
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        //接收用户输入
        char key = ' ';
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
class ArrayQueue{
    /** 数组的最大容量 */
    private int maxSize;
    /** 队列头 */
    private int front;
    /** 队列尾 */
    private int rear;
    /** 该数据用于存放数据，模拟队列 */
    private int[] arr;

    /**
     * 创建队列的构造器
     *
     * @param maxSize
     */
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        //指向队列头部，分析出front是指向队列头的前一个位置
        front = -1;
        //指向队列尾，指向队列尾的数据（即就是队列最后一个数据）
        rear = -1;
    }

    /**
     * 判断队列是否满
     *
     * @return
     */
    public boolean isFull(){
        return rear == maxSize -1;
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
        arr[++rear] = n;
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
        return arr[++front];
    }

    /**
     * 显示队列的所有数据
     */
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列为空，没有数据");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    /**
     * 显示队列的头数据，注意不是取出数据
     */
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front + 1];
    }

}
