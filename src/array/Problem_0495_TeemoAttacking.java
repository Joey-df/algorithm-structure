package array;

//495. 提莫攻击
//https://leetcode-cn.com/problems/teemo-attacking/
//示例1:
//
//输入: [1,4], 2
//输出: 4
//原因: 第 1 秒初，提莫开始对艾希进行攻击并使其立即中毒。中毒状态会维持 2 秒钟，直到第 2 秒末结束。
//第 4 秒初，提莫再次攻击艾希，使得艾希获得另外 2 秒中毒时间。
//所以最终输出 4 秒。
//示例2:
//
//输入: [1,2], 2
//输出: 3
//原因: 第 1 秒初，提莫开始对艾希进行攻击并使其立即中毒。中毒状态会维持 2 秒钟，直到第 2 秒末结束。
//但是第 2 秒初，提莫再次攻击了已经处于中毒状态的艾希。
//由于中毒状态不可叠加，提莫在第 2 秒初的这次攻击会在第 3 秒末结束。
//所以最终输出 3 。
public class Problem_0495_TeemoAttacking {

    public int findPoisonedDuration(int[] arr, int duration) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return duration;
        }
        int total = 0;
        for (int i = 1; i < arr.length; i++) {
            total += Math.min(duration, arr[i] - arr[i - 1]);
        }
        return total + duration;
    }

    //方法2：
    //利用56题区间合并的思路做
    public int findPoisonedDuration2(int[] arr, int duration) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length==1) {
            return duration;
        }
        int total = 0;
        int start = arr[0];
        int end = arr[0] + duration;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > end) { // 开启新区间
                total += end-start;
                start = arr[i];
                end = arr[i]+duration;
            } else {
                end = arr[i]+duration;
            }
        }
        total += end-start;
        return total;
    }
}
