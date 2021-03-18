```
// Brute force recursion: TLE
class Solution1 {
public:
    int findPaths(int m, int n, int N, int i, int j) {
        return dfs(m, n, N, i, j);
    }
    
    int dfs(int m, int n, int N, int i, int j) {
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return 1;
        }

        if (N == 0) {
            return 0; // after N step still within range
        }

        int res = 0;
        for (const auto& dir : dirs) {
            auto ni = i + dir[0];
            auto nj = j + dir[1];
            res += dfs(m, n, N - 1 , ni, nj);
        }

        return res;
    }

private:
    vector<vector<int>> dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
};

// Memoization: AC
class Solution2 {
public:
    int findPaths(int m, int n, int N, int i, int j) {
        memo = vector<vector<vector<int>>> (m, vector<vector<int>>(n, vector<int>(N + 1, -1)));
        return dfs(m, n, N, i, j);
    }
    
    long dfs(int m, int n, int N, int i, int j) {
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return 1;
        }

        if (memo[i][j][N] != -1) {
            return memo[i][j][N];
        }

        if (N == 0) {
            return 0; // after N step still within range
        }

        long res = 0;
        for (const auto& dir : dirs) {
            auto ni = i + dir[0];
            auto nj = j + dir[1];
            res += dfs(m, n, N - 1 , ni, nj);
            res %= MOD;
        }

        memo[i][j][N] = res;
        return memo[i][j][N];
    }

private:
    vector<vector<int>> dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    vector<vector<vector<int>>> memo;
    const int MOD = 1000000007;
};

// DP1: AC
class Solution3 {
public:
    int findPaths(int m, int n, int N, int i, int j) {
        vector<vector<vector<int>>> dp(m, vector<vector<int>>(n, vector<int>(N + 1, 0)));
        long res = 0;
        dp[i][j][0] = 1; // start
        for (int k = 1; k <= N; k++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    for (const auto& dir : dirs) {
                        auto ni = i + dir[0];
                        auto nj = j + dir[1];
                        if (ni < 0 || nj < 0 || ni >= m || nj >= n) {
                            // out of board, accmulate the result
                            res = (res + dp[i][j][k - 1]) % MOD;
                        } else {
                            // within board, the total ways of arriving at pos (ni, nj)
                            // in k steps equals the total ways of arriving at pos (i, j)
                            // in (k - 1) step and then arriving at (ni, nj) from 4 dirs 
                            dp[ni][nj][k] = (dp[ni][nj][k] + dp[i][j][k - 1]) % MOD;
                        }
                        res %= MOD;
                    }
                }
            }
        }

            return res; 
    }

private:
    vector<vector<int>> dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    const int MOD = 1000000007;
};

// DP2: AC (think reverse, the problem is equivalent to starting from outside, in k steps
// how many ways to arrive at postion (r, c))
class Solution {
public:
    int findPaths(int m, int n, int K, int r, int c) {
        vector<vector<vector<long>>> dp(K + 1, vector<vector<long>>(m, vector<long>(n, 0)));
        long res = 0;
        for (int k = 1; k <= K; k++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    long up = (i == 0 ? 1 : dp[k - 1][i - 1][j]);
                    long down = (i == m - 1 ? 1 : dp[k - 1][i + 1][j]);
                    long left = (j == 0 ? 1 : dp[k - 1][i][j - 1]);
                    long right = (j == n - 1 ? 1 : dp[k - 1][i][j + 1]);
                    dp[k][i][j] = (up + down + left + right) % MOD;
                }
            }
        }

            return dp[K][r][c]; 
    }

private:
    const int MOD = 1000000007;
};

```