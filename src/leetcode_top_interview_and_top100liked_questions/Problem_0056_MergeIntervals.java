package leetcode_top_interview_and_top100liked_questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 * 示例 1：
 * <p>
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 * <p>
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * <p>
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 */
public class Problem_0056_MergeIntervals {

    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[][]{};
        }

        Arrays.sort(intervals, (o1,o2)->o1[0] - o2[0]);
        int N = intervals.length;
        List<int[]> list = new ArrayList<>();
        list.add(intervals[0]);
        //[[1,3],[1,2],[1,8],[2,6],[8,10],[15,18]]
        for (int i = 1; i < N; i++) {
            int[] cur = intervals[i];
            int[] pre = list.get(list.size() - 1);
            if (cur[0] >= pre[0] && cur[0] <= pre[1]) {
                pre[1] = Math.max(pre[1], cur[1]);
            } else { //cur[0]>pre[1]
                list.add(cur);
            }
        }
        int[][] ans = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

}
