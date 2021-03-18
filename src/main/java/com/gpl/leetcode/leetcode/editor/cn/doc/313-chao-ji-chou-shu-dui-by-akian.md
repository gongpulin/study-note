# 313. 超级丑数-堆


**简单介绍：**
题目：超级丑数
题目难度：中等
使用语言：JAVA
这道题来自leetcode题库的堆标签。



**解题思路：**
**主要解法：动态规划。**
 [image.png](https://pic.leetcode-cn.com/9a4536684e94be81b9daf4cda57a3274c345359bc2f06da075e694f1f56d00d4-image.png)

**-使用dp来保存我们的丑数序列。
-每个index元素对应一个primes元素。index滑动选取已有dp元素，dp[index[j]] * primes[j]作为备选丑数。
-将备选丑数与当前min比较，取小。完成一次内循环，选出一个丑数，并更新相应位置的index。**



**数据结构：**
要实现对数据的操作，我们要先明确存储数据的数据结构。
该题的数据结构的作用：
**1.整型数组保存丑数序列，索引序列。**


**算法：**
既然明确了我们的数据结构，我们就可以开始我们的算法分析了。
**1.第一步，初始化工作，构建整数数组。
2.第二步，循环n-1次，获取丑数序列。
	选取最小元素为丑数，并更新索引。
3.第三步，返回丑数结果**






**代码部分：**

```java

public class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int [] dp=new int[n];
        dp[0]=1;

        int k=primes.length;
        int []index=new int[k];



        for(int i=1;i<n;i++){
            int min=Integer.MAX_VALUE;
            for(int j=0;j<k;j++){
                if(min>dp[index[j]]*primes[j]){
                    min=dp[index[j]]*primes[j];
                }
            }
            dp[i]=min;
            //滑动index
            for(int j=0;j<k;j++){
                if(min==dp[index[j]]*primes[j]){
                    index[j]++;
                }
            }
        }
        return dp[n-1];
    }
}



```

**时间、空间复杂度：**


 [image.png](https://pic.leetcode-cn.com/6f91a723d63c233a900579510d16298b38e6fe474e2804bab03cbae9d1364cf1-image.png)






**结语：**
晚安！晚安！晚安！晚安！晚安！晚安！晚安！晚安！晚安！晚安！晚安！
