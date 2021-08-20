package linked_list;

/**
 * 430. 扁平化多级双向链表
 * 多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。
 * 这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
 *
 * 给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。
 */
public class Problem_0430_FlattenAMultilevelDoublyLinkedList {
    // Definition for a Node.
    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }
//    public Node flatten(Node head) {
//
//    }
}
