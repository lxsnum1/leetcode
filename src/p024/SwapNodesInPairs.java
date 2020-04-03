package p024;

// 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。 
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 
//
// 示例: 
//
// 给定 1->2->3->4, 你应该返回 2->1->4->3.
// 
// Related Topics 链表

public class SwapNodesInPairs {

    public static void main(String[] args) {
        Solution solution = new SwapNodesInPairs().new Solution();
    }

    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class Solution {
        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }

            ListNode a = head;
            ListNode b = a.next;
            if (b != null) {
                a.next = swapPairs(b.next);
                b.next = a;
            }
            return b;
        }
    }

}