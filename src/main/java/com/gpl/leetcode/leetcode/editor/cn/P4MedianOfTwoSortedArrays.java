//给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。 
//
// 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。 
//
// 你可以假设 nums1 和 nums2 不会同时为空。 
//
// 
//
// 示例 1: 
//
// nums1 = [1, 3]
//nums2 = [2]
//
//则中位数是 2.0
// 
//
// 示例 2: 
//
// nums1 = [1, 2]
//nums2 = [3, 4]
//
//则中位数是 (2 + 3)/2 = 2.5
// 
// Related Topics 数组 二分查找 分治算法


package com.gpl.leetcode.leetcode.editor.cn;
//Java：寻找两个正序数组的中位数
public class P4MedianOfTwoSortedArrays{
    public static void main(String[] args) {
        Solution solution = new P4MedianOfTwoSortedArrays().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        int length1 = nums1.length;
//        int length2 = nums2.length;
//        int totalLength = length1 + length2;
//        if (totalLength % 2 == 1) {
//            int midIndex = totalLength / 2;
//            double median = getKthElement(nums1, nums2, midIndex + 1);
//            return median;
//        } else {
//            int medIndex1 = totalLength / 2 - 1;
//            int medIndex2 = totalLength / 2;
//            double median = (getKthElement(nums1, nums2, medIndex1 + 1) + getKthElement(nums1, nums2, medIndex2 + 1)) / 2.0;
//            return median;
//        }
//    }
//    private double getKthElement(int[] nums1, int[] nums2, int k) {
//        int lenght1 = nums1.length, length2 = nums2.length;
//        int index1 = 0, index2 = 0;
//        int kthElement = 0;
//        while (true) {
//            if (index1 == lenght1) {
//                return nums2[index2 + k - 1];
//            }
//            if (index2 == length2) {
//                return nums1[index1 + k - 1];
//            }
//            if (k == 1) {
//                return Math.min(nums1[index1], nums2[index2]);
//            }
//
//            int half = k / 2;
//            int newIndex1 = Math.min(index1 + half, lenght1) - 1;
//            int newIndex2 = Math.min(index2 + half, length2) - 1;
//            int povit1 = nums1[newIndex1], povit2 = nums2[newIndex2];
//            if (povit1 < povit2) {
//                k -= (newIndex1 - index1) + 1;
//                index1 = newIndex1 + 1;
//            } else {
//                k -= (newIndex2 - index2) + 1;
//                index2 = newIndex2 + 1;
//            }
//
//        }
//    }



        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if (nums1.length > nums2.length) {
                return findMedianSortedArrays(nums2, nums1);
            }
            int m = nums1.length;
            int n = nums2.length;
            int left = 0, right = m, ansi = -1;
            // median1：前一部分的最大值
            // median2：后一部分的最小值
            int median1 = 0, median2 = 0;

            while (left <= right) {
                // 前一部分包含 nums1[0 .. i-1] 和 nums2[0 .. j-1]
                // 后一部分包含 nums1[i .. m-1] 和 nums2[j .. n-1]
                int i = (left + right) / 2;
                int j = (m + n + 1) / 2 - i;

                // nums_im1, nums_i, nums_jm1, nums_j 分别表示 nums1[i-1], nums1[i], nums2[j-1], nums2[j]
                int nums_im1 = (i == 0 ? Integer.MIN_VALUE : nums1[i - 1]);
                int nums_i = (i == m ? Integer.MAX_VALUE : nums1[i]);
                int nums_jm1 = (j == 0 ? Integer.MIN_VALUE : nums2[j - 1]);
                int nums_j = (j == n ? Integer.MAX_VALUE : nums2[j]);

                if (nums_im1 <= nums_j) {
                    ansi = i;
                    median1 = Math.max(nums_im1, nums_jm1);
                    median2 = Math.min(nums_i, nums_j);
                    left = i + 1;
                }
                else {
                    right = i - 1;
                }
            }

            return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
        }
}
//leetcode submit region end(Prohibit modification and deletion)

}