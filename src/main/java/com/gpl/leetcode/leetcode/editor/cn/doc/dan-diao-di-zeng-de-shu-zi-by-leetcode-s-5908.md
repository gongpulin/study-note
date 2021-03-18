#### 方法一：贪心

我们可以从高到低按位构造这个小于等于 *N* 的最大单调递增的数字。假设不考虑 *N* 的限制，那么对于一个长度为 *n* 的数字，最大单调递增的数字一定是每一位都为 *9* 的数字。

记 ![\textit{strN}\[i\] ](./p__textit{strN}_i__.png)  表示数字 *N* 从高到低的第 *i* 位的数字（*i* 从 *0* 开始）。

如果整个数字 *N* 本身已经是按位单调递增的，那么最大的数字即为 *N*。

如果找到第一个位置 *i* 使得 *[0,i-1]* 的数位单调递增且 ![\textit{strN}\[i-1\]>\textit{strN}\[i\] ](./p__textit{strN}_i-1__textit{strN}_i__.png) ，此时 *[0,i]* 的数位都与 *N* 的对应数位相等，仍然被 *N* 限制着，**即我们不能随意填写 *[i+1,n-1]* 位置上的数字**。为了得到最大的数字，我们需要解除 *N* 的限制，来让剩余的低位全部变成 *9* ，即能得到小于 *N* 的最大整数。而从贪心的角度考虑，我们需要尽量让高位与 *N* 的对应数位相等，故尝试让 ![\textit{strN}\[i-1\] ](./p__textit{strN}_i-1__.png)  自身数位减 *1*。此时已经不再受 *N* 的限制，直接将 *[i, n-1]* 的位置上的数全部变为 *9* 即可。

但这里存在一个问题：当 ![\textit{strN}\[i-1\] ](./p__textit{strN}_i-1__.png)  自身数位减 *1* 后可能会使得 ![\textit{strN}\[i-1\] ](./p__textit{strN}_i-1__.png)  和 ![\textit{strN}\[i-2\] ](./p__textit{strN}_i-2__.png)  不再满足递增的关系，因此我们需要从 *i-1* 开始递减比较相邻数位的关系，直到找到第一个位置 *j* 使得 ![\textit{strN}\[j\] ](./p__textit{strN}_j__.png)  自身数位减 *1* 后 ![\textit{strN}\[j-1\] ](./p__textit{strN}_j-1__.png)  和 ![\textit{strN}\[j\] ](./p__textit{strN}_j__.png)  仍然保持递增关系，或者位置 *j* 已经到最左边（即 *j* 的值为 *0*），此时我们将 *[j+1,n-1]* 的数全部变为 *9* 才能得到最终正确的答案。

代码如下。

```C++ [sol1-C++]
class Solution {
public:
    int monotoneIncreasingDigits(int N) {
        string strN = to_string(N);
        int i = 1;
        while (i < strN.length() && strN[i - 1] <= strN[i]) {
            i += 1;
        }
        if (i < strN.length()) {
            while (i > 0 && strN[i - 1] > strN[i]) {
                strN[i - 1] -= 1;
                i -= 1;
            }
            for (i += 1; i < strN.length(); ++i) {
                strN[i] = '9';
            }
        }
        return stoi(strN);
    }
};
```

```Java [sol1-Java]
class Solution {
    public int monotoneIncreasingDigits(int N) {
        char[] strN = Integer.toString(N).toCharArray();
        int i = 1;
        while (i < strN.length && strN[i - 1] <= strN[i]) {
            i += 1;
        }
        if (i < strN.length) {
            while (i > 0 && strN[i - 1] > strN[i]) {
                strN[i - 1] -= 1;
                i -= 1;
            }
            for (i += 1; i < strN.length; ++i) {
                strN[i] = '9';
            }
        }
        return Integer.parseInt(new String(strN));
    }
}
```

```JavaScript [sol1-JavaScript]
var monotoneIncreasingDigits = function(N) {
    const strN = N.toString().split('').map(v => +v);
    let i = 1;
    while (i < strN.length && strN[i - 1] <= strN[i]) {
        i += 1;
    }
    if (i < strN.length) {
        while (i > 0 && strN[i - 1] > strN[i]) {
            strN[i - 1] -= 1;
            i -= 1;
        }
        for (i += 1; i < strN.length; ++i) {
            strN[i] = 9;
        }
    }
    return parseInt(strN.join(''));
};
```

```Golang [sol1-Golang]
func monotoneIncreasingDigits(n int) int {
    s := []byte(strconv.Itoa(n))
    i := 1
    for i < len(s) && s[i] >= s[i-1] {
        i++
    }
    if i < len(s) {
        for i > 0 && s[i] < s[i-1] {
            s[i-1]--
            i--
        }
        for i++; i < len(s); i++ {
            s[i] = '9'
        }
    }
    ans, _ := strconv.Atoi(string(s))
    return ans
}
```

```C [sol1-C]
void itoa(int num, char* str, int* strSize) {
    *strSize = 0;
    while (num > 0) {
        str[(*strSize)++] = num % 10 + '0';
        num /= 10;
    }
    for (int i = 0; i < (*strSize) / 2; i++) {
        int tmp = str[i];
        str[i] = str[(*strSize) - 1 - i];
        str[(*strSize) - 1 - i] = tmp;
    }
}

int monotoneIncreasingDigits(int N) {
    int strNSize;
    char strN[11];
    itoa(N, strN, &strNSize);
    int i = 1;
    while (i < strNSize && strN[i - 1] <= strN[i]) {
        i += 1;
    }
    if (i < strNSize) {
        while (i > 0 && strN[i - 1] > strN[i]) {
            strN[i - 1] -= 1;
            i -= 1;
        }
        for (i += 1; i < strNSize; ++i) {
            strN[i] = '9';
        }
    }
    return atoi(strN);
}
```

**复杂度分析**

- 时间复杂度：![O(\logN) ](./p__O_log_N__.png) ，其中 ![O(\logN) ](./p__O_log_N__.png)  表示数字 *N* 的位数。我们遍历 ![O(\logN) ](./p__O_log_N__.png)  的时间即能构造出满足条件的数字。

- 空间复杂度：![O(\logN) ](./p__O_log_N__.png) 。我们需要 ![O(\logN) ](./p__O_log_N__.png)  的空间存放数字 *N* 每一位的数字大小。