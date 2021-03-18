```
    public String licenseKeyFormatting(String S, int K) {
        S = S.replace("-","");
        S = S.toUpperCase();
        int stLen = S.length()%K == 0 ? K : S.length() - S.length()/K*K;
        StringBuffer str = new StringBuffer(S);
        for(int i = stLen; i < str.length(); i = i+K+1){
            str = str.insert(i,"-");
        }
        return str.toString();
    }
```
