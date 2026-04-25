class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        int MOD = 1_000_000_007;
        // dp[i][j][0] = stable arrays with i zeros, j ones, ending in 0
        // dp[i][j][1] = stable arrays with i zeros, j ones, ending in 1
        long[][][] dp = new long[zero + 1][one + 1][2];

        // Base cases: Arrays with only 0s or only 1s are stable up to the limit
        for (int i = 1; i <= Math.min(zero, limit); i++) {
            dp[i][0][0] = 1;
        }
        for (int j = 1; j <= Math.min(one, limit); j++) {
            dp[0][j][1] = 1;
        }

        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                // To end with 0, we can add a 0 to any stable array ending in 0 or 1
                // subtract invalid cases where adding 0 creates more than 'limit' consecutive 0s
                dp[i][j][0] = (dp[i - 1][j][0] + dp[i - 1][j][1]) % MOD;
                if (i > limit) {
                    // Invalid cases were those ending in 1 exactly limit+1 positions ago
                    dp[i][j][0] = (dp[i][j][0] - dp[i - limit - 1][j][1] + MOD) % MOD;
                }

                // To end with 1, we can add a 1 to any stable array ending in 0 or 1
                // subtract invalid cases where adding 1 creates more than 'limit' consecutive 1s
                dp[i][j][1] = (dp[i][j - 1][0] + dp[i][j - 1][1]) % MOD;
                if (j > limit) {
                    // Invalid cases were those ending in 0 exactly limit+1 positions ago
                    dp[i][j][1] = (dp[i][j][1] - dp[i][j - limit - 1][0] + MOD) % MOD;
                }
            }
        }

        return (int) ((dp[zero][one][0] + dp[zero][one][1]) % MOD);
    }
}