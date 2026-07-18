public class AddDigits {

    /**
     * Solution 1 (Iterative)
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public static int addDigitsIterative(int num) {

        while (num >= 10) {

            int digitSum = 0;

            while (num != 0) {
                digitSum += num % 10;
                num /= 10;
            }

            num = digitSum;
        }

        return num;
    }

    /**
     * Solution 2 (Optimal - Digital Root)
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public static int addDigitsMath(int num) {

        if (num == 0) {
            return 0;
        }

        return 1 + (num - 1) % 9;
    }

    public static void main(String[] args) {

        int[] testCases = { 0, 5, 38, 99, 9999, 12345 };

        for (int num : testCases) {

            System.out.println("Input      : " + num);
            System.out.println("Iterative  : " + addDigitsIterative(num));
            System.out.println("Math       : " + addDigitsMath(num));
            System.out.println("--------------------------------");
        }
    }
}