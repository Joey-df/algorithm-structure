package design;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Implement the RandomizedSet class:
 * <p>
 * bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
 * bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
 * int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called).
 * Each element must have the same probability of being returned.
 * Follow up: Could you implement the functions of the class with each function works in average O(1) time?
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 * [[], [1], [2], [2], [], [1], [2], []]
 * Output
 * [null, true, false, true, 2, true, false, 2]
 * <p>
 * Explanation
 * RandomizedSet randomizedSet = new RandomizedSet();
 * randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
 * randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
 * randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
 * randomizedSet.insert(2); // 2 was already in the set, so return false.
 * randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * -231 <= val <= 231 - 1
 * At most 105 calls will be made to insert, remove, and getRandom.
 * There will be at least one element in the data structure when getRandom is called.
 */
public class Problem_380_Insert_Delete_GetRandom_O1 {

    public class RandomizedSet {

        private Map<Integer, Integer> keyIndexMap; //<key, index>
        private Map<Integer, Integer> indexKeyMap; //<index, key>
        private int size;

        /**
         * Initialize your data structure here.
         */
        public RandomizedSet() {
            keyIndexMap = new HashMap<>();
            indexKeyMap = new HashMap<>();
            size = 0;
        }

        /**
         * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
         */
        public boolean insert(int val) {
            if (keyIndexMap.containsKey(val)) return false; //已经存在直接返回false
            //还不存在就添加
            keyIndexMap.put(val, size); //<3,0>
            indexKeyMap.put(size, val); //<0,3>
            size++; //加完之后size+1
            return true;
        }

        /**
         * Removes a value from the collection. Returns true if the collection contained the specified element.
         */
        public boolean remove(int val) {
            if (!keyIndexMap.containsKey(val)) return false;//不存在直接返回false
            //存在就删除
            int lastVal = indexKeyMap.get(size - 1);//先找到最后一次添加进去的元素是谁
            int removeIndex = keyIndexMap.get(val);//要删除元素的index
            keyIndexMap.put(lastVal, removeIndex);//填洞
            indexKeyMap.put(removeIndex, lastVal);//填洞
            //删除val
            keyIndexMap.remove(val);
            indexKeyMap.remove(size - 1);
            size--;
            return true;
        }

        /**
         * Get a random element from the collection.
         */
        public int getRandom() {
            if (size == 0) return -1;
            return indexKeyMap.get((int) Math.random() * size);
        }
    }

    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(2);
        set.add(1);
        set.add(3);
        System.out.println(set);
        System.out.println(set.first());
        System.out.println(set.last());

    }
}
