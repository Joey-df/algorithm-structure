package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 56. 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 *
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 示例 2：
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * 提示：
 * 1 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^4
 */
public class Problem_0056_MergeIntervals {

    public int[][] merge(int[][] arr) {
        if (arr==null || arr.length==0) return new int[0][2];
        int n = arr.length;
        Arrays.sort(arr, (a,b)->a[0]-b[0]);
        List<int[]> res = new ArrayList<>();
        res.add(arr[0]);
        //[[1,3],[2,6],[8,10],[15,18]]
        for (int i=1;i<n;i++) {
            int[] last = res.get(res.size()-1);
            int end = last[1];
            int[] cur = arr[i];
            if (cur[0]>end) {
                res.add(cur);
            } else { //cur[0]<=end
                last[1] = Math.max(cur[1], end);
            }
        }
        int[][] ans = new int[res.size()][2];
        for (int i=0;i<ans.length;i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

}
