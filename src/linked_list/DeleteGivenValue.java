package linked_list;

/**
 * 删除单链表中指定的元素
 */
public class DeleteGivenValue {

    public static ListNode delete(ListNode head, int val) {
        //如果删除元素的在头部，换头
        while (head != null) {
            if (head.val != val) break;
            head = head.next;
        }
        //head来到第一个不是待删除val的节点位置
        ListNode pre = head, cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
