//给定一个字符串来代表一个学生的出勤记录，这个记录仅包含以下三个字符： 
//
// 
// 'A' : Absent，缺勤 
// 'L' : Late，迟到 
// 'P' : Present，到场 
// 
//
// 如果一个学生的出勤记录中不超过一个'A'(缺勤)并且不超过两个连续的'L'(迟到),那么这个学生会被奖赏。 
//
// 你需要根据这个学生的出勤记录判断他是否会被奖赏。 
//
// 示例 1: 
//
// 输入: "PPALLP"
//输出: True
// 
//
// 示例 2: 
//
// 输入: "PPALLL"
//输出: False
// 
// Related Topics 字符串


package com.gpl.leetcode.leetcode.editor.cn;
//Java：学生出勤记录 I
public class P551StudentAttendanceRecordI{
    public static void main(String[] args) {
        Solution solution = new P551StudentAttendanceRecordI().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean checkRecord(String s) {
        if (s == null) {
            return false;
        }
        int ACount = 0, LCount = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == 'A' && ++ACount > 1) {
                return false;
            }
            if (c == 'L') {
                if (++LCount > 2) {
                    return false;
                }
            } else {
                LCount = 0;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}