package coding_for_great_offer.class05;

import tree.TreeNode;

import java.util.Arrays;
import java.util.Stack;

/**
 * // 本题测试链接 :
 * leetcode 1008题
 * https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
 * 已知一棵搜索二叉树上没有重复值的节点，
 * 现在有一个数组arr，是这棵搜索二叉树先序遍历的结果
 * 请根据arr生成整棵树并返回头节点
 * <p>
 * 最优解：
 * 使用单调栈优化
 */
public class Code01_ConstructBinarySearchTreeFromPreorderTraversal {

    //思路：
    //根据搜索二叉树的性质，每个节点左边的节点值都比自己小，右边的节点值都比自己大
    //给定arr是先序遍历结果，arr[0]是根节点，右边<arr[0]的范围去建左树，>arr[0]的范围去建右树
    //[8,5,1,7,10,12]
    public TreeNode bstFromPreorder(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        return build(arr, 0, arr.length - 1);
    }

    public static TreeNode build(int[] arr, int l, int r) {
        if (l > r) { //[8,10,12]无左树；[8,5,1,7]无右树两种情况都会出现
            return null;
        }
        TreeNode root = new TreeNode(arr[l]);
        int firstBig = l + 1;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i] < arr[l]) {
                firstBig++;
            }
        }
        root.left = build(arr, l + 1, firstBig - 1);
        root.right = build(arr, firstBig, r);
        return root;
    }


    //利用单调栈求出arr中每一个元素右边离自己最近比自己大的元素位置
    public static int[] help(int[] arr) {
        int[] ans = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                int index = stack.pop();
                ans[index] = i;
            }
            stack.push(i);

        }
        while (!stack.isEmpty()) {
            ans[stack.pop()] = -1; //-1表示右边没有比自己大的
        }
        return ans;
    }

    public TreeNode bstFromPreorder2(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        int[] help = help(arr);
        return build2(arr, 0, arr.length - 1, help);
    }

    public static TreeNode build2(int[] arr, int l, int r, int[] help) {
        if (l > r) {
            return null;
        }
        TreeNode root = new TreeNode(arr[l]);
        int firstBig = help[l] == -1 ? r + 1 : help[l];
        root.left = build2(arr, l + 1, firstBig - 1, help);
        root.right = build2(arr, firstBig, r, help);
        return root;
    }

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printArr(help(new int[]{8, 5, 1, 7, 10, 12}));
    }
}
