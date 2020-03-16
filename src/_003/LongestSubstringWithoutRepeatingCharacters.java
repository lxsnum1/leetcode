import java.util.*;

//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 示例 1: 
//
// 输入: "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
    }

    // leetcode submit region begin(Prohibit modification and deletion)

    /**
     * using queue
     */
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            Queue<Character> queue = new ArrayDeque<>();
            // initial maxLength = 0 to account for empty string
            int maxLength = 0;

            for (int i = 0; i < s.length(); i++) {
                while (queue.contains(s.charAt(i))) {
                    queue.remove();
                }
                queue.add(s.charAt(i));
                if (maxLength < queue.size()) {
                    maxLength = queue.size();
                }
            }

            return maxLength;
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)

    /**
     * Solution_V2,
     * <p>
     * using HashSet
     */
    public class SolutionV2 {

        public int lengthOfLongestSubstring(String s) {
            Set<Character> charSet = new HashSet<>();
            int maxLength = 0;

            for (int i = 0, j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                while (charSet.contains(c)) {
                    charSet.remove(s.charAt(i));
                    i = i + 1;
                }
                charSet.add(c);
                maxLength = Math.max(maxLength, charSet.size());
            }

            return maxLength;
        }
    }

    public class SolutionV2_1 {
        public int lengthOfLongestSubstring(String s) {
            Set<Character> charSet = new HashSet<>();
            int maxLength = 0;

            for (int i = 0, j = 0; j < s.length();) {
                if (charSet.contains(s.charAt(j))) {
                    charSet.remove(s.charAt(i++));
                } else {
                    charSet.add(s.charAt(j++));
                    maxLength = Math.max(maxLength, charSet.size());
                }
            }

            return maxLength;
        }
    }

    /**
     * SolutionV3
     * 
     * using HashMap
     */
    public class SolutionV3 {
        public int lengthOfLongestSubstring(String s) {
            int maxLength = 0;
            Map<Character, Integer> map = new HashMap<>();

            // range between i j are the substring wo repeating characters;
            for (int i = 0, j = 0; j < s.length(); j++) {
                if (map.containsKey(s.charAt(j))) {
                    i = Math.max(map.get(s.charAt(j)) + 1, i);
                }
                map.put(s.charAt(j), j);
                maxLength = Math.max(maxLength, j - i + 1);

            }

            return maxLength;
        }
    }

}