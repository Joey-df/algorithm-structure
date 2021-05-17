package coding_for_great_offer.class03;

import java.util.*;

/**
 * 新题：
 * 给定三个参数：
 * 二叉树的头节点head，树上某个节点target，正数K
 * 从target开始，可以向上走或者向下走
 * 返回与target的距离是K的所有节点
 * <p>
 * 1、图的宽度优先遍历
 * 2、生成父map的技巧
 */
public class Code08_DistanceKNodes {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }
    }

    //返回每个节点的父节点组成的map
    public static void getParentsMap(TreeNode cur, Map<TreeNode, TreeNode> map) {
        if (cur == null) {
            return;
        }
        if (cur.left != null) {
            map.put(cur.left, cur);
            getParentsMap(cur.left, map);
        }
        if (cur.right != null) {
            map.put(cur.right, cur);
            getParentsMap(cur.right, map);
        }
    }

    public static List<TreeNode> distanceKNodes(TreeNode root, TreeNode target, int K) {
        List<TreeNode> ans = new ArrayList<>();
        if (root == null || target == null || K < 0) {
            return ans;
        }
        Map<TreeNode, TreeNode> map = new HashMap<>();
        map.put(root, null);
        getParentsMap(root, map);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(target);
        Set<TreeNode> visited = new HashSet<>();
        visited.add(target);
        int cnt = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            while (sz-- > 0) {//处理一层节点
                TreeNode cur = q.poll();
                if (cnt == K) {
                    ans.add(cur); //收集答案
                }
                if (cur.left != null && !visited.contains(cur.left)) {
                    q.offer(cur.left);
                    visited.add(cur.left);
                }
                if (cur.right != null && !visited.contains(cur.right)) {
                    q.offer(cur.right);
                    visited.add(cur.right);
                }
                if (map.get(cur) != null && !visited.contains(map.get(cur))) {
                    q.offer(map.get(cur));
                    visited.add(map.get(cur));
                }

            }
            cnt++;
            if (cnt > K) break;
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode n0 = new TreeNode(0);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);

        n3.left = n5;
        n3.right = n1;
        n5.left = n6;
        n5.right = n2;
        n1.left = n0;
        n1.right = n8;
        n2.left = n7;
        n2.right = n4;

        TreeNode root = n3;
        TreeNode target = n5;
        int K = 2;

        List<TreeNode> ans = distanceKNodes(root, target, K);
        for (TreeNode o1 : ans) {
            System.out.println(o1.val);
        }

    }

}
