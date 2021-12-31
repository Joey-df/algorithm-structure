package binary_search;

/**
 * 668. 乘法表中第k小的数
 * 几乎每一个人都用 乘法表mat。但是你能在乘法表中快速找到第k小的数字吗？
 * mat[i][j] == i * j (1-indexed).
 * 给定高度m 、宽度n 的一张 m * n的乘法表，以及正整数k，你需要返回表中第k 小的数字。
 */
public class Problem_0668_KthSmallestNumberInMultiplicationTable {

    //方法1
    public int findKthNumber(int m, int n, int k) {
        int low = 1, high = m * n + 1;

        while (low < high) {
            int mid = low + (high - low) / 2;
            int c = count(mid, m, n); // check how many numbers are smaller than mid
            if (c >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return high;
    }

    // check how many numbers are smaller than v
    private int count(int v, int m, int n) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            int temp = Math.min(v / i, n);
            count += temp;
        }
        return count;
    }


    //方法2
    public int findKthNumber2(int m, int n, int k) {
        int l = 0, r = m * n;
        while (l < r) {
            int mid = (l + r) / 2;
            int count = 0;
            // check how many numbers are smaller than mid
            for (int i = 1; i <= m; i++)
                count += Math.min(n, mid / i);
            if (count >= k)  // target <= mid
                r = mid;
            else  // target > mid
                l = mid + 1;
        }
        return l;
    }
}
