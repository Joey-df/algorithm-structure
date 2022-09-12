package system_study.class06;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 堆结构
 *
 * 1）堆结构就是用数组实现的完全二叉树结构
 * 2）完全二叉树中如果每棵子树的最大值都在顶部就是大根堆
 * 3）完全二叉树中如果每棵子树的最小值都在顶部就是小根堆
 * 4）堆结构的heapInsert与heapify操作
 * 5）堆结构的增大和减少
 * 6）优先级队列结构，就是堆结构
 */
public class Code02_Heap {

    public static class MyMaxHeap {
        private int[] heap;
        private final int limit;
        private int heapSize; // 两层含义：1、代表堆中元素个数，2、堆中要加数应该加到哪个位置

        public MyMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public void push(int value) {
            if (heapSize == limit) {
                throw new RuntimeException("heap is full");
            }
            heap[heapSize] = value;
            // value  heapSize
            heapInsert(heap, heapSize++); // 最后一步heapSize++
        }

        // 用户此时，让你返回最大值，并且在大根堆中，把最大值删掉
        // 剩下的数，依然保持大根堆组织
        public int pop() {
            int ans = heap[0];
            swap(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return ans;
        }

        // 【上移】
        // 新加进来的数，现在停在了index位置，请依次往上移动，
        // 移动到0位置，或者干不掉自己的父亲了，停！
        private void heapInsert(int[] arr, int index) {
            // [index]   父亲：[index-1]/2
            // 这个while包含了两个终止条件：index来到0位置、或者，arr[index]干不掉自己的父了
            while (arr[index] > arr[(index - 1) / 2]) {
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2; //index来到父亲的位置
            }
        }

        // 【下沉】
        // 从index位置，往下看，不断的
        // 停：较大的孩子都不再比index位置的数大；或者，已经没孩子了
        private void heapify(int[] arr, int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize) { // 如果有左孩子，有没有右孩子，可能有可能没有！
                // 把较大孩子的下标，给largest
                int right = left + 1;
                // 找出左右孩子中较大的下标
                int largest = right < heapSize && arr[right] > arr[left] ? right : left;
                // arr[index]，以及左右孩子中，三者中最大值的下标
                int maxIndex = arr[largest] > arr[index] ? largest : index;
                if (maxIndex == index) { // arr[index]的左右孩子干不掉自己，停！
                    break;
                }
                // index和较大孩子，要互换
                swap(arr, maxIndex, index);
                index = maxIndex; // index下沉到较大孩子的位置
                left = index * 2 + 1;
            }
        }

        private void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

    }

    public static class RightMaxHeap {
        private int[] arr;
        private final int limit;
        private int size;

        public RightMaxHeap(int limit) {
            arr = new int[limit];
            this.limit = limit;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("heap is full");
            }
            arr[size++] = value;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 1; i < size; i++) {
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            int ans = arr[maxIndex];
            arr[maxIndex] = arr[--size];
            return ans;
        }

    }


    public static class MyComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }

    }

    public static void main(String[] args) {
        // 小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>(new MyComparator());
        heap.add(5);
        heap.add(5);
        heap.add(5);
        heap.add(3);
        //  5 , 3
        System.out.println(heap.peek());
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        System.out.println(heap.peek());
        while(!heap.isEmpty()) {
            System.out.println(heap.poll());
        }

        System.out.println("=============分割线================");

        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MyMaxHeap my = new MyMaxHeap(curLimit);
            RightMaxHeap test = new RightMaxHeap(curLimit);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops!");
                }
                if (my.isFull() != test.isFull()) {
                    System.out.println("Oops!");
                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.push(curValue);
                    test.push(curValue);
                } else if (my.isFull()) {
                    if (my.pop() != test.pop()) {
                        System.out.println("Oops!");
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.push(curValue);
                    } else {
                        if (my.pop() != test.pop()) {
                            System.out.println("Oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");

    }

}
