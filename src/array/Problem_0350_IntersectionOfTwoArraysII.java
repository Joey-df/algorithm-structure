package array;

import java.util.*;

/**
 * Given two arrays, write a function to compute their intersection.
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 */
public class Problem_0350_IntersectionOfTwoArraysII {
    public static void main(String[] args) {
        int[] nums1 = {1,2,2,1};
        int nums2[] = {2,2};
        int[] ans = intersect(nums1, nums2);
        for (int i: ans) {
            System.out.println("i = " + i);
        }
    }
    public static int[] intersect(int[] nums1, int[] nums2) {
        if(nums1==null|| nums2==null || nums1.length==0||nums2.length==0) {
            return new int[]{};
        }
        //return process1(nums1, nums2);
        return process2(nums1, nums2);
    }

    //排序 + 双指针
    //时间复杂度 O(N*logN+M*logM)
    //空间复杂度 O(min(M,N))
    private static int[] process2(int[] nums1, int[] nums2) {
        //先让两个数组变有序
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int N = nums1.length;
        int M = nums2.length;
        //p1 p2分别指向两个数组的第一个位置
        int p1 = 0;
        int p2 = 0;
        ArrayList<Integer> list = new ArrayList<>();
        while(p1<N && p2<M){ //有一个越界就停止
            if (nums1[p1]==nums2[p2]) { //相等时收集答案
                list.add(nums1[p1]);
                p1++;
                p2++;
            } else if (nums1[p1] < nums2[p2]) {
                p1++;
            } else { //nums1[p1] > nums2[p2]
                p2++;
            }
        }
        int[] ans = new int[list.size()];
        int k = 0;
        for(int n: list) {
            ans[k++] = n;
        }
        return ans;
    }

    //使用 哈希表
    //时间复杂度 O(N+M) 因为是分别遍历了两个数组
    //空间复杂度 O(min(M,N))
    private static int[] process1(int[] nums1, int[] nums2) {
        //构造map用哪个数组都一样，用短的省空间
        if (nums1.length > nums2.length) {
            return process1(nums2, nums1); //这样就保证了第一个参数nums1是短数组了
        }

        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums1){
            map.put(n, map.containsKey(n) ? map.get(n)+1 : 1);
        }

        List<Integer> list = new ArrayList<>();
        for(int n : nums2){
            if(map.containsKey(n) && map.get(n) > 0) {
                list.add(n);
                map.put(n, map.get(n)-1);
            }
        }
        int[] ans = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}
