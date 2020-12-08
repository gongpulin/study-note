//给定一个整数，将其转化为7进制，并以字符串形式输出。 
//
// 示例 1: 
//
// 
//输入: 100
//输出: "202"
// 
//
// 示例 2: 
//
// 
//输入: -7
//输出: "-10"
// 
//
// 注意: 输入范围是 [-1e7, 1e7] 。 
//


package com.gpl.leetcode.leetcode.editor.cn;
//Java：七进制数
public class P504Base7{
    public static void main(String[] args) {
        Solution solution = new P504Base7().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String convertToBase71(int num) {
        if (num > -7 && num < 7) {
            return String.valueOf(num);
        }
        boolean isNagative = num < 0;
        num = Math.abs(num);
        StringBuilder ans = new StringBuilder();
        while (num > 0) {
            ans.append(num % 7);
            num = num / 7;
        }
        String res = ans.reverse().toString();
        if (isNagative) {
            return "-" + res;
        }
        return res;
    }
        public String convertToBase7(int num) {
            if (num < 0) {
                return "-" + convertToBase7(-num);
            }
            if (num < 7) {
                return String.valueOf(num);
            }
            return convertToBase7(num / 7) + String.valueOf(num % 7);
        }
}
//leetcode submit region end(Prohibit modification and deletion)

}