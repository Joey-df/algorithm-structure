package data_structure.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 380. O(1) 时间插入、删除和获取随机元素
 * 设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构。
 *
 * insert(val)：当元素 val 不存在时，向集合中插入该项。
 * remove(val)：元素 val 存在时，从集合中移除该项。
 * getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
 */
public class Problem_0380_InsertDeleteGetRandom {

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
