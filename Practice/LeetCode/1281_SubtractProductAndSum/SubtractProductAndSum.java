public class SubtractProductAndSum {

    /**
     * Solution 1 (Optimal)
     * Extract each digit, calculate product and sum,
     * then return product - sum.
     *
     * Time Complexity: O(log10(n))
     * Space Complexity: O(1)
     */
    public static int subtractProductAndSum(int n) {
        int product = 1;
        int sum = 0;

        while (n != 0) {
            int digit = n % 10;
            product *= digit;
            sum += digit;
            n /= 10;
        }

        return product - sum;
    }

    public static void main(String[] args) {

        int[] testCases = { 234, 4421, 999, 100, 5 };

        for (int num : testCases) {
            System.out.println("Input  : " + num);
            System.out.println("Output : " + subtractProductAndSum(num));
            System.out.println("--------------------------");
        }
    }
}