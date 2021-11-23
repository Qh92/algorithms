package easy;

import java.util.HashMap;

/**
 * @author Qh
 * @version 1.0
 * @date 2021/11/23 17:50
 */
public class LRUCache {
    /** head指针指向最不常用的数据 */
    Node head;
    /** head指针指向常用的数据 */
    Node tail;

    HashMap<Integer,Node> container = new HashMap<>();

    int capacity;

    int count = 0;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = container.get(key);
        if (node != null) {
            //更改指针
            if (node == head) {
                node.next = head.next;
                head.next.prev = node;
                head = node;
            }else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }

            tail.next = node;
            node.prev = tail;

            tail = node;

            return node.value;
        }else {
            return -1;
        }
    }

    public void put(int key, int value) {
        Node node;
        if (count < capacity){
            if (head == null && tail == null) {
                node = new Node(key,value);
                head = node;
                tail = head;
            }else {
                node = new Node(key,value);
                tail.next = node;
                tail = node.prev;

                tail = node;
            }
            container.put(key,node);
            count++;
        }
        //移除不常用的数据
        else {
            container.remove(head.key);
            node = new Node(key,value);
            node.next = head.next;
            head = node;
        }


    }


    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args){
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(3));
    }
}
