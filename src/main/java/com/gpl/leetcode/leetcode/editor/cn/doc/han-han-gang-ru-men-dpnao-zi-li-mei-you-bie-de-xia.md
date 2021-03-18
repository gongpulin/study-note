```java
class Solution {
    public int countSubstrings(String s, String t) {
        int slen = s.length();
        int tlen = t.length();
        // dp[i][j][0]表示s中以i结束的子串与t中以j结束的子串恰好只有一个字符不同的子字符串数目。
        // dp[i][j][1]表示s中以i结束的子串与t中以j结束的子串相同的数目。
        int[][][] dp = new int[slen][tlen][2];
        // 初始化dp[0][j][0],dp[i][0][0],dp[0][j][1],dp[i][0][1]
        for(int i = 0; i < slen; i++){
            if(s.charAt(i) != t.charAt(0)){
                dp[i][0][0]++;
            }
            else{
                dp[i][0][1]++;
            }
        }
        for(int j = 1; j < tlen; j++){
            if(s.charAt(0) != t.charAt(j)){
                dp[0][j][0]++;
            }
            else{
                dp[0][j][1]++;
            }
        }
        
        for(int i = 1; i < slen; i++){
            for(int j = 1; j < tlen; j++){
                if(s.charAt(i) != t.charAt(j)){
                    dp[i][j][0] = dp[i-1][j-1][1] + 1;
                }
                else{
                    dp[i][j][0] = dp[i-1][j-1][0];
                    dp[i][j][1] = dp[i-1][j-1][1] + 1;
                }
            }
        }
        int res = 0;
        for(int i = 0; i < slen; i++){
            for(int j = 0; j < tlen; j++){
                res += dp[i][j][0];
            }
        }
        return res;
    }
}
```
