import java.util.Scanner;
public class FrequencyNestedLoop {
    public static String[] findFrequency(String text) {
        char[] chars = text.toCharArray(); 
        int[] freq = new int[chars.length]; 
        for (int i = 0; i < chars.length; i++) {
            freq[i] = 1;  
            if (chars[i] == '0') 
                continue;

            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] == chars[j]) {
                    freq[i]++;      
                    chars[j] = '0'; 
                }
            }
        }
        String[] result = new String[chars.length];
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '0' && chars[i] != ' ') { 
                result[index] = chars[i] + " -> " + freq[i];
                index++;
            }
        }
        String[] finalResult = new String[index];
        System.arraycopy(result, 0, finalResult, 0, index);
        return finalResult;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();
        String[] result = findFrequency(input);
        System.out.println("\nCharacter Frequencies:");
        for (String r : result) {
            System.out.println(r);
        }

        sc.close();
    }
}
