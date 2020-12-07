//给定一个单词列表，只返回可以使用在键盘同一行的字母打印出来的单词。键盘如下图所示。 
//
//
//
//
//
//
//
// 示例：
//
// 输入: ["Hello", "Alaska", "Dad", "Peace"]
//输出: ["Alaska", "Dad"]
//
//
//
//
// 注意：
//
//
// 你可以重复使用键盘上同一字符。
// 你可以假设输入的字符串将只包含字母。
// Related Topics 哈希表


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：键盘行
public class P500KeyboardRow{
    public static void main(String[] args) {
        Solution solution = new P500KeyboardRow().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String[] findWords(String[] words) {
        String line1 = "qwertyuiop";
        String line2 = "asdfghjkl";
        String line3 = "zxcvbnm";
        int[] rows = new int[26];
        for (int i = 0; i < line1.length(); i++) {
            rows[Character.toLowerCase(line1.charAt(i) - 'a')] = 1;
        }
        for (int i = 0; i < line2.length(); i++) {
            rows[Character.toLowerCase(line2.charAt(i) - 'a')] = 2;
        }
        for (int i = 0; i < line3.length(); i++) {
            rows[Character.toLowerCase(line3.charAt(i) - 'a')] = 3;
        }
        List<String> list = new ArrayList<>();
        for (String word : words) {
            if (isFindWord(word, rows)) {
                list.add(word);
            }
        }
        String[] ans = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
    private boolean isFindWord(String word, int[] rows) {
        if (word == null) {
            return false;
        }
        int len = word.length();
        int rowNum = rows[Character.toLowerCase(word.charAt(0)) - 'a'];
        for (int i = 1; i < len; i++) {
            if (rows[Character.toLowerCase(word.charAt(i)) - 'a'] != rowNum) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}