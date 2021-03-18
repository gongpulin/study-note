[【LeetCode】0395.至少有K个重复字符的最长子串](https://blog.csdn.net/xt199711/article/details/111502631)
[GitHub：FFIDEAL_LeetCode_Note](https://github.com/ffideal/FFIDEAL_LeetCode_Note)
# 题目要求
**本题共有两个要求**
1. 符合要求的字符串中每一个字符出现的次数都要大于等于给与的数字K
2. 最终返回结果的是符合要求的字符串中最长的字符串长度

# 算法思想
**hash_map + 递归 + 分治**

1. 使用数组 char_map 记录每个字符在字符串中出现的次数（目的是，在遍历字符串 s 的过程中，字符出现次数 N < K，就是分治的锚点）
2. 遍历字符串 s ，若字符 s[i] 在 char_map 中记录次数大于等于 K，则将 s[i] 添加到当前子串 word 中，并且，记录现在子串 word 的长度
3. 但是在遍历的过程中，字符 s[i] 小于 K ，就要分析前面的子串 word 中每次都出现的字符是否符号大于等于 K 这一条件
4. 首先，在某个字符处断，之前的子串 word 作为一个独立的子串，其中的字符需要从字符串 s 中减去
5. 之后子串 word 作为一个独立的字符串，就要使用递归，对 word 中字符做判断，返回符合要求的子串长度
6. 此时，就不需要子串 word ，字符串 s 继续遍历，word 要用来储存之后的子串，所以在完成递归后需要将子串重置
7. 按要求2，需要获得最长的符合要求的子串长度，所以，得到的子串长度与保存的之前最长字符串长度比较，若新得到的子串长度大于老的子串长度，就更新最长子串长度，同时 tmp 是遇到断点前记录的长度，不适用于新的子串了，所以要重置 tmp
8. 如果至字符串 s 末尾，还有一个字符串都符合条件，但没有断点了也就不需要进入17行else中判断那个子串更长了，所以需要在for循环外面创建创建比较
9. 至此完成算法设计

# 图解示例
 [0395.png](https://pic.leetcode-cn.com/1608566681-NhJUDu-0395.png)

# 完整代码
```c++
class Solution {
public:
    int longestSubstring(string s, int k) {
    	if(s.size() < k) return 0;
		int ans = 0;
		int tmp = 0;
		// 记录有多少字符数小于k的 
		int char_map[128] = {0};
		string word = "";
		for(int i = 0; i < s.size(); i++) {
			char_map[s[i]]++;
		}
		for(int i = 0; i < s.size(); i++) {
			if(char_map[s[i]]>=k) {
				word += s[i];
				tmp++;
			} else if(char_map[s[i]]>=k){
				for(int j = 0; j < word.size(); j++) {
					char_map[word[j]]--;
				}
				tmp = longestSubstring(word, k);
				word = "";
				if(ans<tmp) {
					ans = tmp;
				}
				tmp = 0;
			}
			
		}
		if(ans<tmp) {
			ans = tmp;
		}
		return ans;
    }
};

```

# 提交结果
 [【0395】提交结果.png](https://pic.leetcode-cn.com/1608566732-PxMxmf-%E3%80%900395%E3%80%91%E6%8F%90%E4%BA%A4%E7%BB%93%E6%9E%9C.png)
