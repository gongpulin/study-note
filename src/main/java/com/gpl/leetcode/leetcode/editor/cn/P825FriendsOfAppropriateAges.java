//人们会互相发送好友请求，现在给定一个包含有他们年龄的数组，ages[i] 表示第 i 个人的年龄。 
//
// 当满足以下任一条件时，A 不能给 B（A、B不为同一人）发送好友请求： 
//
// 
// age[B] <= 0.5 * age[A] + 7 
// age[B] > age[A] 
// age[B] > 100 && age[A] < 100 
// 
//
// 否则，A 可以给 B 发送好友请求。 
//
// 注意如果 A 向 B 发出了请求，不等于 B 也一定会向 A 发出请求。而且，人们不会给自己发送好友请求。 
//
// 求总共会发出多少份好友请求? 
//
// 
//
// 示例 1： 
//
// 输入：[16,16]
//输出：2
//解释：二人可以互发好友申请。
// 
//
// 示例 2： 
//
// 输入：[16,17,18]
//输出：2
//解释：好友请求可产生于 17 -> 16, 18 -> 17. 
//
// 示例 3： 
//
// 输入：[20,30,100,110,120]
//输出：3
//解释：好友请求可产生于 110 -> 100, 120 -> 110, 120 -> 100.
// 
//
// 
//
// 提示： 
//
// 
// 1 <= ages.length <= 20000. 
// 1 <= ages[i] <= 120. 
// 
// Related Topics 数组


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.Arrays;

//Java：适龄的朋友
public class P825FriendsOfAppropriateAges{
    public static void main(String[] args) {
        Solution solution = new P825FriendsOfAppropriateAges().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        // age[B] <= 0.5 * age[A] + 7
        // age[B] > age[A]
        // age[B] > 100 && age[A] < 100
    public int numFriendRequests(int[] ages) {
        if (ages == null) {
            return 0;
        }
        int[] count = new int[121];
        for (int age : ages) {
            count[age]++;
        }
        int ans = 0;
        for (int ageA = 0; ageA < 121; ageA++) {
            int countA = count[ageA];
            for (int ageB = 0; ageB < 121; ageB++) {
                int countB = count[ageB];
                if (ageA * 0.5 + 7 >= ageB) {
                    continue;
                }
                if (ageA < ageB) {
                    continue;
                }
                if (ageB > 100 && ageA < 100) {
                    continue;
                }
                ans += countA * countB;
                if (ageA == ageB) {
                    ans -= countA;
                }
            }
        }
        return ans;
    }


        //错误答案
    public int numFriendRequests1(int[] ages) {
        if (ages == null) {
            return 0;
        }
        Arrays.sort(ages);
        int len = ages.length;
        int ans = 0;
        for (int i = len - 1; i > 0; i--) {
            int A = ages[i];
            int j = i - 1;
            int B = ages[j];
            if (A == B) {
                ans++;
            }
            while (j >= 0 && ages[j] > 0.5 * A + 7) {
                j--;
                ans++;
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}