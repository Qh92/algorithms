package com.qinh.threadedbinarytree;

/**
 * 代码示例是线索化二叉树的中序遍历
 * 课后作业：前序、后序线索化二叉树
 *
 * @author Qh
 * @version 1.0
 * @date 2021-09-20-21:35
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //中序二叉树
        HeroNode heroNode1 = new HeroNode(1, "tom");
        HeroNode heroNode2 = new HeroNode(3, "jack");
        HeroNode heroNode3 = new HeroNode(6, "smith");
        HeroNode heroNode4 = new HeroNode(8, "marry");
        HeroNode heroNode5 = new HeroNode(10, "king");
        HeroNode heroNode6 = new HeroNode(14, "dim");

        heroNode1.setLeft(heroNode2);
        heroNode1.setRight(heroNode3);
        heroNode2.setLeft(heroNode4);
        heroNode2.setRight(heroNode5);
        heroNode3.setLeft(heroNode6);

        System.out.println("测试线索化");
        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        tree.setRoot(heroNode1);
        tree.threadedNodes();

        //测试，以10号结点进行测试
        HeroNode node5Left = heroNode5.getLeft();
        System.out.println("10号结点的前驱结点是: " + node5Left);
        System.out.println("10号结点的后继结点是: " + heroNode5.getRight());

        System.out.println();

        System.out.println("使用线索化的方式遍历线索二叉树");
        tree.threadedList();

    }
}

/**
 * 定义ThreadedBinaryTree 实现线索化功能的二叉树
 */
class ThreadedBinaryTree{
    private HeroNode root;

    /**
     * 为了实现线索化，需要创建当前结点的前驱结点的指针
     * 在递归进行线索化时，pre总是保留前一个结点
     */
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void threadedNodes(){
        this.threadedNodes(root);
    }

    /**
     * 二叉树进行中序线索化
     * @param node 当前遍历的结点
     */
    public void threadedNodes(HeroNode node){

        //如果当前结点为null，不能线索化
        if (node == null){
            return;
        }
        //先线索化左子树
        threadedNodes(node.getLeft());

        //线索化当前结点
        if (node.getLeft() == null){
            //让当前结点的左指针指向前驱结点
            node.setLeft(pre);
            //修改当前结点的左指针的类型，指向前驱结点
            node.setLeftType(1);
        }
        //处理后继结点
        if (pre != null && pre.getRight() == null){
            //让前驱结点的右指针指向当前结点
            pre.setRight(node);
            //修改前驱结点的右指针类型
            pre.setRightType(1);
        }
        //每处理一个结点后，让当前结点是下一个结点的前驱结点
        pre = node;

        //线索化右子树
        threadedNodes(node.getRight());
    }

    /**
     * 中序遍历线索化二叉树
     */
    public void threadedList(){
        //定义一个变量，存储当前遍历的结点，从root开始
        HeroNode node = root;
        while (node != null){
            //循环的找到leftType == 1的结点，第一个找到的就是8结点
            //后面随着遍历而变化，因为当leftType==1时，说明该结点是按照线索化处理后的有效结点
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }

            //打印当前这个结点
            System.out.println(node);

            //如果当前结点的右指针指向的是后继结点，就一直输出
            while (node.getRightType() == 1){
                //获取当前结点的后继结点
                node = node.getRight();
                System.out.println(node);
            }

            //替换这个遍历的结点
            node = node.getRight();
        }
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
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    /**
     * 1.如果leftType == 0 表示指向的是左子树，如果1则表示指向前屈结点
     * 2.如果rightType ==0 表示指向的是右子树，如果1则表示指向后继结点
     */
    private int leftType;
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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