/* ================================================================
   DAY 3 — FUNCTIONS & SCOPE (Java)
   Phase 0 -> Pre-DSA Foundations | Topic 3 of 8
   ================================================================

   THEORY
   ------------------------------------------------------------
   1. WHAT IS A FUNCTION (METHOD)
      A reusable block of code with a name, optional parameters
      (inputs), and a return type (what it hands back).
         static int add(int a, int b) {
             return a + b;
         }
      "static" here just means "belongs to the class itself, not to
      a specific object" — that's why main() can call these methods
      directly without creating an object first.
      "void" as a return type means the method returns nothing.

   2. THE CALL STACK
      Every time a method is called, Java pushes a new "frame" onto
      a stack — that frame holds that call's local variables and
      where to return to when it's done. When the method finishes,
      its frame is popped off, and control returns to exactly where
      it left off in the calling method.
      This is a LIFO structure (Last In, First Out) — the most
      recently called method is always the first to finish and pop.

   3. PASS BY VALUE VS REFERENCE — the single most misunderstood
      topic for Java beginners. Read this twice.

      Java is ALWAYS pass-by-value. Always. No exceptions. But what
      gets copied depends on the type:

      - For PRIMITIVES (int, double, char...): the actual VALUE is
        copied. The method gets its own separate copy. Changing it
        inside the method does NOT affect the original.

      - For OBJECTS (arrays, String, custom classes...): the
        REFERENCE (memory address) is copied — not the object itself.
        So the method's copy of the reference still points to the
        SAME object in memory. If the method changes the object's
        CONTENTS (like array[0] = 99), the original sees that change,
        because both references point to the same place.
        BUT if the method reassigns the parameter to a whole new
        object, that only changes the LOCAL copy of the reference —
        the original variable back in the caller still points to the
        old object, untouched.

      This is why people say "Java passes objects by reference" —
      it's not quite right. Java passes the REFERENCE by VALUE.

   4. SCOPE
      A variable only exists within the block { } it's declared in.
      Once that block ends, the variable is gone.
      Variables declared inside a method are LOCAL — invisible
      outside that method entirely.

   5. RECURSION (intro)
      A method that calls itself. Every recursive method needs:
      - a BASE CASE — the condition where it stops calling itself
      - a RECURSIVE CASE — where it calls itself with a smaller/
        simpler version of the problem, moving toward the base case
      Without a base case (or a bug that never reaches it), you get
      infinite recursion, which crashes with a StackOverflowError —
      the call stack literally runs out of room.

   ================================================================
   DRY RUN — trace both of these on paper BEFORE reading the code.
   ------------------------------------------------------------
   TRACE 1 — primitive passed to a method:
       void tryChange(int x) { x = 99; }
       int a = 5;
       tryChange(a);
       // what is 'a' now?

     Call tryChange(a) -> Java copies the VALUE 5 into a new local
     variable x inside tryChange's stack frame. x becomes 99 — but
     that only changes the COPY. Once tryChange returns and its frame
     is popped, 'a' back in the caller is still 5. Untouched.

   TRACE 2 — array (object) passed to a method:
       void tryChange(int[] arr) { arr[0] = 99; }
       int[] nums = {5, 6, 7};
       tryChange(nums);
       // what is nums[0] now?

     Call tryChange(nums) -> Java copies the REFERENCE (the address)
     into arr. arr and nums now both point to the SAME array in
     memory. arr[0] = 99 changes the actual array contents at that
     address — and since nums points to that same address, nums[0]
     is now 99 too. The original WAS changed this time.

   Same "pass by value" rule both times — the difference is WHAT
   value gets copied: the number itself, or the address to an object.
   ================================================================ */

public class Day03_FunctionsAndScope {

