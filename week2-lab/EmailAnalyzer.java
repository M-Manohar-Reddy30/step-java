import java.util.*;

public class EmailAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of emails:");
        int n = sc.nextInt();
        sc.nextLine();
        String[] emails = new String[n];
        for (int i = 0; i < n; i++) {
            emails[i] = sc.nextLine();
        }
        analyzeEmails(emails);
        sc.close();
    }

    public static boolean isValidEmail(String email) {
        int atPos = email.indexOf('@');
        int lastAtPos = email.lastIndexOf('@');
        int dotPos = email.lastIndexOf('.');
        if (atPos <= 0 || atPos != lastAtPos || dotPos < atPos + 2 || dotPos == email.length() - 1) {
            return false;
        }
        return true;
    }

    public static String[] extractComponents(String email) {
        int atPos = email.indexOf('@');
        int dotPos = email.lastIndexOf('.');
        String username = email.substring(0, atPos);
        String domain = email.substring(atPos + 1);
        String domainName = email.substring(atPos + 1, dotPos);
        String extension = email.substring(dotPos + 1);
        return new String[]{username, domain, domainName, extension};
    }

    public static void analyzeEmails(String[] emails) {
        int validCount = 0, invalidCount = 0, totalUserLen = 0;
        Map<String, Integer> domainCount = new HashMap<>();
        System.out.printf("%-25s %-15s %-20s %-15s %-10s %-10s\n", "Email", "Username", "Domain", "Domain Name", "Ext", "Valid");
        for (String email : emails) {
            if (isValidEmail(email)) {
                validCount++;
                String[] parts = extractComponents(email);
                totalUserLen += parts[0].length();
                domainCount.put(parts[1], domainCount.getOrDefault(parts[1], 0) + 1);
                System.out.printf("%-25s %-15s %-20s %-15s %-10s %-10s\n", email, parts[0], parts[1], parts[2], parts[3], "Yes");
            } else {
                invalidCount++;
                System.out.printf("%-25s %-15s %-20s %-15s %-10s %-10s\n", email, "-", "-", "-", "-", "No");
            }
        }
        String mostCommonDomain = domainCount.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("None");
        double avgUserLen = validCount > 0 ? (double) totalUserLen / validCount : 0;
        System.out.println("\nStatistics:");
        System.out.println("Valid emails: " + validCount);
        System.out.println("Invalid emails: " + invalidCount);
        System.out.println("Most common domain: " + mostCommonDomain);
        System.out.println("Average username length: " + avgUserLen);
    }
}
