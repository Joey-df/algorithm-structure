package math;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

/**
 * 264. 丑数 II
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 *
 * 示例 1：
 * 输入：n = 10
 * 输出：12
 * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * 解释：1 通常被视为丑数。
 *
 * 提示：
 * 1 <= n <= 1690
 */
public class Problem_0264_UglyNumberII {

    public static int nthUglyNumber1(int n) {
        PriorityQueue<Long> q = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();
        q.offer(1L);
        for (int i = 0; i < n - 1; i++) {
            long cur = q.poll();
            while (!q.isEmpty() && cur==q.peek()) {
                q.poll();
            }
            q.offer(cur * 2);
            q.offer(cur * 3);
            q.offer(cur * 5);
        }
        return q.poll().intValue();
    }

    //使用TreeSet，排序+去重
    public static int nthUglyNumber2(int n) {
        TreeSet<Long> set = new TreeSet<>();
        set.add(1L);
        for (int i = 0; i < n - 1; i++) {
            long cur = set.pollFirst();
            set.add(cur * 2);
            set.add(cur * 3);
            set.add(cur * 5);
        }
        return set.first().intValue();
    }

    public static void main(String[] args) {
        System.out.println(nthUglyNumber2(10));
    }

}
