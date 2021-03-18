JAVA操作字符串基于char速度比较快，频繁replace或者subString效率都是很低的，所以转为char数组比较好处理，因为不确定第一组是K还是小于K，所以需要遍历，在遍历过程中忽略-，转换大小写，同时填充到结果数组里即可，代码如下（注意结果数组长度预留好，就不需要扩容了，以及边界条件判断），最后截取结果数组即可
```
class Solution {
    public String licenseKeyFormatting(String S, int K) {
        char[] chars = S.toCharArray();
        char[] result = new char[chars.length+S.length()/K];
        int length = 0;
        int i = chars.length - 1, j = result.length - 1;
        for (; i >= 0;) {
            if (chars[i] == '-') {
                i--;
                continue;
            }
            if (chars[i] >= 'a' && chars[i] <= 'z') {
                chars[i] = (char) (chars[i] -32);
            }
            if (length != K) {
                result[j] = chars[i];
                length++;
                if (i == 0) {
                    j--;
                    break;
                }else{
                    j--;
                    i--;
                }
            }else{
                length = 1;
                result[j] = '-';
                result[j - 1] = chars[i];
                j -= 2;
                i--;

            }
        }

        return new String(result, j+1, result.length - j-1);
    }
}
```
10:11	info
			解答成功:
			执行耗时:6 ms,击败了98.97% 的Java用户
			内存消耗:39.6 MB,击败了12.50% 的Java用户