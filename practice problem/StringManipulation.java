import java.io.PrintStream;

public class StringManipulation {
   public StringManipulation() {
   }

   public static void main(String[] var0) {
      String var1 = "Java Programming";
      String var2 = new String("Java Programming");
      char[] var3 = new char[]{'J', 'a', 'v', 'a', ' ', 'P', 'r', 'o', 'g', 'r', 'a', 'm', 'm', 'i', 'n', 'g'};
      String var4 = new String(var3);
      System.out.println("String 1: " + var1);
      System.out.println("String 2: " + var2);
      System.out.println("String 3: " + var4);
      System.out.println("\nComparing with '==':");
      System.out.println("str1 == str2: " + (var1 == var2));
      System.out.println("str1 == str3: " + (var1 == var4));
      System.out.println("\nComparing with .equals():");
      PrintStream var10000 = System.out;
      boolean var10001 = var1.equals(var2);
      var10000.println("str1.equals(str2): " + var10001);
      var10000 = System.out;
      var10001 = var1.equals(var4);
      var10000.println("str1.equals(str3): " + var10001);
      System.out.println("\nExplanation:");
      System.out.println("The '==' operator checks if both variables refer to the SAME object in memory.");
      System.out.println(".equals() checks if the values (contents) of the strings are the same.");
      System.out.println("String literals with the same value share memory in the String Pool, but 'new String()' creates a new object.");
      String var5 = "Programming Quote:\n\"Code is poetry\" - Unknown\nPath: C:\\Java\\Projects";
      System.out.println("\n" + var5);
   }
}
