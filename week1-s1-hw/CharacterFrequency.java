import java.util.Scanner;
public class CharacterFrequency {
    public static char[] uniqueCharacters(String text) {
        String unique = "";
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            boolean found = false;
            for (int j = 0; j < unique.length(); j++) {
                if (unique.charAt(j) == ch) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                unique += ch;
            }
        }
        return unique.toCharArray();
    }
    public static String[][] frequencyOfCharacters(String text) {
        int[] freq = new int[256]; 
        for (int i = 0; i < text.length(); i++) {
            freq[text.charAt(i)]++;
        }
        char[] unique = uniqueCharacters(text);
        String[][] result = new String[unique.length][2];
        for (int i = 0; i < unique.length; i++) {
            result[i][0] = String.valueOf(unique[i]);    
            result[i][1] = String.valueOf(freq[unique[i]]); 
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();
        String[][] result = frequencyOfCharacters(input);
        System.out.println("\nCharacter Frequencies:");
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i][0] + " -> " + result[i][1]);
        }
        sc.close();
    }
}
