package tree;

import java.util.LinkedList;
import java.util.Queue;

// 297. 二叉树的序列化与反序列化
// 先序遍历解法
public class Problem_0297_SerializeAndDeserializeBinaryTree {


    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<String> queue = new LinkedList<>();
        pre(root, queue);
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            sb.append(cur == null ? "null" : cur);
            sb.append(",");
        }
        String ans = sb.toString();
        return ans.substring(0, ans.length() - 1);
    }

    // queue 用来收集先序遍历的结果，空节点就收集null
    public static void pre(TreeNode root, Queue<String> queue) {
        if (root == null) {
            queue.offer(null);
        } else {
            queue.offer(String.valueOf(root.val));
            pre(root.left, queue);
            pre(root.right, queue);
        }
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        } else {
            String[] arr = data.split(",");
            Queue<String> queue = new LinkedList<>();
            for (int i = 0; i < arr.length; i++) {
                queue.offer(arr[i]);
            }
            return buildFormPre(queue);
        }
    }

    private static TreeNode buildFormPre(Queue<String> queue) {
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        return preBuild(queue);
    }

    private static TreeNode preBuild(Queue<String> queue) {
        String cur = queue.poll();
        if ("null".equals(cur)) {
            return null;
        } else {
            TreeNode head = new TreeNode(Integer.parseInt(cur));
            head.left = preBuild(queue);
            head.right = preBuild(queue);
            return head;
        }
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        serialize(root);
    }

}
