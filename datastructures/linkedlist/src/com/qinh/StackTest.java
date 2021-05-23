package com.qinh;

import java.util.Stack;

/**
 * 栈Stack的基本使用
 *
 * @author Qh
 * @version 1.0
 * @date 2021-05-23-19:47
 */
public class StackTest {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        //入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");
        //出栈
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }
}
