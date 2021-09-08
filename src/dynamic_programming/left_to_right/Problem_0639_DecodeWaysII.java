package dynamic_programming.left_to_right;

/**
 * 639. 解码方法 II
 * 一条包含字母 A-Z 的消息通过以下的方式进行了编码：
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 除了上述的条件以外，现在加密字符串可以包含字符 '*'了，字符'*'可以被当做1到9当中的任意一个数字。
 *
 * 给定一条包含数字和字符'*'的加密信息，请确定解码方法的总数。
 *
 * 同时，由于结果值可能会相当的大，所以你应当对10^9 + 7取模。（翻译者标注：此处取模主要是为了防止溢出）
 *
 * 示例 1 :
 *
 * 输入: "*"
 * 输出: 9
 * 解释: 加密的信息可以被解密为: "A", "B", "C", "D", "E", "F", "G", "H", "I".
 * 示例 2 :
 *
 * 输入: "1*"
 * 输出: 9 + 9 = 18（翻译者标注：这里1*可以分解为1,* 或者当做1*来处理，所以结果是9+9=18）
 * 说明 :
 *
 * 输入的字符串长度范围是 [1, 105]。
 * 输入的字符串只会包含字符 '*' 和 数字'0' - '9'。
 */
public class Problem_0639_DecodeWaysII {

    public static int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] str = s.toCharArray();
        return process(str, 0);
    }

    //递归含义
    //str 固定输入参数(只包含'*'和数字字符)
    //str[0...index-1]位置已经转完了
    //当前来到index位置做决定，返回str[index....]自由转化的方法数
    public static int process(char[] str, int index) {
        if (index == str.length) {
            return 1; //str[0...str.length-1]已经搞定了，此时是一种方法
        }
        if (str[index] == '0') {
            return 0; //'0'不能单独转
        }
        //str[index]不是'0' 是*或数字
        long ways = 0;
        if (str[index] == '*') {
            //*单转
            ways += 9 * process(str, index + 1);
            //*和str[index+1]一起转
            if (index + 1 < str.length) {
                if (str[index + 1] != '*') { //str[index+1]是数字
                    ways += (str[index + 1] - '0' > 6 ? 1 : 2) * process(str, index + 2);
                } else {//str[index+1]是*  **
                    ways += 15 * process(str, index + 2);
                }
            }
        } else { //str[index]是数字
            //str[index]单转
            ways += process(str, index + 1);
            //str[index]和str[index+1]一起转
            if (index + 1 < str.length) {
                if (str[index + 1] != '*') {
                    if ((str[index] - '0') * 10 + str[index + 1] - '0' < 27) {//str[index+1]是数字
                        ways += process(str, index + 2);
                    }
                } else { //str[index + 1] == '*'
                    ways += (str[index] == '1' ? 9 : (str[index] == '2' ? 6 : 0)) * process(str, index + 2);
                }
            }
        }

        return (int) (ways % (long) 1000000007);
    }

    public static int dp(char[] str) {
        int n = str.length;
        long[] dp = new long[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (str[i] != '0') {
                long ways = 0;
                if (str[i] == '*') {
                    //*单转
                    ways += 9 * dp[i + 1];
                    //*和str[index+1]一起转
                    if (i + 1 < str.length) {
                        if (str[i + 1] != '*') { //str[index+1]是数字
                            ways += (str[i + 1] - '0' > 6 ? 1 : 2) * dp[i + 2];
                        } else {//str[index+1]是*  **
                            ways += 15 * dp[i + 2];
                        }
                    }
                } else { //str[index]是数字
                    //str[index]单转
                    ways += dp[i + 1];
                    //str[index]和str[index+1]一起转
                    if (i + 1 < str.length) {
                        if (str[i + 1] != '*') {
                            if ((str[i] - '0') * 10 + str[i + 1] - '0' < 27) {//str[index+1]是数字
                                ways += dp[i + 2];
                            }
                        } else { //str[index + 1] == '*'
                            ways += (str[i] == '1' ? 9 : (str[i] == '2' ? 6 : 0)) * dp[i + 2];
                        }
                    }
                }

                dp[i] = (int) (ways % 1000000007);
            }
        }
        return (int) dp[0];
    }

    public static void main(String[] args) {
        String s = "7*9*3*6*3*0*5*4*9*7*3*7*1*8*3*2*0*0*6*";
        System.out.println(process(s.toCharArray(), 0));
        System.out.println(dp(s.toCharArray()));
    }

}
