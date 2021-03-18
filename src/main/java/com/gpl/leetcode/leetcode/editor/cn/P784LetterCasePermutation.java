//给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。 
//
// 
//
// 示例：
//输入：S = "a1b2"
//输出：["a1b2", "a1B2", "A1b2", "A1B2"]
//
//输入：S = "3z4"
//输出：["3z4", "3Z4"]
//
//输入：S = "12345"
//输出：["12345"]
// 
//
// 
//
// 提示： 
//
// 
// S 的长度不超过12。 
// S 仅由数字和字母组成。 
// 
// Related Topics 位运算 回溯算法


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：字母大小写全排列
public class P784LetterCasePermutation{
    public static void main(String[] args) {
        Solution solution = new P784LetterCasePermutation().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> letterCasePermutation1(String S) {
        List<StringBuilder> ans = new ArrayList<>();
        ans.add(new StringBuilder());
        for (Character c : S.toCharArray()) {
            int n = ans.size();
            if (Character.isLetter(c)) {
                for (int i = 0; i < n; i++) {
                    ans.add(new StringBuilder(ans.get(i)));
                    ans.get(i).append(Character.toLowerCase(c));
                    ans.get(n + i).append(Character.toUpperCase(c));
                }
            } else {
                for (int i = 0; i < n; i++) {
                    ans.get(i).append(c);
                }
            }
        }
        List<String> res = new ArrayList<>();
        for (StringBuilder sb : ans) {
            res.add(sb.toString());
        }
        return res;

    }


    public List<String> letterCasePermutation(String S) {
        List<String> ans = new ArrayList<>();
        char[] arr = S.toCharArray();
        backtrack(ans, 0, arr);
        return ans;
    }
    private void backtrack(List<String> ans, int start, char[] arr) {
        if (start == arr.length) {
            ans.add(new String(arr));
            return;
        }
        char c = arr[start];
        backtrack(ans, start + 1, arr);
        if (Character.isLetter(c)) {
            arr[start] ^= 1 << 5;
            backtrack(ans, start + 1, arr);
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}