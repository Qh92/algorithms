package com.qinh;

/**
 * 顺序存储二叉树：
 * 1) 顺序存储二叉树通常只考虑完全二叉树
 * 2) 第n个元素的左子节点为 2*n + 1
 * 3) 第n个元素的右子结点为 2*n + 2
 * 4) 第n个元素的父结点为 (n-1)/2
 * 5) n:表示二叉树中的第几个元素（按0开始编号）
 *
 * 顺序二叉树的应用实例：堆排序
 *
 * @author Qh
 * @version 1.0
 * @date 2021-09-20-0:04
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrayBinaryTree tree = new ArrayBinaryTree(arr);
        tree.preOrder(0);
    }

}

class ArrayBinaryTree{
    /** 存储数据结点的数组 */
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 完成顺序存储二叉树的前序遍历
     * @param index 数组下标
     */
    public void preOrder(int index){
        //判断数组
        if (arr == null || arr.length ==0 ){
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //输出当前元素
        System.out.println(arr[index]);
        //向左递归遍历
        if ((index * 2 + 1) < arr.length){
            preOrder(2 * index +1);
        }
        //向右递归遍历
        if ((index * 2 + 2) < arr.length){
            preOrder(2 * index +2);
        }
    }


}
