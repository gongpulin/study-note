### 解题思路
将所有数转换的成二进制，现在我们先看第0位，求任意两个数第0位的汉明距离的总和。我们从左往右遍历数组，当遍历到第i个数nums[i]时，nums[i]第0位能贡献的汉明距离等于i左边所有数的第0位与nums[i]的第0位不同的数个数，本来还需要加上右边的数，但为了避免重复计算，我们只看左边的数，所以我们可以一边遍历一边统计出现的0和1的个数。这样当我们遍历完数组，就可以得到所有数的第0位能贡献的汉明距离。而题目的答案就是求出所有位能贡献的汉明距离

### 代码

```java
class Solution {
    public int totalHammingDistance(int[] nums) {
        int ans = 0;
        int len = nums.length;
        for(int i = 0; i < 32; i++){
            int total_0 = 0;//0的个数
            int total_1 = 0;//1的个数
            for(int j = 0; j < len; j++){
                if((nums[j] & 1) == 0){
                    ans += total_1;//当前数的对应位为0，所以加上左边1的个数
                    total_0++;
                }else{
                    ans += total_0;//当前数的对应位为1，所以加上左边0的个数
                    total_1++;
                }
                nums[j] >>>= 1;
            }
        }
        return ans;
    }
}
```