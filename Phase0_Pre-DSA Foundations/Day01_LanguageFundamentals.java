/* ================================================================
   DAY 1 — LANGUAGE FUNDAMENTALS (Java)
   Phase 0 -> Pre-DSA Foundations | Topic 1 of 8
   ================================================================

   THEORY
   ------------------------------------------------------------
   A variable is a named storage location with a fixed type. Once you
   declare its type, it can never hold a different kind of value later.
   That's what "statically typed" means.

      int age = 25;   // variable "age", type int, currently holds 25

   1. PRIMITIVE TYPES — and the ONE rule that gives you every range
      Every primitive number type stores a fixed number of bits.
      For an n-bit SIGNED type, one bit is reserved for the sign
      (+ or -), leaving (n-1) bits for the actual magnitude. So:

            range = -2^(n-1)  to  2^(n-1) - 1

      Apply that single rule to each size, and you never need to
      memorize four separate numbers again:

      byte     8-bit   -2^7  to 2^7  - 1   (-128 to 127)
      short   16-bit   -2^15 to 2^15 - 1   (-32,768 to 32,767)
      int     32-bit   -2^31 to 2^31 - 1   (-2,147,483,648 to 2,147,483,647)  <- used 90% of the time
      long    64-bit   -2^63 to 2^63 - 1   (write literals as 10000000000L, note the L)

      char is the one exception — it's 16-bit but UNSIGNED (no sign bit,
      it just stores a Unicode character code), so its range is a plain:
      char    16-bit   0 to 2^16 - 1   (0 to 65,535)

      float and double are decimal types — they don't follow the integer
      formula above. Their "range" question matters less than their
      PRECISION (how many digits they can represent accurately):
      float    32-bit   ~7 accurate decimal digits   (write as 3.14f, note the f)
      double   64-bit   ~15 accurate decimal digits   <- used 90% of the time for decimals

      boolean is conceptually 1 bit: true or false, nothing else.

   2. REFERENCE TYPES
      Everything else, like String, is a "reference type" — the variable
      doesn't hold the value directly, it holds the memory address of
      where the actual object lives.
         String name = "Alice";

   3. OPERATORS
      Arithmetic : + - * / %
      Relational : == != > < >= <=      (asks a yes/no question)
      Logical    : && || !              (combines yes/no answers)
      Assignment : = += -= *= /= %=
      Unary      : ++ --                (adds/subtracts 1)
      Ternary    : condition ? ifTrue : ifFalse   (a mini if-else in one line)

      THE #1 BEGINNER TRAP:
      7 / 2 gives you 3, NOT 3.5. Why? Because both 7 and 2 are int
      values, and int math throws away the leftover decimal. If you
      want 3.5, at least one side must be a double: 7.0 / 2 gives 3.5.

   4. TYPE CASTING (converting a value from one type to another)
      Widening (small type -> big type) = automatic, safe, no cast needed.
         int -> long -> float -> double
      Narrowing (big type -> small type) = you must force it with (type),
      and it CHOPS OFF the extra part instead of rounding:
         double d = 9.7;
         int x = (int) d;   // x = 9  (NOT 10 — it truncates, not rounds)

   5. INPUT / OUTPUT (talking to the program)
      Output : System.out.println(...)   prints and moves to a new line
               System.out.print(...)     prints and stays on the same line
      Input  : Scanner class reads what the user types

      TRAP: after sc.nextInt(), the Enter key press is still "waiting"
      in the input. If you call sc.nextLine() right after, it grabs
      that leftover Enter instead of new text. Fix: add one throwaway
      sc.nextLine() to clear it first.

   ================================================================
   DRY RUN — trace this on paper BEFORE you read the code below.
   This is the single most important habit for getting good at DSA:
   never trust code you haven't mentally run yourself first.
   ------------------------------------------------------------
   double price = 49.99;
   int wholePart = (int) price;

     price       [ 49.99 ]   <- a double jar
     wholePart   [   ?   ]   <- an int jar, about to receive a forced cast

   Casting double -> int just CHOPS the decimal part off. It does not
   round to the nearest whole number. So wholePart becomes 49, not 50.

   Second trace — this pattern comes back CONSTANTLY later in the
   roadmap (digit extraction, base conversion, hashing), so get it
   into muscle memory now:
   int a = 17, b = 5;
   int q = a / b;   // 17 = 5*3 + 2   ->   q = 3  (the quotient)
   int r = a % b;   //                     r = 2  (the remainder)
   ================================================================ */

