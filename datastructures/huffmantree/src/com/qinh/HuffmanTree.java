package com.qinh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 赫夫曼树
 *
 * @author Qh
 * @version 1.0
 * @date 2021-09-21-21:00
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        Node root = createHuffmanTree(arr);
        preOrder(root);
    }

    /**
     * 前序遍历
     */
    public static void preOrder(Node root){
        if (root != null){
            root.preOrder();
        }else {
            System.out.println("空树.........");
        }
    }

    /**
     * 创建赫夫曼树
     * @param arr 需要被构建为赫夫曼树的数组
     * @return node 最终的根结点
     */
    public static Node createHuffmanTree(int[] arr){
        /*
        第一步为了操作方便
        1.遍历arr数组
        2.将arr的每个元素构成一个Node
        3.将Node放入到ArrayList中
        */
        List<Node> nodes = new ArrayList<>(arr.length);
        for (int value : arr){
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1){
            //从小到大排序
            Collections.sort(nodes);
            //System.out.println(nodes);

            //取出根节点权值最小的两颗二叉树
            //取出权值最小的结点（二叉树）
            Node leftNode = nodes.get(0);
            //取出权值第二小的结点（二叉树）
            Node rightNode = nodes.get(1);

            //构建一颗新的二叉树
            Node parent = new Node(leftNode.getValue() + rightNode.getValue());
            parent.setLeft(leftNode);
            parent.setRight(rightNode);

            //从ArrayList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //将parent加入到nodes
            nodes.add(parent);
        }
        //返回赫夫曼树的根结点
        return nodes.get(0);
    }
}

/**
 * 创建可排序的结点类
 */
class Node implements Comparable<Node>{
    /** 结点权值 */
    private int value;
    /** 指向左子结点 */
    Node left;
    /** 指向右子结点 */
    Node right;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }

    @Override
    public int compareTo(Node o) {
        //默认从小到大排序
        return this.value - o.value;
    }
}
