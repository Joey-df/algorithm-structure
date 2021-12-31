package greedy;

import java.util.Arrays;

//452. 用最少数量的箭引爆气球
//https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons/
//
//BTW here are the questions that can be solved with the same technique
//
//56 Merge Intervals <- very similar
//435 Non-overlapping Intervals <- very similar
//252 Meeting Rooms
//253 Meeting Rooms II
//
//Practice them in a row for better understanding
public class Problem_0452_MinimumNumberOfArrowsToBurstBalloons {


    // 最多有n个不重叠的区间，则至少需要n个箭头穿透所有区间。
    //    不同：不重叠区间那个问题中，两区间边界触碰，不算重叠；而本题中，边界触碰也算重叠。
    //    参照435
    public int findMinArrowShots(int[][] arrs) {
        if (arrs==null || arrs.length==0) return 0;
        //等价于 (a,b) -> (a[1]-b[1])
        //Integer.compare(a[1], b[1])
        //主要是防止[[-2147483646,-2147483645],[2147483646,2147483647]]此种用例相减后越界
        Arrays.sort(arrs, (a,b) -> Integer.compare(a[1], b[1]));
        int count=1;
        int prePos = arrs[0][1];
        int n = arrs.length;
        //[[10,16],[2,8],[1,6],[7,12]]
        //[[1,6],[2,8],[7,12],[10,16]]
        for (int i = 1; i < n; i++) {
            int[] cur = arrs[i];
            int curStart = cur[0];
            if (curStart > prePos) {
                count++;
                prePos = cur[1];
            }
        }
        return count;
    }

}
