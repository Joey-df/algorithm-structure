package greedy;

import java.util.Arrays;

/**
 * 435. 至少移除几个区间让剩下的区间互不重叠
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 *
 * 注意:
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 *
 * 示例 1:
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 *
 * 示例 2:
 * 输入: [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 *
 * 示例 3:
 * 输入: [ [1,2], [2,3] ]
 * 输出: 0
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 */
public class Problem_0435_NonOverlappingIntervals {

    //1、找出互不重叠区间数量count
    //2、返回n-count
    public int eraseOverlapIntervals(int[][] arrs) {
        if (arrs==null || arrs.length<2) return 0;
        //Arrays.sort(arrs, (a,b) -> a[1]-b[1]); //按照区间的结尾升序排序
        Arrays.sort(arrs, (a,b) -> Integer.compare(a[1], b[1])); //按照区间的结尾升序排序
        int n = arrs.length;
        int count=1;
        int preEnd=arrs[0][1];
        for (int i = 1; i < n; i++) {
            int[] cur = arrs[i];
            //当前区间的开头 >= 前一个区间的结尾：相当于开启一个不重叠的区间
            if (cur[0] >= preEnd) {
                count++;
                preEnd=cur[1];
            }
        }
        return n-count;
    }

}
