package system_study.class14;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 并查集
 *
 * 1） 有若干个样本a、b、c、d…类型假设是V
 * 2） 在并查集中一开始认为每个样本都在单独的集合里
 * 3） 用户可以在任何时候调用如下两个方法：
 *        boolean isSameSet(V x, V y) : 查询样本x和样本y是否属于一个集合
 *        void union(V x, V y) : 把x和y各自所在集合的所有样本合并成一个集合
 * 4） isSameSet和union方法的代价越低越好
 *
 *
 * 1）每个节点都有一条往上指的指针
 * 2）节点a往上找到的头节点，叫做a所在集合的代表节点
 * 3）查询x和y是否属于同一个集合，就是看看找到的代表节点是不是一个
 * 4）把x和y各自所在集合的所有点合并成一个集合，只需要小集合的代表点挂在大集合的代表点的下方即可
 *
 * 并查集的优化
 * 1）节点往上找代表点的过程，把沿途的链变成扁平的
 * 2）小集合挂在大集合的下面
 * 3）如果方法调用很频繁，那么单次调用的代价为O(1)，两个方法都如此
 */
public class Code05_UnionFind {

    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }

    public static class UnionFind<V> {
        public HashMap<V, Node<V>> nodes;
        // 存放每个节点与父亲节点的对应关系
        public HashMap<Node<V>, Node<V>> parents;
        // sizeMap中，只有代表节点，才有记录，key为代表节点
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionFind(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V cur : values) {
                Node<V> node = new Node<>(cur);
                nodes.put(cur, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        // 给你一个节点，请你往上到不能再往上，把代表返回
        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> path = new Stack<>();
            while (cur != parents.get(cur)) {
                path.push(cur);
                cur = parents.get(cur);
            }
            // 此时cur为代表节点
            while (!path.isEmpty()) { // 扁平化操作
                parents.put(path.pop(), cur);
            }
            return cur;
        }

        public boolean isSameSet(V a, V b) {
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        public void union(V a, V b) {
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                parents.put(small, big); // 小挂大
                sizeMap.put(big, aSetSize + bSetSize);
                sizeMap.remove(small);
            }
        }

        public int sets() {
            return sizeMap.size();
        }

    }
}
