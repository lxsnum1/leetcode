package p017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 示例: 
//
// 输入："23"
//输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
// 
//
// 说明: 
//尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。 
// Related Topics 字符串 回溯算法

import java.util.List;

public class LetterCombinationsOfAPhoneNumber {

    public static void main(String[] args) {
        SolutionV2 solution = new LetterCombinationsOfAPhoneNumber().new SolutionV2();
        solution.letterCombinations("23");
    }

    class Solution {
        public List<String> letterCombinations(String digits) {

            List<String> list = new ArrayList<>();
            if (digits == null || digits.length() == 0) {
                return list;
            }
            list.add("");
            for (int i = 0; i < digits.length(); i++) {
                list = combine(list, digits.charAt(i));
            }
            return list;
        }

        List<Character> numToCharList(char ch) {
            switch (ch) {
                case '2':
                    return Arrays.asList('a', 'b', 'c');
                case '3':
                    return Arrays.asList('d', 'e', 'f');
                case '4':
                    return Arrays.asList('g', 'h', 'i');
                case '5':
                    return Arrays.asList('j', 'k', 'l');
                case '6':
                    return Arrays.asList('m', 'n', 'o');
                case '7':
                    return Arrays.asList('p', 'q', 'r', 's');
                case '8':
                    return Arrays.asList('t', 'u', 'v');
                case '9':
                    return Arrays.asList('w', 'x', 'y', 'z');
                default:
                    return Arrays.asList();
            }
        }

        List<String> combine(List<String> ls, char ch) {
            List<String> newList = new ArrayList<>();
            for (String string : ls) {
                for (char c : numToCharList(ch)) {
                    newList.add(string + c);
                }
            }
            return newList;
        }
    }

    class SolutionV2 {
        private List<String> res;
        private String letterMap[] = { "", // 0
                "", // 1
                "abc", // 2
                "def", // 3
                "ghi", // 4
                "jkl", // 5
                "mno", // 6
                "pqrs", // 7
                "tuv", // 8
                "wxyz" // 9
        };

        public List<String> letterCombinations(String digits) {
            res = new LinkedList<>();
            if (digits == null || digits.equals("")) {
                return res;
            }

            findCombination(digits, 0, "");
            return res;
        }

        private void findCombination(String digits, int index, String s) {
            if (index == digits.length()) {
                res.add(s);
                return;
            }

            String letter = letterMap[digits.charAt(index) - '0'];
            for (int i = 0; i < letter.length(); i++) {
                findCombination(digits, index + 1, s + letter.charAt(i));
            }
        }
    }
}