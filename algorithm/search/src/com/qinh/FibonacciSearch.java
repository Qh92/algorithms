package com.qinh;

/**
 * 斐波那契算法
 *
 * @author Qh
 * @version 1.0
 * @date 2021-10-05-14:19
 */
public class FibonacciSearch {
    private static int maxSize = 20;

    public static void main(String[] args) {

    }


    /**
     * 获取斐波那契数列
     * @return
     */
    private static int[] fib(){
        int[] f = new int[maxSize];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i-1] + f[i-2];
        }
        return f;
    }

    /**
     * 编写斐波那契算法，使用非递归的方式
     * @param arr 数组
     * @param key 需要查找的关键码（值）
     * @return 返回对应的下标，如果没有找到，返回-1
     */
    private static int fibSearch(int[] arr,int key){
        return -1;
    }

}