    public static void main(String[] args) {
        System.out.println("=== CODE DEMO ===");
        demoConcepts();

        System.out.println("\n=== TIER 1 — Concept Check ===");
        System.out.println("[T1] isEven(7) = " + tier1_IsEven(7));
        System.out.println("[T1] maxOfThree(4, 9, 2) = " + tier1_MaxOfThree(4, 9, 2));

        System.out.println("\n=== TIER 2 — Interview Style ===");
        System.out.println("[T2] factorial(5) = " + tier2_Factorial(5));
        System.out.println("[T2] fibonacci(7) = " + tier2_Fibonacci(7));

        System.out.println("\n=== TIER 3 — CP / Edge Cases ===");
        System.out.println("[T3] fastPower(2, 10) = " + tier3_FastPower(2, 10));
        System.out.println("[T3] gcd(48, 18) = " + tier3_Gcd(48, 18));
        System.out.println("[T3] gcd(0, 7) = " + tier3_Gcd(0, 7));

        System.out.println("\n=== SELF TEST (predict on paper FIRST, then run and check) ===");
        runSelfTests();
    }

    // ================================================================
    // CODE DEMO
    // ================================================================
    static void demoConcepts() {
        // ---- basic function with return type ----
        int sum = add(3, 4);
        System.out.println("add(3, 4) = " + sum);

        // ---- pass by value: primitive ----
        int a = 5;
        tryChangePrimitive(a);
        System.out.println("After tryChangePrimitive, a is still: " + a);

        // ---- pass by value: reference (object contents CAN change) ----
        int[] nums = {5, 6, 7};
        tryChangeArrayContents(nums);
        System.out.println("After tryChangeArrayContents, nums[0] is now: " + nums[0]);

        // ---- reassigning a reference parameter does NOT affect the caller ----
        int[] nums2 = {1, 2, 3};
        tryReassignArray(nums2);
        System.out.println("After tryReassignArray, nums2[0] is still: " + nums2[0]);

        // ---- scope ----
        int outer = 100;
        {
            int inner = 200;   // only exists inside this block
            System.out.println("Inside block: outer=" + outer + " inner=" + inner);
        }
        // inner is NOT visible here anymore — uncommenting the next line
        // would cause a compile error: "cannot find symbol"
        // System.out.println(inner);
        System.out.println("Outside block: outer=" + outer + " (inner no longer exists)");

        // ---- recursion with visible call stack depth ----
        System.out.print("Recursion trace for factorialTraced(4): ");
        factorialTraced(4, 1);
        System.out.println();
    }

    static int add(int a, int b) {
        return a + b;
    }

    static void tryChangePrimitive(int x) {
        x = 99;   // only changes the local copy
    }

    static void tryChangeArrayContents(int[] arr) {
        arr[0] = 99;   // changes the actual shared array
    }

    static void tryReassignArray(int[] arr) {
        arr = new int[]{999, 999, 999};   // arr now points elsewhere;
                                           // the caller's reference is untouched
    }

    // Recursion that prints its own depth, to make the call stack visible
    static int factorialTraced(int n, int depth) {
        System.out.print("[depth " + depth + ": n=" + n + "] ");
        if (n <= 1) return 1;               // base case
        return n * factorialTraced(n - 1, depth + 1);  // recursive case
    }

    // ================================================================
    // TIER 1 — Concept Check
    // ================================================================

    static boolean tier1_IsEven(int n) {
        return n % 2 == 0;
        // Time: O(1)  Space: O(1)
    }

    static int tier1_MaxOfThree(int a, int b, int c) {
        int max = a;
        if (b > max) max = b;
        if (c > max) max = c;
        return max;
        // Time: O(1)  Space: O(1)
    }

    // ================================================================
    // TIER 2 — Interview Style
    // ================================================================

    // Factorial via recursion.
    // PATTERN RECOGNITION: the "multiply by n, recurse on n-1" shape
    // is the simplest possible recursion template — every recursive
    // solution you'll ever write follows this same base+recursive
    // case structure, just with different logic inside.
    static long tier2_Factorial(int n) {
        if (n <= 1) return 1;         // base case
        return n * tier2_Factorial(n - 1);   // recursive case
        // Time: O(n)  Space: O(n) — due to n stack frames stacking up
    }

