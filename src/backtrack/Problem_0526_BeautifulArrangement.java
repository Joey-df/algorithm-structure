package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 526. 优美的排列
 * 假设有从 1 到 n 的 n 个整数。用这些整数构造一个数组 perm（下标从 1 开始），
 * 只要满足下述条件 之一 ，该数组就是一个 优美的排列 ：
 * 
 * perm[i] 能够被 i 整除
 * i 能够被 perm[i] 整除
 * 给你一个整数 n ，返回可以构造的 优美排列 的 数量 。
 * 
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 * 解释：
 * 第 1 个优美的排列是 [1,2]：
 * - perm[1] = 1 能被 i = 1 整除
 * - perm[2] = 2 能被 i = 2 整除
 * 第 2 个优美的排列是 [2,1]:
 * - perm[1] = 2 能被 i = 1 整除
 * - i = 2 能被 perm[2] = 1 整除
 * 
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * 
 * 提示：
 * 1 <= n <= 15
 */
//带条件的全排列
public class Problem_0526_BeautifulArrangement {

    public static int countArrangement(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        List<List<Integer>> ans = new ArrayList<>();
        fun(arr, n, 0, ans);
        print(ans);
        return ans.size();
    }

    //arr：固定参数
    //n：固定参数，arr的长度
    //index：当前来到index位置做决定，
    //arr[0...index-1]已经做好决定了，沿途所做决定形成的路径就是arr当前的状态
    //ans：收集答案
    public static void fun(int[] arr, int n, int index, List<List<Integer>> ans) {
        if (index == n) { //到达越界位置，说明arr[0...n-1]已经做好决定了，此时收集答案
            ArrayList<Integer> sub = new ArrayList<>();
            for (int num : arr) {
                sub.add(num);
            }
            ans.add(sub);
        } else {
            //index ~ n-1 任意一个位置的数都可以来到index位置
            for (int i = index; i < arr.length; i++) {
                swap(arr, index, i);
                //根据题意应该取 index+1
                if (arr[index] % (index + 1) == 0 || (index + 1) % arr[index] == 0) {
                    fun(arr, n, index + 1, ans);
                }
                swap(arr, index, i); //恢复现场
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }


    public static void print(List<List<Integer>> ans) {
        for (int i = 0; i < ans.size(); i++) {
            for (int j = 0; j < ans.get(i).size(); j++) {
                System.out.print(ans.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        countArrangement(5);
    }

}
