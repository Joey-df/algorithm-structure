package coding_for_great_offer.class03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Leetcode514 自由之路
// 本题测试链接 : https://leetcode.com/problems/freedom-trail/
public class Code07_FreedomTrail {
    //拨号函数
    //电话字符长度为size，首位相接的圆形
    //返回从i拨到j最小的距离
    //两种可能：正着拨、反着拨
    public static int dial(int i, int j, int size) {
        int p1 = Math.abs(i - j);
        int p2 = size - p1;
        return Math.min(p1, p2);
    }

    //根据给定的电话字符串生成map
    //map key:电话中的字符 value：每个字符出现的位置组成的list
    public static Map<Character, List<Integer>> help(String phone) {
        Map<Character, List<Integer>> map = new HashMap<>();
        char[] str = phone.toCharArray();
        int N = str.length;
        for (int i = 0; i < N; i++) {
            if (!map.containsKey(str[i])) {
                map.put(str[i], new ArrayList<>());
            }
            map.get(str[i]).add(i);
        }
        return map;
    }

    //递归函数
    //preButton：前一个指向的位置在哪，初始为0
    //curIndex: 当前来到的位置,即当前需要搞定的字符
    //aim 还要搞定的目标
    //N：电话字符串长度
    //map:没给字符出现在哪些位置
    //返回：指针上一步指在preButton，当前要搞定curIndex位置，组成aim的最小代价返回
    public static int process(int preButton, int curIndex, char[] aim, int N, Map<Character, List<Integer>> map, int[][] dp) {
        if (dp[preButton][curIndex] != 0) { //0表示没算过
            return dp[preButton][curIndex];
        }
        int ans = Integer.MAX_VALUE;
        if (curIndex == aim.length) {
            ans = 0;
            dp[preButton][curIndex] = ans;
            return ans;
        }
        //还有字符需要搞定呢
        char cur = aim[curIndex];
        List<Integer> positions = map.get(cur);
        for (int i = 0; i < positions.size(); i++) {
            int next = positions.get(i);
            int cost = dial(preButton, next, N) + 1 + process(next, curIndex + 1, aim, N, map, dp);
            ans = Math.min(ans, cost);
            dp[preButton][curIndex] = ans;
        }
        return ans;
    }

    public static int findRotateSteps(String ring, String key) {
        if (ring == null || ring.length() == 0 || key == null || key.length() == 0) {
            return 0;
        }
        Map<Character, List<Integer>> map = help(ring);
        char[] str = key.toCharArray();
        int[][] dp = new int[ring.length() + 1][key.length() + 1];
        return process(0, 0, str, ring.length(), map, dp);
    }

}
