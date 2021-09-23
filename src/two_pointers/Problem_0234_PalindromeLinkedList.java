package two_pointers;

import linked_list.ListNode;

import java.util.Stack;

/**
 * 234. 回文链表
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 *
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class Problem_0234_PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        if (head==null || head.next==null) return true;
        ListNode s = head;
        ListNode f = head;
        while (f!=null && f.next!=null) {
            s = s.next;
            f = f.next.next;
        }
        //s: 奇数时来到中点，偶数时来到下中点
        Stack<ListNode> stack = new Stack<>();
        while (s!=null) {
            stack.push(s);
            s = s.next;
        }
        ListNode cur = head;
        while (!stack.isEmpty()) {
            if (cur.val!=stack.pop().val) return false;
            cur = cur.next;
        }
        return true;
    }

}
