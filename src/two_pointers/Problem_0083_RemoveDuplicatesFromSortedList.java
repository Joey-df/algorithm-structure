package two_pointers;

import linked_list.ListNode;

/**
 * 83. 删除排序链表中的重复元素(使每个元素只出现一次)
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，
 * 请你删除所有重复的元素，使每个元素 只出现一次 。
 *
 * 返回同样按升序排列的结果链表。
 */
//进阶：删除无序单链表中值重复出现的节点
public class Problem_0083_RemoveDuplicatesFromSortedList {

    // [1,1,2,3,3]
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head.next;
        ListNode pre = head;
        while (cur != null) {
            if (cur.val == pre.val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

}
