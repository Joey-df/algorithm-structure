package system_study.util;

public class LinkListUtil {

    /**
     * 随机生成一个双向链表
     * @param n 链表长度
     * @param v val的上限
     * @return
     */
    public static DoubleListNode generateRandomDoubleLinkedList(int n, int v) {
        if (n == 0) {
            return null;
        }
        DoubleListNode[] arr = new DoubleListNode[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new DoubleListNode((int) (Math.random() * v));
        }
        DoubleListNode head = arr[0];
        DoubleListNode pre = head;
        for (int i = 1; i < n; i++) {
            pre.next = arr[i];
            arr[i].last = pre;
            pre = arr[i];
        }
        return head;
    }

    // 克隆一个双向链表
    public static DoubleListNode cloneDoubleLinkedList(DoubleListNode head) {
        if (head == null) {
            return null;
        }
        DoubleListNode h = new DoubleListNode(head.value);
        DoubleListNode p = h;
        head = head.next;
        while (head != null) {
            DoubleListNode c = new DoubleListNode(head.value);
            p.next = c;
            c.last = p;
            p = c;
            head = head.next;
        }
        return h;
    }

    // 判断两个双向链表是否相等
    public static boolean equal(DoubleListNode h1, DoubleListNode h2) {
        return doubleLinkedListToString(h1).equals(doubleLinkedListToString(h2));
    }

    // 链表转成字符串
    public static String doubleLinkedListToString(DoubleListNode head) {
        DoubleListNode cur = head;
        DoubleListNode end = null;
        StringBuilder builder = new StringBuilder();
        while (cur != null) {
            builder.append(cur.value + " ");
            end = cur;
            cur = cur.next;
        }
        builder.append("| ");
        while (end != null) {
            builder.append(end.value + " ");
            end = end.last;
        }
        return builder.toString();
    }
}
