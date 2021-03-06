package p005;

//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。 
//
// 示例 1： 
//
// 输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案。
// 
//
// 示例 2： 
//
// 输入: "cbbd"
//输出: "bb"
// 
// Related Topics 字符串 动态规划

public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubstring().new Solution();
    }

    // leetcode submit region begin(Prohibit modification and deletion)
    /**
     * expand around center sub string
     * 
     */
    class Solution {
        public String longestPalindrome(String s) {
            if (s == null || s.length() == 0) {
                return "";
            }

            int start = 0, end = 0;
            for (int i = 0; i < s.length(); i++) {
                // 1、如果传入重合的索引编码，进行中心扩散，此时得到的回文子串的长度是奇数；
                // 2、如果传入相邻的索引编码，进行中心扩散，此时得到的回文子串的长度是偶数。
                int len1 = expandAroundCenter(s, i, i);
                int len2 = expandAroundCenter(s, i, i + 1);
                int maxLen = Math.max(len1, len2);
                if (maxLen > end - start + 1) {
                    start = i - (maxLen - 1) / 2;
                    end = i + maxLen / 2;
                }
            }
            // substring(i,j) -> [i,j), include i , not j
            return s.substring(start, end + 1);
        }

        /** return the length of palindrome */
        private int expandAroundCenter(String s, int left, int right) {
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            return right - left - 1;
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)

    /**
     * SolutionV2
     * 
     * brute force
     */
    public class SolutionV2 {

        public String longestPalindrome(String s) {
            if (s == null || s.length() < 1) {
                return "";
            }
            int maxLen = 0;
            int start = 0, end = 0;
            for (int i = 0; i < s.length(); i++) {
                for (int j = 1; j < s.length() + 1; j++) {
                    if (maxLen < j - i && isPalindromic(s.substring(i, j))) {
                        maxLen = j - i;
                        start = i;
                        end = j;
                    }
                }
            }
            return s.substring(start, end);
        }

        private boolean isPalindromic(String s) {
            int len = s.length();
            for (int i = 0; i < len / 2; i++) {
                if (s.charAt(i) != s.charAt(len - i - 1)) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * SolutionV3
     * 
     * dynamic programming
     * 
     * similar to the previous brute force method, but using space to exchange for
     * time;
     */
    public class SolutionV3 {
        public String longestPalindrome(String s) {
            if (s.length() < 2) {
                return s;
            }
            int len = s.length();
            int maxLen = 0;
            int start = 0;
            boolean[][] dp = new boolean[len][len];

            for (int j = 0; j < len; j++) { // 状态转移顺序决定此处填表顺序
                for (int i = 0; i <= j; i++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        if (j - i < 2) {
                            dp[i][j] = true;
                        } else {
                            dp[i][j] = dp[i + 1][j - 1];
                        }
                    } else {
                        dp[i][j] = false;
                    }
                    if (dp[i][j]) {
                        if (maxLen < j - i + 1) {
                            maxLen = j - i + 1;
                            start = i;
                        }
                    }
                }
            }
            return s.substring(start, start + maxLen);
        }
    }

}