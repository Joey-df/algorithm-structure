package binary_search;

import java.util.Arrays;

/**
 * 475. 供暖器
 * 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
 * 在加热器的加热半径范围内的每个房屋都可以获得供暖。
 * 现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。
 * 说明：所有供暖器都遵循你的半径标准，加热的半径也一样。
 * <p>
 * 示例 1:
 * 输入: houses = [1,2,3], heaters = [2]
 * 输出: 1
 * 解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
 * <p>
 * 示例 2:
 * 输入: houses = [1,2,3,4], heaters = [1,4]
 * 输出: 1
 * 解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
 * <p>
 * 示例 3：
 * 输入：houses = [1,5], heaters = [2]
 * 输出：3
 * <p>
 * 提示：
 * <p>
 * 1 <= houses.length, heaters.length <= 3 * 10^4
 * 1 <= houses[i], heaters[i] <= 10^9
 */
public class Problem_0475_Heaters {

    // houses = [1,2,3,4], heaters = [1,4]
    public static int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int ans = 0;
        for (int i = 0; i < houses.length; i++) {
            int left = findLessOrEqualsRightBound(heaters, houses[i]); //左边离得最近的邻居
            int right = findGreaterOrEqualsLeftBound(heaters, houses[i]); //右边离得最近的邻居
            //System.out.println(left + "   " + right);
            int curRad = Math.min(HEATER2R(heaters, left, houses[i]), HEATER2R(heaters, right, houses[i]));
            ans = Math.max(ans, curRad);
        }
        return ans;
    }


    public static int HEATER2R(int[] heaters, int heaterPos, int housePos) {
        if (heaterPos == -1) return Integer.MAX_VALUE;
        else return Math.abs(heaters[heaterPos] - housePos);
    }

    //在有序数组arr中找>=target最左的位置
    public static int findGreaterOrEqualsLeftBound(int[] arr, int target) {
        int n = arr.length;
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (arr[m] >= target) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return l == n ? -1 : l; //找到了就是l；没找到l等于n；
    }


    //在有序数组arr中找<=target最右的位置
    public static int findLessOrEqualsRightBound(int[] arr, int target) {
        int n = arr.length;
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (arr[m] <= target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return r; //如果找到了就是r，如果没找到r会来到-1位置
    }


    public static void main(String[] args) {
        int[] arr = {3,4,5};
        int[] heaters = {1, 2, 3, 6, 7};
        System.out.println(findRadius(arr, heaters));
    }
}
