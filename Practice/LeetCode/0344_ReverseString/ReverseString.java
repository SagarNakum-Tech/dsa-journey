public class ReverseString {

    /*
     * ==========================================================
     * Solution 1: My Recursive Solution
     * ==========================================================
     *
     * Time Complexity : O(n)
     * Space Complexity: O(n) (Recursion Stack)
     */
    public static void reverseStringRecursive(char[] s) {

        reverse(s, 0, s.length - 1);
    }

    private static void reverse(char[] s, int left, int right) {

        if (left >= right) {
            return;
        }

        char temp = s[left];
        s[left] = s[right];
        s[right] = temp;

        reverse(s, left + 1, right - 1);
    }

    /*
     * ==========================================================
     * Solution 2: My Iterative Two-Pointer Solution (Optimal)
     * ==========================================================
     *
     * Time Complexity : O(n)
     * Space Complexity: O(1)
     */
    public static void reverseStringIterative(char[] s) {

        int left = 0;
        int right = s.length - 1;

        while (left < right) {

            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            left++;
            right--;
        }
    }

    private static void printArray(char[] s) {

        System.out.print("[ ");

        for (char c : s) {
            System.out.print(c + " ");
        }

        System.out.println("]");
    }

    public static void main(String[] args) {

        char[] arr1 = {'h', 'e', 'l', 'l', 'o'};
        char[] arr2 = {'H', 'a', 'n', 'n', 'a', 'h'};

        System.out.println("Original Array 1:");
        printArray(arr1);

        reverseStringRecursive(arr1);

        System.out.println("Recursive:");
        printArray(arr1);

        System.out.println();

        System.out.println("Original Array 2:");
        printArray(arr2);

        reverseStringIterative(arr2);

        System.out.println("Iterative:");
        printArray(arr2);
    }
}