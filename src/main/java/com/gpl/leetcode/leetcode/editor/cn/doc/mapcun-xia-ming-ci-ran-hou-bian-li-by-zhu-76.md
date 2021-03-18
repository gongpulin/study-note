### 解题思路
1.排序，并且hashmap存下名次
2.遍历赋值
### 代码

```java
class Solution {
      public String[] findRelativeRanks(int[] nums) {
        int len = nums.length;
        int[] temp = nums.clone();
        Arrays.sort(temp);      
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < len; i++) {
            map.put(temp[i], i);
        }
        String[] res = new String[len];
        for (int i = 0; i < len; i++) {
            int index = map.get(nums[i]);
            if (index == len - 1) {
                res[i] = "Gold Medal";
            } else if (index == len - 2) {
                res[i] = "Silver Medal";
            } else if (index == len - 3) {
                res[i] = "Bronze Medal";
            } else {
                res[i] = String.valueOf(len-index);
            }
        }
        return res;
    }
}
```