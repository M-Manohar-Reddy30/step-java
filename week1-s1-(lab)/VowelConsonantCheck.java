import java.util.*;

public class VowelConsonantCheck {
        static String charType(char ch) {
        if (ch >= 'A' && ch <= 'Z') ch = (char)(ch + 32);
        if (ch >= 'a' && ch <= 'z')
            return "aeiou".indexOf(ch) != -1 ? "Vowel" : "Consonant";
        return "Not a Letter";
    }

    static String[][] analyze(String s) {
        String[][] result = new String[s.length()][2];
        for (int i = 0; i < s.length(); i++) {
            result[i][0] = String.valueOf(s.charAt(i));
            result[i][1] = charType(s.charAt(i));
        }
        return result;
    }

    static void display(String[][] arr) {
        System.out.println("Char\tType");
        for (String[] row : arr)
            System.out.println(row[0] + "\t" + row[1]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        display(analyze(sc.nextLine()));
    }
}
