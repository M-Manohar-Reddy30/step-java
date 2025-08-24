import java.util.*;

public class CaesarCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        System.out.print("Enter shift value: ");
        int shift = sc.nextInt();
        String encrypted = encrypt(text, shift);
        String decrypted = decrypt(encrypted, shift);
        System.out.println("\nOriginal: " + text);
        displayAscii(text);
        System.out.println("Encrypted: " + encrypted);
        displayAscii(encrypted);
        System.out.println("Decrypted: " + decrypted);
        System.out.println("Valid: " + text.equals(decrypted));
        sc.close();
    }

    public static String encrypt(String text, int shift) {
        StringBuilder res = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c))
                res.append((char)('A' + (c - 'A' + shift + 26) % 26));
            else if (Character.isLowerCase(c))
                res.append((char)('a' + (c - 'a' + shift + 26) % 26));
            else res.append(c);
        }
        return res.toString();
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, -shift);
    }

    public static void displayAscii(String text) {
        for (char c : text.toCharArray())
            System.out.print(c + ":" + (int)c + " ");
        System.out.println();
    }
}
