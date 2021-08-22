package linked_list;

/**
 * 445. 两数相加 II
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。
 * 它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 * 示例1：
 * 输入：l1 = [7,2,4,3], l2 = [5,6,4]
 * 输出：[7,8,0,7]
 *
 * 示例2：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[8,0,7]
 *
 * 示例3：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 *
 * 提示：
 * 链表的长度范围为 [1, 100]
 * 0 <= node.val <= 9
 * 输入数据保证链表代表的数字无前导 0
 */
//与leetcode2类似
public class Problem_0445_AddTwoNumbersII {

    public static ListNode add(ListNode head1, ListNode head2) {
        head1 = reverse(head1);
        head2 = reverse(head2);
        if (head1==null&&head2==null) {
            return null;
        }
        if (head1==null ^ head2==null) {
            return head1==null ? head2 : head1;
        }
        //都不为空
        //先搞定头节点
        int sum = head1.val + head2.val;
        ListNode head = new ListNode(sum%10);
        ListNode cur=head;
        head1 = head1.next;
        head2 = head2.next;
        int carry=sum/10;
        while (head1!=null || head2!=null) {
            int v1 = head1!=null ? head1.val : 0;
            int v2 = head2!=null ? head2.val : 0;
            sum = v1+v2+carry;
            cur.next = new ListNode(sum%10);
            carry = sum/10;
            head1 = head1!=null ? head1.next : null;
            head2 = head2!=null ? head2.next : null;
            cur = cur.next;
        }
        if (carry==1) { //important
            cur.next = new ListNode(1);
        }
        return reverse(head);
    }


    private static ListNode reverse(ListNode head) {
        if (head==null) return null;
        ListNode next=null;
        ListNode pre=null;
        while(head!=null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

}
