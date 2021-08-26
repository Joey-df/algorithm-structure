package array;

/**
 * 274. H 指数
 * 给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数。计算并返回该研究者的 h 指数。
 * h 指数的定义：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （n 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。
 * 且其余的 n - h 篇论文每篇被引用次数 不超过 h 次。
 * 例如：某人的 h 指数是 20，这表示他已发表的论文中，每篇被引用了至少 20 次的论文总共有 20 篇。
 * 提示：如果 h 有多种可能的值，h 指数 是其中最大的那个。
 * <p>
 * 示例 1：
 * 输入：citations = [3,0,6,1,5]
 * 输出：3
 * 解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。
 * 由于研究者有 3 篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3。
 * 示例 2：
 * 输入：citations = [1,3,1]
 * 输出：1
 * <p>
 * 提示：
 * <p>
 * n == citations.length
 * 1 <= n <= 5000
 * 0 <= citations[i] <= 1000
 */
//计数排序
public class Problem_0274_HIndex {

    //The idea is to see that the result can only range from 0 to the length of the array
    // (because we can't have h-index greater than the total papers published).
    // So we create an array "map" which acts like a HashMap (using pigeon hole principle)
    // and loop backwards from the highest element,
    // then we find "total" which is the total number of papers that has more than i nums,
    // and we stop when total>=i (total number of papers with more than i nums >= i).
    // We don't need to keep going because we are trying the biggest i possible, we we stop and return the result.
    public int hIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int[] map = new int[N + 1];
        for (int num : nums) {
            map[(num >= N) ? N : num]++;
        }
        int total = 0;
        for (int i = N; i >= 0; i--) {
            total += map[i];
            if (total >= i) {
                total = i;
                break;
            }
        }
        return total;
    }


}
