package com.qinh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰计算器
 * 1.输入一个逆波兰表达式（后缀表达式），使用栈（stack），计算其结果
 * 2.支持小括号和多位整数
 *
 * @author Qh
 * @version 1.0
 * @date 2021-10-01-20:28
 */
public class PolandNotation {
    public static void main(String[] args) {

        /*
        先定义一个逆波兰表达式
        （3+4）*5-6 => 3 4 + 5 * 6 -
        为了方便，逆波兰表达式的数字和符号使用空格隔开
         */
        //String suffixExpression = "3 4 + 5 * 6 - ";
        String suffixExpression = "30 4 + 5 * 6 - ";
        /*
        思路：
        1.先将"3 4 + 5 * 6 - " => 放到ArrayList中
        2.将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算
         */
        List<String> rpnList = getListString(suffixExpression);
        System.out.println(rpnList);

        int result = calculate(rpnList);
        System.out.println(result);

        /*
        完成将一个中缀表达式转换为后缀表达式
        1.1+((2+3)×4)-5 => 转成 1 2 3 + 4 * + 5 -
        2.先将中缀表达式转换为ArrayList
        3.将得到的中缀表达式对应的List 转成 后缀表达式对应的字符串 => 转为List
         */
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println(infixExpressionList);

        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        //[1, 2, 3, +, 4, *, +, 5, -]
        System.out.println("后缀表达式对应的List: " + suffixExpressionList);
        System.out.println("输出的结果: " + calculate(suffixExpressionList));

    }

    /**
     * 依次将数据和运算符放入到ArrayList中
     * @param suffixExpression 逆波兰表达式
     * @return
     */
    public static List<String> getListString(String suffixExpression){
        String[] s = suffixExpression.split(" ");
        return Arrays.asList(s);
    }

    /**
     * 完成对逆波兰表达式的运算
     * 1.从左至右扫描，将3和4压入栈
     * 2.遇到运算符，弹出栈顶和次顶元素，计算两个的值，并将值再压入栈中
     * 3.再次扫描遇到数字，将数字压入栈中
     * 4.依次类推，直到运算完结果
     * @param ls
     * @return
     */
    public static int calculate(List<String> ls){
        Stack<String> stack = new Stack();
        for (String item : ls){
            //使用正则表达式来取出整数
            if (item.matches("\\d+")){
                stack.push(item);
            }else {
                //pop出两个数，并运算，再入栈
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int result = 0;
                if (item.equals("+")){
                    result = num2 + num1;
                }else if (item.equals("-")){
                    result = num2 - num1;
                }else if (item.equals("*")){
                    result = num2 * num1;
                }else if (item.equals("/")){
                    result = num2 / num1;
                }else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(result + "");
            }
        }
        //最后留在stack中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }

    /**
     * 将中缀表达式转成对应的List
     * @param s
     * @return
     */
    public static List<String> toInfixExpressionList(String s){
        List<String> ls = new ArrayList<>();
        //这是一个指针，用于遍历中缀表达式字符串
        int i = 0;
        //对多位数的拼接
        String str;
        //每遍历到一个字符，就放入到c
        char c;
        do {
            //如果c是一个非数字，就直接加入到集合中
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57){
                ls.add("" + c);
                i++;
            }
            //如果是一个数，需要考虑多位数
            else {
                //每次都需要清空该拼接字符串
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57){
                    //拼接多位数
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        }while (i < s.length());
        return ls;
    }

    /**
     * 将中缀表达式集合转为后缀表达式集合
     * 1 初始化两个栈：运算符栈s1和储存中间结果的栈s2；
     * 2 从左至右扫描中缀表达式；
     * 3 遇到操作数时，将其压s2；
     * 4 遇到运算符时，比较其与s1栈顶运算符的优先级：
     * 1).如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
     * 2).否则，若优先级比栈顶运算符的高，也将运算符压入s1；
     * 3).否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较；
     * 5 遇到括号时：  (1) 如果是左括号“(”，则直接压入s1 (2) 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
     * 6 重复步骤2至5，直到表达式的最右边
     * 7 将s1中剩余的运算符依次弹出并压入s2
     * 8  依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
     * @param infixList
     * @return
     */
    public static List<String> parseSuffixExpressionList(List<String> infixList){
        //定义一个符号栈
        Stack<String> stack = new Stack<>();
        //此处可以不使用栈来存储中间结果，而使用List来存储中间结果
        List<String> list = new ArrayList<>();

        for (String item : infixList){
            //如果是一个数，加入list
            if (item.matches("\\d+")){
                list.add(item);
            }else if (item.equals("(")){
                stack.push(item);
            }else if (item.equals(")")){
                //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!stack.peek().equals("(")){
                    list.add(stack.pop());
                }
                //将这一对括号丢弃
                stack.pop();
            }else {
                //当item的优先级小于等于栈顶运算符，将栈顶的运算符弹出并加入到list中，再次转到(4.1)与栈中新的栈顶运算符相比较
                while (stack.size() != 0 && Operation.getValue(stack.peek()) >= Operation.getValue(item)){
                    list.add(stack.pop());
                }
                //还需要将item压入栈中
                stack.push(item);
            }
        }

        //将stack中剩余的运算符依次弹出并加入list
        while (stack.size() != 0){
            list.add(stack.pop());
        }
        //按顺序输出就是对应的后缀表达式对应的List
        return list;
    }

}

/**
 * 返回运算符对应的优先级
 */
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
        }
        return result;
    }
}
