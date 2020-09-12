package dynamic_programming.range;


/**
 * 第24节、经典面试题(八)
 * 训练营3期PPT(第6节) 题目二
 *
 * 给定一个只由 0(假)、1(真)、&(逻辑与)、|(逻辑或)和^(异或)五种字符组成 的字符串express，再给定一个布尔值 desired。
 * 返回express能有多少种组合 方式，可以达到desired的结果。
 * 【举例】
 * express="1^0|0|1"，desired=false
 * 只有 1^((0|0)|1)和 1^(0|(0|1))的组合可以得到 false，返回 2。 express="1"，desired=false
 * 无组合则可以得到false，返回0
 *
 * https://github.com/algorithmzuo/trainingcamp003/blob/master/src/class06/Code02_ExpressionNumber.java
 *
 * 范围上的尝试模型
 * 从暴力递归到动态规划
 */
public class ExpressionNumber {



}
