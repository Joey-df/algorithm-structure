package dynamic_programming.left_to_right;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 *
 * Example 1:
 *
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 *
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */
public class Problem_91_Decode_Ways {

    //从左往右的尝试模型
    //暴力尝试
    //递归含义：[0...index-1]已经搞定了，不用操心了
    //        index开头及其往右自由选择，decode的方式有多少中
    public static int process(char[] str, int index) {
        //if (index > str.length) return 0;
        if (index==str.length) { //表示[0...str.length-1]已经搞定了,即整个字符串已经decode好了，此时是一种正确的方法
            return 1;
        }
        //走到这里说明 index及其之后还有字符
        //枚举当前位置的数字
        //1、当前位置是0字符,index位置直接面对0这个字符，无法转换，此时是一种无效的方法
        if (str[index]=='0') {
            return 0;
        }
        //走到这里说明：index及其之后还有字符 并且 str[index]!='0'
        //当前位置是1: 两种情况
        if (str[index]=='1') {
            int ans= process(str, index+1); //index位置的1单独转，然后index+1位置去做决定
            if (index+1<str.length) {
                ans += process(str, index + 2); //index的1与index+1位置的字符合在一起转(10~19之间可以直接转)，然后index+2位置去做决定
            }
            return ans;
        }
        //当前位置是2：两种情况
        if (str[index]=='2') {
            //index位置的2单独转，然后index+1位置去做决定
            int p1 = process(str, index+1);
            //index的2与index+1位置的字符合在一起转(前提是20~26之间才可以直接转)，然后index+2位置去做决定
            int p2 = -1;
            if (index+1<str.length && (str[index+1]>='0' && str[index+1]<='6')) {
                p2 = process(str, index+2);
            }
            return (p2==-1) ? p1 : (p1+p2);
        }

        //当前位置是[3,9]区间
        return process(str, index+1);
    }

    //暴力递归改动态规划
    //dp[i]的含义：从i位置出发到str结尾，转化的方法数是多少？
    public static int dpWay(char[] str) {
        int N = str.length;
        int[] dp = new int[N+1];
        dp[N] = 1;
        //普遍位置：从右往左天
        for (int i = N-1; i >= 0; i--) {
            //当前位置是0
            if (str[i]=='0') {
                dp[i] = 0;
            }
            //当前位置是1
            if (str[i]=='1') {
                dp[i] = dp[i+1];
                if (i+1<N) {
                    dp[i] += dp[i+2];
                }
            }
            //当前位置是2
            if (str[i]=='2') {
                int p1 = dp[i+1];
                int p2 = -1;
                if (i+1<N && (str[i+1]>='0' && str[i+1]<='6')) {
                    p2 = dp[i+2];
                }
                dp[i] = (p2==-1) ? p1 : (p1+p2);
            }
            //当前位置是[3,9]区间
            if (str[i]>'2') {
                dp[i] = dp[i+1];
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        String s = "221";
        char[] str = s.toCharArray();
        System.out.println("process(str,0) = " + process(str,0));
        System.out.println("dpWay(str) = " + dpWay(str));
    }
}
