import java.util.Scanner;

public class StringMethods {
   public StringMethods() {
   }

   public static void main(String[] var0) {
      Scanner var1 = new Scanner(System.in);
      System.out.print("Enter your full name (First Last): ");
      String var2 = var1.nextLine();
      System.out.print("Enter your favorite programming language: ");
      String var3 = var1.nextLine();
      System.out.print("Enter a sentence about your programming experience: ");
      String var4 = var1.nextLine();
      String var5 = "";
      String var6 = "";
      String[] var7 = var2.trim().split("\\s+");
      if (var7.length >= 2) {
         var5 = var7[0];
         var6 = var7[1];
      } else {
         var5 = var2;
         var6 = "(not provided)";
      }

      int var8 = var4.replace(" ", "").length();
      String var9 = var3.toUpperCase();
      System.out.println("\n--- Summary ---");
      System.out.println("First Name: " + var5);
      System.out.println("Last Name: " + var6);
      System.out.println("Favorite Language (uppercase): " + var9);
      System.out.println("Experience Sentence: " + var4);
      System.out.println("Character Count (excluding spaces): " + var8);
      var1.close();
   }
}
