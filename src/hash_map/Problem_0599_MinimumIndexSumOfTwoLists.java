package hash_map;

import java.util.*;

/**
 * 599. 两个列表的最小索引总和
 * 假设Andy和Doris想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
 * 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。
 * 如果答案不止一个，则输出所有答案并且不考虑顺序。
 * 你可以假设总是存在一个答案。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 * ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
 * 输出: ["Shogun"]
 * 解释: 他们唯一共同喜爱的餐厅是“Shogun”。
 * 示例 2:
 * <p>
 * 输入:
 * ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 * ["KFC", "Shogun", "Burger King"]
 * 输出: ["Shogun"]
 * 解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
 * 提示:
 * <p>
 * 两个列表的长度范围都在 [1, 1000]内。
 * 两个列表中的字符串的长度将在[1，30]的范围内。
 * 下标从0开始，到列表的长度减1。
 * 两个列表都没有重复的元素。
 */
public class Problem_0599_MinimumIndexSumOfTwoLists {

    public static String[] findRestaurant(String[] arr1, String[] arr2) {
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        for (int i = 0; i < arr1.length; i++) {
            map1.put(arr1[i], i);
        }
        for (int i = 0; i < arr2.length; i++) {
            map2.put(arr2[i], i);
        }
        //<索引和, arr1中对应的位置>
        //使用treeMap，按索引和从小到大排序，取出firstKey就是最小的索引和
        TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>();
        for (String k : map1.keySet()) {
            if (map2.containsKey(k)) {
                int index1 = map1.get(k);
                int index2 = map2.get(k);
                int indexSum = index1 + index2;
                if (!treeMap.containsKey(indexSum)) {
                    treeMap.put(indexSum, new ArrayList<>());
                }
                treeMap.get(indexSum).add(index1);
            }
        }
        List<Integer> list = treeMap.get(treeMap.firstKey()); //存的是index
        String[] ans = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = arr1[list.get(i)];
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] arr1 = {"Shogun", "Piatti", "Tapioca Express", "Burger King", "KFC"};
        String[] arr2 = {"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"};
        findRestaurant(arr1, arr2);
    }
}
