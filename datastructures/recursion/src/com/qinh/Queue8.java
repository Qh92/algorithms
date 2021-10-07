package com.qinh;

/**
 * 八皇后问题
 *
 * @author Qh
 * @version 1.0
 * @date 2021-10-02-16:06
 */
public class Queue8 {

    /**
     * 定义一个max表示共有多少个皇后
     */
    private int max = 8;

    /**
     * 定义数组array,保存皇后放位置的结果，比如arr={0,4,7,5,2,6,1,3}
     */
    private int[] array = new int[max];

    private int count;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println(queue8.count);
    }

    /**
     * 输出皇后摆放的位置
     */
    private void print(){
        count++;
        for (int value : array) {
            System.out.print(value + "");
        }
        System.out.println();
    }

    /**
     * 当放置第n个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突
     * @param n 表示第几个皇后
     * @return
     */
    private boolean judge(int n){
        for (int i = 0; i < n; i++) {
            /*
             array[i] == array[n] 表示判断 第n个皇后是否和前面的n-1个皇后在同一列
             Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i个皇后是否在同一斜线上
             */
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }

    /**
     * 放置第n个皇后
     * @param n
     */
    private void check(int n){
        // n=8,其实8个皇后就已然放好
        if (n == max){
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后n，放到该行的第i列
            array[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if (judge(n)){
                //不冲突，接着放n+1个皇后，即开始递归
                check(n+1);
            }
            //如果冲突，就继续执行array[n] = i;即将第n个皇后，放置在本行的后一个位置
        }
    }


}
