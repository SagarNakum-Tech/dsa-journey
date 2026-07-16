/* ================================================================
   DAY 2 — CONTROL FLOW (Java)
   Phase 0 -> Pre-DSA Foundations | Topic 2 of 8
   ================================================================

   THEORY
   ------------------------------------------------------------
   Control flow is how a program decides WHICH lines to run, and
   HOW MANY TIMES to run them. Without it, code just runs top to
   bottom once, in a straight line — control flow is what makes
   programs actually make decisions and repeat work.

   1. IF / ELSE — branching (choosing a path)
      if (condition) {
          // runs only if condition is true
      } else if (anotherCondition) {
          // runs only if the first was false AND this is true
      } else {
          // runs only if ALL above were false
      }
      Conditions are checked TOP TO BOTTOM, and the moment one matches,
      the rest are skipped entirely — even if a later condition would
      also have been true.

   2. SWITCH — a cleaner alternative when checking ONE variable against
      many fixed values (instead of a long if/else-if chain)
      switch (value) {
          case 1:
              // code
              break;      // <- without this, execution "falls through"
          case 2:
              // code
              break;
          default:
              // runs if nothing matched
      }
      THE #1 SWITCH TRAP: forgetting break. Without it, Java doesn't
      stop at the matching case — it keeps running every case below it
      too, until it hits a break or the switch ends. This is called
      "fall-through" and it's a very common silent bug.

   3. LOOPS — repeating code
      for loop — best when you know how many times to repeat:
          for (int i = 0; i < 5; i++) { ... }
          //   init      condition   update
      while loop — best when you don't know the count in advance,
      only a condition to keep checking:
          while (condition) { ... }
      do-while loop — same as while, but checks the condition AFTER
      running the body once, so it ALWAYS runs at least one time:
          do { ... } while (condition);

   4. BREAK vs CONTINUE
      break    — exits the loop immediately, skips everything remaining
      continue — skips ONLY the rest of the current iteration, then
                 moves on to the next one (loop keeps running)

   ================================================================
   DRY RUN — trace this on paper BEFORE reading the code below.
   ------------------------------------------------------------
   for (int i = 1; i <= 5; i++) {
       if (i == 3) continue;
       if (i == 5) break;
       System.out.print(i + " ");
   }

   Walk through it iteration by iteration:
     i=1 -> not 3, not 5 -> print "1 "
     i=2 -> not 3, not 5 -> print "2 "
     i=3 -> IS 3 -> continue -> skip the print, jump to i++
     i=4 -> not 3, not 5 -> print "4 "
     i=5 -> IS 5 -> break -> loop stops immediately, nothing prints

   Final output: 1 2 4
   Notice 3 is skipped (continue) but 5 never even gets a chance to
   print (break stops the loop before reaching the print line).
   ================================================================ */

public class Day02_ControlFlow {

    public static void main(String[] args) {
        System.out.println("=== CODE DEMO ===");
        demoConcepts();

        System.out.println("\n=== TIER 1 — Concept Check ===");
        tier1_ClassifyNumber(-7);
        tier1_SumOfFirstN(10);

        System.out.println("\n=== TIER 2 — Interview Style ===");
        tier2_FizzBuzz(15);
        tier2_IsLeapYear(2024);
        tier2_IsLeapYear(1900);

        System.out.println("\n=== TIER 3 — CP / Edge Cases ===");
        tier3_FirstPrimeFactor(91);
        tier3_DigitalRoot(0);
        tier3_DigitalRoot(9875);

        System.out.println("\n=== SELF TEST (predict on paper FIRST, then run and check) ===");
        runSelfTests();
    }

    // ================================================================
    // CODE DEMO — every concept from the theory, runnable end to end
    // ================================================================
    static void demoConcepts() {
        // ---- if / else ----
        int temp = 15;
        if (temp > 30) {
            System.out.println("Hot");
        } else if (temp > 15) {
            System.out.println("Warm");
        } else {
            System.out.println("Cool");   // this one runs: 15 is not > 15
        }

        // ---- switch with break ----
        int day = 3;
        switch (day) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            default:
                System.out.println("Some other day");
        }

