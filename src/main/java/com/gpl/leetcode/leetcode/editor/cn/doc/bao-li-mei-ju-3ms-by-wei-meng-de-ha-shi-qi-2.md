### 解题思路
此处撰写解题思路

### 代码

```java
class Solution {
    // 暴力枚举
    // 前两个for循环代表着 s t的初始节点
    // k代表从初始节点选多少个单词进行对比
    // 直观, 好理解
    public int countSubstrings(String s, String t) {
        char s_arr[] = s.toCharArray();
        char t_arr[] = t.toCharArray();
        int ans = 0;
        for (int i = 0; i < s_arr.length; i++) {
            for (int j = 0; j < t_arr.length; j++) {
                int count = 0; // 固定ij两个头,开始计数不同的字符
                for (int k = 0; i + k < s_arr.length && j + k < t_arr.length; k++) {
                    count += (s_arr[i+k] != t_arr[j+k]) ? 1 : 0;
                    if (count > 1) {
                        break;
                    }
                    if (count == 1) {
                        ++ans;
                    }
                }
            }
        }
        return ans;
    }
}
```