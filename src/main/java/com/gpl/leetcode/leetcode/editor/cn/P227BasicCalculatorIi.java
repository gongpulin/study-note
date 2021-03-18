//实现一个基本的计算器来计算一个简单的字符串表达式的值。 
//
// 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格 。 整数除法仅保留整数部分。 
//
// 示例 1: 
//
// 输入: "3+2*2"
//输出: 7
// 
//
// 示例 2: 
//
// 输入: " 3/2 "
//输出: 1 
//
// 示例 3: 
//
// 输入: " 3+5 / 2 "
//输出: 5
// 
//
// 说明： 
//
// 
// 你可以假设所给定的表达式都是有效的。 
// 请不要使用内置的库函数 eval。 
// 
// Related Topics 字符串


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.Stack;

//Java：基本计算器 II
public class P227BasicCalculatorIi{
    public static void main(String[] args) {
        Solution solution = new P227BasicCalculatorIi().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public int calculate(String s) {
            if (s == null) {
                return 0;
            }
            s = s.trim();
            Stack<Long> stack = new Stack<>();
            char op = '+';
            long num = 0, len = s.length();
            for (int i = 0; i < len; i++) {
                char c = s.charAt(i);
                if (c == ' ') {
                    continue;
                }
                if (Character.isDigit(c)) {
                    num = num * 10 + (c - '0');
                }
                if (!Character.isDigit(c) || i >= len - 1) {
                    if (op == '+') {
                        stack.push(num);
                    } else if (op == '-') {
                        stack.push(-num);
                    } else if (op == '*') {
                        long pre = stack.pop();
                        stack.push(pre * num);
                    } else if (op == '/') {
                        long pre = stack.pop();
                        stack.push(pre / num);
                    }
                    if (i >= len - 1) {
                        break;
                    }
                    op = c;
                    num = 0;
                }
            }
            int ans = 0;
            while (!stack.isEmpty()) {
                ans += stack.pop();
            }
            return ans;
        }

        /**
         * 错误答案，中间可能有空格
         */
        public int calculate123456(String s) {
            if (s == null) {
                return 0;
            }
            Stack<Long> stack = new Stack<>();
            int len = s.length();
            long num = 0;
            for (int i = 0; i < len; i++) {
                char c = s.charAt(i);
                if (c == ' ') {
                    continue;
                }
                if (Character.isDigit(c)) {
                    num = num * 10 + (c - '0');
                }
                if (!Character.isDigit(c)) {
                    if (c == '+') {
                        stack.push(num);
                    } else if (c == '-') {
                        stack.push(-num);
                    } else if (c == '*') {
                        long next = s.charAt(++i) - '0';
                        stack.push(next * num);
                    } else if (c == '/') {
                        long next = s.charAt(++i) - '0';
                        stack.push(num / next);
                    }
                    num = 0;
                }
            }
            int ans = 0;
            while (!stack.isEmpty()) {
                ans += stack.pop();
            }
            return ans;
        }
}
//leetcode submit region end(Prohibit modification and deletion)

}