package com.qinh.zcy.class03;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Node;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 哈希表和有序表
 *
 * @author Qh
 * @version 1.0
 * @date 2022-01-13 23:35
 */
public class HashAndTree {

    public static class Node {
        public int value;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            return value == node.value;
        }

        @Override
        public int hashCode() {
            return value;
        }
    }

    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.value - o2.value;
        }
    }

    public static void main(String[] args) {
        Node nodeA = new Node(1);
        Node nodeB = new Node(7);
        Node nodeC = new Node(4);

        // 红黑树
        TreeSet<Node> treeSet = new TreeSet<>(new NodeComparator());
        // 以下代码会报错，因为没有提供Node类型的比较器
        try {
            treeSet.add(nodeA);
            treeSet.add(nodeB);
            treeSet.add(nodeC);
        } catch (Exception e) {
            System.out.println("错误信息 : " + e.getMessage());
        }

        // 展示有序表常用操作
        TreeMap<Integer,String> treeMap = new TreeMap<>();
        treeMap.put(7, "我是7");
        treeMap.put(1, "我是1");
        treeMap.put(20, "我是20");
        treeMap.put(8, "我是8");
        treeMap.put(18, "我是18");
        treeMap.put(39, "我是39");
        System.out.println(treeMap.containsKey(8));
        System.out.println(treeMap.get(1));
        System.out.println(treeMap.firstKey() + ", 我最小");
        System.out.println(treeMap.lastKey() + ", 我最大");
        System.out.println(treeMap.floorKey(9) + ", 在表中所有<=9的数中，我离8最近");
        System.out.println(treeMap.ceilingKey(9) + ", 在表中所有>=9的数中，我离8最近");
        System.out.println(treeMap.floorKey(19) + ", 在表中所有<=19的数中，我离19最近");
        System.out.println(treeMap.ceilingKey(19) + ", 在表中所有>=19的数中，我离19最近");
    }
}
