package leetcode_top_interview_questions;

import linked_list.ListNode;

//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
//提示：
//链表中结点的数目为 sz
//1 <= sz <= 30
//0 <= Node.val <= 100
//1 <= n <= sz
public class Problem_0019_RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode s = dummy;
        ListNode f = dummy;
        int i = 0;
        //快指针先跳N+1步，然后快慢指针同步跳
        while (i++ <= n) { //等价写法 for (int i=0;i<=n;i++)
            f = f.next;
        }
        while (f != null) {
            s = s.next;
            f = f.next;
        }
        s.next = s.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        int n=5;
        for (int j=0;j<=n;j++) {
            System.out.println("j:"+j);;
        }
        System.out.println("=======");
        int i=0;
        while (i++ <= n) {
            System.out.println("i:"+i);;
        }
    }
}
