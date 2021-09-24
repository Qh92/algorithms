package com.qinh;

/**
 * 二叉排序树：对于二叉排序树的任何一个非叶子结点，要求左子结点的值比当前结点的值小，右子结点的值比当前结点的值大
 *
 * @author Qh
 * @version 1.0
 * @date 2021/9/24 15:07
 */
public class BinarySortTreeDemo {
    public static void main(String[] args){
        int[] arr = {7,3,10,12,5,1,9,2};
        BinarySortTree sortTree = new BinarySortTree();
        for (int i : arr){
            sortTree.add(new Node(i));
        }
        System.out.println("中序遍历二叉排序树....");
        //二叉排序树的中序遍历是按升序遍历
        sortTree.infixOrder();

        System.out.println("测试删除叶子结点");
        sortTree.deleteNode(2);
        sortTree.deleteNode(1);
        System.out.println("删除结点后");
        sortTree.infixOrder();



    }
}

/**
 * 二叉排序树
 */
class BinarySortTree{
    private Node root;

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

        }
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


    public void deleteNode(int value){



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