import java.util.Scanner;

public class Day01_LanguageFundamentals {

    public static void main(String[] args) {
        System.out.println("=== CODE DEMO ===");
        demoConcepts();

        System.out.println("\n=== TIER 1 — Concept Check ===");
        tier1_CelsiusToFahrenheit(37);
        tier1_AreaOfCircle(5);

        System.out.println("\n=== TIER 2 — Interview Style ===");
        tier2_SwapWithoutThirdVariable(5, 10);
        tier2_DigitSumOfNumber(9875);

        System.out.println("\n=== TIER 3 — CP / Edge Cases ===");
        tier3_ReverseIntegerWithOverflowCheck(1534236469); // classic overflow trap
        tier3_CountDigitsWithoutToString(-102030);         // negative + zero edge cases

        System.out.println("\n=== SELF TEST (predict on paper FIRST, then run and check) ===");
        runSelfTests();

        // Uncomment to try live keyboard input:
        // liveInputDemo();
    }

    // ================================================================
    // CODE DEMO — every concept from the theory, runnable end to end
    // ================================================================
    static void demoConcepts() {
        int age = 25;
        double price = 49.99;
        char grade = 'A';
        boolean isPassed = true;
        long population = 8000000000L;  // needs L, value is bigger than int can hold

        System.out.println("Age: " + age);
        System.out.println("Price: " + price);
        System.out.println("Grade: " + grade);
        System.out.println("Passed: " + isPassed);
        System.out.println("Population: " + population);

        int a = 17, b = 5;
        System.out.println("Sum: " + (a + b));
        System.out.println("Quotient (int/int): " + (a / b));   // 3
        System.out.println("Remainder: " + (a % b));            // 2
        System.out.println("True division: " + (a / (double) b)); // 3.4

        double d = 9.7;
        int truncated = (int) d;
        System.out.println("Truncated 9.7 -> " + truncated);

        int smallNum = 10;
        double widened = smallNum;
        System.out.println("Widened 10 -> " + widened);

        int score = 72;
        String result = (score >= 40) ? "Pass" : "Fail";
        System.out.println("Score " + score + " -> " + result);
    }

    // ================================================================
    // TIER 1 — Concept Check
    // Goal: prove you can directly APPLY the formulas/rules from theory.
    // ================================================================

    // Convert Celsius to Fahrenheit: F = C * 9/5 + 32
    // WHY THIS MATTERS: forces you to notice 9/5 as ints would give 1, not 1.8 —
    // the exact int-division trap from the theory section, in disguise.
    static void tier1_CelsiusToFahrenheit(double celsius) {
        double fahrenheit = celsius * 9.0 / 5.0 + 32;
        System.out.println("[T1] " + celsius + "C = " + fahrenheit + "F");
        // Time: O(1)  Space: O(1) — every Tier 1 problem here is pure arithmetic.
    }

    // Area of a circle given radius: A = pi * r^2
    static void tier1_AreaOfCircle(double radius) {
        double area = Math.PI * radius * radius;
        System.out.println("[T1] Area of circle (r=" + radius + "): " + area);
    }

    // ================================================================
    // TIER 2 — Interview Style
    // Goal: the same core idea, wrapped in a slightly real problem shape.
    // ================================================================

    // Swap two numbers WITHOUT a third variable.
    // PATTERN RECOGNITION: "swap without extra space" shows up whenever an
    // interviewer wants to test if you understand arithmetic vs XOR tricks.
    static void tier2_SwapWithoutThirdVariable(int a, int b) {
        System.out.println("[T2] Before: a=" + a + " b=" + b);
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("[T2] After:  a=" + a + " b=" + b);
        // Time: O(1)  Space: O(1)
        // WATCH OUT: a + b can overflow int range for very large numbers —
        // in real interviews, mention the XOR swap (a ^= b; b ^= a; a ^= b;)
        // as the overflow-safe alternative.
    }

