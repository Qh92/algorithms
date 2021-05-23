package com.qinh;

/**
 * 双向链表
 *
 * @author Qh
 * @version 1.0
 * @date 2021-05-23-20:19
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表的测试");
        //先创捷节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        DoubleLinkedList list = new DoubleLinkedList();
        list.add(hero1);
        list.add(hero2);
        list.add(hero3);
        list.add(hero4);

        list.list();
        
        //修改
        HeroNode2 hero5 = new HeroNode2(4, "公孙胜", "入云龙");
        list.update(hero5);
        System.out.println("修改后的链表情况");
        list.list();

        //删除
        list.delete(3);
        System.out.println("删除后的链表情况");
        list.list();

        DoubleLinkedList list1 = new DoubleLinkedList();
        list1.addByOrder(hero1);
        list1.addByOrder(hero4);
        list1.addByOrder(hero3);
        list1.addByOrder(hero2);
        System.out.println("按编号顺序添加");
        list1.list();


    }
}

/**
 * 双向链表
 */
class DoubleLinkedList{
    /**
     * 先初始化一个头节点，头节点不要动,不存放具体的数据
     */
    private HeroNode2 head = new HeroNode2(0,"","");

    public HeroNode2 getHead() {
        return head;
    }

    public void setHead(HeroNode2 head) {
        this.head = head;
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
        HeroNode2 temp = head.next;
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

    public void add(HeroNode2 node){
        //head节点不能动，因此需要一个辅助遍历temp
        HeroNode2 temp = head;
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
        //形成双向链表
        temp.next = node;
        node.pre = temp;
    }

    public void addByOrder(HeroNode2 heroNode){
        //头节点不能动，因此仍然通过辅助指针来帮助找到添加的位置
        //因为是单链表，因此找的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode2 temp = head;
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
            heroNode.pre = temp;
            if (temp != null){
                temp.next = heroNode;
            }
        }
    }

    /**
     * 修改一个节点的内容，可以看到双向链表的节点内容修改和单向链表一样
     *
     * @param newHeroNode
     */
    public void update(HeroNode2 newHeroNode){
        //判断是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode2 temp = head.next;
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
     * 从双向链表中删除节点
     * 说明
     * 1.对于双向链表，可以直接找到要删除的这个节点
     * 2.找到后，自我删除即可
     *
     * @param no
     */
    public void delete(int no){

        //判断当前链表是否为空
        if (head.next == null){
            System.out.println("链表为空，无法删除");
            return;
        }
        //辅助变量（指针）
        HeroNode2 temp = head.next;
        //是否找到待删除节点
        boolean flag = false;
        while (true){
            //已经到链表的最后
            if (temp == null){
                break;
            }
            //找到的待删除节点的前一个节点temp
            if (temp.no == no){
                flag = true;
                break;
            }
            //temp后移，遍历
            temp = temp.next;
        }
        //找到
        if (flag){
            //可以删除
            temp.pre.next = temp.next;
            //如果是最后一个节点，就不需要执行下面这句话，否则出现空指针异常
            if (temp.next != null){
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.printf("要删除的 %d 节点不存在\n",no);
        }
    }
}

class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    /**
     * 指向下一个节点，默认为null
     */
    public HeroNode2 next;

    /**
     * 指向前一个节点，默认为null
     */
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
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
