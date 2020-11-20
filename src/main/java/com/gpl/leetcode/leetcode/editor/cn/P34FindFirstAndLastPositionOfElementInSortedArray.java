//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。 
//
// 你的算法时间复杂度必须是 O(log n) 级别。 
//
// 如果数组中不存在目标值，返回 [-1, -1]。 
//
// 示例 1: 
//
// 输入: nums = [5,7,7,8,8,10], target = 8
//输出: [3,4] 
//
// 示例 2: 
//
// 输入: nums = [5,7,7,8,8,10], target = 6
//输出: [-1,-1] 
// Related Topics 数组 二分查找


package com.gpl.leetcode.leetcode.editor.cn;
//Java：在排序数组中查找元素的第一个和最后一个位置
public class P34FindFirstAndLastPositionOfElementInSortedArray{
    public static void main(String[] args) {
        Solution solution = new P34FindFirstAndLastPositionOfElementInSortedArray().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public int[] searchRange(int[] nums, int target) {
            int[] ans = {-1, -1};
            if (nums == null || nums.length == 0) {
                return ans;
            }
            int leftBound = leftBound(nums, target);
            if (leftBound == -1) {
                return ans;
            }
            int rightBound = rightBound(nums, target);
            ans[0] = leftBound;
            ans[1] = rightBound;
            return ans;
        }

        /**
         * left取值范围 [0,nums.length]
         * right取值范围[-1, nums.length - 1]
         */
        private int leftBound(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    right = mid - 1;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                }
            }
            if (left == nums.length || nums[left] != target) {
                return -1;
            }
            return left;
        }
        private int rightBound(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    left = mid + 1;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                }
            }
            if (right < 0 || nums[left - 1] != target) {
                return -1;
            }
            return left - 1;
        }


        private int leftBound1(int[] nums, int target) {
            int left = 0, right = nums.length;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    right = mid;
                } else if (nums[mid] > target) {
                    right = mid;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                }
            }
            if (left == nums.length || nums[left] != target) {
                return -1;
            }
            return left;
        }


        private int rightBound1(int[] nums, int target) {
            int left = 0, right = nums.length;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    left = mid + 1;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    right = mid;
                }
            }
            if (right < 0 || nums[left - 1] != target) { //注意这里结束条件是left == right是nums[right - 1] != target
                return -1;
            }
            return left - 1;
        }


        /**
         * 先求出一个target下标，然后左右线性遍历
         */
        public int[] searchRange1(int[] nums, int target) {
        int[] ans = {-1, -1};
        if(nums == null) {
            return ans;
        }
        int index = binarySearch(nums, target);
        if(index == -1) {
            return ans;
        }
        int left = index, right = index;
        while(left >= 0 && nums[left] == target) {
            left--;
        }
        while(right <= nums.length - 1 && nums[right] == target) {
            right++;
        }
        ans[0] = left+1;
        ans[1] = right-1;
        return ans;
    }
    private int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                return mid;
            } else if(target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}