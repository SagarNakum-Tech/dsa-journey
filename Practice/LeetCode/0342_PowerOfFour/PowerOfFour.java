public class PowerOfFour {

    /*
     * ==========================================================
     * Solution 1: My Original Solution (Division)
     * ==========================================================
     *
     * Keep dividing by 4 until the number is no longer divisible.
     * If the remaining number is 1, then it is a power of 4.
     *
     * Time Complexity : O(log₄ n)
     * Space Complexity: O(1)
     */
    public static boolean isPowerOfFourDivision(int n) {

        if (n <= 0) {
            return false;
        }

        while (n % 4 == 0) {
            n /= 4;
        }

        return n == 1;
    }

    /*
     * ==========================================================
     * Solution 2: Recursive
     * ==========================================================
     *
     * Time Complexity : O(log₄ n)
     * Space Complexity: O(log₄ n)
     */
    public static boolean isPowerOfFourRecursive(int n) {

        if (n == 1) {
            return true;
        }

        if (n <= 0 || n % 4 != 0) {
            return false;
        }

        return isPowerOfFourRecursive(n / 4);
    }

    /*
     * ==========================================================
     * Solution 3: Bit Manipulation (Optimal)
     * ==========================================================
     *
     * Conditions:
     * 1. n is a power of 2.
     * 2. The only set bit is at an even position.
     *
     * 0x55555555 = 01010101010101010101010101010101
     *
     * Time Complexity : O(1)
     * Space Complexity: O(1)
     */
    public static boolean isPowerOfFourBitwise(int n) {

        return n > 0
                && (n & (n - 1)) == 0
                && (n & 0x55555555) != 0;
    }

    /*
     * ==========================================================
     * Solution 4: Mathematical
     * ==========================================================
     *
     * Every power of 4 is also a power of 2 and satisfies:
     *
     * n % 3 == 1
     *
     * Time Complexity : O(1)
     * Space Complexity: O(1)
     */
    public static boolean isPowerOfFourMath(int n) {

        return n > 0
                && (n & (n - 1)) == 0
                && (n % 3 == 1);
    }

    public static void main(String[] args) {

        int[] testCases = { -4, 0, 1, 2, 4, 8, 16, 32, 64, 256, 1024 };

        for (int n : testCases) {

            System.out.println("----------------------------------");
            System.out.println("Input      : " + n);

            System.out.println("Division   : " + isPowerOfFourDivision(n));
            System.out.println("Recursive  : " + isPowerOfFourRecursive(n));
            System.out.println("Bitwise    : " + isPowerOfFourBitwise(n));
            System.out.println("Math       : " + isPowerOfFourMath(n));
        }
    }
}