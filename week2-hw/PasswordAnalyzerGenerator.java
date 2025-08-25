import java.util.*;

public class PasswordAnalyzerGenerator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of passwords to analyze:");
        int n = Integer.parseInt(sc.nextLine());
        String[] passwords = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Enter password " + (i + 1) + ":");
            passwords[i] = sc.nextLine();
        }
        System.out.printf("%-15s %-7s %-7s %-7s %-7s %-12s %-7s %-10s\n", "Password", "Length", "Upper", "Lower", "Digits", "SpecialChars", "Score", "Strength");
        for (String pw : passwords) {
            int[] counts = analyze(pw);
            int score = calculateScore(pw, counts);
            String strength = getStrength(score);
            System.out.printf("%-15s %-7d %-7d %-7d %-7d %-12d %-7d %-10s\n", pw, pw.length(), counts[0], counts[1], counts[2], counts[3], score, strength);
        }
        System.out.println("Enter desired length for generated password:");
        int len = Integer.parseInt(sc.nextLine());
        String strongPassword = generatePassword(len);
        System.out.println("Generated Strong Password: " + strongPassword);
    }

    static int[] analyze(String pw) {
        int upper = 0, lower = 0, digit = 0, special = 0;
        for (int i = 0; i < pw.length(); i++) {
            char c = pw.charAt(i);
            int ascii = (int) c;
            if (ascii >= 65 && ascii <= 90) upper++;
            else if (ascii >= 97 && ascii <= 122) lower++;
            else if (ascii >= 48 && ascii <= 57) digit++;
            else if (ascii >= 33 && ascii <= 126) special++;
        }
        return new int[]{upper, lower, digit, special};
    }

    static int calculateScore(String pw, int[] counts) {
        int score = 0;
        if (pw.length() > 8) score += (pw.length() - 8) * 2;
        if (counts[0] > 0) score += 10;
        if (counts[1] > 0) score += 10;
        if (counts[2] > 0) score += 10;
        if (counts[3] > 0) score += 10;
        String lower = pw.toLowerCase();
        if (lower.contains("123") || lower.contains("abc") || lower.contains("qwerty")) score -= 10;
        return score;
    }

    static String getStrength(int score) {
        if (score <= 20) return "Weak";
        else if (score <= 50) return "Medium";
        else return "Strong";
    }

    static String generatePassword(int len) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*()-_=+[]{}|;:,.<>?";
        String all = upper + lower + digits + special;
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(upper.charAt(rand.nextInt(upper.length())));
        sb.append(lower.charAt(rand.nextInt(lower.length())));
        sb.append(digits.charAt(rand.nextInt(digits.length())));
        sb.append(special.charAt(rand.nextInt(special.length())));
        for (int i = 4; i < len; i++) {
            sb.append(all.charAt(rand.nextInt(all.length())));
        }
        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < sb.length(); i++) chars.add(sb.charAt(i));
        Collections.shuffle(chars);
        StringBuilder finalPw = new StringBuilder();
        for (char c : chars) finalPw.append(c);
        return finalPw.toString();
    }
}
