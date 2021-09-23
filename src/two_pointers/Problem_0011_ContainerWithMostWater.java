package two_pointers;

/**
 * 11. 盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 */
public class Problem_0011_ContainerWithMostWater {

    public static int maxArea(int[] arr) {
        if (arr==null || arr.length<2) return 0;
        int n = arr.length;
        int l = 0;
        int r = n-1;
        int ans = 0;
        while (l < r) {
            if (arr[l] < arr[r]) {
                ans = Math.max(ans, arr[l]*(r-l));
                l++;
            } else {
                ans = Math.max(ans, arr[r]*(r-l));
                r--;
            }
        }
        return ans;
    }
}
