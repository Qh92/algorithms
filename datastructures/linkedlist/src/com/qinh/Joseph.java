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
