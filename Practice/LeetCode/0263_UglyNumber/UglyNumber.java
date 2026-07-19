public class UglyNumber {

    /**
     * Solution 1 (Optimal)
     *
     * Keep dividing by 2, 3, and 5 until no longer divisible.
     * If the remaining number is 1, it is an Ugly Number.
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public static boolean isUgly(int n) {

        if (n <= 0) {
            return false;
        }

        while (n % 2 == 0) {
            n /= 2;
        }

        while (n % 3 == 0) {
            n /= 3;
        }

        while (n % 5 == 0) {
            n /= 5;
        }

        return n == 1;
    }

    public static void main(String[] args) {

        int[] testCases = { -5, 0, 1, 2, 3, 5, 6, 8, 14, 25, 30, 37 };

        for (int num : testCases) {

            System.out.println("Input  : " + num);
            System.out.println("Output : " + isUgly(num));
            System.out.println("--------------------------------");
        }
    }
}