package dynamic_programming.left_to_right;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 403. 青蛙过河
 * 一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。
 * 青蛙可以跳上石子，但是不可以跳入水中。
 * 给你石子的位置列表 stones（用单元格序号 升序 表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。
 * 开始时， 青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格 1 跳至单元格 2 ）。
 * 如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1 个单位。
 * 另请注意，青蛙只能向前方（终点的方向）跳跃。
 *
 * 示例 1：
 * 输入：stones = [0,1,3,5,6,8,12,17]
 * 输出：true
 * 解释：青蛙可以成功过河，按照如下方案跳跃：跳 1 个单位到第 2 块石子, 然后跳 2 个单位到第 3 块石子,
 * 接着 跳 2 个单位到第 4 块石子, 然后跳 3 个单位到第 6 块石子, 跳 4 个单位到第 7 块石子,
 * 最后，跳 5 个单位到第 8 个石子（即最后一块石子）。
 *
 * 示例 2：
 * 输入：stones = [0,1,2,3,4,8,9,11]
 * 输出：false
 * 解释：这是因为第 5 和第 6 个石子之间的间距太大，没有可选的方案供青蛙跳跃过去。
 *
 * 提示：
 * 2 <= stones.length <= 2000
 * 0 <= stones[i] <= 2^31 - 1
 * stones[0] == 0
 */
public class Problem_0403_FrogJump {

    public static boolean canCross(int[] stones) {
        Set<Integer> set = new HashSet<>();
        for (int stone: stones) set.add(stone);
        int n = stones.length;
        return fun(1,1,stones[n-1], set);
    }

    //当前来到cur位置，来到cur之前跳了pre步
    //end为终止位置
    //返回能否跳到end
    //set：装的是每一个石子的位置（即数组中每个元素的值）
    public static boolean fun(int cur, int pre, int end, Set<Integer> set) {
        if (cur==end) return true;
        //cur不在set中表示跳落水中了，当前cur不在石头上
        if (!set.contains(cur)) return false;
        //还没到end
        return (pre>1 && fun(cur+pre-1, pre-1, end, set)) //跳k-1步
                || fun(cur+pre, pre, end, set)
                || fun(cur+pre+1, pre+1, end, set);
    }


    ////////////////////////////////////////
    //两个可变参数，改记忆化搜索
    public static boolean canCross2(int[] stones) {
        Set<Integer> set = new HashSet<>();
        for (int stone: stones) set.add(stone);
        Map<Integer, Map<Integer,Boolean>> dp = new HashMap<>();
        int n = stones.length;
        return fun2(1,1,stones[n-1], set, dp);
    }

    //当前来到cur位置，来到cur之前跳了pre步
    //end为终止位置
    //返回能否跳到end
    //set：装的是每一个石子的位置（即数组中每个元素的值）
    //Map<Integer, Map<Integer,Boolean>> dp: <cur <pre, result>>
    public static boolean fun2(int cur, int pre, int end, Set<Integer> set, Map<Integer, Map<Integer,Boolean>> dp) {
        if (cur==end) return true;
        if (!set.contains(cur)) return false;
        if (dp.containsKey(cur) && dp.get(cur).containsKey(pre)) {
            return dp.get(cur).get(pre);
        }
        //还没到end
        boolean ans = (pre>1 && fun2(cur+pre-1, pre-1, end, set, dp))
                || fun2(cur+pre, pre, end, set, dp)
                || fun2(cur+pre+1, pre+1, end, set, dp);
        if (!dp.containsKey(cur)) {
            dp.put(cur, new HashMap<>());
        }
        dp.get(cur).put(pre, ans);
        return ans;
    }

    public static void main(String[] args) {
        int[] stones = {0,1,3,5,6,8,12,17};
        System.out.println(canCross(stones));
        System.out.println(canCross2(stones));
    }

}
