package com.qinh;

import java.util.Stack;

/**
 * 单链表
 *
 * @author Qh
 * @version 1.0
 * @date 2021-05-20-20:37
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        //先创捷节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建链表
        SingleLinkedList list = new SingleLinkedList();
//        list.add(hero1);
//        list.add(hero2);
//        list.add(hero3);
//        list.add(hero4);

        list.addByOrder(hero1);
        list.addByOrder(hero3);
        list.addByOrder(hero4);
        list.addByOrder(hero2);
        list.addByOrder(hero3);

        //显示链表
        list.list();

        //修改节点
        HeroNode heroNode = new HeroNode(2, "小卢", "小笼包");
        list.update(heroNode);
        System.out.println("修改后的链表");
        list.list();

        //删除节点
//        list.delete(1);
//        System.out.println("删除后的链表");
//        list.list();
//        list.delete(4);
//        System.out.println("删除后的链表");
//        list.list();
//        list.delete(2);
//        list.delete(3);
//        System.out.println();
//        list.list();

        //求单链表中有效节点的个数
        System.out.println("有效的节点个数: " + getLength(list.getHead()));

        //测试是否得到倒数第k个节点
        HeroNode lastIndexNode = findLastIndexNode(list.getHead(), 3);
        System.out.println("result: " +lastIndexNode);

        //测试链表反转
        /*reverseList(list.getHead());
        System.out.println("反转后的链表: ");
        list.list();*/
        //reverseList.list();

        System.out.println("测试逆序打印单链表");
        reversePrint(list.getHead());


    }

    /**
     * 获取到单链表的节点的个数（如果是带头节点的链表，不统计头节点）
     *
     * @param head 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head){
        int count = 0;
        //空链表
        if (head.next == null){
            return count;
        }
        //定义一个辅助的变量
        HeroNode current = head.next;
        while (current != null){
            count++;
            current = current.next;
        }
        return count;
    }

    /**
     * 查找单链表中的倒数第k个节点【新浪面试题】
     * 思路
     * 1.编写一个方法，接收head节点，同时接收一个index
     * 2.index表示是倒数第index个节点
     * 3.先把链表从头到尾遍历，得到链表的总的长度 getLength
     * 4.得到size后，从链表的第一个开始遍历(size - index)个，就可以得到
     * 5.如果找到了，则返回该节点，否则返回null
     *
     * @param head
     * @param index
     * @return
     */
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        //如果链表为空，则返回null
        if (head.next == null){
            return null;
        }
        //第一次遍历得到链表的长度(节点个数)
        int size = getLength(head);
        //第二次遍历 size-index位置，就是我们倒数的第k个节点
        //先做index的校验
        if (index <= 0 || index > size){
            return null;
        }
        HeroNode current = head.next;
        for (int i = 0; i < size-index; i++) {
            current = current.next;
        }
        return current;
    }

    /**
     * 将单链表反转
     *
     * @param head
     */
    public static void reverseList(HeroNode head){
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null){
            return;
        }
        //定义一个辅助的指针，遍历原来的链表
        HeroNode current = head.next;
        //指向当前节点的下一个节点
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0,"","");
        /*
        遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
         */
        while (current != null){
            //先暂时保存当前节点的下一个节点，因为后面需要使用
            next = current.next;
            //将current的下一个节点指向新的链表的最前端
            current.next = reverseHead.next;
            //将current连接到新的链表上
            reverseHead.next = current;
            //让current后移
            current = next;
        }
        //将head.next指向reverseHead.next，实现单链表的反转
        head.next = reverseHead.next;
    }

    /*public static SingleLinkedList reverse(SingleLinkedList list){
        SingleLinkedList newList = new SingleLinkedList();
        //当前指针位置
        HeroNode current = list.getHead().next;
        //添加链表头
        list.getHead().next = null;
        newList.setHead(list.getHead());
        //遍历原来的链表
        while (true){
            if (current == null){
                break;
            }
            HeroNode temp = current.next;
            current.next = newList.getHead().next;
            newList.getHead().next = current;

            current = temp;
        }
        list = newList;
        return list;
    }*/

    /**
     * 利用栈数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，实现逆序打印的效果
     */
    public static void reversePrint(HeroNode head){
        //空链表，不能打印
        if (head.next == null){
            return;
        }
        //创建一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode current = head.next;
        //将链表的所有节点压入栈
        while (current != null){
            stack.push(current);
            //current后移，这样就可以压入下一个节点
            current = current.next;
        }
        //将栈中的节点进行打印，pop出栈
        while (stack.size() > 0){
            //stack特点先进后出
            System.out.println(stack.pop());
        }
    }


}

