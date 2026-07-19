public class ValidPalindrome {

    /*
     * ==========================================================
     * Solution 1: My Original Two Pointer
     * ==========================================================
     *
     * Time Complexity : O(n)
     * Space Complexity: O(1)
     */
    public static boolean isPalindromeOriginal(String s) {

        int i = 0;
        int j = s.length() - 1;

        while (i < j) {

            char ch1 = s.charAt(i);
            char ch2 = s.charAt(j);

            boolean valid1 = isAlphaNumeric(ch1);
            boolean valid2 = isAlphaNumeric(ch2);

            if (valid1 && valid2) {

                if (Character.toLowerCase(ch1)
                        != Character.toLowerCase(ch2)) {
                    return false;
                }

                i++;
                j--;

            } else if (valid1) {

                j--;

            } else if (valid2) {

                i++;

            } else {

                i++;
                j--;
            }
        }

        return true;
    }

    /*
     * ==========================================================
     * Solution 2: Cleaner Two Pointer (Interview Preferred)
     * ==========================================================
     *
     * Time Complexity : O(n)
     * Space Complexity: O(1)
     */
    public static boolean isPalindromeOptimal(String s) {

        int i = 0;
        int j = s.length() - 1;

        while (i < j) {

            while (i < j && !isAlphaNumeric(s.charAt(i))) {
                i++;
            }

            while (i < j && !isAlphaNumeric(s.charAt(j))) {
                j--;
            }

            if (Character.toLowerCase(s.charAt(i))
                    != Character.toLowerCase(s.charAt(j))) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

    private static boolean isAlphaNumeric(char ch) {

        return ('a' <= ch && ch <= 'z')
                || ('A' <= ch && ch <= 'Z')
                || ('0' <= ch && ch <= '9');
    }

    public static void main(String[] args) {

        String[] testCases = {
                "A man, a plan, a canal: Panama",
                "race a car",
                " ",
                "0P",
                "a1a",
                "Madam",
                "No 'x' in Nixon",
                "Able was I, ere I saw Elba"
        };

        for (String s : testCases) {

            System.out.println("--------------------------------------");
            System.out.println("Input    : " + s);

            System.out.println("Original : "
                    + isPalindromeOriginal(s));

            System.out.println("Optimal  : "
                    + isPalindromeOptimal(s));
        }
    }
}