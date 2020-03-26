package p010;

//给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
//
// '.' 匹配任意单个字符
//'*' 匹配零个或多个前面的那一个元素
// 
//
// 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。 
//
// 说明: 
//
// 
// s 可能为空，且只包含从 a-z 的小写字母。 
// p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。 
// 
//
// 示例 1: 
//
// 输入:
//s = "aa"
//p = "a"
//输出: false
//解释: "a" 无法匹配 "aa" 整个字符串。
// 
//
// 示例 2: 
//
// 输入:
//s = "aa"
//p = "a*"
//输出: true
//解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
// 
//
// 示例 3: 
//
// 输入:
//s = "ab"
//p = ".*"
//输出: true
//解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
// 
//
// 示例 4: 
//
// 输入:
//s = "aab"
//p = "c*a*b"
//输出: true
//解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
// 
//
// 示例 5: 
//
// 输入:
//s = "mississippi"
//p = "mis*is*p*."
//输出: false 
// Related Topics 字符串 动态规划 回溯算法

public class RegularExpressionMatching {

    public static void main(String[] args) {
        Solution solution = new RegularExpressionMatching().new Solution();
        boolean isMatch = solution.isMatch("ab", ".*c");
    }

    class Solution {
        public boolean isMatch(String s, String p) {
            if (p.isEmpty()) {
                return s.isEmpty();
            }

            boolean firstMatch = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
            if (p.length() > 1 && p.charAt(1) == '*') {
                return (firstMatch && isMatch(s.substring(1), p)) || isMatch(s, p.substring(2));
            } else {
                return firstMatch && isMatch(s.substring(1), p.substring(1));
            }
        }
    }

    /**
     * danamic programming, ref: https://github.com/MisterBooo/LeetCodeAnimation
     * <br>
     * <li>如果p.charAt(j) == s.charAt(i) : dp[i][j] = dp[i-1][j-1]；</li>
     * <li>如果 p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1]；</li>
     * 
     * <li>如果 p.charAt(j) == '*':</li>
     * <li>如果 p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2] //in this case,
     * a* only counts as empty</li>
     * <li>如果 p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.'：
     * <ul>
     * <li>dp[i][j] =dp[i-1][j] //in this case, a* counts as multiple a</li>
     * <li>or dp[i][j] = dp[i][j-2] // in this case, a* counts as empty</li>
     * </ul>
     */
    class SolutionV2 {
        public boolean isMatch(String s, String p) {
            if (p.equals(s)) {
                return true;
            }

            char[] sArr = s.toCharArray();
            char[] pArr = p.toCharArray();

            boolean dp[][] = new boolean[sArr.length + 1][pArr.length + 1];
            dp[0][0] = true;

            for (int j = 1; j <= pArr.length; j++) {
                dp[0][j] = pArr[j - 1] == '*' ? dp[0][j - 2] : false;
            }

            for (int i = 1; i <= sArr.length; i++) {
                for (int j = 1; j <= pArr.length; j++) {
                    if (sArr[i - 1] == pArr[j - 1] || pArr[j - 1] == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else if (pArr[j - 1] == '*') {
                        dp[i][j] = dp[i][j - 2];

                        if (sArr[i - 1] == pArr[j - 2] || pArr[j - 2] == '.') {
                            dp[i][j] |= dp[i - 1][j];
                        }
                    } else {
                        dp[i][j] = false;
                    }
                }
            }
            return dp[sArr.length][pArr.length];
        }
    }
}