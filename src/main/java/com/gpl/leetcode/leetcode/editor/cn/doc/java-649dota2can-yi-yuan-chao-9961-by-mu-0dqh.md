**胜利条件：**
存活的人是同一个阵营的人，那么该阵营获胜。
**解题思路**
1.  首先考虑每个人的状态，要么是被禁止的无法行使权利，要么是可以行使ban人的权利。
2.  那么每次ban的人要是后面距离自己最近的敌方阵营的人。
3.  但若每次都要往后去寻找第一个距离最近的敌方阵营的人时间复杂度必定是很高的。
4.  不妨记录下当前阵营中被ban的人的数量（当前被ban的R阵营的人数记为curBanR，当前被ban的D阵营的人数记为curBanD），之后在遍历到某个阵营的人的时候，判断该阵营当前被ban的人数是否大于0，就可以判断出当前这个人是否被ban。
5.  好了，解决了寻找第一个距离最近的敌方阵营的人的问题了，那么如何判断哪方胜利呢。
6.  所以还需要记录阵营被ban的总人数（R阵营被ban的总人数记为totalBanR，D阵营被ban的总人数记为totalBanD）
7.  只要判断某个阵营被ban的总人数 大于等于 该阵营实际的总人数，说明这个阵营所有的人都阵亡了，另一个阵营获胜。
8.  因此需要记录每个阵营实际的总人数（R阵营总人数 记录为Runmber，D阵营总人数 记录为Dnumber）

有了解题思路，下面举个比较复杂的例子说明：

例如字符串序列：RDDRRDRDD

开始遍历字符串

 [image.png](https://pic.leetcode-cn.com/1607655655-rMIkWn-image.png)

当前位置是R，记录R阵营的总人数Runmber+1，接着判断curBanR是否为0，若等于0，说明当前这个人可以行使ban人的权利，此时修改curBanD + 1表示D阵营有个人被ban了，同时被ban的总数totalBanD也要加1.

 [image.png](https://pic.leetcode-cn.com/1607655880-xeDsTU-image.png)

当前位置是D，记录D阵营的总人数Dnumber+1，判断curBanD是否为0，发现此时的curBanD不等于0，说明这个人被ban了，那么将它标记为“d”,以说明之后他都没有权利，无需在考虑这个位置，修改curBanD - 1


 [image.png](https://pic.leetcode-cn.com/1607656325-SKEyYR-image.png)

当前位置是D，记录D阵营的总人数Dnumber + 1，判断curBanD是否为0，发现此时的curBanD等于0，说明这个人可以行使ban人的权利，此时修改curBanR + 1 表示R阵容有人被ban了，同时被ban的总数totalBanR + 1

 [image.png](https://pic.leetcode-cn.com/1607656357-FaTxmt-image.png)

当前位置是R，记录R阵营的总人数Rnumber + 1，判断curBanR是否为0，发现此时的curBanR不等于0，说明这个人被ban了，那么将它标记为‘r'，以说明之后他都没有权利，无需在考虑这个位置，修改curBanR - 1

之后的逻辑类似上面，下面贴出图片自行分析

 [image.png](https://pic.leetcode-cn.com/1607656577-hdXUUg-image.png)

第一轮结束，判断 totalBanR是否大于等于 Runmber 若满足，则说明R阵营所有人都被ban了 则D阵营获胜。
反之 R阵营获胜。

这一轮的结果，无法分出胜负，所以需要进行下一轮的PK，但是这一轮的时候，不再需要记录 双方阵营的总人数了，此时的总人数已经统计完毕。

下一轮的过程如下：

 [image.png](https://pic.leetcode-cn.com/1607656743-xsDhYv-image.png)

和上一轮一样，继续判断是否分出胜负，若未分出胜负，继续

 [image.png](https://pic.leetcode-cn.com/1607656792-DUxirI-image.png)


最后的结果表明  totalBanR 大于等于 Runmber 说明R阵营的人全部阵亡，D阵营获胜。

**注意**
其实最后一轮的时候，并不需要遍历到最后，在遍历的过程中，若出现了一方阵营被ban的总人数等于这一方阵容实际的总人数的时候，说明全部阵亡，提前结束遍历。

**java代码**
```java
 public String predictPartyVictory(String senate) {
        int Rnumber = 0;//R阵营总人数
        int Dnumber = 0;//D阵营总人数
        int curBanR = 0;//当前被ban
        int curBanD = 0;//当前被ban
        int totalBanR = 0;//被ban总数
        int totalBanD = 0;//被ban总数
        char[] chars = senate.toCharArray();
        boolean flag = true;
        while(true){
            for(int i = 0;i < chars.length;i++){
                char cur = chars[i];
                if(cur == 'R'){
                    if(flag)
                        Rnumber++;
                    if(curBanR == 0){
                        curBanD++;
                        totalBanD++;
                        if(totalBanD == Dnumber  && !flag)return "Radiant";
                    }else{
                        curBanR--;
                        chars[i] = 'r';
                    }
                }else if(cur == 'D'){
                    if(flag)
                        Dnumber++;
                    if(curBanD == 0){
                        curBanR++;
                        totalBanR++;
                        if(totalBanR == Rnumber  && !flag)return "Dire";
                    }else{
                        curBanD--;
                        chars[i] = 'd';
                    }
                }
            }
            flag = false;
            if(totalBanD >= Dnumber)return "Radiant";
            if(totalBanR >= Rnumber)return "Dire";
        }
    }
```

**执行结果**

 [截屏2020-12-11 上午11.21.43.png](https://pic.leetcode-cn.com/1607656906-paODGz-%E6%88%AA%E5%B1%8F2020-12-11%20%E4%B8%8A%E5%8D%8811.21.43.png)








