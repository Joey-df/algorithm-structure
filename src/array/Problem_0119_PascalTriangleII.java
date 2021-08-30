package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 119. 杨辉三角 II
 * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
 * <p>
 * 示例 1:
 * 输入: rowIndex = 3
 * 输出: [1,3,3,1]
 * 示例 2:
 * 输入: rowIndex = 0
 * 输出: [1]
 * 示例 3:
 * 输入: rowIndex = 1
 * 输出: [1,1]
 */
//https://leetcode-cn.com/problems/pascals-triangle-ii
public class Problem_0119_PascalTriangleII {
    //想像成一个正方形矩阵的左下半区
    //1
    //11
    //121
    //1331
    //14341
    //第一列是1，对角线是1
    public List<Integer> getRow(int rowIndex) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            if (i >= 2) {
                for (int j = i - 1; j > 0; j--) { //必须从后往前填，dp空间压缩的思想
                    ans.set(j, ans.get(j - 1) + ans.get(j)); //左上+上
                }
            }
            ans.add(1);
        }
        return ans;
    }

}