import java.util.*;

public class ManualReplace {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter main text: ");
        String text = sc.nextLine();
        System.out.print("Enter substring to find: ");
        String find = sc.nextLine();
        System.out.print("Enter replacement substring: ");
        String replace = sc.nextLine();

        String manualResult = manualReplace(text, find, replace);
        String builtinResult = text.replace(find, replace);

        System.out.println("\nManual Result:   " + manualResult);
        System.out.println("Built-in Result: " + builtinResult);
        System.out.println("Match? " + manualResult.equals(builtinResult));
        sc.close();
    }

    public static String manualReplace(String text, String find, String replace) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < text.length()) {
            int pos = text.indexOf(find, i);
            if (pos == -1) {
                result.append(text.substring(i));
                break;
            }
            result.append(text, i, pos);
            result.append(replace);
            i = pos + find.length();
        }
        return result.toString();
    }
}
