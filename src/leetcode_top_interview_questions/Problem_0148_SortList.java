package leetcode_top_interview_questions;

import linked_list.ListNode;

/**
 * 148. 排序链表
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 * 示例 1：
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 *
 * 示例 2：
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-list
 */
public class Problem_0148_SortList {

    //递归含义
    //head为头的链表进行归并排序，返回排序后的链表头节点
    public ListNode sortList(ListNode head) {
        if (head==null || head.next==null) {
            return head;
        }
        ListNode s = head;
        ListNode f = head.next;
        while (f!=null && f.next!=null) {
            s = s.next;
            f = f.next.next;
        }
        //s来到中点 / 上中点
        ListNode h2 = s.next;
        s.next = null;
        //对h1 h2排序
        ListNode sortedH1 = sortList(head);
        ListNode sortedH2 = sortList(h2);
        //merge
        return merge(sortedH1, sortedH2);
    }


    public ListNode merge(ListNode h1, ListNode h2) {
        if(h1==null && h2==null) return null;
        if (h1==null) return h2;
        if (h2==null) return h1;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (h1!=null && h2!=null) {
            if (h1.val<=h2.val) {
                cur.next = new ListNode(h1.val);
                h1 = h1.next;
            } else {
                cur.next = new ListNode(h2.val);
                h2 = h2.next;
            }
            cur = cur.next;
        }
        while (h1!=null) {
            cur.next = new ListNode(h1.val);
            h1 = h1.next;
            cur = cur.next;
        }
        while (h2!=null) {
            cur.next = new ListNode(h2.val);
            h2 = h2.next;
            cur = cur.next;
        }
        return dummy.next;
    }
}
