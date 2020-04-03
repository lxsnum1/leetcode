package p023;

import java.util.ArrayDeque;
import java.util.Deque;

//合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。 
//
// 示例: 
//
// 输入:
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//输出: 1->1->2->3->4->4->5->6 
// Related Topics 堆 链表 分治算法

public class MergeKSortedLists {

    public static void main(String[] args) {
        Solution solution = new MergeKSortedLists().new Solution();
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
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length < 1) {
                return null;
            }
            return mergeKLists(lists, 0, lists.length - 1);
        }

        private ListNode mergeKLists(ListNode[] lists, int start, int end) {
            if (start == end) {
                return lists[start];
            }
            int mid = (start + end) / 2;
            return mergeTwoLists(mergeKLists(lists, start, mid), mergeKLists(lists, mid + 1, end));
        }

        private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            } else if (l2 == null) {
                return l1;
            }

            if (l1.val < l2.val) {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeTwoLists(l1, l2.next);
                return l2;
            }
        }
    }

    class SolutionV2 {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length < 1) {
                return null;
            }

            Deque<ListNode> deque = new ArrayDeque<>();
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null) {
                    deque.add(lists[i]);
                }
            }

            while (deque.size() > 1) {
                deque.add(mergeTwoLists(deque.remove(), deque.remove()));
            }
            return deque.peek();
        }

        private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            } else if (l2 == null) {
                return l1;
            }

            if (l1.val < l2.val) {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeTwoLists(l1, l2.next);
                return l2;
            }
        }
    }

}