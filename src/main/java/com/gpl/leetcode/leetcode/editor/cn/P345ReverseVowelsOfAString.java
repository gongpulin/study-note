//编写一个函数，以字符串作为输入，反转该字符串中的元音字母。 
//
// 
//
// 示例 1： 
//
// 输入："hello"
//输出："holle"
// 
//
// 示例 2： 
//
// 输入："leetcode"
//输出："leotcede" 
//
// 
//
// 提示： 
//
// 
// 元音字母不包含字母 "y" 。 
// 
// Related Topics 双指针 字符串


package com.gpl.leetcode.leetcode.editor.cn;
//Java：反转字符串中的元音字母
public class P345ReverseVowelsOfAString{
    public static void main(String[] args) {
        Solution solution = new P345ReverseVowelsOfAString().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseVowels(String s) {
        if (s == null) {
            return null;
        }
        char[] arr = s.toCharArray();
        int left = 0, right = arr.length - 1;
        while (left < right) {
            while (left < right && !isVowels(arr[left])) {
                left++;
            }
            while (left < right && !isVowels(arr[right])) {
                right--;
            }
            swap(arr, left, right);
            left++;
            right--;
        }
        return new String(arr);
    }
    private boolean isVowels(char c) {
        switch (c) {
            case 'a' : return true;
            case 'e' : return true;
            case 'i' : return true;
            case 'o' : return true;
            case 'u' : return true;
            case 'A' : return true;
            case 'E' : return true;
            case 'I' : return true;
            case 'O' : return true;
            case 'U' : return true;
            default : return false;
        }
    }
    private void swap(char[] arr, int left, int right) {
        char temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}