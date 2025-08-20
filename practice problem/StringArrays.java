public class StringArrays {
    public static String findLongestName(String[] names) {
        String longest = names[0];
        for (String name : names) {
            if (name.length() > longest.length()) {
                longest = name;
            }
        }
        return longest;
    }
    public static int countNamesStartingWith(String[] names, char letter) {
        int count = 0;
        char target = Character.toLowerCase(letter);
        for (String name : names) {
            if (!name.isEmpty() && Character.toLowerCase(name.charAt(0)) == target) {
                count++;
            }
        }
        return count;
    }
    public static String[] formatNames(String[] names) {
        String[] formatted = new String[names.length];
        for (int i = 0; i < names.length; i++) {
            String[] parts = names[i].split(" ");
            if (parts.length >= 2) {
                formatted[i] = parts[1] + ", " + parts[0];
            } else {
                formatted[i] = names[i]; 
            }
        }
        return formatted;
    }
    public static void main(String[] args) {
        String[] students = {
            "John Smith", "Alice Johnson", "Bob Brown",
            "Carol Davis", "David Wilson"
        };
        String longestName = findLongestName(students);
        System.out.println("Longest Name: " + longestName);

        int countA = countNamesStartingWith(students, 'A');
        System.out.println("Names starting with 'A': " + countA);

        String[] formattedNames = formatNames(students);
        System.out.println("Formatted Names:");
        for (String name : formattedNames) {
            System.out.println(name);
        }
    }
}
