import java.util.*;

class FileInfo {
    String original, name, ext, category, newName;
    FileInfo(String f) {
        original = f;
        int dot = f.lastIndexOf(".");
        if (dot > 0) { name = f.substring(0, dot); ext = f.substring(dot).toLowerCase(); }
        else { name = f; ext = ""; }
    }
}

public class FileOrganizer {
    static String categorize(String ext) {
        if (ext.equals(".txt") || ext.equals(".doc")) return "Document";
        if (ext.equals(".jpg") || ext.equals(".png")) return "Image";
        if (ext.equals(".mp3") || ext.equals(".wav")) return "Audio";
        if (ext.equals(".mp4") || ext.equals(".avi")) return "Video";
        return "Unknown";
    }

    static void renameFiles(List<FileInfo> files) {
        Map<String,Integer> count = new HashMap<>();
        String date = java.time.LocalDate.now().toString();
        for (FileInfo f : files) {
            f.category = categorize(f.ext);
            String base = f.category + "_" + date;
            int n = count.getOrDefault(base, 0) + 1;
            count.put(base, n);
            f.newName = base + "_" + n + f.ext;
        }
    }

    static void analyzeContent(FileInfo f, String content) {
        if (f.ext.equals(".txt")) {
            if (content.contains("resume")) f.category += "-Resume";
            else if (content.contains("report")) f.category += "-Report";
            else if (content.contains("class") || content.contains("public")) f.category += "-Code";
        }
    }

    static void report(List<FileInfo> files) {
        Map<String,Integer> catCount = new HashMap<>();
        System.out.printf("%-20s %-15s %-25s\n","Original","Category","New Name");
        for (FileInfo f : files) {
            System.out.printf("%-20s %-15s %-25s\n",f.original,f.category,f.newName);
            catCount.put(f.category,catCount.getOrDefault(f.category,0)+1);
        }
        System.out.println("\nCategory Counts:");
        for (String c : catCount.keySet()) System.out.println(c+": "+catCount.get(c));
    }

    static void batchCommands(List<FileInfo> files) {
        System.out.println("\nBatch Rename Commands:");
        for (FileInfo f : files)
            System.out.println("rename " + f.original + " " + f.newName);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<FileInfo> files = new ArrayList<>();
        System.out.println("Enter file names (end with empty line):");
        String line;
        while (!(line = sc.nextLine()).isEmpty()) files.add(new FileInfo(line));
        renameFiles(files);
        for (FileInfo f : files) if (f.ext.equals(".txt")) analyzeContent(f,"sample resume text");
        report(files);
        batchCommands(files);
    }
}
