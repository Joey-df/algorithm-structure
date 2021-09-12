package dynamic_programming.left_to_right;

import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * 313. 超级丑数
 * 超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。
 * 给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。
 * 题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。
 * <p>
 * 示例 1：
 * 输入：n = 12, primes = [2,7,13,19]
 * 输出：32
 * 解释：给定长度为 4 的质数数组 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
 * <p>
 * 示例 2：
 * 输入：n = 1, primes = [2,3,5]
 * 输出：1
 * 解释：1 不含质因数，因此它的所有质因数都在质数数组 primes = [2,3,5] 中。
 * <p>
 * 提示：
 * 1 <= n <= 10^6
 * 1 <= primes.length <= 100
 * 2 <= primes[i] <= 1000
 * 题目数据 保证 primes[i] 是一个质数
 * primes 中的所有值都 互不相同 ，且按 递增顺序 排列
 */
public class Problem_0313_SuperUglyNumber {

    //TimeLimitExceededException
    public static int nthSuperUglyNumber(int n, int[] primes) {
        TreeSet<Long> set = new TreeSet<>();
        set.add(1L);
        for (int i = 0; i < n - 1; i++) {
            long cur = set.pollFirst();
            for (int num : primes) {
                set.add(num * cur);
            }
        }
        return set.first().intValue();
    }

    public static class Node {
        int val;
        int prime;
        int index;

        public Node(int val, int prime, int index) {
            this.val = val;
            this.prime = prime;
            this.index = index;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", prime=" + prime +
                    ", index=" + index +
                    '}';
        }
    }

    public static int nthSuperUglyNumber2(int n, int[] primes) {
        PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> (a.val - b.val));
        for (int i = 0; i < primes.length; i++) {
            queue.offer(new Node(primes[i], primes[i], i));
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;
        int i = 1;
        while (i < n) {
            Node node = queue.poll();
            int curVal = node.val;
            int prime = node.prime;
            int index = node.index;
            System.out.println(node);
            if (curVal != dp[i - 1]) {
                dp[i] = curVal;
                i++;
            }
            queue.offer(new Node(prime * dp[index + 1], prime, index + 1));
        }
        return dp[n - 1];
    }


    public static void main(String[] args) {
        int n = 12;
        int[] arr = {2, 7, 13, 19};
        System.out.println(nthSuperUglyNumber2(n, arr));
    }
}
