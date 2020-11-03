package linked_list;

//反转双向链表
public class Reverse_Double_LinkedList {

    private static class DoubleNode {
        private int val;
        private DoubleNode next;
        private DoubleNode last;

        DoubleNode() {
        }

        DoubleNode(int val) {
            this.val = val;
        }
    }

    public static DoubleNode reverse(DoubleNode head) {
        DoubleNode pre = null, next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }
}
