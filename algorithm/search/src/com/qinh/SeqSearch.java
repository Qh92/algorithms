package com.qinh;

/**
 * 线性查找
 *
 * @author Qh
 * @version 1.0
 * @date 2021-10-04-21:34
 */
public class SeqSearch {

    public static void main(String[] args) {
        int[] arr = {1,9,11,-1,34,89};
        int index = seqSearch(arr, -1);
        System.out.println(index);
    }

    private static int seqSearch(int[] arr,int value){
        for (int i = 0; i < arr.length; i++) {
            if (value == arr[i]){
                return i;
            }
        }
        return -1;
    }
}
