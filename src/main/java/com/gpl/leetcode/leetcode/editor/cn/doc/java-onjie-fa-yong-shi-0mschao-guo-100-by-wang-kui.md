### 解题思路

先计算area的平方根，如果可以除尽则直接返回；
从其平方根开始向1开始遍历是否可以被area除尽，可以则直接返回这两个数；

说明：如果从平方根往上取复杂度就是O(n)了，往下取最多√n次，复杂度为O(√n)。

### 代码

```java
class Solution {
    public int[] constructRectangle(int area) {
        int sqrt = (int)Math.sqrt(area);
        if(sqrt * sqrt == area){
            return new int[]{sqrt, sqrt};
        }
        for(int i = sqrt; i >= 1; i --){
            if(area % i == 0){
                return new int[]{area / i, i};
            }
        }
        return null;
    }
}
```