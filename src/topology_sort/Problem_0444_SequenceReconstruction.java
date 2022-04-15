package topology_sort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 剑指 Offer II 115. 重建序列
 * 请判断原始的序列 org 是否可以从序列集 seqs 中唯一地 重建 。
 * 序列 org 是 1 到 n 整数的排列，其中 1 ≤ n ≤ 104。
 * 重建 是指在序列集 seqs 中构建最短的公共超序列，即  seqs 中的任意序列都是该最短序列的子序列。
 * <p>
 * 示例 1：
 * 输入: org = [1,2,3], seqs = [[1,2],[1,3]]
 * 输出: false
 * 解释：[1,2,3] 不是可以被重建的唯一的序列，因为 [1,3,2] 也是一个合法的序列。
 * <p>
 * 示例 2：
 * 输入: org = [1,2,3], seqs = [[1,2]]
 * 输出: false
 * 解释：可以重建的序列只有 [1,2]。
 * <p>
 * 示例 3：
 * 输入: org = [1,2,3], seqs = [[1,2],[1,3],[2,3]]
 * 输出: true
 * 解释：序列 [1,2], [1,3] 和 [2,3] 可以被唯一地重建为原始的序列 [1,2,3]。
 * <p>
 * 示例 4：
 * 输入: org = [4,1,5,2,6,3], seqs = [[5,2,6,3],[4,1,5,2]]
 * 输出: true
 * 提示：
 * 1 <= n <= 10^4
 * org 是数字 1 到 n 的一个排列
 * 1 <= segs[i].length <= 10^5
 * seqs[i][j] 是 32 位有符号整数
 * 注意：本题与主站 444 题相同：https://leetcode-cn.com/problems/sequence-reconstruction/
 */
// 测试链接
// https://leetcode-cn.com/problems/ur2n8P/
public class Problem_0444_SequenceReconstruction {

    public static boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        if (seqs == null || seqs.size() == 0) return false;

        //set查看序列集合中的元素是否完整(满足1～n)
        Set<Integer> set = new HashSet<>();
        int n = org.length;
        for(List<Integer> seq : seqs){
            for(int num : seq) set.add(num);
        }
        //原始序列只有一个元素且不在序列集中
        if(n == 1 && !set.contains(n)) return false;
        //序列集元素不完整
        if(set.size() != n) return false;

        // 使用邻接表构建图
        ArrayList<ArrayList<Integer>> nexts = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            nexts.add(i, new ArrayList<>()); // 0位置弃而不用
        }
        int[] indegree = new int[n + 1];// 0位置弃而不用
        for (List<Integer> cur : seqs) {
            for (int i = 0; i < cur.size() - 1; i++) {
                int from = cur.get(i);
                int to = cur.get(i + 1);
                nexts.get(from).add(to);
                indegree[to]++;
            }
        }

        int l = 0;
        int r = 0;
        int[] zeroQueue = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                zeroQueue[r++] = i;
            }
        }
        if (l == r || r - l > 1) return false; // 没有入度为0的点，或者 入度为0的节点超过一个，直接false
        List<Integer> help = new ArrayList<>();
        while (l < r) {
            if (r - l > 1) return false; // 每一步，如果入度为0的节点超过一个，直接false
            int cur = zeroQueue[l++]; // 出队列
            help.add(cur); // 按拓扑排序的顺序收集
            ArrayList<Integer> sub = nexts.get(cur);
            for (int i = 0; i < sub.size(); i++) {
                if (--indegree[sub.get(i)] == 0) {
                    zeroQueue[r++] = sub.get(i);
                }
            }
        }
        if (help.size() != n) return false; //如果拓扑排序的结果链表长度不等于org的长度，说明图有环，直接false
        for (int i = 0; i < n; i++) {
            if (org[i] != help.get(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] org = new int[]{1};
        List<List<Integer>> seqs = new ArrayList<>();
        ArrayList<Integer> sub = new ArrayList<>();
        sub.add(2);
        seqs.add(sub);
        boolean b = sequenceReconstruction(org, seqs);
        System.out.println(b);
    }

}
