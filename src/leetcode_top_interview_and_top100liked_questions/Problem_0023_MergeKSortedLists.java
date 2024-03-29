package leetcode_top_interview_and_top100liked_questions;

import linked_list.ListNode;

import java.util.PriorityQueue;

/**
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * 示例 1：
 * <p>
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 * <p>
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：lists = [[]]
 * 输出：[]
 */
public class Problem_0023_MergeKSortedLists {

    public static ListNode mergeKLists(ListNode[] lists) {

        PriorityQueue<ListNode> q = new PriorityQueue<>((o1, o2) -> (o1.val - o2.val));
        for (ListNode head : lists) {
            if (head != null)
                q.offer(head);
        }
        if (q.isEmpty()) { //lists = []的情况
            return null;
        }

        //至此，q中放入了每个链表的头节点
        ListNode head = q.poll();
        ListNode pre = head;
        if (pre.next!=null) {
            q.offer(pre.next);
        }

        while (!q.isEmpty()) {
            ListNode cur = q.poll();
            pre.next = cur;
            pre = cur;
            if (cur.next != null) {
                q.offer(cur.next);
            }
        }
        return head;
    }
}
