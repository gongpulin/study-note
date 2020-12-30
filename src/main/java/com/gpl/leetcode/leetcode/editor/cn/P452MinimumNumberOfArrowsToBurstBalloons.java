//在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以y坐标并不重要，因此只要知道开始和结束的x
//坐标就足够了。开始坐标总是小于结束坐标。平面内最多存在104个气球。 
//
// 一支弓箭可以沿着x轴从不同点完全垂直地射出。在坐标x处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足 xstart ≤
// x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量
//。 
//
// Example: 
//
// 
//输入:
//[[10,16], [2,8], [1,6], [7,12]]
//
//输出:
//2
//
//解释:
//对于该样例，我们可以在x = 6（射爆[2,8],[1,6]两个气球）和 x = 11（射爆另外两个气球）。
// 
// Related Topics 贪心算法 排序


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;

//Java：用最少数量的箭引爆气球
public class P452MinimumNumberOfArrowsToBurstBalloons{
    public static void main(String[] args) {
        Solution solution = new P452MinimumNumberOfArrowsToBurstBalloons().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public int findMinArrowShots(int[][] points) {
            if (points == null || points.length == 0) {
                return 0;
            }
//            Arrays.sort(points, (v1, v2) -> v1[1] - v2[1]);
            Arrays.sort(points, new Comparator<int[]> () {
                @Override
                public int compare(int[] o1, int[] o2) {
                    long res = (long)o1[1] - (long)o2[1];
                    if (res < 0) {
                        return -1;
                    } else if (res == 0) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            });
            int end = points[0][1];
            int ans = 1;
            for (int[] point : points) {
                if (point[0] > end) {
                    ans++;
                    end = point[1];
                }
            }
            return ans;
        }
    public int findMinArrowShots1(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
//        Arrays.sort(points, (a, b) -> a[0] - b[0]);
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                long res = (long)o1[1] - (long)o2[1];
                if (res < 0) {
                    return -1;
                } else if (res == 0) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        int len = points.length;
        int ans = len;
        int i = 0;
        while (i < len) {
            int j = i + 1;
            while (j < len) {
                if (points[i][1] >= points[j][0]) {
                    j++;
                    ans--;
                } else {
                    break;
                }
            }
            i = j;
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}