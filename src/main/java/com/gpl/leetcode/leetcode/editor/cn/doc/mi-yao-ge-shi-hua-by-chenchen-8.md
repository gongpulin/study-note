### 解题思路
1. 如果length<K,则直接返回过滤的string
2. 根据length%K，来判断第一项的长度
3. 每K个正则匹配除了第一项的部分
4. 最后，连接第一项和其他项，并用"-"连接

### 代码

```javascript
/**
 * @param {string} S
 * @param {number} K
 * @return {string}
 */
var licenseKeyFormatting = function(S, K) {
    S = S.toLocaleUpperCase().replace(/[-]/g, "");
    let l = S.length;
    if (l < K) {
        return S;
    }
    let mod = l % K;
    let reg = new RegExp(`[\\w]{${K}}`, "g");
    let first = "", rest;
    if (mod == 0) {
        rest = S;
    } else {
        first = S.slice(0, mod)
        rest = S.slice(mod);
    }
    let result = rest.match(reg);
    if (first) {
        result.unshift(first);
    }
    return result.join("-");
};
```