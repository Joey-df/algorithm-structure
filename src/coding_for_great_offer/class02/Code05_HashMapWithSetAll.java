package coding_for_great_offer.class02;

import java.util.HashMap;

/**
 * 数据结构设计题
 * 设计一个HashMap，具有正常的put、get功能之外，具有setAll功能
 * 调用setAll之后，所有的key get出来的value一样；setAll之后再put指定key，对应的value会更新
 * (新增题)
 */
public class Code05_HashMapWithSetAll {

    public static class MyValue<V> {
        public V value;
        public long time;

        public MyValue(V v, long t) {
            value = v;
            time = t;
        }
    }

    public static class MyHashMap<K, V> {
        private HashMap<K, MyValue<V>> map;
        private long time;
        private MyValue<V> setAll;

        public MyHashMap() {
            map = new HashMap<>();
            time = 0;
            setAll = new MyValue<V>(null, -1);
        }

        public void put(K key, V value) {
            map.put(key, new MyValue<V>(value, time++));
        }

        public void setAll(V value) {
            setAll = new MyValue<V>(value, time++);
        }

        public V get(K key) {
            if (!map.containsKey(key)) {
                return null;
            }
            if (map.get(key).time > setAll.time) {
                return map.get(key).value;
            } else {
                return setAll.value;
            }
        }
    }

}
