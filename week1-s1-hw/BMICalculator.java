import java.util.Scanner;
public class BMICalculator {
    public static String[][] calculateBMI(double[][] hwData) {
        String[][] result = new String[hwData.length][4]; 

        for (int i = 0; i < hwData.length; i++) {
            double weight = hwData[i][0];
            double heightCm = hwData[i][1];
            double heightM = heightCm / 100; 
            double bmi = weight / (heightM * heightM);

            String status;
            if (bmi < 18.5) status = "Underweight";
            else if (bmi < 24.9) status = "Normal";
            else if (bmi < 29.9) status = "Overweight";
            else status = "Obese";

            result[i][0] = String.format("%.2f", heightCm);
            result[i][1] = String.format("%.2f", weight);
            result[i][2] = String.format("%.2f", bmi);
            result[i][3] = status;
        }
        return result;
    }
    public static void displayTable(String[][] data) {
        System.out.printf("%-10s %-10s %-10s %-15s\n", "Height(cm)", "Weight(kg)", "BMI", "Status");
        System.out.println("------------------------------------------------------");
        for (String[] row : data) {
            System.out.printf("%-10s %-10s %-10s %-15s\n", row[0], row[1], row[2], row[3]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[][] hwData = new double[4][2];

        for (int i = 0; i < 4; i++) {
            System.out.println("Enter details for Person " + (i + 1));
            System.out.print("Weight (kg): ");
            hwData[i][0] = sc.nextDouble();
            System.out.print("Height (cm): ");
            hwData[i][1] = sc.nextDouble();
        }

        String[][] bmiData = calculateBMI(hwData);
        displayTable(bmiData);
        sc.close();
    }
}