    // Fibonacci via plain recursion.
    // WATCH OUT: this is deliberately the SLOW version — it recomputes
    // the same values over and over. fibonacci(7) alone makes 40+ calls.
    // This exact inefficiency is the reason Phase 5 (Dynamic Programming)
    // exists — DP is essentially "recursion that remembers its answers."
    static long tier2_Fibonacci(int n) {
        if (n <= 1) return n;          // base case: fib(0)=0, fib(1)=1
        return tier2_Fibonacci(n - 1) + tier2_Fibonacci(n - 2);
        // Time: O(2^n) — exponential, genuinely slow past ~n=35
        // Space: O(n) — max call stack depth at any one time
    }

    // ================================================================
    // TIER 3 — CP / Edge Cases
    // ================================================================

    // Fast exponentiation (power by squaring) — computes base^exp in
    // O(log exp) instead of the naive O(exp).
    // PATTERN RECOGNITION: "halve the problem each recursive call"
    // is the divide-and-conquer shape — same idea behind binary search
    // and merge sort later in the roadmap.
    static long tier3_FastPower(long base, int exp) {
        if (exp == 0) return 1;                       // base case
        long half = tier3_FastPower(base, exp / 2);
        if (exp % 2 == 0) {
            return half * half;
        } else {
            return half * half * base;
        }
        // Time: O(log exp)  Space: O(log exp)
        // EDGE CASE to think about: negative exponents aren't handled
        // here on purpose — that needs a double return type instead.
    }

    // GCD via the Euclidean algorithm — a short, elegant recursion.
    // EDGE CASE handled: gcd(0, n) should return n, not crash or loop
    // forever — this works automatically because the base case is b==0.
    static int tier3_Gcd(int a, int b) {
        if (b == 0) return a;          // base case
        return tier3_Gcd(b, a % b);    // recursive case
        // Time: O(log(min(a,b)))  Space: O(log(min(a,b)))
    }

    // ================================================================
    // SELF TEST
    // ================================================================
    static void runSelfTests() {
        // Q1: pass by value with a primitive
        int a = 10;
        addFive(a);
        System.out.println("Q1 -> a after addFive(a) = " + a + "   (expected: 10, unchanged)");

        // Q2: pass by value with an array (contents change)
        int[] arr = {1, 2, 3};
        setFirstToZero(arr);
        System.out.println("Q2 -> arr[0] after setFirstToZero(arr) = " + arr[0] + "   (expected: 0)");

        // Q3: scope — this variable only exists inside the if block
        int result = 0;
        if (true) {
            int temp = 50;
            result = temp;
        }
        System.out.println("Q3 -> result = " + result + "   (expected: 50 — temp itself no longer exists here, but its value was copied out via result)");

        // Q4: recursion base case check
        System.out.println("Q4 -> tier2_Factorial(0) = " + tier2_Factorial(0) + "   (expected: 1, by definition)");
    }

    static void addFive(int x) {
        x = x + 5;
    }

    static void setFirstToZero(int[] arr) {
        arr[0] = 0;
    }
}

/* ================================================================
   REEL IDEA — Day 3 (Functions & Scope)
   No reveal in the video. Answer gets pinned in the comments after posting.
   ------------------------------------------------------------
   Hook overlay (code on screen):
   "Everyone says Java passes objects 'by reference.' This proves
   that's not quite true."

       static void tryReplace(int[] arr) {
           arr = new int[]{99, 99, 99};
       }

       int[] nums = {1, 2, 3};
       tryReplace(nums);
       System.out.println(nums[0]);

   Question overlay: "Comment A or B:
   A) 99   B) 1"

   End screen: "Correct answer + why, pinned below 📌"

   Caption: "If Java really passed objects by reference, this would
   print 99. It doesn't. Comment A or B — the real answer explains
   something most Java tutorials get slightly wrong."

   Pinned answer (post 15-30 min after upload):
   "Answer: B) 1. Java is ALWAYS pass-by-value — for objects, what
   gets copied is the REFERENCE (address), not the object. Reassigning
   the parameter inside the method just points the LOCAL copy of that
   reference somewhere new — the original variable back in main()
   still points to the old array, completely untouched. This is why
   'pass by reference' is technically the wrong term for Java."
   ================================================================ */
