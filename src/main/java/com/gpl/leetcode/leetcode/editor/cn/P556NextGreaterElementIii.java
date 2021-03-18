//给定一个32位正整数 n，你需要找到最小的32位整数，其与 n 中存在的位数完全相同，并且其值大于n。如果不存在这样的32位整数，则返回-1。 
//
// 示例 1: 
//
// 
//输入: 12
//输出: 21
// 
//
// 示例 2: 
//
// 
//输入: 21
//输出: -1
// 
// Related Topics 字符串


package com.gpl.leetcode.leetcode.editor.cn;
//Java：下一个更大元素 III
public class P556NextGreaterElementIii{
    public static void main(String[] args) {
        Solution solution = new P556NextGreaterElementIii().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int nextGreaterElement(int n) {
        String s = n + "";
        char[] arr = s.toCharArray();
        int len = s.length();
        int i = len - 2;
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }
        if (i < 0) {
            return -1;
        }
        int j = len - 1;
        while (j >= 0 && arr[j] <= arr[i]) {
            j--;
        }
        swap(arr, i, j);
        reverse(arr, i + 1, len - 1);
        try {
            return Integer.parseInt(new String(arr));
        } catch (Exception e) {
            return -1;
        }
    }
    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    private void reverse(char[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start, end);
            start++;
            end--;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}