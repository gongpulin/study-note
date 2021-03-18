### 解题思路
本题解 借鉴了 **`题解区大佬`** 的思想：
> 1、删除 k 个 数字：
>>1.查找 非“递增”数对，   
>>若找到，则 删除第一个 非“递增”数对 的 第一个数字 
>> 2. 若 整个序列满足“递增”，
>> 则 删除最后一个元素
> 
> 2、若 当前字符串的 第一个字符是 '0'，且 字符串长度不为1
> 则 删除当前第一个字符
> 3、删去队首的 字符'0'
> 4、结果 转换

### 运行结果
 [image.png](https://pic.leetcode-cn.com/1605413711-YgddmO-image.png)

### 代码

```java
class Solution {
    public String removeKdigits(String num, int k) {
        char[] charArray = num.toCharArray();
        int length = num.length();

        if (k == 0) {
            return num;
        }

        if (k == length) {
            return "0";
        }

        /*
            删除 k 个 数字
         */
        while (k != 0) {
            int index= 0;
            boolean flag = true;

            /*
                查找 非“递增”数对，
                若找到，则 删除第一个 非“递增”数对 的 第一个数字
             */
            for (; index < length - 1; index++) {
                /*
                    若 当前数字 比 后面的数字 大(当前两个数 满足 “非递增”数对)
                    删除 当前数字(将 后续数组元素 前移，并将 length 减一)
                 */
                if (charArray[index] > charArray[index + 1]) {
                    for (int i = index; i < length - 1; i++) {
                        charArray[i] = charArray[i + 1];
                    }
                    flag = false;
                    length--;
                    k--;
                    break;
                }
            }

            /*
                若 整个序列满足“递增”，则 删除最后一个元素
             */
            if (flag && k != 0) {
                length--;
                k--;
            }
        }

        /*
            若 当前字符串的 第一个字符是 '0'，且 字符串长度不为1
            则 删除当前第一个字符
         */
        boolean canDelete = true;
        while (canDelete) {
            canDelete = false;
            if (charArray[0] == '0' && length != 1) {
                canDelete = true;
                for (int i = 0; i < length - 1; i++) {
                    charArray[i] = charArray[i + 1];
                }
                length--;
            }
        }

        /*
            将结果 转换为 字符串
         */
        char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            result[i] = charArray[i];
        }

        return String.valueOf(result);
    }
}
```
打卡第116天，加油！！！
虽然真的可能是一分都没有，但是还是紧张得等待着结果，
看群里说就这两天就出来了，真的紧张😫
毕竟下届没机会参加了，我太难啦😱