import java.util.*;

public class SpellChecker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] dictionary = {"java", "programming", "is", "fun", "and", "challenging", "to", "learn"};
        System.out.println("Enter a sentence:");
        String sentence = sc.nextLine();
        String[] words = extractWords(sentence);
        System.out.printf("%-15s %-15s %-10s %-15s%n", "Word", "Suggestion", "Distance", "Status");
        for (String word : words) {
            String suggestion = findClosestWord(word.toLowerCase(), dictionary);
            int distance = calculateDistance(word.toLowerCase(), suggestion.toLowerCase());
            if (word.equalsIgnoreCase(suggestion))
                System.out.printf("%-15s %-15s %-10d %-15s%n", word, "-", 0, "Correct");
            else
                System.out.printf("%-15s %-15s %-10d %-15s%n", word, suggestion, distance, "Misspelled");
        }
        sc.close();
    }

    public static String[] extractWords(String text) {
        ArrayList<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i <= text.length(); i++) {
            if (i == text.length() || !Character.isLetter(text.charAt(i))) {
                if (start < i) words.add(text.substring(start, i));
                start = i + 1;
            }
        }
        return words.toArray(new String[0]);
    }

    public static int calculateDistance(String w1, String w2) {
        int[][] dp = new int[w1.length() + 1][w2.length() + 1];
        for (int i = 0; i <= w1.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= w2.length(); j++) dp[0][j] = j;
        for (int i = 1; i <= w1.length(); i++) {
            for (int j = 1; j <= w2.length(); j++) {
                if (w1.charAt(i - 1) == w2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                else dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
            }
        }
        return dp[w1.length()][w2.length()];
    }

    public static String findClosestWord(String word, String[] dictionary) {
        String closest = word;
        int minDist = Integer.MAX_VALUE;
        for (String dictWord : dictionary) {
            int dist = calculateDistance(word, dictWord);
            if (dist < minDist) {
                minDist = dist;
                closest = dictWord;
            }
        }
        return closest;
    }
}
