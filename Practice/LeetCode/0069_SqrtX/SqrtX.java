public class SqrtX {

    /**
     * Solution 1 (Optimal)
     * Binary Search
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public static int mySqrtBinarySearch(int x) {
        if (x == 0) {
            return 0;
        }

        int left = 1;
        int right = x;
        int answer = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (mid <= x / mid) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    /**
     * Solution 2 (Brute Force)
     * Linear Search
     *
     * Time Complexity: O(√n)
     * Space Complexity: O(1)
     */
    public static int mySqrtLinear(int x) {
        if (x == 0) {
            return 0;
        }

        for (int i = 1; i <= x; i++) {
            long square = (long) i * i;

            if (square == x) {
                return i;
            }

            if (square > x) {
                return i - 1;
            }
        }

        return 0;
    }

    public static void main(String[] args) {

        int[] testCases = { 0, 1, 4, 8, 9, 15, 16, 25, 2147395599, Integer.MAX_VALUE };

        for (int num : testCases) {
            System.out.println("Input          : " + num);
            System.out.println("Binary Search  : " + mySqrtBinarySearch(num));
            System.out.println("Linear Search  : " + mySqrtLinear(num));
            System.out.println("--------------------------------------");
        }
    }
}