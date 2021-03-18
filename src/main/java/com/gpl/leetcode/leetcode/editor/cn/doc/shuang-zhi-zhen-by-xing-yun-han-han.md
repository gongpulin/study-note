### 解题思路
普通的双指针解法

### 代码

```java
class Solution {
    public int findContentChildren(int[] g, int[] s) {
     int g0 = 0;
     int s0 = 0;
  //先对两个数组排序，g是胃口大小数组，s是饼干大小数组
     Arrays.sort(g);
     Arrays.sort(s);
     if( g == null|| s == null) return 0;
  //这里循环的条件是两个指针不达到两个数组的长度，也就是没有元素移动了
     while(g0<g.length && s0<s.length){
         if(g[g0]<=s[s0]){
  //当胃口比饼干小，那么匹配成功，分给小孩
             g0++;
         }
         s0++;
     }
     return g0;
    }
}
```