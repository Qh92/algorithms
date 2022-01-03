package com.qinh.zcy.class02;

/**
 * 递归获取数组最大值
 * 可以将递归运算看做一颗二叉树的后序遍历
 *
 * @author Qh
 * @version 1.0
 * @date 2022-01-02 13:14
 */
public class GetMaxByRecursion {
    public static void main(String[] args) {
        int[] arr = {1,2,10,20,3,9,6,40,2,6,7,8,32};
        System.out.println(getMax(arr));
    }

    public static int getMax(int[] arr) {
        return process(arr,0,arr.length - 1);
    }

    /**
     * arr[L..R]范围上求最大值
     * @param arr
     * @param L
     * @param R
     * @return
     */
    private static int process(int[] arr, int L, int R) {
        //数组上只有一个数
        if (L == R) {
            return arr[L];
        }
        // 中点，防止 (L+R)/2 (L+R)溢出
        int mid = L + ((R - L) >> 1);
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }
}
