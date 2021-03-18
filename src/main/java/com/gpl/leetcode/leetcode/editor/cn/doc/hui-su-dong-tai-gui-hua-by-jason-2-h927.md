# 解法一 回溯法
思路:
这道题与[688. “马”在棋盘上的概率](https://leetcode-cn.com/problems/knight-probability-in-chessboard/)类似。也写过它的[题解](https://leetcode-cn.com/problems/knight-probability-in-chessboard/)

```
d[cur][r][c]=-1;//步数cur，位置r，c的方案数. -1表示还未算过
dfs(cur,r,c){
    if(cur >= N){
        返回0;
    }
    if(d[cur][r][c] != -1) 已算过，返回d[cur][r][c];
    d[cur][r][c] = 0;

    for((nx,ny) in (r,c)可移动的4个位置){
        if((nx,ny)在棋盘上){
            d[cur][r][c] += dfs(cur+1,nx,ny);
        }else{
            d[cur][r][c] += 1;
        }
    }
    返回d[cur][r][c];
}
```


代码

```cpp
const int MOD=1000000000+7;
int d[55][55][55];
class Solution {
public:
    int mm;
    int nn;
    int NN;
    int findPaths(int m, int n, int N, int i, int j) {
        mm = m;
        nn = n;
        NN = N;
        memset(d,0xcf,sizeof(d));
        return dfs(0,i,j);
    }

    int dfs(int cur,int x,int y){
        if(cur >= NN){
            return 0;
        }
        int& ans = d[cur][x][y];
        if(ans >= 0) return ans;
        ans = 0;
        const int dx[4]={1,-1,0, 0};
        const int dy[4]={0, 0,1,-1};
        for(int i=0;i<4;++i){
            const int nx = x+dx[i];
            const int ny = y+dy[i];
            if(in_board(nx,ny)){
                int r = dfs(cur+1,nx,ny);
                ans = (ans + r) % MOD;
            }else{
                ans = (ans + 1) % MOD;
            }
        }
        return ans;
    }

    bool in_board(int x,int y){
        return x>=0 && x<mm && y>=0 && y<nn;
    }
};
```

# 解法二 动态规划
将上面的思路转成动态规划。
1.算出每个位置走1步就越界的情况数。
2.在第1步的基础上，算出每个位置走2步就越界的情况数。
3.在第2步的基础上，算出每个位置走3步就越界的情况数。
......
如此，迭代
```
for(r in [0,m-1]){
    for(c in [0,n-1]){
        for((nx,ny) in (r,c)可移动的8个位置){
            if((nx,ny)在棋盘上){
                d[0][x][y] += 1;
            }
        }
    }
}

for(cur in [1,N-1]){
    for(r in [0,m-1]){
        for(c in [0,n-1]){
            for((nx,ny) in (r,c)可移动的8个位置){
                if((nx,ny)在棋盘上){
                    d[cur][x][y] += d[cur-1][nx][ny];
                }
            }
        }
    }
}

ans = 0;//解
for(cur in [0,N-1]){
    ans += d[cur][i][j];
}
```

代码：

```cpp
const int MOD=1000000000+7;
int d[55][55][55];
class Solution {
public:
    int mm;
    int nn;
    int NN;
    int findPaths(int m, int n, int N, int i, int j) {
        mm = m;
        nn = n;
        NN = N;

        memset(d,0,sizeof(d));
        const int dx[4]={1,-1,0, 0};
        const int dy[4]={0, 0,1,-1};
        for(int x=0;x<m;++x){
            for(int y=0;y<n;++y){
                for(int k=0;k<4;++k){
                    const int nx = x+dx[k];
                    const int ny = y+dy[k];
                    if(!in_board(nx,ny)){
                        d[0][x][y] += 1;
                    }
                }
            }
        }
        for(int cur=1;cur<N;++cur){
            for(int x=0;x<m;++x){
                for(int y=0;y<n;++y){
                    for(int k=0;k<4;++k){
                        const int nx = x+dx[k];
                        const int ny = y+dy[k];
                        if(in_board(nx,ny)){
                            d[cur][x][y] = (d[cur][x][y] + d[cur-1][nx][ny])%MOD;
                        }
                    }
                }
            }    
        }
        int ans = 0;
        for(int cur=0;cur<N;++cur){
            ans = (ans + d[cur][i][j]) % MOD;
        }
        return ans;
    }

    bool in_board(int x,int y){
        return x>=0 && x<mm && y>=0 && y<nn;
    }
};
```
