package com.qinh;

/**
 * 二叉树
 *
 * @author Qh
 * @version 1.0
 * @date 2021-09-19-18:11
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {
        //二叉树
        BinaryTree tree = new BinaryTree();
        //结点
        HeroNode heroNode1 = new HeroNode(1, "宋江");
        HeroNode heroNode2 = new HeroNode(2, "吴用");
        HeroNode heroNode3 = new HeroNode(3, "卢俊义");
        HeroNode heroNode4 = new HeroNode(4, "林冲");
        HeroNode heroNode5 = new HeroNode(5, "关胜");

        tree.setRoot(heroNode1);
        heroNode1.setLeft(heroNode2);
        heroNode1.setRight(heroNode3);
        heroNode3.setRight(heroNode4);
        heroNode3.setLeft(heroNode5);

        System.out.println("前序遍历");
        //只有4个节点时： 1 2 3 4  五个节点：1 2 3 5 4
        tree.preOrder();

        System.out.println();
        System.out.println("中序遍历");
        // 2 1 3 4 五个节点：2 1 5 3 4
        tree.infixOrder();

        System.out.println();
        System.out.println("后序遍历");
        // 2 4 3 1 五个节点：2 5 4 3 1
        tree.postOrder();

        System.out.println();
        /*
        查找指定结点
        1) 分别使用三种查找方式，查找no=5的结点
        2) 并分析各种查找方式，分别比较了多少次
         */
        //前序查找的次数：4次
        System.out.println("前序查找");
        HeroNode resultNode = tree.preOrderSearch(5);
        if (resultNode != null){
            System.out.printf("找到了，信息为no = %d name=%s", resultNode.getNo(),resultNode.getName());
        }else {
            System.out.printf("没有找到 no = %d 的英雄",5);
        }

        System.out.println();

        //中序查找的次数：3次
        System.out.println("中序查找");
        resultNode = tree.infixOrderSearch(5);
        if (resultNode != null){
            System.out.printf("找到了，信息为no = %d name=%s", resultNode.getNo(),resultNode.getName());
        }else {
            System.out.printf("没有找到 no = %d 的英雄",5);
        }

        System.out.println();

        //后序查找的次数：2次
        System.out.println("后序查找");
        resultNode = tree.postOrderSearch(5);
        if (resultNode != null){
            System.out.printf("找到了，信息为no = %d name=%s", resultNode.getNo(),resultNode.getName());
        }else {
            System.out.printf("没有找到 no = %d 的英雄",5);
        }

        System.out.println();

        System.out.println("删除结点..........");
        System.out.println("删除前，前序遍历");
        tree.preOrder();
        //tree.deleteNode(5);
        //tree.deleteNode(3);
        tree.deleteNode(3);
        System.out.println("删除后，前序遍历");
        tree.preOrder();


    }

}

/**
 * 定义BinaryTree 二叉树
 */
