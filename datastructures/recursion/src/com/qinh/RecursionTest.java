package com.qinh;

/**
 * 递归问题
 * 递归需要遵守的重要规则：
 * 1.执行一个方法时，就会创建一个新的受保护的独立空间（栈空间）
 * 2.方法的局部变量是独立的，不会相互影响，比如n变量
 * 3.如果方法中使用的是引用类型变量（比如数组），就会共享该引用类型的数据
 * 4.递归必须向退出递归的条件逼近，否则就是无限递归，出现StackOverflowError
 * 5.当一个方法执行完毕，或者遇到return，就会返回，遵守谁调用，就将结果返回给谁，同时当方法执行完毕或者返回时，该方法也就执行完毕
 *
 * @author Qh
 * @version 1.0
 * @date 2021-10-02-10:20
 */
public class RecursionTest {
    public static void main(String[] args) {
        System.out.println(factorial(4));
    }

    /**
     * 打印问题
     * @param n
     */
    private static void test(int n){
        if (n > 2){
            test(n-1);
        }
        System.out.println("n=" + n);
    }

    /**
     * 阶乘问题
     * @param n
     * @return
     */
    private static int factorial(int n){
        if (n == 1){
            return 1;
        }else {
            return factorial(n - 1) * n;
        }
    }
}
