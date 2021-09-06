package dynamic_programming.left_to_right;

/**
 * 338. 比特位计数
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,1]
 *
 * 示例 2:
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 进阶:
 *
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 */
public class Problem_0338_CountingBits {

    //O(n*sizeof(integer))
    public static int[] process(int n) {
        int[] ans = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            ans[i] = help(i);
        }
        return ans;
    }

    public static int help(int n) {
        int ans = 0;
        while (n != 0) {
            int rightOne = n & (~n + 1);
            ans++;
            n ^= rightOne;
        }
        return ans;
    }

    //An easy recurrence for this problem is f[i] = f[i / 2] + i % 2.
    //Explaination.
    //Take number X for example, 10011001.
    //Divide it in 2 parts:
    //<1>the last digit ( 1 or 0, which is " i&1 ", equivalent to " i%2 " )
    //<2>the other digits ( the number of 1, which is " f[i >> 1] ", equivalent to " f[i/2] " )
    public int[] countBits(int num) {
        int[] f = new int[num + 1];
        for (int i=1; i<=num; i++) {
            f[i] = f[i >> 1] + (i & 1);
        }
        return f;
    }

    public static void main(String[] args) {
        int n = 116;
        int[] ans = process(n);
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
    }
}
