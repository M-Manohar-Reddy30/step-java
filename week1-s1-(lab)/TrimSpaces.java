import java.util.*;

public class TrimSpaces {
    static int[] findTrimIndexes(String s) {
        int start = 0, end = s.length() - 1;
        while (start <= end && s.charAt(start) == ' ') start++;
        while (end >= start && s.charAt(end) == ' ') end--;
        return new int[]{start, end};
    }
    static String mySubstring(String s, int start, int end) {
        String result = "";
        for (int i = start; i <= end; i++) result += s.charAt(i);
        return result;
    }
    static boolean myEquals(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        for (int i = 0; i < s1.length(); i++)
            if (s1.charAt(i) != s2.charAt(i)) return false;
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text with spaces: ");
        String input = sc.nextLine();

        int[] indexes = findTrimIndexes(input);
        String customTrim = mySubstring(input, indexes[0], indexes[1]);
        String builtInTrim = input.trim();

        System.out.println("Custom Trim: '" + customTrim + "'");
        System.out.println("Built-in Trim: '" + builtInTrim + "'");
        System.out.println("Match? " + myEquals(customTrim, builtInTrim));
    }
}
