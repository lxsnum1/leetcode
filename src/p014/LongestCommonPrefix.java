package p014;

//编写一个函数来查找字符串数组中的最长公共前缀。
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 示例 1: 
//
// 输入: ["flower","flow","flight"]
//输出: "fl"
// 
//
// 示例 2: 
//
// 输入: ["dog","racecar","car"]
//输出: ""
//解释: 输入不存在公共前缀。
// 
//
// 说明: 
//
// 所有输入只包含小写字母 a-z 。 
// Related Topics 字符串

public class LongestCommonPrefix {

    public static void main(String[] args) {
        Solution solution = new LongestCommonPrefix().new Solution();
    }

    /**
     * 线性查找
     */
    class Solution {
        public String longestCommonPrefix(String[] strs) {
            if (strs.length == 0) {
                return "";
            }
            int i = 0;
            for (; i < strs[0].length(); i++) {
                for (int j = 1; j < strs.length; j++) {
                    if (i == strs[j].length() || strs[0].charAt(i) != strs[j].charAt(i)) {
                        return strs[0].substring(0, i);
                    }
                }
            }
            return strs[0].substring(0, i);
        }
    }

    /**
     * SolutionV2
     * 
     * 二分查找法, 与方法一相似结构，优化为二分查找
     */
    class SolutionV2 {
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            }

            int low = 0, high = strs[0].length() - 1;
            int mid = 0;
            while (low <= high) { // "=" to find if no common prefix
                mid = (low + high) / 2;
                if (isCommonPrefix(strs, mid)) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            return strs[0].substring(0, (low + high + 1) / 2);

        }

        /** If substring(0, index + 1) is the common prefix */
        private boolean isCommonPrefix(String[] strs, int index) {
            String str0 = strs[0].substring(0, index + 1);
            for (int i = 1; i < strs.length; i++) {
                if (!strs[i].startsWith(str0)) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * SolutionV3
     * 
     * Trie optimized for a-z
     */
    public class SolutionV3 {

        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            }

            return null;
        }
    }
}