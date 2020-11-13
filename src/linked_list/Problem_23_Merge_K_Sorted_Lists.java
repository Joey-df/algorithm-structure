package linked_list;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 *
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 *
 *
 * Example 1:
 *
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 */
public class Problem_23_Merge_K_Sorted_Lists {

    //lists中放的是每个小链表的头节点
    public ListNode mergeKLists(ListNode[] lists) {
        //小根堆
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        //先把每个链表的头节点放进去
        for (int i = 0; i < lists.length; i++) {
            if (lists[i]==null) continue; //为了兼容[[],[]]  或者 [[],[1]]这种case
            heap.add(lists[i]);
        }

        if (heap.isEmpty()) {
            return null;
        }

        //确定头节点
        ListNode head = heap.poll();
        ListNode pre = head;
        if (pre.next!=null) {
            heap.add(pre.next); //让heap长度恢复为lists.length
        }

        while (!heap.isEmpty()) {
            ListNode cur = heap.poll();
            pre.next = cur; //  串联到新链表尾部
            pre = cur;
            if (cur.next!=null) {
                heap.add(cur.next); //出一个，就进一个
            }
        }

        return head;
    }
}
