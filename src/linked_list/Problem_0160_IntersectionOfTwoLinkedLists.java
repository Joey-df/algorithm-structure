package linked_list;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
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
