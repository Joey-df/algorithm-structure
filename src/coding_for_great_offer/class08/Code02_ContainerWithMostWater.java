package coding_for_great_offer.class08;

//Leetcode11原题：
//https://leetcode.com/problems/container-with-most-water/
public class Code02_ContainerWithMostWater {

    //舍弃答案法
    //双指针，只寻找推高答案的可能性
    public static int mostWater(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int l = 0;
        int r = arr.length - 1;
        int ans = 0;
        while (l < r) {
            if (arr[l] < arr[r]) {
                ans = Math.max(ans, arr[l] * (r - l));
                l++;
            } else {
                ans = Math.max(ans, arr[r] * (r - l));
                r--;
            }
        }
        return ans;
    }

}