    // Sum of digits of a number, using % and / (no String conversion).
    // PATTERN RECOGNITION: "process a number digit by digit" is the base
    // pattern for digit DP, palindrome-number checks, and Armstrong numbers.
    static void tier2_DigitSumOfNumber(int n) {
        int sum = 0;
        int num = n;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        System.out.println("[T2] Digit sum of " + n + " = " + sum);
        // Time: O(number of digits) = O(log10 n)   Space: O(1)
    }

    // ================================================================
    // TIER 3 — CP / Edge Cases
    // Goal: same base idea, but now with the traps that separate
    // "solved it once" from "actually mastered it."
    // ================================================================

    // Reverse the digits of an integer, and detect overflow (classic CP problem).
    // PATTERN RECOGNITION: any time you reverse/rebuild a number digit by
    // digit, you must ask "can this exceed int range?" BEFORE it happens,
    // not after — this is the #1 thing CP judges catch you on.
    static void tier3_ReverseIntegerWithOverflowCheck(int n) {
        int reversed = 0;
        int num = n;
        while (num != 0) {
            int digit = num % 10;
            // Overflow check BEFORE the multiply/add actually happens:
            if (reversed > (Integer.MAX_VALUE - digit) / 10) {
                System.out.println("[T3] Reversing " + n + " would overflow int -> return 0");
                return;
            }
            reversed = reversed * 10 + digit;
            num /= 10;
        }
        System.out.println("[T3] Reverse of " + n + " = " + reversed);
        // Time: O(log10 n)  Space: O(1)
    }

    // Count digits in a number WITHOUT converting to String — handles
    // negative numbers and zero correctly (both are common edge-case traps).
    static void tier3_CountDigitsWithoutToString(int n) {
        if (n == 0) {
            System.out.println("[T3] Digit count of 0 = 1");
            return;
        }
        int num = Math.abs(n);   // handles the negative-number edge case
        int count = 0;
        while (num > 0) {
            count++;
            num /= 10;
        }
        System.out.println("[T3] Digit count of " + n + " = " + count);
        // Time: O(log10 n)  Space: O(1)
    }

    // ================================================================
    // SELF TEST — predict each output on paper FIRST, then run this file
    // and check yourself against the printed "expected" value.
    // ================================================================
    static void runSelfTests() {
        int x = 7 / 2;
        double y = 7 / 2;
        System.out.println("Q1 -> x=" + x + " y=" + y + "   (expected: x=3 y=3.0)");

        int q2 = (int) 9.99;
        System.out.println("Q2 -> (int) 9.99 = " + q2 + "   (expected: 9)");

        int score = 39;
        String q3 = (score >= 40) ? "Pass" : "Fail";
        System.out.println("Q3 -> score 39 -> " + q3 + "   (expected: Fail)");

        // Uncomment the next line to SEE a compile error on purpose:
        // long bad = 10000000000;   // fails: literal is read as int first
        long good = 10000000000L;
        System.out.println("Q4 -> long with L suffix = " + good + "   (expected: 10000000000)");
    }

    // ================================================================
    // OPTIONAL: live keyboard input demo (uncomment the call in main to use)
    // ================================================================
    static void liveInputDemo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your age: ");
        int userAge = sc.nextInt();
        System.out.println("Hello " + name + ", you are " + userAge + " years old.");
        sc.close();
    }
}

/* ================================================================
   REEL IDEA (Instagram — for today's topic)
   ------------------------------------------------------------
   FORMAT: Screen recording of your code editor. Type ONLY this:

        int a = 7;
        int b = 2;
        System.out.println(a / b);

   Text overlay: "What does this print? Comment your guess before
   you scroll down"

   Wait 2 seconds (build curiosity), then run it on screen to reveal "3".

   Caption: "90% of beginners get this wrong on Day 1 of learning Java.
   Comment your guess before watching till the end. Follow for a
   new one every day."

   WHY THIS WORKS: it's a single line of code (low effort to read),
   has a genuinely counter-intuitive answer (3, not 3.5), and invites
   a comment BEFORE revealing the answer — that's what drives comments
   instead of silent scrolling.
   ================================================================ */