/**
 * 定义SingleLinkedList管理英雄
 */
class SingleLinkedList{

    /**
     * 先初始化一个头节点，头节点不要动,不存放具体的数据
     */
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    public void setHead(HeroNode head) {
        this.head = head;
    }
    //添加节点到单向链表
    /**
    思路，当不考虑编号顺序时
    1.找到当前链表的最后节点
    2.将最后这个节点的next，指向新的节点
     */
    public void add(HeroNode node){
        //head节点不能动，因此需要一个辅助遍历temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true){
            //找到链表的最后
            if (temp.next == null){
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //将最后这个节点的next指向新的节点
        temp.next = node;
    }

    /**
     * 第二种方式在添加英雄时，根据排名将英雄插入到指定位置(如果有这个排名，则添加失败，并给出提示)
     */
    public void addByOrder(HeroNode heroNode){
        //头节点不能动，因此仍然通过辅助指针来帮助找到添加的位置
        //因为是单链表，因此找的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        //添加的编号是否存在，默认为false
        boolean flag = false;
        while (true){
            //说明temp已经在链表的最后
            if (temp.next == null){
                break;
            }
            //位置找到，就在temp的后面插入
            if (temp.next.no > heroNode.no){
                break;
            }
            //说明希望添加的heroNode的编号已然存在
            else if (temp.next.no == heroNode.no){
                flag = true;
                break;
            }
            //后移，遍历当前链表
            temp = temp.next;
        }
        //判断flag的值
        //不能添加，编号已经存在
        if (flag){
            System.out.printf("准备插入的英雄编号 %d 已经存在，不能加入\n" ,heroNode.no);
        } else {
            //插入到链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 修改节点的信息，根据no编号来修改，即no编号不能改
     * 说明
     * 1.根据newHeroNode的no来修改即可
     */
    public void update(HeroNode newHeroNode){
        //判断是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        //表示是否找到该节点
        boolean flag = false;
        while (true){
            if (temp == null){
                break;
            }
            if (temp.no == newHeroNode.no){
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }
        //没有找到元素
        else {
            System.out.printf("没有找到编号 %d 的节点，不能修改\n",newHeroNode.no);
        }
    }

    /**
     * 删除节点
     * 思路
     * 1.head节点不能动，需要一个temp辅助节点找到待删除节点的前一个节点
     * 2.说明我们在比较时，是temp.next.no和需要删除的节点的no比较
     */
    public void delete(int no){
        HeroNode temp = head;
        //是否找到待删除节点
        boolean flag = false;
        while (true){
            //已经到链表的最后
            if (temp.next == null){
                break;
            }
            //找到的待删除节点的前一个节点temp
            if (temp.next.no == no){
                flag = true;
                break;
            }
            //temp后移，遍历
            temp = temp.next;
        }
        //找到
        if (flag){
            //可以删除
            temp.next = temp.next.next;
        }else {
            System.out.printf("要删除的 %d 节点不存在\n",no);
        }
    }


    /**
     * 显示链表【遍历】
     */
    public void list(){
        //先判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true){
            //判断是否到链表最后
            if (temp == null){
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }

}

/**
 * 定义一个HeroNode，每个HeroNode对象就是一个节点
 */
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    /**
     * 指向下一个节点
     */
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
