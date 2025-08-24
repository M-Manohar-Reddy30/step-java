import java.util.*;

public class AdvancedStringAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== ADVANCED STRING ANALYZER ===");
        System.out.print("Enter first string: ");
        String s1 = sc.nextLine();
        System.out.print("Enter second string: ");
        String s2 = sc.nextLine();
        performAllComparisons(s1, s2);
        analyzeMemoryUsage(s1, s2);
        System.out.println("Optimized Processing: " + optimizedStringProcessing(new String[]{s1, s2}));
        demonstrateStringIntern();
        sc.close();
    }

    public static double calculateSimilarity(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++)
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) dp[i][j] = j;
                else if (j == 0) dp[i][j] = i;
                else dp[i][j] = (a.charAt(i - 1) == b.charAt(j - 1)) ? dp[i - 1][j - 1]
                        : 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
            }
        int dist = dp[a.length()][b.length()];
        int maxLen = Math.max(a.length(), b.length());
        return maxLen == 0 ? 100.0 : (100.0 * (maxLen - dist) / maxLen);
    }

    public static void performAllComparisons(String s1, String s2) {
        System.out.println("Reference Equality (==): " + (s1 == s2));
        System.out.println("Content Equality (equals): " + s1.equals(s2));
        System.out.println("Ignore Case Equality: " + s1.equalsIgnoreCase(s2));
        System.out.println("Lexicographic CompareTo: " + s1.compareTo(s2));
        System.out.println("Lexicographic CompareToIgnoreCase: " + s1.compareToIgnoreCase(s2));
        System.out.println("Similarity %: " + calculateSimilarity(s1, s2));
    }

    public static void analyzeMemoryUsage(String... strs) {
        for (String s : strs) System.out.println("Approx memory for \"" + s + "\": " + (40 + 2 * s.length()) + " bytes");
    }

    public static String optimizedStringProcessing(String[] inputs) {
        StringBuilder sb = new StringBuilder();
        for (String s : inputs) sb.append(s).append(" ");
        return sb.toString().trim();
    }

    public static void demonstrateStringIntern() {
        String a = new String("Hello");
        String b = "Hello";
        String c = a.intern();
        System.out.println("a==b: " + (a == b));
        System.out.println("b==c: " + (b == c));
    }
}
