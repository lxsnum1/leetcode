package p022;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。 

// 例如，给出 n = 3，生成结果为： 
//
// [
//  "((()))",
//  "(()())",
//  "(())()",
//  "()(())",
//  "()()()"
//]
// 
// Related Topics 字符串 回溯算法

public class GenerateParentheses {

    public static void main(String[] args) {
        Solution solution = new GenerateParentheses().new Solution();
    }

    class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> ans = new LinkedList<>();
            backtrack(ans, "", 0, 0, n);
            return ans;
        }

        private void backtrack(List<String> ans, String cur, int open, int close, int n) {

            if (open == n && close == n) {
                ans.add(cur);
                return;
            }

            if (open < n) { // key point: you can add '(' when open < n
                backtrack(ans, cur + '(', open + 1, close, n);
            }
            if (close < open) { // key point: you can add ')' when close < n
                backtrack(ans, cur + ')', open, close + 1, n);
            }
        }
    }

}