import java.util.*;

public class TextFormatter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text:");
        String text = sc.nextLine();
        System.out.print("Enter line width: ");
        int width = sc.nextInt();
        sc.close();

        String[] words = extractWords(text);
        List<String> justified = justifyText(words, width);
        List<String> centered = centerAlign(justified, width);

        System.out.println("\nOriginal Text:\n" + text);
        System.out.println("\nJustified Text:");
        displayFormatted(justified);
        System.out.println("\nCenter-Aligned Text:");
        displayFormatted(centered);

        comparePerformance(words, width);
    }

    static String[] extractWords(String text) {
        List<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if (start != i) words.add(text.substring(start, i));
                start = i + 1;
            }
        }
        if (start < text.length()) words.add(text.substring(start));
        return words.toArray(new String[0]);
    }

    static List<String> justifyText(String[] words, int width) {
        List<String> result = new ArrayList<>();
        int i = 0;
        while (i < words.length) {
            int lineLen = words[i].length();
            int j = i + 1;
            while (j < words.length && lineLen + 1 + words[j].length() <= width) {
                lineLen += 1 + words[j].length();
                j++;
            }
            StringBuilder line = new StringBuilder();
            int gaps = j - i - 1;
            if (j == words.length || gaps == 0) {
                for (int k = i; k < j; k++) {
                    line.append(words[k]);
                    if (k < j - 1) line.append(" ");
                }
                while (line.length() < width) line.append(" ");
            } else {
                int spaces = (width - lineLen + gaps) / gaps;
                int extra = (width - lineLen + gaps) % gaps;
                for (int k = i; k < j; k++) {
                    line.append(words[k]);
                    if (k < j - 1) {
                        for (int s = 0; s < spaces; s++) line.append(" ");
                        if (extra-- > 0) line.append(" ");
                    }
                }
            }
            result.add(line.toString());
            i = j;
        }
        return result;
    }

    static List<String> centerAlign(List<String> lines, int width) {
        List<String> centered = new ArrayList<>();
        for (String line : lines) {
            String trimmed = line.stripTrailing();
            int pad = width - trimmed.length();
            int left = pad / 2;
            int right = pad - left;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < left; i++) sb.append(" ");
            sb.append(trimmed);
            for (int i = 0; i < right; i++) sb.append(" ");
            centered.add(sb.toString());
        }
        return centered;
    }

    static void displayFormatted(List<String> lines) {
        int count = 1;
        for (String line : lines) {
            System.out.printf("%2d | %s | %d\n", count++, line, line.length());
        }
    }

    static void comparePerformance(String[] words, int width) {
        long start = System.nanoTime();
        justifyText(words, width);
        long sbTime = System.nanoTime() - start;

        start = System.nanoTime();
        List<String> concat = new ArrayList<>();
        int i = 0;
        while (i < words.length) {
            int lineLen = words[i].length();
            int j = i + 1;
            while (j < words.length && lineLen + 1 + words[j].length() <= width) {
                lineLen += 1 + words[j].length();
                j++;
            }
            String line = "";
            for (int k = i; k < j; k++) line += words[k] + " ";
            concat.add(line);
            i = j;
        }
        long strTime = System.nanoTime() - start;

        System.out.println("\nPerformance Comparison:");
        System.out.printf("StringBuilder: %d ns\n", sbTime);
        System.out.printf("String Concatenation: %d ns\n", strTime);
    }
}
