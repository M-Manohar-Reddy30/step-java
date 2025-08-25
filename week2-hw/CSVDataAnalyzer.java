import java.util.*;

public class CSVDataAnalyzer {
    static String[][] parseCSV(String input) {
        int rows = 1, cols = 1;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '\n') rows++;
            if (input.charAt(i) == ',' && rows == 1) cols++;
        }
        String[][] data = new String[rows][cols];
        int r = 0, c = 0, start = 0; boolean inQuotes = false;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '\"') inQuotes = !inQuotes;
            else if ((ch == ',' && !inQuotes) || ch == '\n') {
                data[r][c++] = input.substring(start, i).replace("\"", "");
                if (ch == '\n') { r++; c = 0; }
                start = i + 1;
            }
        }
        data[r][c] = input.substring(start).replace("\"", "");
        return data;
    }

    static void cleanData(String[][] data) {
        for (int i = 0; i < data.length; i++)
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = data[i][j].trim();
                if (data[i][j].isEmpty()) data[i][j] = "MISSING";
            }
    }

    static void analyzeData(String[][] data) {
        for (int col = 0; col < data[0].length; col++) {
            boolean numeric = true;
            for (int row = 1; row < data.length; row++)
                for (char ch : data[row][col].toCharArray())
                    if (ch < '0' || ch > '9') { numeric = false; break; }
            if (numeric) {
                int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, sum = 0, count = 0;
                for (int row = 1; row < data.length; row++) {
                    try {
                        int v = Integer.parseInt(data[row][col]);
                        min = Math.min(min, v); max = Math.max(max, v);
                        sum += v; count++;
                    } catch (Exception e) {}
                }
                if (count > 0) System.out.println(data[0][col] +
                    ": Min=" + min + " Max=" + max + " Avg=" + (sum / count));
            }
        }
    }

    static void formatTable(String[][] data) {
        int[] widths = new int[data[0].length];
        for (int j = 0; j < data[0].length; j++)
            for (int i = 0; i < data.length; i++)
                widths[j] = Math.max(widths[j], data[i][j].length()) + 2;
        for (String[] row : data) {
            System.out.print("|");
            for (int j = 0; j < row.length; j++)
                System.out.printf(" %-"+widths[j]+"s|", row[j]);
            System.out.println();
        }
    }

    static void generateSummary(String[][] data) {
        int total = data.length - 1, missing = 0;
        for (int i = 1; i < data.length; i++)
            for (String val : data[i]) if (val.equals("MISSING")) missing++;
        double comp = 100.0 * (total * data[0].length - missing) / (total * data[0].length);
        System.out.println("Total Records: " + total);
        System.out.println("Missing Fields: " + missing);
        System.out.println("Completeness: " + String.format("%.2f", comp) + "%");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        System.out.println("Enter CSV data (end with empty line):");
        String line; while (!(line = sc.nextLine()).isEmpty()) sb.append(line).append("\n");
        String[][] data = parseCSV(sb.toString());
        cleanData(data);
        System.out.println("\nFormatted Table:"); formatTable(data);
        System.out.println("\nData Analysis:"); analyzeData(data);
        System.out.println("\nSummary Report:"); generateSummary(data);
    }
}
