public class FibonacciNumber {

    /**
     * Solution 1: Recursion
     *
     * Time Complexity: O(2^n)
     * Space Complexity: O(n)
     */
    public static int fibRecursive(int n) {
        if (n <= 1) {
            return n;
        }

        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }

    /**
     * Solution 2: DP (Memoization)
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int fibMemoization(int n) {
        int[] dp = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            dp[i] = -1;
        }

        return helper(n, dp);
    }

    private static int helper(int n, int[] dp) {

        if (n <= 1) {
            return n;
        }

        if (dp[n] != -1) {
            return dp[n];
        }

        dp[n] = helper(n - 1, dp) + helper(n - 2, dp);

        return dp[n];
    }

    /**
     * Solution 3: DP (Tabulation)
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int fibTabulation(int n) {

        if (n <= 1) {
            return n;
        }

        int[] dp = new int[n + 1];

        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    /**
     * Solution 4: Space Optimized DP (Optimal)
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int fibSpaceOptimized(int n) {

        if (n <= 1) {
            return n;
        }

        int prev2 = 0;
        int prev1 = 1;

        for (int i = 2; i <= n; i++) {

            int current = prev1 + prev2;

            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    public static void main(String[] args) {

        int[] testCases = {0, 1, 2, 3, 5, 10, 20};

        for (int n : testCases) {

            System.out.println("Input            : " + n);
            System.out.println("Recursive        : " + fibRecursive(n));
            System.out.println("Memoization      : " + fibMemoization(n));
            System.out.println("Tabulation       : " + fibTabulation(n));
            System.out.println("Space Optimized  : " + fibSpaceOptimized(n));
            System.out.println("--------------------------------------");
        }
    }
}