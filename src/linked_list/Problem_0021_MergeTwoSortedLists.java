package linked_list;

/**
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。
 * 新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 示例 1：
 * <p>
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 */
public class Problem_0021_MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null ^ l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                pre.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                pre.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            pre = pre.next;
        }

        while (l1 != null) {
            pre.next = new ListNode(l1.val);
            pre = pre.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            pre.next = new ListNode(l2.val);
            pre = pre.next;
            l2 = l2.next;
        }
        return dummy.next;
    }
}
