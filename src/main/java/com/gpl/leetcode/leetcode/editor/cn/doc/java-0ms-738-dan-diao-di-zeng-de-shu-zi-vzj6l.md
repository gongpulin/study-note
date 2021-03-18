 [738. 单调递增的数字](https://pic.leetcode-cn.com/1607979706-nwBdtB-image.png)

如果右边有数字小于当前位数，那么当前位-1，右边全部置9。否则按照位数向右递归。

```java
    public int monotoneIncreasingDigits(int N) {
        if (N < 10) return N;
        int pow = (int) Math.log10(N); // N 的位数
        int pow10 = (int) (Math.pow(10, pow)); // 比N小的最大 10^x
        int n = N / pow10; // N 的首位
        int threshold = n; // 继续递归的阈值
        for (int i = 0; i < pow; i++) threshold = threshold * 10 + n;
        // 如果小于阈值，返回 x999999..
        return N < threshold ? n * pow10 - 1 : 
                n * pow10 + monotoneIncreasingDigits(N - n * pow10);
    }
```