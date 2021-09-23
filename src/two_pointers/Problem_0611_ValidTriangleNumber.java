package two_pointers;

import java.util.Arrays;

/**
 * 611. 有效三角形的个数
 * 给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
 *
 * 示例 1:
 *
 * 输入: [2,2,3,4]
 * 输出: 3
 * 解释:
 * 有效的组合是:
 * 2,3,4 (使用第一个 2)
 * 2,3,4 (使用第二个 2)
 * 2,2,3
 * 注意:
 *
 * 数组长度不超过1000。
 * 数组里整数的范围为 [0, 1000]。
 */
//https://leetcode-cn.com/problems/valid-triangle-number/solution/ming-que-tiao-jian-jin-xing-qiu-jie-by-jerring/
public class Problem_0611_ValidTriangleNumber {

    //前置知识：
    //如果三条边长度为a、b、c、则同时满足a+b>c && a+c+b && b+c>a时，a、b、c可以组成三角形
    // 如果较小的两条边之和 > 最大的边，则一定满足其他两个不等式，一定可以组成三角形！

    // 同向双指针解法
    // 首先对数组排序。
    // 固定最长的一条边，运用双指针扫描
    // 如果 nums[l] + nums[r] > nums[i]，
    // 同时说明 nums[l + 1] + nums[r] > nums[i], ...,
    //         nums[r - 1] + nums[r] > nums[i]，满足的条件的有 r - l 种，r 左移进入下一轮。
    //如果 nums[l] + nums[r] <= nums[i]，l 右移进入下一轮。
    //枚举结束后，总和就是答案。
    //时间复杂度为 O(n^2)
    public int triangleNumber(int[] nums) {
        // 0 1 2 3 4 5 6 7
        //[2,2,3,4,5,6,7,8]
        // 2           7,8  //8做最大边的三角形有6个
        if (nums==null || nums.length<3) return 0;
        Arrays.sort(nums);
        int ans = 0;
        int n = nums.length;
        for (int i = n-1; i >= 2; i--) { //枚举每个最大的边
            int curMaxSide = nums[i];
            int l = 0;
            int r = i-1;
            while (l<r) {
                if (nums[l]+nums[r] > curMaxSide) {
                    ans += r-l;
                    r--;
                } else l++; //nums[l]+nums[l] <= curMaxSide都不能构成三角形，l右移进入下一轮
            }
        }
        return ans;
    }

}
