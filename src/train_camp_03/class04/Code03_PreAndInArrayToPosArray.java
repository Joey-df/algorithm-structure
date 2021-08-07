package train_camp_03.class04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 已知一棵二叉树中没有重复节点，并且给定了这棵树的中序遍历数组和先序遍历 数组，返回后序遍历数组。
 * 比如给定:
 * int[] pre = { 1, 2, 4, 5, 3, 6, 7 };
 * int[] in = { 4, 2, 5, 1, 6, 3, 7 }; 返回:
 * {4,5,2,6,7,3,1}
 */
//根据先序数组和中序数组生成后序数组
//https://www.nowcoder.com/questionTerminal/5ae5174f17674e458028ce12bc8bfe0b
public class Code03_PreAndInArrayToPosArray {

    public static List<Integer> getPostOrderArr(int[] preOrder, int[] inOrder) {
        List<Integer> ans = new ArrayList<>();
        if (preOrder == null || inOrder == null) {
            return ans;
        }
        if (preOrder.length != inOrder.length) {
            return ans;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            map.put(inOrder[i], i);
        }
        int N = preOrder.length;
        int[] post = new int[N];
        process(preOrder, 0, N - 1, inOrder, 0, N - 1, post, 0, N - 1, map);
        for (int i = 0; i < N; i++) {
            ans.add(i, post[i]);
        }
        return ans;
    }

    public static void process(int[] pre, int l1, int r1, int[] in, int l2, int r2, int[] post, int l3, int r3, Map<Integer, Integer> map) {
        if (l1 > r1) {
            return;
        }
        if (l1 == r1) {
            post[r3] = pre[l1];
            return;
        }
        //l1<r1
        post[r3] = pre[l1];
        int index = map.get(pre[l1]); //pre[l1]在in中的位置
        int leftSize = index - l2;
        process(pre, l1 + 1, l1 + leftSize, in, l2, index - 1, post, l3, l3 + leftSize - 1, map);
        process(pre, l1 + leftSize + 1, r1, in, index + 1, r2, post, l3 + leftSize, r3 - 1, map);
    }

    public static void main(String[] args) {
        int[] pre = new int[]{1, 2, 4, 5, 3, 6, 7};
        int[] in = new int[]{4, 2, 5, 1, 6, 3, 7};
        System.out.println(getPostOrderArr(pre, in));
    }
}
