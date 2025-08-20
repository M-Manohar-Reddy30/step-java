import java.util.*;

public class ShortestLongest {
    static int findLength(String s) {
        int count = 0;
        try { while (true) { s.charAt(count); count++; } }
        catch (Exception e) {}
        return count;
    }

    static String[] customSplit(String s) {
        List<String> words = new ArrayList<>();
        StringBuilder w = new StringBuilder();
        for (int i = 0; i < findLength(s); i++) {
            char c = s.charAt(i);
            if (c == ' ') { if (findLength(w.toString()) > 0) { words.add(w.toString()); w.setLength(0); } }
            else w.append(c);
        }
        if (findLength(w.toString()) > 0) words.add(w.toString());
        return words.toArray(new String[0]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String[] words = customSplit(sc.nextLine());

        String shortest = words[0], longest = words[0];
        for (String w : words) {
            if (findLength(w) < findLength(shortest)) shortest = w;
            if (findLength(w) > findLength(longest)) longest = w;
        }

        System.out.println("Shortest: " + shortest + " (" + findLength(shortest) + ")");
        System.out.println("Longest: " + longest + " (" + findLength(longest) + ")");
    }
}
