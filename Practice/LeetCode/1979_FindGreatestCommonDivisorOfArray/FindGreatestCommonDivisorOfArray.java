public class FindGreatestCommonDivisorOfArray {

    /*
     * ==========================================================
     * Solution 1: My Original Solution (Recursive Euclidean Algorithm)
     * ==========================================================
     *
     * Find the minimum and maximum element in the array,
     * then compute their GCD recursively.
     *
     * Time Complexity : O(n + log(min))
     * Space Complexity: O(log(min))
     */
    public static int findGCDRecursive(int[] nums) {

        int min = nums[0];
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {

            if (nums[i] < min) {
                min = nums[i];
            } else if (nums[i] > max) {
                max = nums[i];
            }
        }

        return gcdRecursive(max, min);
    }

    private static int gcdRecursive(int a, int b) {

        if (b == 0) {
            return a;
        }

        return gcdRecursive(b, a % b);
    }

    /*
     * ==========================================================
     * Solution 2: Iterative Euclidean Algorithm
     * ==========================================================
     *
     * Time Complexity : O(n + log(min))
     * Space Complexity: O(1)
     */
    public static int findGCDIterative(int[] nums) {

        int min = nums[0];
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {

            if (nums[i] < min) {
                min = nums[i];
            } else if (nums[i] > max) {
                max = nums[i];
            }
        }

        return gcdIterative(max, min);
    }

    private static int gcdIterative(int a, int b) {

        while (b != 0) {
            int remainder = a % b;
            a = b;
            b = remainder;
        }

        return a;
    }

    public static void main(String[] args) {

        int[][] testCases = {
                {2, 5, 6, 9, 10},
                {7, 5, 6, 8, 3},
                {3, 3},
                {12, 18, 24},
                {15, 30, 45, 60}
        };

        for (int[] nums : testCases) {

            System.out.print("Array: ");

            for (int num : nums) {
                System.out.print(num + " ");
            }

            System.out.println();

            System.out.println("Recursive GCD : " + findGCDRecursive(nums));
            System.out.println("Iterative GCD : " + findGCDIterative(nums));

            System.out.println("--------------------------------------");
        }
    }
}