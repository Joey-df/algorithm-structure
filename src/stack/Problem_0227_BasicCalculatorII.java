package stack;

import java.util.LinkedList;

/**
 * 227. 基本计算器 II
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 *
 * 整数除法仅保留整数部分。
 *
 * 示例 1：
 * 输入：s = "3+2*2"
 * 输出：7
 * 示例 2：
 * 输入：s = " 3/2 "
 * 输出：1
 * 示例 3：
 * 输入：s = " 3+5 / 2 "
 * 输出：5
 *
 * 提示：
 * 1 <= s.length <= 3 * 105
 * s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
 * s 表示一个 有效表达式
 * 表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
 * 题目数据保证答案是一个 32-bit 整数
 */
public class Problem_0227_BasicCalculatorII {

    public static int calculate(String str) {
        return f(str.toCharArray(), 0)[0];
    }

    // 请从str[i...]往下算，遇到字符串终止位置或者右括号，就停止
    // 返回两个值，长度为2的数组
    // 0) 负责的这一段的结果是多少
    // 1) 负责的这一段计算到了哪个位置
    public static int[] f(char[] str, int i) {
        //每个子过程一个que
        LinkedList<String> que = new LinkedList<>();
        int cur = 0;
        int[] sub = null;
        // 从i出发，开始撸串
        while (i < str.length && str[i] != ')') {
            if (str[i] >= '0' && str[i] <= '9') { //遇到的是数字字符
                cur = cur * 10 + str[i++] - '0';
            } else if (str[i] != '(') { // 遇到的是运算符号
                addNum(que, cur);
                que.addLast(String.valueOf(str[i++]));
                cur = 0;
            } else { // 遇到左括号了
                sub = f(str, i + 1);
                cur = sub[0];
                i = sub[1] + 1;
            }
        }
        addNum(que, cur);
        return new int[] { getNum(que), i };
    }

    public static void addNum(LinkedList<String> que, int num) {
        if (!que.isEmpty()) {
            int cur = 0;
            String top = que.pollLast();
            if (top.equals("+") || top.equals("-")) {
                que.addLast(top); //如果是加减号，拿出来再重新放回去
            } else {
                //如果是乘除号，将倒数第二位置的数字与即将进来的num做运算后，得到的结果，放到队尾
                cur = Integer.valueOf(que.pollLast());
                num = top.equals("*") ? (cur * num) : (cur / num);
            }
        }
        que.addLast(String.valueOf(num));
    }

    //que中只剩下数字和加减号
    //计算que中的结果
    public static int getNum(LinkedList<String> que) {
        int res = 0;
        boolean add = true;
        String cur = null;
        int num = 0;
        while (!que.isEmpty()) {
            cur = que.pollFirst();
            if (cur.equals("+")) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            } else { //数字
                num = Integer.valueOf(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "8*((-7+6)-4)+8*1";
        int ans = calculate(s);
        System.out.println(ans);
    }

}
