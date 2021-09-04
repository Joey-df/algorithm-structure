package tree;

/**
 * 687. 最长同值路径
 * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。
 * 这条路径可以经过也可以不经过根节点。
 * 注意：两个节点之间的路径长度由它们之间的边数表示。
 * 示例 1:
 * 输入:
 *
 *      5
 *     / \
 *    4   5
 *   / \   \
 *  1   1   5
 * 输出:
 * 2
 *
 * 示例 2:
 * 输入:
 *      1
 *     / \
 *    4   5
 *   / \   \
 *  4   4   5
 * 输出:
 * 2
 * 注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。
 */
public class Problem_0687_LongestUnivaluePath {

    public int longestUnivaluePath(TreeNode root) {
        if (root==null) return 0;
        return fun(root).allMax - 1;
    }

    public static class Info {
        int mustFromRoot; //必须从根节点出发往下扎的同值路径的节点数
        int allMax; //不要求必须从根节点出发的，整棵树上的同值路径的节点数

        public Info(int mustFromRoot, int allMax) {
            this.mustFromRoot = mustFromRoot;
            this.allMax = allMax;
        }
    }

    public static Info fun(TreeNode x) {
        if (x==null) {
            return new Info(0,0);
        }
        Info l = fun(x.left);
        Info r = fun(x.right);
        int mustFromRoot = 1;//只有x自己
        if (x.left!=null && x.val == x.left.val) { //只往左扎
            mustFromRoot = Math.max(mustFromRoot, l.mustFromRoot+1);
        }
        if (x.right!=null && x.val == x.right.val) { //只往右扎
            mustFromRoot = Math.max(mustFromRoot, r.mustFromRoot+1);
        }
        int allMax = Math.max(l.allMax, r.allMax);
        allMax = Math.max(allMax, mustFromRoot); //五种情况求最大
        //左根右全连起来
        if (x.left!=null && x.val==x.left.val && x.right!=null && x.right.val==x.val) {
            allMax = Math.max(allMax, l.mustFromRoot+r.mustFromRoot+1);
        }
        return new Info(mustFromRoot, allMax);
    }

}
