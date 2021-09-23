package two_pointers;

import linked_list.ListNode;

/**
 * 86. 分隔链表
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 *
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 *
 * 示例 1：
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 * 示例 2：
 * 输入：head = [2,1], x = 2
 * 输出：[1,2]
 *
 * 提示：
 * 链表中节点的数目在范围 [0, 200] 内
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 */
public class Problem_0086_PartitionList {

    //使用分段指针
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        ListNode lessHead = null;
        ListNode lessTail = null; // 小于x区的头尾
        ListNode geHead = null;
        ListNode geTail = null; // 大于等于x区的头尾
        ListNode cur = head;
        ListNode next = null; // save next node
        while (cur != null) {
            next = cur.next;
            cur.next = null; // important
            if (cur.val < x) {
                if (lessHead == null) {
                    lessHead = cur;
                    lessTail = cur;
                } else {
                    lessTail.next = cur;
                    lessTail = cur;
                }
            } else { //>=
                if (geHead == null) {
                    geHead = cur;
                    geTail = cur;
                } else {
                    geTail.next = cur;
                    geTail = cur;
                }
            }
            cur = next;
        }
        if (lessHead == null) {
            return geHead;
        } else {
            lessTail.next = geHead;
            return lessHead;
        }
    }

}
