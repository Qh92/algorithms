package com.qinh;

/**
 * AVL树：平衡二叉树也叫平衡二叉搜索树
 * 特点：它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树
 * 平衡二叉树的常用实现方法有红黑树、AVL树、替罪羊树、Treap、伸展树等
 *
 * @author Qh
 * @version 1.0
 * @date 2021-09-27-0:21
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] arr = {4,3,6,5,7,8};
        AVLTree avlTree = new AVLTree();
        //添加结点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        //遍历
        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println("在没有做平衡处理前");
        //4
        System.out.println("树的高度: " + avlTree.getRoot().height());
        //1
        System.out.println("树的左子树的高度: " + avlTree.getRoot().leftHeight());
        //3
        System.out.println("树的右子树的高度: " + avlTree.getRoot().rightHeight());
    }
}

/**
 * 创建AVLTree
 */
class AVLTree{

    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * 创建二叉排序树，添加结点
     * @param node
     */
    public void add(Node node){
        //第一次添加结点
        if (root == null){
            root = node;
        }else {
            root.add(node);
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        if (root != null){
            root.infixOrder();
        }else {
            System.out.println("当前二叉排序树为空.........");
        }
    }

    /**
     * 查找结点
     * @param value 待查找的结点值
     * @return 查找到的结点
     */
    public Node search(int value){
        if (root == null){
            return null;
        }else {
            return root.search(value);
        }
    }

    /**
     * 查找结点的父节点
     * @param value 待查找的结点值
     * @return 查找到的父节点
     */
    public Node searchParent(int value){
        if (root == null){
            return null;
        }else {
            return root.searchParent(value);
        }
    }

    public void deleteNode(int value){
        if (root == null){
            return;
        }else {
            //需要先去找到待删除的结点
            Node targetNode = search(value);
            //判断是否找到了待删除的结点
            if (targetNode == null){
                return;
            }
            //如果我们发现当前这颗二叉排序树只有一个结点
            if (root.getLeft() == null && root.getRight() == null){
                root = null;
                return;
            }
            //查找父节点
            Node parentNode = searchParent(value);
            //如果删除的结点是叶子结点
            if (targetNode.getLeft() == null && targetNode.getRight() == null){
                //判断targetNode是父结点的左子结点还是右子结点
                if (parentNode.getLeft() != null && parentNode.getLeft().getValue() == value){
                    parentNode.setLeft(null);
                }else if (parentNode.getRight() != null && parentNode.getRight().getValue() == value){
                    parentNode.setRight(null);
                }
            }
            //删除有两颗子树的结点
            else if(targetNode.getLeft() != null && targetNode.getRight() != null){
                int minVal = searchAndDeleteRightMinNode(targetNode.getRight());
                targetNode.setValue(minVal);
            }
            //删除只有一颗子树的结点
            else {
                //如果删除的结点有左子结点
                if (targetNode.getLeft() != null){
                    if (parentNode != null){
                        //如果targetNode是父结点的左子结点
                        if (parentNode.getLeft().getValue() == targetNode.getValue()){
                            parentNode.setLeft(targetNode.getLeft());
                        }
                        //如果targetNode是父结点的右子结点
                        else if (parentNode.getRight().getValue() == targetNode.getValue()){
                            parentNode.setRight(targetNode.getLeft());
                        }
                    }else {
                        root = targetNode.getLeft();
                    }
                }
                //如果删除的结点有右子结点
                else {
                    if (parentNode != null){
                        //如果targetNode是父结点的左子结点
                        if (parentNode.getLeft().getValue() == targetNode.getValue()){
                            parentNode.setLeft(targetNode.getRight());
                        }
                        //如果targetNode是父结点的右子结点
                        else if (parentNode.getRight().getValue() == targetNode.getValue()){
                            parentNode.setRight(targetNode.getRight());
                        }
                    }else {
                        root = targetNode.getRight();
                    }
                }
            }
        }
    }

    /**
     * 查找以当前删除结点为根结点的右子树的最小结点并删除当前最小结点
     * @param node 传入的结点（当做二叉排序树的根结点）
     * @return 返回二叉排序树的右子树中最小的结点
     */
    public int searchAndDeleteRightMinNode(Node node){
        Node target = node;
        //循环的查找左子结点，就会找到最小的结点
        while (target != null && target.getLeft() != null){
            target = target.getLeft();
        }
        //target指向最小的结点,并删除该最小结点
        deleteNode(target.getValue());
        return target.getValue();
    }
}

/**
 * 创建Node结点
 */
class Node{
    private int value;
    private Node left;
    private Node right;

    /**
     * 返回以该结点为根结点的树的高度
     * @return
     */
    public int height(){
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * 返回左子树的高度
     * @return
     */
    public int leftHeight(){
        return left == null ? 0 : left.height();
    }

    /**
     * 返回右子树的高度
     * @return
     */
    public int rightHeight(){
        return right == null ? 0 : right.height();
    }

    /**
     * 构造二叉排序树
     * @param node 添加的结点
     */
    public void add(Node node){
        if (node == null){
            return;
        }
        //添加的结点比当前结点小
        if (node.getValue() < this.getValue()){
            //如果当前结点的左子树为空，则添加到当前结点的左边
            if (this.getLeft() == null){
                this.setLeft(node);
            }else {
                //递归进行判断
                this.getLeft().add(node);
            }
        }
        else if (node.getValue() >= this.getValue()){
            if (this.getRight() == null){
                this.setRight(node);
            }else {
                this.getRight().add(node);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        if (this.getLeft() != null){
            this.getLeft().infixOrder();
        }
        System.out.println(this);
        if (this.getRight() != null){
            this.getRight().infixOrder();
        }
    }

    /**
     * 查找结点
     * @param value 查找的值
     * @return 查找到的结点
     */
    public Node search(int value){
        if (value == this.getValue()){
            return this;
        }
        //如果查找的结点比当前结点小
        else if (value < this.getValue()){
            if (this.getLeft() == null){
                return null;
            }
            return this.getLeft().search(value);
        }
        else {
            if (this.getRight() == null){
                return null;
            }
            return this.getRight().search(value);
        }
    }

    /**
     * 查找需要查找结点的父结点
     * @param value 需要查找的结点的值
     * @return 父结点，如果为空则为null
     */
    public Node searchParent(int value){
        //如果当前结点就是查找的结点的父节点，则返回
        if (this.left != null && this.left.value == value
                || this.right != null && this.right.value == value){
            return this;
        }else {
            //如果查找的值小于当前结点的值，并且当前结点的左子结点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }


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
}
