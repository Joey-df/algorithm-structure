package coding_for_great_offer.class04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 字节原题
 * 数组为{3, 2, 2, 3, 1}，查询为(0, 3, 2)
 * 意思是在数组里下标0~3这个范围上，有几个2？答案返回2。
 * 假设给你一个数组arr，
 * 对这个数组的查询非常频繁，都给出来
 * 请返回所有查询的结果
 *
 * 算法原型：二分查找
 */
public class Code01_QueryHobby {

    public static int process(int[] arr, int L, int R, int target) {
        //生成所有种类的数字出现在哪些位置 组成的map
        Map<Integer, ArrayList<Integer>> help = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!help.containsKey(arr[i])) {
                help.put(arr[i], new ArrayList<>());
            }
            help.get(arr[i]).add(i);
        }

        if (!help.containsKey(target)) {
            return 0;
        }
        //arr中包含target
        ArrayList<Integer> list = help.get(target);
        System.out.println(list);
        int leftBound = findLeftBound(list, L);
        System.out.println(leftBound);
        int rightBound = findRightBound(list, R);
        System.out.println(rightBound);
        return rightBound < leftBound ? 0 : rightBound - leftBound + 1;
    }

    // 在有序数组arr中，找>=target最左的位置
    public static int findLeftBound(ArrayList<Integer> arr, int target) {
        int l = 0;
        int r = arr.size() - 1;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (arr.get(m) >= target) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        //如果找到了，l就是>=target最左的位置，如果没找到，l会来到arr.length+1的位置
        return l;
    }

    // 在有序数组arr中，找<=target最右的位置
    public static int findRightBound(ArrayList<Integer> arr, int target) {
        int l = 0;
        int r = arr.size() - 1;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (arr.get(m) <= target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return r;//如果没找到，r会来到-1位置
    }

    public static void main(String[] args) {
        System.out.println(process(new int[]{3, 2, 2, 3, 1}, 1, 10, 3));
    }
}
