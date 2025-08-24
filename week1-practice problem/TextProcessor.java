import java.util.*;

public class TextProcessor {

    public static String cleanInput(String input) {
        return input.trim().replaceAll("\\s+", " ");
    }

    public static void analyzeText(String text) {
        String[] words = text.replaceAll("[^a-zA-Z0-9\\s]", "").split("\\s+");
        System.out.println("Words: " + words.length);
        System.out.println("Characters (no spaces): " + text.replace(" ", "").length());
        System.out.println("Sentences: " + text.split("[.!?]").length);

        String longest = "";
        for (String w : words) if (w.length() > longest.length()) longest = w;
        System.out.println("Longest Word: " + longest);
    }

    public static String[] getWordsSorted(String text) {
        String[] words = text.replaceAll("[^a-zA-Z0-9\\s]", "").split("\\s+");
        Arrays.sort(words, String.CASE_INSENSITIVE_ORDER);
        return words;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a paragraph: ");
        String text = cleanInput(sc.nextLine());

        analyzeText(text);

        System.out.println("\nSorted Words:");
        for (String w : getWordsSorted(text)) System.out.println(w);

        System.out.print("\nSearch word: ");
        String search = sc.nextLine();
        boolean found = Arrays.stream(getWordsSorted(text)).anyMatch(w -> w.equalsIgnoreCase(search));
        System.out.println(found ? "Found!" : "Not Found!");

        sc.close();
    }
}
