package linked_list;

/**
 * 92. 反转链表 II
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
 * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 * 示例 2：
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 *
 * 提示：
 * 链表中节点数目为 n
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 *
 * 进阶： 你可以使用一趟扫描完成反转吗？
 */
public class Problem_0092_ReverseLinkedListII {

    public ListNode reverseBetween(ListNode head, int l, int r) {
        if (head==null || head.next==null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        for (int i=0; i<l-1; i++) {
            cur = cur.next;
            pre = pre.next;
        }
        //[1,2,3,4,5], left = 2, right = 4为例
        //pre来到1，cur来到2
        ListNode next = null;
        ListNode pre2 = null;
        head = cur;
        for (int i = 0; i < r - l + 1; i++) {
            next = head.next;
            head.next = pre2;
            pre2 = head;
            head = next;
        }
        //next来到5，pre来到4
        //调整指向关系
        //2连5，1连4
        pre.next.next = next; //2连5
        pre.next = pre2; //1连4
        return dummy.next; //必须得这么写，不然有些用例会出错
    }
}
