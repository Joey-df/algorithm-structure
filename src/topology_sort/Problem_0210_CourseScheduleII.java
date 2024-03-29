package topology_sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 210. 课程表 II
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 *
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 *
 * 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
 *
 * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 *
 * 示例 1:
 *
 * 输入: 2, [[1,0]]
 * 输出: [0,1]
 * 解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 * 示例 2:
 *
 * 输入: 4, [[1,0],[2,0],[3,1],[3,2]]
 * 输出: [0,1,2,3] or [0,2,1,3]
 * 解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 *      因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 */
//图的拓扑排序
public class Problem_0210_CourseScheduleII {

    public static class Node {
        public int name;
        public int in;
        public ArrayList<Node> nexts;

        public Node(int n) {
            name = n;
            in = 0;
            nexts = new ArrayList<>();
        }
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] ans = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            ans[i] = i;
        }
        if (prerequisites == null || prerequisites.length == 0) {
            return ans; //如果没有任何依赖关系，直接顺序填满ans返回
        }
        HashMap<Integer, Node> nodes = new HashMap<>();
        for (int[] arr : prerequisites) {
            int to = arr[0];
            int from = arr[1];
            if (!nodes.containsKey(to)) {
                nodes.put(to, new Node(to));
            }
            if (!nodes.containsKey(from)) {
                nodes.put(from, new Node(from));
            }
            Node t = nodes.get(to);
            Node f = nodes.get(from);
            f.nexts.add(t);
            t.in++;
        }
        int index = 0;
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (!nodes.containsKey(i)) { // 这句主要是处理没有任何依赖的课程 如 10 [[1,0],[0,2]]这种case
                ans[index++] = i;
            } else {
                if (nodes.get(i).in == 0) {
                    zeroInQueue.add(nodes.get(i));
                }
            }
        }
        int needPrerequisiteNums = nodes.size();
        int count = 0;
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            ans[index++] = cur.name;
            count++;
            for (Node next : cur.nexts) {
                if (--next.in == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return count == needPrerequisiteNums ? ans : new int[0];
    }

    public static void main(String[] args) {
        int[] order = findOrder(10, new int[][]{{1, 0},{0,2}});
        print(order);
    }

    public static void print(int [] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}

