### 解题思路
1. 记录写入 字典 的字符串数组
2. 比较：
- 长度比较
- 相等比较
- 改变次数比较
(由于相等比较已经在上一步做完了，因此我们只需要在此处判断改变次数是否为1次就行)


### 运行结果
 [image.png](https://pic.leetcode-cn.com/3fba77a38106113b4d38f32ba1997985d7e83c754a44070146d7d55e637ea3d6-image.png)

### 代码

```java
class MagicDictionary {
    private List<String> dictionary
            = new ArrayList<>();

    /**
     * Initialize your data structure here.
     */
    public MagicDictionary() {

    }

    /**
     * Build a dictionary through a list of words
     */
    public void buildDict(String[] dict) {
        for (int i = 0; i < dict.length; i++) {
            if (!this.dictionary.contains(dict[i])) {
                this.dictionary.add(dict[i]);
            }
        }
    }

    /**
     * Returns if there is any word in the trie that equals to the given word after modifying exactly one character
     */
    public boolean search(String word) {
        List<String> similar = new ArrayList<>();
        int wordLen = word.length();

        for (String magic : this.dictionary) {
            if (wordLen == magic.length() && !word.equals(magic) && canBeBuilt(magic, word, wordLen)) {
                    return true;
            }
        }

        return false;
    }

    private boolean canBeBuilt(String cur, String tar, int length) {
        boolean flag = true;

        for (int i = 0; i < length; i++) {
            if (cur.charAt(i) != tar.charAt(i)) {
                if (flag) {
                    flag = false;
                } else {
                    return false;
                }
            }
        }

        return true;
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 */
```

打卡第12天，加油！！！