public class PowerOfThree {

    /*
     * ==========================================================
     * Solution 1: My Original Solution (Division)
     * ==========================================================
     *
     * Keep dividing by 3 until it is no longer divisible.
     *
     * Time Complexity : O(log₃ n)
     * Space Complexity: O(1)
     */
    public static boolean isPowerOfThreeDivision(int n) {

        if (n <= 0) {
            return false;
        }

        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }

    /*
     * ==========================================================
     * Solution 2: Recursive
     * ==========================================================
     *
     * Time Complexity : O(log₃ n)
     * Space Complexity: O(log₃ n)
     */
    public static boolean isPowerOfThreeRecursive(int n) {

        if (n == 1) {
            return true;
        }

        if (n <= 0 || n % 3 != 0) {
            return false;
        }

        return isPowerOfThreeRecursive(n / 3);
    }

    /*
     * ==========================================================
     * Solution 3: Mathematical (Optimal)
     * ==========================================================
     *
     * 1162261467 = 3^19 (Largest power of 3 within int range)
     *
     * Any positive power of 3 must divide it exactly.
     *
     * Time Complexity : O(1)
     * Space Complexity: O(1)
     */
    public static boolean isPowerOfThreeMath(int n) {

        return n > 0 && 1162261467 % n == 0;
    }

    public static void main(String[] args) {

        int[] testCases = { -9, 0, 1, 3, 9, 27, 45, 81, 243, 59049, 1162261467 };

        for (int n : testCases) {

            System.out.println("----------------------------------");
            System.out.println("Input      : " + n);

            System.out.println("Division   : " + isPowerOfThreeDivision(n));
            System.out.println("Recursive  : " + isPowerOfThreeRecursive(n));
            System.out.println("Math       : " + isPowerOfThreeMath(n));
        }
    }
}