import java.util.*;

public class StringUtilityApp {
    static StringBuilder out = new StringBuilder();
    public static void main(String[] a) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1.Text 2.Transform 3.ASCII 4.Perf 5.Compare 6.Custom 0.Exit");
            int c = sc.nextInt(); sc.nextLine(); if (c == 0) break;
            switch (c) {
                case 1 -> { System.out.print("Text: "); text(sc.nextLine()); }
                case 2 -> { System.out.print("Text: "); String t = sc.nextLine();
                            System.out.print("Ops (comma): "); System.out.println(transform(t, sc.nextLine().split(","))); }
                case 3 -> { System.out.print("Text: "); ascii(sc.nextLine()); }
                case 4 -> { System.out.print("Iterations: "); perf(sc.nextInt()); }
                case 5 -> { System.out.print("Count: "); int n = sc.nextInt(); sc.nextLine();
                            String[] arr = new String[n]; for (int i = 0; i < n; i++) arr[i] = sc.nextLine(); compare(arr); }
                case 6 -> { System.out.print("Text: "); custom(sc.nextLine()); }
            }
        }
        System.out.println("\n=== RESULTS ===\n" + out);
    }

    static void text(String t) {
        String[] w = t.trim().split("\\s+"), s = t.split("[.!?]");
        out.append("Len: ").append(t.length()).append(" Words: ").append(w.length)
           .append(" Sentences: ").append(s.length).append("\n");
        Map<Character, Integer> f = new HashMap<>();
        for (char c : t.toCharArray()) if (Character.isLetterOrDigit(c)) f.put(c, f.getOrDefault(c, 0) + 1);
        out.append("Freq: ").append(f).append("\n");
    }

    static String transform(String t, String[] ops) {
        StringBuilder sb = new StringBuilder(t);
        for (String o : ops) switch (o.trim().toLowerCase()) {
            case "trim" -> t = t.trim();
            case "upper" -> t = t.toUpperCase();
            case "lower" -> t = t.toLowerCase();
            case "reverse" -> sb.reverse();
        }
        return oContains(ops, "reverse") ? sb.toString() : t;
    }

    static void ascii(String t) {
        for (char c : t.toCharArray()) out.append(c).append(":").append((int) c).append(" ");
        out.append("\nCipher(+3): ").append(caesar(t, 3)).append("\n");
    }

    static void perf(int n) {
        long t1 = time(() -> { String s = ""; for (int i = 0; i < n; i++) s += "x"; });
        long t2 = time(() -> { StringBuilder sb = new StringBuilder(); for (int i = 0; i < n; i++) sb.append("x"); });
        long t3 = time(() -> { StringBuffer sbf = new StringBuffer(); for (int i = 0; i < n; i++) sbf.append("x"); });
        out.append("String:").append(t1).append("ns SB:").append(t2).append("ns SBF:").append(t3).append("ns\n");
    }

    static void compare(String[] s) {
        for (int i = 0; i < s.length - 1; i++) {
            String a = s[i], b = s[i + 1];
            out.append(a).append(" vs ").append(b).append(" == ").append(a == b)
               .append(" equals ").append(a.equals(b)).append(" ignore ").append(a.equalsIgnoreCase(b))
               .append(" cmp ").append(a.compareTo(b)).append(" sim ").append(sim(a, b)).append("%\n");
        }
    }

    static void custom(String t) {
        String r = new StringBuilder(t).reverse().toString();
        out.append("Palindrome: ").append(t.equalsIgnoreCase(r)).append("\n");
    }

    static String caesar(String t, int s) {
        StringBuilder r = new StringBuilder();
        for (char c : t.toCharArray()) {
            if (Character.isUpperCase(c)) r.append((char) ('A' + (c - 'A' + s + 26) % 26));
            else if (Character.isLowerCase(c)) r.append((char) ('a' + (c - 'a' + s + 26) % 26));
            else r.append(c);
        }
        return r.toString();
    }

    static double sim(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++)
            for (int j = 0; j <= b.length(); j++)
                dp[i][j] = (i == 0) ? j : (j == 0) ? i :
                    (a.charAt(i - 1) == b.charAt(j - 1)) ? dp[i - 1][j - 1] :
                    1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
        int d = dp[a.length()][b.length()], m = Math.max(a.length(), b.length());
        return m == 0 ? 100.0 : (100.0 * (m - d) / m);
    }

    static boolean oContains(String[] arr, String val) {
        for (String s : arr) if (s.trim().equalsIgnoreCase(val)) return true;
        return false;
    }

    static long time(Runnable r) { long t = System.nanoTime(); r.run(); return System.nanoTime() - t; }
}
