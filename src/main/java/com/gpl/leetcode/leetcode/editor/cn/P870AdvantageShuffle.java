//给定两个大小相等的数组 A 和 B，A 相对于 B 的优势可以用满足 A[i] > B[i] 的索引 i 的数目来描述。
//
// 返回 A 的任意排列，使其相对于 B 的优势最大化。
//
//
//
// 示例 1：
//
// 输入：A = [2,7,11,15], B = [1,10,4,11]
//输出：[2,11,7,15]
//
//
// 示例 2：
//
// 输入：A = [12,24,8,32], B = [13,25,32,11]
//输出：[24,32,8,12]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length = B.length <= 10000 
// 0 <= A[i] <= 10^9 
// 0 <= B[i] <= 10^9 
// 
// Related Topics 贪心算法 数组


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Java：优势洗牌
public class P870AdvantageShuffle{
    public static void main(String[] args) {
        Solution solution = new P870AdvantageShuffle().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        public int[] advantageCount(int[] A, int[] B) {
            //找到大于B的A的最小值，如果不存在，则返回A的最小值
            Arrays.sort(A);
            List<Integer> input = new ArrayList<>();
            for(Integer tmp : A){
                input.add(tmp);
            }
            int[] res = new int[A.length];
            for(int i=0; i<B.length; ++i){
                int index = findMinMax(input, B[i]);
                res[i] = input.get(index);
                input.remove(index);
            }
            return res;
        }

        private int findMinMax(List<Integer> input, int target){
            //二分法查找大于target的最小值
            int left = 0;
            int right = input.size()-1;
            if(input.get(left) > target){
                return left;
            }
            if(input.get(right) <= target) {
                return left;
            }
            while(left < right) {
                int mid = left + (right-left) / 2;
                if(input.get(mid) <= target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return right;
        }




        //错误答案测试用例:[9,1,2,4,5]
        //			     [6,2,9,1,4]
    public int[] advantageCount1(int[] A, int[] B) {
        int len = A.length;
        for (int i = 0; i < len - 1; i++) {
            int minIndex = i;
            int min = Integer.MAX_VALUE;
            for (int j = i; j < len; j++) {
                if (A[j] > B[i] && A[j] < min) {
                    minIndex = j;
                    min = A[j];
                }
            }
            swap(A, minIndex, i);
        }
        return A;
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}