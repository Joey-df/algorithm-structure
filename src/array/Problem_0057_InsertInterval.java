package array;

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
    //输入参数区间已经按照L有序
    //所以直接遍历即可
    //O(N)
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        if ((intervals == null || intervals.length == 0) && (newInterval == null || newInterval.length == 0)) {
            return new int[0][2]; //同时为空
        }
        if (intervals == null || intervals.length == 0) { //intervals为空，newInterval不空
            int[][] ans = new int[1][2];
            ans[0] = newInterval;
            return ans;
        }
        if (newInterval == null || newInterval.length == 0) {//newInterval为空，intervals不空
            return intervals;
        }
        //走到这；说明都不为空
        List<int[]> list = new ArrayList<>();
        //intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
        int newLeft = newInterval[0], newRight = newInterval[1]; //新区间的左右边界
        int i = 0;
        int N = intervals.length;
        //找出右边界严格小于新区间左边界的 直接add
        while (i < N && intervals[i][1] < newLeft) {
            list.add(intervals[i++]);
        }
        //走到这里说明 intervals[i][1] >= newLeft
        while (i < N && intervals[i][0] <= newRight) {
            //直接在newInterval上原地修改，newInterval为合并后的新区间
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        list.add(newInterval);

        //左边界严格大于 新区间的右边界
        while (i < N /*&& intervals[i][0] > newRight*/) {
            list.add(intervals[i++]);
        }

        int[][] ans = new int[list.size()][2];
        int index = 0;
        for (int[] sub : list) {
            ans[index++] = sub;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][] {
                new int[]{1,2},
                new int[]{3,5},
                new int[]{6,7},
                new int[]{8,10},
                new int[]{12,16}
        };
        int[] newInterval = new int[]{4,8};

        System.out.println(insert(intervals, newInterval));
    }
}
