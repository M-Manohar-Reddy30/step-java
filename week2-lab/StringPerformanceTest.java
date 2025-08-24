import java.util.*;

public class StringPerformanceTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of iterations: ");
        int n = sc.nextInt();

        Result r1 = testStringConcat(n);
        Result r2 = testStringBuilder(n);
        Result r3 = testStringBuffer(n);

        System.out.printf("%-15s %-15s %-15s%n", "Method", "Time(ms)", "Length");
        System.out.printf("%-15s %-15d %-15d%n", "String", r1.time, r1.length);
        System.out.printf("%-15s %-15d %-15d%n", "StringBuilder", r2.time, r2.length);
        System.out.printf("%-15s %-15d %-15d%n", "StringBuffer", r3.time, r3.length);

        sc.close();
    }

    static class Result {
        long time;
        int length;
        Result(long time, int length) { this.time = time; this.length = length; }
    }

    public static Result testStringConcat(int n) {
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < n; i++) s += "a";
        long end = System.currentTimeMillis();
        return new Result(end - start, s.length());
    }

    public static Result testStringBuilder(int n) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append("a");
        long end = System.currentTimeMillis();
        return new Result(end - start, sb.length());
    }

    public static Result testStringBuffer(int n) {
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) sb.append("a");
        long end = System.currentTimeMillis();
        return new Result(end - start, sb.length());
    }
}
