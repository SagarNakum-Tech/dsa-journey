public class PalindromeNumber {

    // Solution 1: Mathematical approach (Optimal)
    public static boolean isPalindromeMath(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }

        int reversedHalf = 0;

        while (x > reversedHalf) {
            reversedHalf = reversedHalf * 10 + (x % 10);
            x /= 10;
        }

        return x == reversedHalf || x == reversedHalf / 10;
    }

    // Solution 2: String + Two Pointer approach
    public static boolean isPalindromeString(int x) {
        if (x < 0) {
            return false;
        }

        String num = Integer.toString(x);

        int left = 0;
        int right = num.length() - 1;

        while (left < right) {
            if (num.charAt(left) != num.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        int[] testCases = {121, -121, 10, 12321, 123321, 12345, 0, 1, 1001, 2147447412};

        for (int num : testCases) {
            boolean mathResult = isPalindromeMath(num);
            boolean stringResult = isPalindromeString(num);

            System.out.println("Number : " + num);
            System.out.println("Math   : " + mathResult);
            System.out.println("String : " + stringResult);
            System.out.println("----------------------------");
        }
    }
}