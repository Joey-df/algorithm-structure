package system_study.class14;

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
}
