package system_study.class07;

//加强堆

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * T一定要是非基础类型，有基础类型需求包一层（Inner）
 */
public class HeapGreater<T> {

    private ArrayList<T> heap;
    private HashMap<T, Integer> indexMap;
    private int heapSize;
    private Comparator<? super T> comp; // 自定义比较器

    public HeapGreater(Comparator<T> c) {
        heap = new ArrayList<>();
        // 反向索引表 key: 元素 value: 元素在数组中的下标
        indexMap = new HashMap<>();
        heapSize = 0;
        comp = c;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    public T peek() {
        return heap.get(0);
    }

    public void push(T obj) {
        heap.add(obj);
        indexMap.put(obj, heapSize);
        heapInsert(heapSize++);
    }

    public T pop() {
        T ans = heap.get(0);
        swap(0, heapSize - 1);
        indexMap.remove(ans);
        heap.remove(--heapSize);
        heapify(0);
        return ans;
    }

    // 从堆上删除任意一个值
    // 流程：
    // 1、用数组中的最后一个值覆盖待删除元素的位置
    // 2、resign（上浮 or 下沉）
    // 3、维护反向索引表
    public void remove(T obj) {
        T replace = heap.get(heapSize - 1);
        int index = indexMap.get(obj);
        indexMap.remove(obj);
        heap.remove(--heapSize);
        if (obj != replace) { // 边界条件：要删的就是数组中的最后一个值
            heap.set(index, replace);
            indexMap.put(replace, index);
            resign(replace);
        }
    }

    public void resign(T obj) {
        heapInsert(indexMap.get(obj));
        heapify(indexMap.get(obj));
    }

    // 请返回堆上的所有元素
    public List<T> getAllElements() {
        List<T> ans = new ArrayList<>();
        for (T c : heap) {
            ans.add(c);
        }
        return ans;
    }

    // 上浮
    private void heapInsert(int index) {
        // <0: 不管比较器怎么定义，这里比较大小的时候统一这么写
        while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // 下沉
    private void heapify(int index) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int right = left + 1;
            int largest = right < heapSize && comp.compare(heap.get(right), heap.get(left)) < 0 ? right : left;
            int maxIndex = comp.compare(heap.get(largest), heap.get(index)) < 0 ? largest : index;
            if (maxIndex == index) {
                break;
            }
            swap(maxIndex, index);
            index = maxIndex;
            left = index * 2 + 1;
        }
    }

    // 把交换两个位置的值，和维护反向索引表，封装在这一个方法中
    private void swap(int i, int j) {
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        heap.set(i, o2);
        heap.set(j, o1);
        indexMap.put(o2, i);
        indexMap.put(o1, j);
    }

    public static void main(String[] args) {

        // 测试大根堆
        HeapGreater heap = new HeapGreater((Comparator<Integer>) (o1, o2) -> o2-o1);
        heap.push(1);
        heap.push(3);
        heap.push(2);
        heap.push(10);
        System.out.println(heap.getAllElements());
        while (!heap.isEmpty()) {
            System.out.println(heap.pop());
        }

    }

}

