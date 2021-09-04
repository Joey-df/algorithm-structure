package tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//559. N 叉树的最大深度
public class Problem_0559_MaximumDepthOfNaryTree {
    // Definition for a Node.
    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
    //dfs: depth随着递归序不断变化，每第一次到一个新节点就+1，最后一次到节点就-1，叶子节点时收集答案
    private static int max=0;
    private static int depth=0;
    public int maxDepth1(Node root) {
        if (root==null) return 0;
        dfs(root);
        return max;
    }

    public void dfs(Node root) {
        if (root==null) return;
        depth++; //第一次到达节点时depth++
        if (root.children.size()==0) { //到达叶子节点时收集答案
            max = Math.max(max, depth);
        }
        for (Node node: root.children) {
            maxDepth1(node);
        }
        depth--; //最后一次到达节点时depth--
    }


    //利用bfs
    public int maxDepth2(Node root) {
        if (root==null) return 0;
        int ans = 0;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                for (Node n: node.children) {
                    q.offer(n);
                }
            }
            ans++;
        }
        return ans;
    }
}
