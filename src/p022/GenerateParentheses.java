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
            if (n < 1) {
                return Arrays.asList("");
            }
            if (n == 1) {
                return Arrays.asList("()");
            }

            LinkedList<String> list = new LinkedList<>();
            for (String string : generateParenthesis(n - 1)) {
                list.add("(" + string + ")");
                list.add("()" + string);
                list.add(string + "()");
            }
            list.removeLast();
            return list;
        }

        private List<String> findCombiantion(int n, List<String> currList) {
            List<String> newList = new LinkedList<>();
            for (String string : currList) {
                newList.add("(" + string + ")");
                newList.add("()" + string);
                newList.add(string + ")");
            }
            currList.remove(currList.size() - 1);
            return currList;
        }
    }

}