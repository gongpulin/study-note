//将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。 
//
// 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下： 
//
// L   C   I   R
//E T O E S I I G
//E   D   H   N
// 
// len = (numRows+1) * (x/2)
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


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：Z 字形变换
public class P6ZigzagConversion{
    public static void main(String[] args) {
        Solution solution = new P6ZigzagConversion().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        public String convert(String s, int numRows) {
            if (s == null || numRows == 0) {
                return "";
            }
            if (numRows == 1) {
                return s;
            }
            int len = s.length();
            int cycleLen = 2 * numRows - 2;
            StringBuilder ans = new StringBuilder();
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j + i < len; j += cycleLen) {
                    ans.append(s.charAt(j + i));
                    if (i != 0 && i != numRows - 1 && j + cycleLen -i < len) {
                        ans.append(s.charAt(j + cycleLen - i));
                    }
                }
            }
            return ans.toString();
        }
//    public String convert(String s, int numRows) {
//        if (s == null || numRows == 0) {
//            return "";
//        }
//        if (numRows == 1) {
//            return s;
//        }
//        int len = s.length();
//        List<StringBuilder> rows = new ArrayList<>();
//        for (int i = 0; i < Math.min(len, numRows); i++) {
//            rows.add(new StringBuilder());
//        }
//        char[] arr = s.toCharArray();
//        boolean goingDown = false;
//        int curRow = 0;
//        for (char c : arr) {
//            rows.get(curRow).append(c);
//            if (curRow == 0 || curRow == numRows - 1) {
//                goingDown = ! goingDown;
//            }
//            curRow +=  goingDown ? 1 : -1;
//        }
//        StringBuilder ans = new StringBuilder();
//        for (StringBuilder sb : rows) {
//            ans.append(sb);
//        }
//        return ans.toString();
//
//    }



}
//leetcode submit region end(Prohibit modification and deletion)

}