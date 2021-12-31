package two_pointers;

import linked_list.ListNode;

/**
 * 160. 两个无环链表的相交节点
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。
 * 如果两个链表不存在相交节点，返回 null 。
 *
 * 题目数据 保证 整个链式结构中不存在环。
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 */

public class Problem_0160_IntersectionOfTwoLinkedLists {

    //分别遍历两个链表找到最末尾的两个节点endA、endB，如果endA!=endB,不相交
    //如果endA==endB,则相交，根据长度找到相交节点
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        //都不为空
        ListNode endA = headA, endB = headB;
        int lenA = 1, lenB = 1;
        while (endA.next != null) {
            lenA++;
            endA = endA.next;
        }
        while (endB.next != null) {
            lenB++;
            endB = endB.next;
        }

        if (endA != endB) {
            return null;
        }
        //endA==endB
        ListNode longer = lenA > lenB ? headA : headB;
        ListNode shorter = longer == headA ? headB : headA;

        for (int i = 0; i < Math.abs(lenA - lenB); i++) {
            longer = longer.next;
        }
        while (longer != shorter) {
            longer = longer.next;
            shorter = shorter.next;
        }
        return longer;
    }
}