        // ---- switch WITHOUT break, to show fall-through on purpose ----
        System.out.print("Fall-through demo for case 1: ");
        int x = 1;
        switch (x) {
            case 1:
                System.out.print("one ");
                // no break here on purpose
            case 2:
                System.out.print("two ");
                // no break here either
            case 3:
                System.out.print("three ");
                break;
            default:
                System.out.print("default ");
        }
        System.out.println("(notice it printed one, two, AND three)");

        // ---- for loop ----
        System.out.print("For loop 1 to 5: ");
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // ---- while loop ----
        System.out.print("While loop countdown: ");
        int n = 5;
        while (n > 0) {
            System.out.print(n + " ");
            n--;
        }
        System.out.println();

        // ---- do-while: runs at least once, even if condition is false ----
        System.out.print("Do-while with false condition upfront: ");
        int m = 10;
        do {
            System.out.print(m + " ");   // this still prints once
        } while (m < 5);
        System.out.println("(ran once even though 10 < 5 is false)");

        // ---- break and continue together ----
        System.out.print("Break+continue demo: ");
        for (int i = 1; i <= 5; i++) {
            if (i == 3) continue;
            if (i == 5) break;
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // ================================================================
    // TIER 1 — Concept Check
    // ================================================================

    // Classify a number as Positive, Negative, or Zero.
    static void tier1_ClassifyNumber(int n) {
        String result;
        if (n > 0) {
            result = "Positive";
        } else if (n < 0) {
            result = "Negative";
        } else {
            result = "Zero";
        }
        System.out.println("[T1] " + n + " is " + result);
        // Time: O(1)  Space: O(1)
    }

    // Sum of first N natural numbers, using a loop (then compare mentally
    // to the formula N*(N+1)/2 — same answer, very different approach).
    static void tier1_SumOfFirstN(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        System.out.println("[T1] Sum of first " + n + " numbers = " + sum);
        // Time: O(n)  Space: O(1)
    }

    // ================================================================
    // TIER 2 — Interview Style
    // ================================================================

    // FizzBuzz — THE most asked warm-up question in real interviews.
    // PATTERN RECOGNITION: multiple independent conditions checked in
    // priority order — order of the if-checks matters here.
    static void tier2_FizzBuzz(int upTo) {
        System.out.print("[T2] FizzBuzz 1-" + upTo + ": ");
        for (int i = 1; i <= upTo; i++) {
            if (i % 15 == 0) {
                System.out.print("FizzBuzz ");
            } else if (i % 3 == 0) {
                System.out.print("Fizz ");
            } else if (i % 5 == 0) {
                System.out.print("Buzz ");
            } else {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        // Time: O(n)  Space: O(1)
        // WATCH OUT: checking i % 15 == 0 must come FIRST — if you check
        // i % 3 and i % 5 separately before it, 15 would print "Fizz"
        // and stop there, never reaching "Buzz".
    }

    // Leap year check — classic case of boolean logic that LOOKS simple
    // but has a well-known edge case most people forget.
    // Rule: divisible by 4 AND (NOT divisible by 100, OR divisible by 400)
    static void tier2_IsLeapYear(int year) {
        boolean isLeap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        System.out.println("[T2] " + year + " is leap year? " + isLeap);
        // Time: O(1)  Space: O(1)
        // WATCH OUT: 1900 is divisible by 4, so people assume it's a leap
        // year. It's NOT — 1900 is divisible by 100 but not by 400.
        // 2000 WAS a leap year because it's divisible by 400.
    }

    // ================================================================
    // TIER 3 — CP / Edge Cases
    // ================================================================

    // Find the smallest prime factor of a number, using nested loops
    // with break. PATTERN RECOGNITION: "find the first X that satisfies
    // a condition" is exactly when break earns its place — searching
    // further after you already have your answer wastes time.
    static void tier3_FirstPrimeFactor(int n) {
        int num = n;
        for (int i = 2; i <= num; i++) {
            if (num % i == 0) {
                System.out.println("[T3] Smallest prime factor of " + n + " = " + i);
                return;   // stop the moment we find it — no need to keep looping
            }
        }
        System.out.println("[T3] " + n + " has no factor > 1 (n was <= 1)");
        // Time: O(n) worst case (n is prime itself)  Space: O(1)
        // EDGE CASE: n = 1 has no prime factor at all — the loop never
        // finds anything and falls through to the message below it.
    }

    // Digital root: repeatedly sum the digits of a number until only
    // one digit remains. PATTERN RECOGNITION: "repeat an operation until
    // a stable/final state is reached" is a while-loop shape you'll see
    // again in many places (e.g. union-find path compression later).
    static void tier3_DigitalRoot(int n) {
        int num = n;
        // EDGE CASE 1: negative numbers — decide up front how to handle them
        if (num < 0) num = -num;

        // EDGE CASE 2: n = 0 is already a single digit — the while loop
        // below would never even run, which is actually correct here.
        while (num >= 10) {
            int sum = 0;
            int temp = num;
            while (temp > 0) {
                sum += temp % 10;
                temp /= 10;
            }
            num = sum;
        }
        System.out.println("[T3] Digital root of " + n + " = " + num);
        // Time: O(log n) roughly — shrinks fast each pass  Space: O(1)
    }

    // ================================================================
    // SELF TEST — predict each output on paper FIRST, then run this file
    // and check yourself against the printed "expected" value.
    // ================================================================
    static void runSelfTests() {
        // Q1: switch fall-through
        System.out.print("Q1 -> ");
        int x = 2;
        switch (x) {
            case 1:
                System.out.print("A");
            case 2:
                System.out.print("B");
            case 3:
                System.out.print("C");
                break;
            case 4:
                System.out.print("D");
        }
        System.out.println("   (expected: BC — starts at case 2, falls through to 3, stops at break)");

        // Q2: do-while always runs once
        System.out.print("Q2 -> ");
        int count = 0;
        do {
            count++;
        } while (false);
        System.out.println("count = " + count + "   (expected: 1)");

        // Q3: continue vs break together
        System.out.print("Q3 -> ");
        for (int i = 1; i <= 4; i++) {
            if (i == 2) continue;
            if (i == 4) break;
            System.out.print(i + " ");
        }
        System.out.println("  (expected: 1 3 — 2 is skipped, loop stops entirely before printing 4)");

        // Q4: leap year edge case
        boolean leap1900 = (1900 % 4 == 0 && 1900 % 100 != 0) || (1900 % 400 == 0);
        System.out.println("Q4 -> Is 1900 a leap year? " + leap1900 + "   (expected: false)");
    }
}

/* ================================================================
   REEL IDEA — Day 2 (Control Flow)
   No reveal in the video. Answer gets pinned in the comments after posting.
   ------------------------------------------------------------
   Hook overlay (code on screen):
   "This switch statement is missing something. Most beginners don't
   notice until it's too late."

       int x = 1;
       switch (x) {
           case 1: System.out.print("one ");
           case 2: System.out.print("two ");
           case 3: System.out.print("three "); break;
       }

   Question overlay: "Comment A or B:
   A) Prints only 'one'
   B) Prints 'one two three'"

   End screen text: "Correct answer + why, pinned below 📌"

   Caption: "This bug has a name: fall-through. Missing ONE keyword
   (break) changes the entire output. Comment A or B before you check
   the pin — I bet more people get this wrong than right."

   Pinned comment (post ~15-30 min after upload):
   "Answer: B) 'one two three'. Without break, Java doesn't stop at
   the matching case — it just keeps running every case below it until
   it hits a break or the switch ends. Always add break unless you
   want fall-through on purpose."
   ================================================================ */
