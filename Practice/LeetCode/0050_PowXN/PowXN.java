public class PowXN {

    /*
     * ==========================================================
     * Solution 1: My Original Brute Force
     * ==========================================================
     * Time Complexity : O(|n|)
     * Space Complexity: O(1)
     *
     * NOTE:
     * Works for most test cases but is very slow for large |n|.
     */
    public static double myPowOriginalBruteForce(double x, int n) {

        if (x == 0) {
            return 0;
        }

        if (n == 0) {
            return 1;
        }

        double ans = 1;

        for (int i = 1; i <= n; i++) {
            ans *= x;
        }

        for (int i = -1; i >= n; i--) {
            ans /= x;
        }

        return ans;
    }

    /*
     * ==========================================================
     * Solution 2: Improved Brute Force
     * ==========================================================
     * Time Complexity : O(|n|)
     * Space Complexity: O(1)
     *
     * Handles Integer.MIN_VALUE correctly.
     */
    public static double myPowBruteForce(double x, int n) {

        long power = n;

        if (power < 0) {
            x = 1 / x;
            power = -power;
        }

        double ans = 1;

        for (long i = 0; i < power; i++) {
            ans *= x;
        }

        return ans;
    }

    /*
     * ==========================================================
     * Solution 3: My Original Recursive
     * ==========================================================
     * Time Complexity : O(log n)
     * Space Complexity: O(log n)
     *
     * NOTE:
     * Does not correctly handle Integer.MIN_VALUE.
     */
    public static double myPowOriginalRecursive(double x, int n) {

        if (n == 0) {
            return 1;
        }

        double half = myPowOriginalRecursive(x, n / 2);

        if (n % 2 == 0) {
            return half * half;
        } else if (n < 0) {
            return half * half * (1 / x);
        } else {
            return half * half * x;
        }
    }

    /*
     * ==========================================================
     * Solution 4: Improved Recursive
     * ==========================================================
     * Time Complexity : O(log n)
     * Space Complexity: O(log n)
     */
    public static double myPowRecursive(double x, int n) {

        long power = n;

        if (power < 0) {
            x = 1 / x;
            power = -power;
        }

        return powerRecursive(x, power);
    }

    private static double powerRecursive(double x, long n) {

        if (n == 0) {
            return 1;
        }

        double half = powerRecursive(x, n / 2);

        if (n % 2 == 0) {
            return half * half;
        }

        return half * half * x;
    }

    /*
     * ==========================================================
     * Solution 5: My Original Iterative
     * ==========================================================
     * Time Complexity : O(log n)
     * Space Complexity: O(1)
     *
     * NOTE:
     * Does not correctly handle negative exponents and
     * Integer.MIN_VALUE.
     */
    public static double myPowOriginalIterative(double x, int n) {

        double result = 1;

        while (n != 0) {

            if ((n & 1) == 1) {

                if (n < 0) {
                    result *= (1 / x);
                } else {
                    result *= x;
                }
            }

            x = x * x;
            n /= 2;
        }

        return result;
    }

    /*
     * ==========================================================
     * Solution 6: Optimal Iterative Binary Exponentiation
     * ==========================================================
     * Time Complexity : O(log n)
     * Space Complexity: O(1)
     */
    public static double myPowOptimal(double x, int n) {

        long power = n;

        if (power < 0) {
            x = 1 / x;
            power = -power;
        }

        double result = 1;

        while (power > 0) {

            if ((power & 1) == 1) {
                result *= x;
            }

            x *= x;
            power >>= 1;
        }

        return result;
    }

    public static void main(String[] args) {

        double[] bases = {2.0, 2.1, -2.0, 5.0};
        int[] exponents = {10, 3, -2, 0};

        for (int i = 0; i < bases.length; i++) {

            double x = bases[i];
            int n = exponents[i];

            System.out.println("---------------------------------------");
            System.out.println("x = " + x + ", n = " + n);

            System.out.println("Original Brute Force : " + myPowOriginalBruteForce(x, n));
            System.out.println("Improved Brute Force : " + myPowBruteForce(x, n));

            System.out.println("Original Recursive   : " + myPowOriginalRecursive(x, n));
            System.out.println("Improved Recursive   : " + myPowRecursive(x, n));

            System.out.println("Original Iterative   : " + myPowOriginalIterative(x, n));
            System.out.println("Optimal Iterative    : " + myPowOptimal(x, n));
        }
    }
}