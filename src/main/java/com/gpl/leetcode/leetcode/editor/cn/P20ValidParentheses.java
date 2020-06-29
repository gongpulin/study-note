//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 注意空字符串可被认为是有效字符串。 
//
// 示例 1: 
//
// 输入: "()"
//输出: true
// 
//
// 示例 2: 
//
// 输入: "()[]{}"
//输出: true
// 
//
// 示例 3: 
//
// 输入: "(]"
//输出: false
// 
//
// 示例 4: 
//
// 输入: "([)]"
//输出: false
// 
//
// 示例 5: 
//
// 输入: "{[]}"
//输出: true 
// Related Topics 栈 字符串


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.Stack;

//Java：有效的括号
public class P20ValidParentheses{
    public static void main(String[] args) {
        Solution solution = new P20ValidParentheses().new Solution();
        // TO TEST
        System.out.println(solution.isValid("{]"));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isValid(String s) {
        boolean res = true;
        if ("".equals(s)) {
            return true;
        }
        if (s == null || s.length() % 2 != 0) {
            return false;
        }
        char[] sList = s.toCharArray();
        Stack<Character> stack = new Stack();
        for (int i = 0; i < sList.length; i++) {   //循环关注数组下标是否越界
            if (sList[i] == '(' || sList[i] == '{' || sList[i] == '[') {
                stack.push(sList[i]);
            } else {
                if ( !stack.isEmpty()) {
                    char temp = stack.pop();    // stack  之类的容器，从里面取数据之前必须判断集合是否为空
                    if ((temp == '(' && sList[i] != ')') || (temp == '[' && sList[i] != ']') || (temp == '{' && sList[i] != '}')) {
                        res = false;
                        break;
                    }
                }

            }
        }
        if (!stack.isEmpty()) {
            res = false;
        }
        return res;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}