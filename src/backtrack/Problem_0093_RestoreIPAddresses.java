package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s containing only digits, return all possible valid IP addresses that can be obtained from s. You can return them in any order.
 *
 * A valid IP address consists of exactly four integers, each integer is between 0 and 255, separated by single dots and cannot have leading zeros.
 * For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses and "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
 *
 * Example 1:
 *
 * Input: s = "25525511135"
 * Output: ["255.255.11.135","255.255.111.35"]
 * Example 2:
 *
 * Input: s = "0000"
 * Output: ["0.0.0.0"]
 * Example 3:
 *
 * Input: s = "1111"
 * Output: ["1.1.1.1"]
 * Example 4:
 *
 * Input: s = "010010"
 * Output: ["0.10.0.10","0.100.1.0"]
 * Example 5:
 *
 * Input: s = "101023"
 * Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 3000
 * s consists of digits only.
 */
public class Problem_0093_RestoreIPAddresses {

    //递归含义：
    //index:当前来到的位置
    //str [index...N-1]所形成的答案 返回
    //[0...index-1]已经搞定了，不用操心了
    //path：[0...index-1]已搞定，沿途经过所形成的路径为path
    //ans: 收集答案
    public static void process(String s, int index, List<String> path, List<String> ans) {
        char[] str = s.toCharArray();
        int N = str.length;
        if (index > N || path.size() > 4) return;
        if (index == N && path.size() == 4) { //[0...str.length-1]已经搞定了
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                sb.append(path.get(i));
                if (i < path.size() - 1) {
                    sb.append(".");
                }
            }
            ans.add(sb.toString());
            return;
        }
        //index后面有字符
        //1、index位置是0
        if (index < N && str[index] == '0') {
            path.add("0");
            process(s, index + 1, path, ans);
            path.remove(path.size() - 1);
            return;
        }
        //2、index位置非0
        //2.1 取一位
        if (index + 1 <= N) {
            path.add(s.substring(index, index + 1));
            process(s, index + 1, path, ans);
            path.remove(path.size() - 1);
        }
        //2.2 取2位
        if (index + 2 <= N) {
            path.add(s.substring(index, index + 2));
            process(s, index + 2, path, ans);
            path.remove(path.size() - 1);
        }
        //2.3 取3位
        if (index + 3 <= N) {
            int sub = Integer.parseInt(s.substring(index, index + 3));
            if (sub <= 255) {
                path.add(String.valueOf(sub));
                process(s, index + 3, path, ans);
                path.remove(path.size() - 1);
            }
        }
    }

    public static List<String> ways(String s) {
        List<String> ans = new ArrayList<>();
        if ("".equals(s) || s.length() == 0 || s.length() < 4 || s.length() > 12) {
            return ans;
        }
        List<String> path = new ArrayList<>();
        process(s, 0, path, ans);
        return ans;
    }

    public static void main(String[] args) {
        String s = "25525511135";
        System.out.println(ways(s));
    }
}
