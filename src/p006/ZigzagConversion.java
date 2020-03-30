package p006;

import java.util.ArrayList;
import java.util.List;

//将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
//
// 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下： 
//
// L   C   I   R
//E T O E S I I G
//E   D   H   N
// 
//
// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。 
//
// 请你实现这个将字符串进行指定行数变换的函数： 
//
// string convert(string s, int numRows); 
//
// 示例 1: 
//
// 输入: s = "LEETCODEISHIRING", numRows = 3
//输出: "LCIRETOESIIGEDHN"
// 
//
// 示例 2: 
//
// 输入: s = "LEETCODEISHIRING", numRows = 4
//输出: "LDREOEIIECIHNTSG"
//解释:
//
//L     D     R
//E   O E   I I
//E C   I H   N
//T     S     G 
// Related Topics 字符串

public class ZigzagConversion {

    public static void main(String[] args) {
        Solution solution = new ZigzagConversion().new Solution();
    }

    class Solution {
        public String convert(String s, int numRows) {
            if (numRows < 2) {
                return s;
            }

            List<StringBuilder> rows = new ArrayList<>();
            for (int i = 0; i < Math.min(numRows, s.length()); i++) {
                rows.add(new StringBuilder());
            }

            int direction = -1;
            int j = 0;
            for (int i = 0; i < s.length(); i++) {
                rows.get(j).append(s.charAt(i));
                if (j == 0 || j == numRows - 1) {
                    direction = -1 * direction;
                }
                j = j + direction;
            }

            for (int i = 1; i < Math.min(numRows, s.length()); i++) {
                rows.get(0).append(rows.get(i));
            }
            return rows.get(0).toString();
        }
    }
}