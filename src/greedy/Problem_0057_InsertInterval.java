package greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * 57. 插入区间
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 * 示例 1：
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * 示例 2：
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * 示例 3：
 * 输入：intervals = [], newInterval = [5,7]
 * 输出：[[5,7]]
 * 示例 4：
 * 输入：intervals = [[1,5]], newInterval = [2,3]
 * 输出：[[1,5]]
 * 示例 5：
 * 输入：intervals = [[1,5]], newInterval = [2,7]
 * 输出：[[1,7]]
 *
 * 提示：
 * 0 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= intervals[i][0] <= intervals[i][1] <= 10^5
 * intervals 根据 intervals[i][0] 按 升序 排列
 * newInterval.length == 2
 * 0 <= newInterval[0] <= newInterval[1] <= 10^5
 */
public class Problem_0057_InsertInterval {

    //arrs已经按照区间起始端点排好序
    //请在arrs中插入新区间newArr，返回插入后的结果（该合并的合并）
    public int[][] insert(int[][] arrs, int[] newArr) {
        if (arrs==null || arrs.length==0) {
            return new int[][]{newArr};
        }
        //intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8] 为例
        //            [[1,2],[3,10],[12,16]]
        //整体分为三段：
        //第一段：cur.end < newArr.start，直接插入，i++
        //第二段：插入新区间，合并区间，newArr.start<=cur.end && newArr.end>=cur.start
        //第三段：直接插入剩余的区间，newArr.end < cur.start
        int n = arrs.length;
        List<int[]> res = new ArrayList<>();
        int i=0;
        int newStart = newArr[0];
        int newEnd = newArr[1];
        while (i<n && arrs[i][1]<newStart) {
            res.add(arrs[i++]);
        }
        //newStart<=curEnd
        while (i<n && arrs[i][0]<=newEnd) { //合并
            newArr[0] = Math.min(newArr[0], arrs[i][0]);
            newArr[1] = Math.max(newArr[1], arrs[i][1]);
            i++;
        }
        res.add(newArr);
        while (i<n) {
            res.add(arrs[i++]);
        }
        int[][] ans = new int[res.size()][2];
        for (int j = 0; j < ans.length; j++) {
            ans[j] = res.get(j);
        }
        return ans;
    }

}
