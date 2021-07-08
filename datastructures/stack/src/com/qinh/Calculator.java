package com.qinh;

/**
 * 栈模拟计算操作 7*2*2-5+1-5+3-4 = ？
 *
 * @author Qh
 * @version 1.0
 * @date 2021/7/8 15:16
 */
public class Calculator {

    public static void main(String[] args){
        //表达式
        String expression = "7*2*2-5+1-5+3-42";
        //创建两个栈，数栈和符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //表达式的下标
        int index = 0;
        //数栈中的两个值
        int num1 = 0;
        int num2 = 0;
        //运算符
        int oper = 0;
        //计算的结果
        int res = 0;
        //每次扫描的字符
        char ch = ' ';
        //用于拼接多位数
        String keepNum = "";
        while (true){
            //依次解析表达式
            ch = expression.substring(index, index + 1).charAt(0);
            //如果是运算符
            if (operStack.isOper(ch)){
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()){
                    //如果符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符,就需要从数栈中pop出两个数,
                    //在从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //再把计算的结果入数栈
                        numStack.push(res);
                        //然后把当前的运算符入符号栈
                        operStack.push(ch);
                    }
                    //如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
                    else {
                        operStack.push(ch);
                    }
                }
                //为空
                else{
                    //如果为空直接入符号栈
                    operStack.push(ch);
                }
            }
            //是数字
            else{
                //numStack.push(ch - 48); //? "1+3" '1' => 1
                //分析思路
                //1. 当处理多位数时，不能发现是一个数就立即入栈，因为它可能是多位数
                //2. 在处理数，需要向expression的表达式的index 后再看一位,如果是数就进行扫描，如果是符号才入栈
                //3. 因此我们需要定义一个变量 字符串，用于拼接

                //处理多位数
                keepNum += ch;

                //如果ch已经是expression的最后一位，就直接入栈
                if (index == expression.length() - 1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    if (operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            //让index+1,并判断是否扫描到expression最后
            index++;
            if (index >= expression.length()){
                break;
            }
        }

        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行
        while (true){
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字
            if (operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        //将数栈的最后数，pop出，就是结果
        int res2 = numStack.pop();
        System.out.printf("表达式 %s = %d",expression,res2);
    }
}

/**
 * 定义一个ArrayStack表示栈
 */
class ArrayStack2{
    /** 栈的大小 */
    private int maxSize;
    /** 数组，数组模拟栈，数据就放在该数组中 */
    private int[] stack;
    /** top表示栈顶，初始化为-1 */
    private int top = -1;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    /**
     * 栈满
     *
     * @return
     */
    public boolean isFull(){
        return top == maxSize - 1;
    }

    /**
     * 栈空
     *
     * @return
     */
    public boolean isEmpty(){
        return top == -1;
    }

    /**
     * 入栈
     *
     * @param value
     */
    public void push(int value){
        //先判断栈是否满
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈，将栈顶的数据返回
     *
     * @return
     */
    public int pop(){
        //先判断栈是否为空
        if (isEmpty()){
            //抛出异常
            throw new RuntimeException("栈空，没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 获取栈顶元素，但不出栈
     *
     * @return
     */
    public int peek(){
        return stack[top];
    }

    /**
     * 显示栈的情况，遍历时，需要从栈顶开始显示数据
     */
    public void list(){
        if (isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }
        //需要从栈顶开始显示数据
        for (int i = top; i >= 0; i--) {
            System.out.println("stack[" + i + "]=" + stack[i]);
        }
    }

    /**
     * 返回运算符的优先级，数字越大，则优先级就越高
     *
     * @param oper
     * @return
     */
    public int priority(int oper){
        if (oper == '*' || oper == '/'){
            return 1;
        }
        else if (oper == '+' || oper == '-'){
            return 0;
        }
        else{
            return -1;
        }
    }

    /**
     * 判断是不是一个运算符
     *
     * @param val
     * @return
     */
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    /**
     * 计算方法
     *
     * @param num1
     * @param num2
     * @param oper
     * @return
     */
    public int cal(int num1,int num2,int oper){
        //用于存放计算的结果
        int res = 0;
        switch (oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num2 * num1;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}


