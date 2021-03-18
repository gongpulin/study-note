## 思路
### 中心思想：获取整数的32位二进制表示，然后从左到右每4位转成一位16进制，然后去掉前导零，注意0特殊处理。

### 代码

```java
class Solution {
    public String toHex(int num) {
        if (num == 0) { return "0"; }   // 0特殊处理
         // 获取32位二进制位
        int[] bits = new int[32];      
        for (int i = 31; i >= 0; i--) { 
            bits[i] = num & 0x1;
            num >>>= 1;
        }
        // 从左到右每4位转成1位十六进制
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i <= 28; i += 4) {
            int sum = 8 * bits[i] + 4 * bits[i + 1] +
                      2 * bits[i + 2] + 1 * bits[i + 3];
            if (sum <= 9) {
                ans.append((char)(sum + '0'));
            } else if (sum <= 15) {
                ans.append((char)(sum - 10 + 'a'));
            } 
        }
        // 去掉前导0，这就是为什么0要特殊处理的原因
        // 因为0的十六进制还是全为0，用这个循环会越界访问
        while (ans.charAt(0) == '0') {
            ans.deleteCharAt(0);
        }
        return ans.toString();
        
    }
}
```

## 改进版

```java
class Solution {
    public String toHex(int num) {
        if (num == 0) { return "0"; }   // 0特殊处理
        char[] hex = "0123456789abcdef".toCharArray();  // 相当于映射关系
        StringBuilder ans = new StringBuilder();
        while (num != 0) {
            int temp = num & 0xf;   // 取低4位的十进制值
            ans.append(hex[temp]);  // 映射对应字符
            num >>>= 4;             // 逻辑右移4位
        }
        // while的循环条件保证了不会出现前导0
        // 但是从低位开始转换多了一步reverse反转
        return ans.reverse().toString();
    }
}
```
 [image.png](https://pic.leetcode-cn.com/1600862628-pdcYfZ-image.png)

### 注意使用的是逻辑右移
### 如果本文对你有帮助，可以给个大拇指呀！
### 如果你有什么建议或疑问，可以评论留言呀！