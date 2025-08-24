import java.util.*;

public class CaseConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();

        String upper = toUpper(text);
        String lower = toLower(text);
        String title = toTitle(text);

        System.out.println("\nOriginal   : " + text);
        System.out.println("Upper(ASCII): " + upper + " | Built-in: " + text.toUpperCase());
        System.out.println("Lower(ASCII): " + lower + " | Built-in: " + text.toLowerCase());
        System.out.println("Title(ASCII): " + title);
        sc.close();
    }

    public static String toUpper(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c >= 'a' && c <= 'z') sb.append((char)(c - 32));
            else sb.append(c);
        }
        return sb.toString();
    }

    public static String toLower(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c >= 'A' && c <= 'Z') sb.append((char)(c + 32));
            else sb.append(c);
        }
        return sb.toString();
    }

    public static String toTitle(String text) {
        StringBuilder sb = new StringBuilder();
        boolean start = true;
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                if (start && c >= 'a' && c <= 'z') sb.append((char)(c - 32));
                else if (!start && c >= 'A' && c <= 'Z') sb.append((char)(c + 32));
                else sb.append(c);
                start = false;
            } else {
                sb.append(c);
                if (c == ' ') start = true;
            }
        }
        return sb.toString();
    }
}
