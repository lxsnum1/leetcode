package p002;

//给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。 
//
// 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。 
//
// 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 示例： 
//
// 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 0 -> 8
//原因：342 + 465 = 807
// 
// Related Topics 链表 数学

public class AddTwoNumbers {
    public static void main(String[] args) {
        Solution solution = new AddTwoNumbers().new Solution();
    }

    // leetcode submit region begin(Prohibit modification and deletion)

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
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }

            int mod = (l1.val + l2.val) % 10;
            int carry = (l1.val + l2.val) / 10;
            ListNode result = new ListNode(mod);
            if (carry == 0) {
                result.next = addTwoNumbers(l1.next, l2.next);
            } else {
                result.next = addTwoNumbers(new ListNode(carry), addTwoNumbers(l1.next, l2.next));
            }
            return result;
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)

    class SolutionV2 {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }

            int mod = (l1.val + l2.val) % 10;
            int carry = (l1.val + l2.val) / 10;
            ListNode result = new ListNode(mod);

            result.next = addTwoNumbers(l1.next, l2.next);
            if (carry != 0) {
                result.next = addTwoNumbers(new ListNode(carry), result.next);
            }
            return result;
        }
    }
}