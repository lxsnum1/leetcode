package p020;

import java.util.ArrayDeque;
import java.util.Deque;

//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 注意空字符串可被认为是有效字符串。 
//
// 示例 1: 
//
// 输入: "()"
//输出: true
// 
//
// 示例 2: 
//
// 输入: "()[]{}"
//输出: true
// 
//
// 示例 3: 
//
// 输入: "(]"
//输出: false
// 
//
// 示例 4: 
//
// 输入: "([)]"
//输出: false
// 
//
// 示例 5: 
//
// 输入: "{[]}"
//输出: true 
// Related Topics 栈 字符串

public class ValidParentheses {

    public static void main(String[] args) {
        Solution solution = new ValidParentheses().new Solution();
        boolean ans = solution.isValid("{[]}");
    }

    class Solution {
        public boolean isValid(String s) {

            Deque<Character> stack = new ArrayDeque<>();
            for (int i = 0; i < s.length(); i++) {
                if (!stack.isEmpty() && isMatch(stack.peek(), s.charAt(i))) {
                    stack.pop();
                } else {
                    stack.push(s.charAt(i));
                }
            }
            return stack.isEmpty();
        }

        private boolean isMatch(char a, char b) {
            if ((a == '(' && b == ')') || (a == '[' && b == ']') || (a == '{' && b == '}')) {
                return true;
            } else {
                return false;
            }
        }
    }
}