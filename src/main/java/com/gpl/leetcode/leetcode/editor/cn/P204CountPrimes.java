//统计所有小于非负整数 n 的质数的数量。 
//
// 示例: 
//
// 输入: 10
//输出: 4
//解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
// 
// Related Topics 哈希表 数学


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.Arrays;

//Java：计数质数
public class P204CountPrimes{
    public static void main(String[] args) {
        Solution solution = new P204CountPrimes().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public int countPrimes(int n) {
            boolean[] isPrim = new boolean[n];
            Arrays.fill(isPrim, true);
            for (int i = 2; i * i < n; i++) {
                if (isPrim[i]) {
                    for (int j = i * i; j < n; j += i) {
                        isPrim[j] = false;
                    }
                }
            }
            int ans = 0;
            for (int i = 2; i < n; i++) {
                if (isPrim[i]) {
                    ans++;
                }
            }
            return ans;
        }
    public int countPrimes1(int n) {
        int ans = 0;
        for (int i = 2; i < n; i++) {
            if (isPrimes1(i)) {
                ans++;
            }
        }
        return ans;
    }
    private boolean isPrimes1(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}