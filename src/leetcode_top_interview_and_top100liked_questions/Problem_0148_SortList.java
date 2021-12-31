package leetcode_top_interview_and_top100liked_questions;

import linked_list.ListNode;

/**
 * 148. 排序链表
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * <p>
 * 示例 1：
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * <p>
 * 示例 2：
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-list
 */
//单链表排序
public class Problem_0148_SortList {

    //递归含义
    //head为头的链表进行归并排序，返回排序后的链表头节点
    //O(nlogn)
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode s = head;
        ListNode f = head.next;
        while (f != null && f.next != null) {
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


    public ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }

    //选择排序
    public ListNode sortList2(ListNode head) {
        if (head==null || head.next==null) return head;
        for(ListNode p=head; p!=null; p=p.next) {
            //每一趟遍历，找到当前节点到结尾之间的值最小的node
            ListNode min=p;
            for (ListNode q=p.next; q!=null; q=q.next) {
                if (q.val < min.val) {
                    min=q;
                }
            }
            //swap
            int t=p.val;
            p.val=min.val;
            min.val=t;
        }
        return head;
    }
}
