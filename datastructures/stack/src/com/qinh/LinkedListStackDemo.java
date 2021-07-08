package com.qinh;

/**
 * 单向链表模拟栈
 *
 * @author Qh
 * @version 1.0
 * @date 2021/7/8 9:36
 */
public class LinkedListStackDemo {

    public static void main(String[] args){
        Node node1 = new Node(1, "钢铁侠");
        Node node2 = new Node(2, "路飞");
        Node node3 = new Node(3, "卡卡西");
        Stack stack = new Stack(4);
        stack.push(node2);
        stack.push(node3);
        stack.push(node1);
        stack.list();
        System.out.println(stack.getCount());
        System.out.println("---------");
        stack.pop();
        stack.list();
        System.out.println(stack.getCount());
        System.out.println("============");
        stack.pop();
        stack.list();
        System.out.println(stack.getCount());
        System.out.println("============");
        stack.pop();
        stack.list();
        System.out.println(stack.getCount());
        System.out.println("============");
        stack.pop();
        stack.list();
        System.out.println(stack.getCount());
    }
}

/**
 * 栈结构
 */
class Stack{
    /**最大容量*/
    private int maxSize;
    /**链表数量*/
    private int count;
    /**栈顶节点*/
    private Node topNode;
    /**栈底节点*/
    private Node buttomNode;

    public Stack(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getCount() {
        return count;
    }

    /**
     * 添加数据
     *
     * @param node
     */
    public void push(Node node){
        if (count > maxSize){
            System.out.println("栈已满");
            return;
        }
        count++;
        //第一次添加
        if (topNode == null){
            topNode = node;
            buttomNode = node;
        }
        //不是第一次添加
        else{
            //将之前的栈顶节点的前置节点指向新增的节点
            topNode.setPrev(node);
            //再将新增的节点置为栈顶元素
            topNode = node;
        }
    }

    /**
     * 弹出元素
     *
     * @return
     */
    public Node pop(){
        if (count <= 0){
            System.out.println("栈已空");
            return null;
        }
        Node node = topNode;
        //栈顶元素的前一个节点
        Node next = buttomNode;
        while (true){
            if (next == null || next.getPrev() == node){
                break;
            }
            next = next.getPrev();
        }
        //重设栈顶元素
        topNode = next;
        if (next != null){
            next.setPrev(null);
        }
        count--;
        return node;
    }

    /**
     * 遍历栈元素
     */
    public void list(){
        if (count <= 0){
            System.out.println("栈为空");
            return;
        }
        Node next = buttomNode;
        while (true){
            System.out.println(next);
            //表明已到栈顶
            if (next.getPrev() == null){
                break;
            }
            next = next.getPrev();
        }
    }

}

/**
 * 链表节点
 */
class Node{
    private int no;
    private String name;
    /**
     * 前置节点
     */
    private Node prev;

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
