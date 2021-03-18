**解法一：效率高，运行速度超过100%**
思路：通过位运算获取最大位数的位置，然后利用抑或运算取反
```
class Solution {
    public int findComplement(int num) {
        int maxBitNum = 0;
        int tmpNum = num;
        while (tmpNum > 0) {
            maxBitNum += 1;
            tmpNum >>= 1;
        }
        return num ^ ((1 << maxBitNum) - 1);
    }
}
```

**解法二：效率低，但容易理解**
思路：转成字符串后通过抑或位运算取反，再转回int
```
class Solution {
     public int findComplement(int num) {
        String complementStr = "";
        String binaryStr = Integer.toBinaryString(num);
        for (int i = 0; i < binaryStr.length(); i++) {
            complementStr += binaryStr.charAt(i) ^ '1';
        }
        return Integer.parseInt(complementStr, 2);
    }
}
```


