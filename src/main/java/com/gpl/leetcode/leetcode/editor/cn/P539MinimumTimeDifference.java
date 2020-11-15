//给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。 
//
// 
//
// 示例 1： 
//
// 
//输入：timePoints = ["23:59","00:00"]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：timePoints = ["00:00","23:59","00:00"]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 2 <= timePoints <= 2 * 104 
// timePoints[i] 格式为 "HH:MM" 
// 
// Related Topics 字符串


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Java：最小时间差
public class P539MinimumTimeDifference{
    public static void main(String[] args) {
        Solution solution = new P539MinimumTimeDifference().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findMinDifference(List<String> timePoints) {
        int len = timePoints.size();
        int[] minutes = new int[len];
        for (int i = 0; i < len; i++) {
            minutes[i] = getMinute(timePoints.get(i));
        }
        Arrays.sort(minutes);
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < len; i++) {
            ans = Math.min(ans, minutes[i] - minutes[i - 1]);
            if (ans == 0) {
                return 0;
            }
        }
        ans = Math.min(ans, minutes[0] + 1440 - minutes[len - 1]);
        return ans;
    }
    private int getMinute(String s) {
        String[] arr = s.split(":");
        return Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}