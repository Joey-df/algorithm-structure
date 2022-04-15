package dynamic_programming.left_to_right;

import java.util.HashMap;

/**
 * 给定一个字符串Str
 * 返回Str的所有子序列中有多少不同的字面值
 */
// 不同字面值的子序列
// 本题测试链接 : https://leetcode.com/problems/distinct-subsequences-ii/
public class Problem_0940_DistinctSubsequencesII {

    public int distinctSubseqII(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] str = s.toCharArray();
        int mod = 1000000007;
        //k：每个位置的字符x
        //v：必须以x字符结尾的不同的子序列个数
        HashMap<Character, Integer> map = new HashMap<>();
        int all = 1; //包含空集
        for (char x : str) {
            //以x字符结尾的子序列 新增个数为add
            int add = (all - map.getOrDefault(x, 0) + mod) % mod;
            all = (all + add) % mod;
            map.put(x, (map.getOrDefault(x, 0) + add) % mod);
        }
        return all - 1; //结果不包含空集，所以-1
    }

}
