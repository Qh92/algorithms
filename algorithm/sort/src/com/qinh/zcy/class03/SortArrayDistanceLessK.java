package com.qinh.zcy.class03;

import java.util.PriorityQueue;

/**
 * 已知一个几乎有序的数组，几乎有序是指，如果把数组排好顺序的话，
 * 每个元素移动的距离可以不超过k，并且k相对于数组来说比较小。
 * 请选择一个合适的排序算法针对这个数组进行排序
 *
 * @author Qh
 * @version 1.0
 * @date 2022-01-10 23:08
 */
public class SortArrayDistanceLessK {


    public void sortedArrDistanceLessK(int[] arr,int k) {
        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        int index = 0;
        for (; index < Math.min(arr.length, k) ; index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length;i++,index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }

    public static void main(String[] args) {
        // 优先级队列，小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        heap.add(1);
        heap.add(19);
        heap.add(5);
        heap.add(10);
        heap.add(9);

        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }

    }
}
