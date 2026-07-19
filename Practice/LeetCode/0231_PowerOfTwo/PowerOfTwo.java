public class PowerOfTwo {

    /**
     * Solution 1 (Iterative Division)
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public static boolean isPowerOfTwoIterative(int n) {

        if (n <= 0) {
            return false;
        }

        while (n != 1) {

            if (n % 2 != 0) {
                return false;
            }

            n /= 2;
        }

        return true;
    }

    /**
     * Solution 2 (Optimal - Bit Manipulation)
     *
     * A power of two has exactly one set bit.
     *
     * Example:
     * 8  = 1000
     * 7  = 0111
     * 8 & 7 = 0000
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public static boolean isPowerOfTwoBitwise(int n) {

        return n > 0 && (n & (n - 1)) == 0;
    }

    public static void main(String[] args) {

        int[] testCases = { -8, -1, 0, 1, 2, 3, 4, 8, 16, 18, 64, 1024 };

        for (int num : testCases) {

            System.out.println("Input      : " + num);
            System.out.println("Iterative  : " + isPowerOfTwoIterative(num));
            System.out.println("Bitwise    : " + isPowerOfTwoBitwise(num));
            System.out.println("--------------------------------");
        }
    }
}