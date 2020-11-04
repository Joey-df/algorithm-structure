package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * <p>
 * You may assume that the intervals were initially sorted according to their start times.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * Example 2:
 * <p>
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 * Example 3:
 * <p>
 * Input: intervals = [], newInterval = [5,7]
 * Output: [[5,7]]
 * Example 4:
 * <p>
 * Input: intervals = [[1,5]], newInterval = [2,3]
 * Output: [[1,5]]
 * Example 5:
 * <p>
 * Input: intervals = [[1,5]], newInterval = [2,7]
 * Output: [[1,7]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= intervals[i][0] <= intervals[i][1] <= 105
 * intervals is sorted by intervals[i][0] in ascending order.
 * newInterval.length == 2
 * 0 <= newInterval[0] <= newInterval[1] <= 105
 */
public class Problem_57_Insert_Interval {
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
