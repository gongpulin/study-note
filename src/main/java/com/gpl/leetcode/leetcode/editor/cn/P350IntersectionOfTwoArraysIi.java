//给定两个数组，编写一个函数来计算它们的交集。 
//
// 
//
// 示例 1： 
//
// 输入：nums1 = [1,2,2,1], nums2 = [2,2]
//输出：[2,2]
// 
//
// 示例 2: 
//
// 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出：[4,9] 
//
// 
//
// 说明： 
//
// 
// 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。 
// 我们可以不考虑输出结果的顺序。 
// 
//
// 进阶： 
//
// 
// 如果给定的数组已经排好序呢？你将如何优化你的算法？ 
// 如果 nums1 的大小比 nums2 小很多，哪种方法更优？ 
// 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？ 
// 
// Related Topics 排序 哈希表 双指针 二分查找


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.*;

//Java：两个数组的交集 II
public class P350IntersectionOfTwoArraysIi{
    public static void main(String[] args) {
        Solution solution = new P350IntersectionOfTwoArraysIi().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public int[] intersect(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int len1 = nums1.length, len2 = nums2.length;
            int index1 = 0, index2 = 0, index = 0;
            int[] intersect = new int[Math.min(len1, len2)];
            while (index1 < len1 && index2 < len2) {
                if (nums1[index1] > nums2[index2]) {
                    index2++;
                } else if (nums1[index1] < nums2[index2]) {
                    index1++;
                } else {
                    intersect[index++] = nums1[index1];
                    index1++;
                    index2++;
                }
            }
            return Arrays.copyOfRange(intersect, 0, index);
        }
    public int[] intersect2(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect2(nums2, nums1);
        }
        Map<Integer, Integer> map = new HashMap<>();

        for (int num1 : nums1) {
            map.put(num1, map.getOrDefault(num1, 0) + 1);
        }
        //        List<Integer> intersectList = new ArrayList<Integer>();
        int[] intersect = new int[nums2.length];
        int index = 0;
        for (int num2 : nums2) {
            if (map.containsKey(num2) && map.get(num2) > 0) {
                intersect[index++] = num2;
//                intersectList.add(num2);
                map.put(num2, map.get(num2) - 1);
            }
        }
//        int[] ans = new int[intersectList.size()];
//        int index = 0;
//        for (int num : intersectList) {
//            ans[index++] = num;
//        }
//        return ans;
        return Arrays.copyOfRange(intersect, 0, index);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}