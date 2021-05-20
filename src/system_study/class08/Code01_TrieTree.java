package system_study.class08;

/**
 * 前缀树（prefix tree  / trie）
 *
 * 1）单个字符串中，字符从前到后的加到一棵多叉树上
 * 2）字符放在路上，节点上有专属的数据项（常见的是pass和end值）
 * 3）所有样本都这样添加，如果没有路就新建，如有路就复用
 * 4）沿途节点的pass值增加1，每个字符串结束时来到的节点end值增加1
 *
 * 可以完成前缀相关的查询
 */
public class Code01_TrieTree {
}
