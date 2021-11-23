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
        if (capacity <= 0) {
            throw new RuntimeException("容量设置异常");
        }
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = container.get(key);
        if (node != null) {
            //更改指针
            if (node == head) {
                Node next = head.next;
                head.next.prev = null;
                head.next = null;
                head = next;
            } else if (node != tail){
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
        Node containerNode = container.get(key);
        if (containerNode != null) {
            node = new Node(key, value);
            container.put(key, node);
            if (tail != containerNode) {
                if (head == containerNode) {
                    Node next = head.next;
                    head.next.prev = null;
                    head.next = null;
                    head = next;
                }else {
                    containerNode.prev.next = containerNode.next;
                    containerNode.next.prev = containerNode.prev;
                }
                tail.next = node;
                containerNode.prev = tail;
                tail = node;
            }
        }else {
            if (count < capacity){
                if (head == null && tail == null) {
                    node = new Node(key,value);
                    head = node;
                    tail = node;
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

                Node next = head.next;
                head.next.prev = null;
                head.next = null;
                head = next;

                tail.next = node;
                node.prev = tail;

                tail = node;

                container.put(key, node);
            }
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
        LRUCache lruCache = new LRUCache(10);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        lruCache.put(4, 5);
        lruCache.put(1, 10);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(3));
    }
}
