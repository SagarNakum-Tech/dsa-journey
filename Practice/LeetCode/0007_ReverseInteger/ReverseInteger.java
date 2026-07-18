public class ReverseInteger {

    /**
     * Solution 1 (Recommended)
     * Reverse Integer with proper overflow handling.
     *
     * Time Complexity: O(log10(n))
     * Space Complexity: O(1)
     */
    public static int reverseOptimal(int x) {
        int reversed = 0;

        while (x != 0) {
            int rem = x % 10;
            x /= 10;

            // Check positive overflow
            if (reversed > Integer.MAX_VALUE / 10 ||
                    (reversed == Integer.MAX_VALUE / 10 && rem > 7)) {
                return 0;
            }

            // Check negative overflow
            if (reversed < Integer.MIN_VALUE / 10 ||
                    (reversed == Integer.MIN_VALUE / 10 && rem < -8)) {
                return 0;
            }

            reversed = reversed * 10 + rem;
        }

        return reversed;
    }

    /**
     * Solution 2 (Learning Purpose)
     *
     * Uses Math.abs() and stores the sign separately.
     *
     * NOTE:
     * This solution DOES NOT work for Integer.MIN_VALUE
     * because Math.abs(Integer.MIN_VALUE) still returns
     * Integer.MIN_VALUE due to integer overflow.
     */
    public static int reverseUsingAbs(int x) {

        boolean isNegative = x < 0;

        int num = Math.abs(x);
        int reversed = 0;

        while (num != 0) {
            int rem = num % 10;

            if (reversed > (Integer.MAX_VALUE - rem) / 10) {
                return 0;
            }

            reversed = reversed * 10 + rem;
            num /= 10;
        }

        return isNegative ? -reversed : reversed;
    }

    public static void main(String[] args) {

        int[] testCases = { 123, 123, 120, 1534236469, -2147483412, Integer.MAX_VALUE, Integer.MIN_VALUE };

        for (int num : testCases) {

            System.out.println("Input               : " + num);
            System.out.println("Optimal Solution    : " + reverseOptimal(num));
            System.out.println("Using Math.abs()    : " + reverseUsingAbs(num));
            System.out.println("-------------------------------------------");
        }
    }
}