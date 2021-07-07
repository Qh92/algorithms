package com.qinh;

/**
 * 约瑟夫问题
 *
 * @author Qh
 * @version 1.0
 * @date 2021-06-01-23:52
 */
public class Joseph {
    public static void main(String[] args) {
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.addBoy(5);
        list.showBoy();

        //测试小孩出圈
        list.countBoy(1,2,5);
    }
}

/**
 * 创建一个环形单向链表
 */
class CircleSingleLinkedList{
    /**创建一个first节点，当前没有编号 */
    private Boy first = null;

    /**
     * 添加小孩节点，构成一个环形的链表
     */
    public void addBoy(int nums){
        //num做一个数据校验
        if (nums < 1){
            System.out.println("nums值不正确");
            return;
        }
        //辅助指针，帮助构建环形链表
        Boy currentBoy = null;
        //使用for循环来创建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号，创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1){
                first = boy;
                //构成环
                first.setNext(first);
                //让currentBoy指向第一个小孩
                currentBoy = first;
            }else {
                currentBoy.setNext(boy);
                boy.setNext(first);
                currentBoy = boy;
            }
        }
    }

    /**
     * 遍历当前的环形链表
     */
    public void showBoy(){
        //判断链表是否为空
        if (first == null){
            System.out.println("没有任何元素");
            return;
        }
        //因为first不能动，因此仍然使用一个辅助指针完成遍历
        Boy currentBoy = first;
        while (true){
            System.out.printf("小孩的编号%d \n",currentBoy.getNo());
            //说明已经遍历完成
            if (currentBoy.getNext() == first){
                break;
            }
            //让currentBoy后移
            currentBoy = currentBoy.getNext();
        }
    }

    /**
     * 根据用户的输入，计算出小孩出圈的顺序
     *
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo,int countNum,int nums){
        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建一个辅助指针，帮助完成小孩出圈
        Boy helper = first;
        //需要创建一个辅助指针(变量)helper,事先应该指向环形链表的最后这个节点
        while (true){
            //说明helper指向最后一个节点
            if (helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first和helper移动k-1次
        for (int i = 0; i < startNo -1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first和helper指针同时的移动m-1次，然后出圈
        //这里是一个循环操作，直到圈中只有一个节点
        while (true){
            //说明圈中只有一个节点
            if (helper == first){
                break;
            }
            //让first和helper指针同时的移动countNum - 1
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n",first.getNo());
            //这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d \n",helper.getNext().getNo());
    }

}

class Boy{
    /** 编号 */
    private int no;
    /** 指向下一个节点，默认为null */
    private Boy next;

    public Boy() {
    }

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
