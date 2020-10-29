package design;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Median is the middle value in an ordered integer list.
 * If the size of the list is even, there is no middle value.
 * So the median is the mean of the two middle value.
 * <p>
 * For example,
 * [2,3,4], the median is 3
 * <p>
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * <p>
 * Design a data structure that supports the following two operations:
 * <p>
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 * <p>
 * <p>
 * Example:
 * <p>
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * <p>
 * <p>
 * Follow up:
 * <p>
 * If all integer numbers from the stream are between 0 and 100, how would you optimize it?
 * If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 */

//使用两个堆，maxHeap、minHeap
//第一个数进maxHeap
//如果curNum > maxHeap的堆顶，curNum进小根堆，否则进大根堆
//任何时候 heap1的长度-heap2的长度==2时，heap1的堆顶元素弹出进heap2
public class Problem_295_Find_Median_from_Data_Stream {

    private static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });
    private static PriorityQueue<Integer> minHeap = new PriorityQueue<>();//默认就是小根队
    private int size;

    public void addNum(int num) {
        if (size == 0) { //第一个元素进大根堆
            maxHeap.offer(num);
        } else {
            if (num > maxHeap.peek()) {
                minHeap.offer(num);
            } else { //num <= maxHeap.peek()
                maxHeap.offer(num);
            }
            balance(); //使两个堆的大小差不超过1
        }
        size++;
    }

    //balance
    private void balance() {
        if (maxHeap.size() - minHeap.size() > 1) {
            minHeap.offer(maxHeap.poll());
        }
        if (minHeap.size() - maxHeap.size() > 1) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else if (maxHeap.size() < minHeap.size()) {
            return minHeap.peek();
        } else {
            return (double)(maxHeap.peek() + minHeap.peek()) / 2;
        }
    }

}