class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        if (this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        if (this.root != null){
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder(){
        if (this.root != null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 前序查找
     * @param no 查找的no值
     * @return 查找到的结点
     */
    public HeroNode preOrderSearch(int no){
        if (root != null){
            return root.preOrderSearch(no);
        }else {
            return null;
        }
    }

    /**
     * 中序查找
     * @param no 查找的no值
     * @return 查找到的结点
     */
    public HeroNode infixOrderSearch(int no){
        if (root != null){
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }

    /**
     * 后序查找
     * @param no 查找的no值
     * @return 查找到的结点
     */
    public HeroNode postOrderSearch(int no){
        if (root != null){
            return root.postOrderSearch(no);
        }else {
            return null;
        }
    }

    public void deleteNode(int no){
        if (root != null){
            //如果只有一个root结点，判断root结点是不是需要删除的结点
            if (root.getNo() == no){
                root = null;
            }else {
                //递归删除
                //root.deleteNode(no);
                root.deleteNodePlus(no);
            }
        }else {
            System.out.println("空树，不能删除");
        }
    }

}

/**
 * 先创建HeroNode结点
 */
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /*
    二叉树的遍历说明：使用前序、中序、后序对二叉树进行遍历
    1) 前序遍历：先输出父结点，再遍历左子树和右子树
    2) 中序遍历：先遍历左子树，再输出父结点，再遍历右子树
    3) 后序遍历：先遍历左子树，再遍历右子树，最后输出父结点
     */

    /**
     * 编写前序遍历的方法
     */
    public void preOrder(){
        //先输出父节点
        System.out.println(this);
        //递归向左子树前序遍历
        if (this.left != null){
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null){
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        //递归向左子树中序遍历
        if (this.left != null){
            this.left.infixOrder();
        }
        //输出父结点
        System.out.println(this);
        //递归向右子树中序遍历
        if (this.right != null){
            this.right.infixOrder();
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder(){
        if (this.left != null){
            this.left.postOrder();
        }
        if (this.right != null){
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序遍历查找：
     * 1.先判断当前结点的no是否等于要查找的，如果相等就返回
     * 2.如果不相等，则判断当前结点的左子结点是否为空，如果不为空，则递归前序查找
     * 3.如果左递归前序查找，找到结点，则返回，否则继续判断，当前结点的右子节点是否为空，如果不为空，则递归前序查找
    */
    /**
     * 前序遍历查找
     * @param no 查找的no
     * @return 如果找到就返回该Node,如果没有找到返回null
     */
    public HeroNode preOrderSearch(int no){
        System.out.println("进入前序查找.........");
        //比较当前结点是不是查找的值
        if (this.no == no){
            return this;
        }
        //判断当前结点的左子结点是否为空，如果不为空，则递归前序查找
        HeroNode resultNode = null;
        if (this.left != null){
            resultNode = this.left.preOrderSearch(no);
        }
        //说明左子树找到了
        if (resultNode != null){
            return resultNode;
        }
        //左递归前序查找，找到结点，则返回，否则继续判断，当前结点的右子节点是否为空，如果不为空，则递归前序查找
        if (this.right != null){
            resultNode = this.right.preOrderSearch(no);
        }
        return resultNode;
    }

    /**
     * 中序查找思路：
     * 1.判断当前结点的左子结点是否为空，如果不为空，则递归中序查找
     * 2.如果找到，则返回，如果没有找到，就和当前结点比较，如果是则返回当前结点，否则继续进行右递归的中序查找
     * 3.如果右递归中序查找，找到就返回，否则返回null
     */
    public HeroNode infixOrderSearch(int no){
        //判断左结点
        HeroNode resultNode = null;
        if (this.left != null){
            resultNode = this.left.infixOrderSearch(no);
        }
        if (resultNode != null){
            return resultNode;
        }
        System.out.println("进入中序查找..........");
        //判断当前结点
        if (this.no == no){
            return this;
        }

        //判断右结点
        if (this.right != null){
            resultNode = this.right.infixOrderSearch(no);
        }
        return resultNode;
    }

    /**
     * 后序查找：
     * 1.判断当前结点的左子节点是否为空，如果不为空，则递归后序查找
     * 2.如果找到，就返回，如果没有找到，就判断当前结点的右子结点是否为空，如果不为空，则右递归进行后序查找，如果找到，就返回
     * 3.和当前结点进行比较，如果找到就返回找到的结点，如果没有，就返回null
     */
    public HeroNode postOrderSearch(int no){
        //判断左子结点
        HeroNode resultNode = null;
        if (this.left != null){
            resultNode = this.left.postOrderSearch(no);
        }
        if (resultNode != null){
            return resultNode;
        }

        //判断右子结点
        if (this.right != null){
            resultNode = this.right.postOrderSearch(no);
        }
        if (resultNode != null){
            return resultNode;
        }
        System.out.println("进入后序查找..........");
        //判断当前结点
        if (this.no == no){
            return this;
        }
        return resultNode;
    }

    /**
     * 删除结点思路：
     * 1.如果树是空树或者只有一个root结点，并且root结点就是需要删除的结点，则等价将二叉树置空
     * 2.因为我们的二叉树是单向的，所以我们是判断单曲结点的子结点是否需要删除结点，而不能去判断当前这个结点是不是需要删除的结点
     * 3.如果当前结点的左子结点不为空，并且左子节点就是要删除的结点，就将this.left=null,并且就返回（结束递归删除）
     * 4.如果当前结点的右子结点不为空，并且右子节垫就是要删除的结点，就将this.right=null,并且就返回（结束递归删除）
     * 5.如果第二和第三步没有删除结点，那么我们就需要向左子树进行递归删除
     * 6.如果第四步也没有删除结点，则应当向右子树进行递归删除
     */
    /**
     * 递归删除结点
     * 1.如果删除的结点是叶子结点，则删除该结点
     * 2.如果删除的结点时非叶子结点，则删除该子树
     */
    public void deleteNode(int no){
        //判断左子结点
        if (this.left != null && this.left.no == no){
            this.left = null;
            return;
        }
        //判断右子结点
        if (this.right != null && this.right.no == no){
            this.right = null;
            return;
        }
        //左子结点递归判断
        if (this.left != null){
            this.left.deleteNode(no);
        }
        //右子结点递归判断
        if (this.right != null){
            this.right.deleteNode(no);
        }
    }

    /**
     * 课后练习
     * 如果要删除的结点是非叶子结点，现在我们不希望将该非叶子结点为根结点的子树删除，需要指定规则，假如规定如下：
     * 1.如果该非叶子结点A只有一个子结点B，则子结点B代替结点A
     * 2.如果该非叶子结点A有左子结点B和右子结点C，则让左子结点B替代结点A
     */
    public void deleteNodePlus(int no){
        //判断左子结点
        if (this.left != null && this.left.no == no){
            HeroNode temp = null;
            if (this.left.left != null){
                temp = this.left.left;
                this.setLeft(temp);
            }
            if (temp == null && this.left.right != null){
                this.setLeft(this.left.right);
            }
            //this.left = null;
            return;
        }
        //判断右子结点
        if (this.right != null && this.right.no == no){
            HeroNode temp = null;
            if (this.right.left != null){
                temp = this.right.left;
                this.setRight(temp);
            }
            if (temp == null && this.right.right != null){
                this.setRight(this.right.right);
            }
            //this.right = null;
            return;
        }

        //左子结点递归判断
        if (this.left != null){
            this.left.deleteNodePlus(no);
        }
        //右子结点递归判断
        if (this.right != null){
            this.right.deleteNodePlus(no);
        }
    }


}
