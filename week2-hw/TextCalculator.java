import java.util.*;

public class TextCalculator {
    static boolean validate(String expr) {
        int bal = 0;
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (!(Character.isDigit(c) || "+-*/() ".indexOf(c) >= 0)) return false;
            if (c == '(') bal++;
            if (c == ')') bal--;
            if (bal < 0) return false;
        }
        return bal == 0;
    }
    static int eval(String expr) {
        while (expr.contains("(")) {
            int r = expr.indexOf(")");
            int l = expr.lastIndexOf("(", r);
            int val = eval(expr.substring(l + 1, r));
            expr = expr.substring(0, l) + val + expr.substring(r + 1);
        }
        List<Integer> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        for (int i = 0; i < expr.length();) {
            if (expr.charAt(i) == ' ') { i++; continue; }
            if ("+-*/".indexOf(expr.charAt(i)) >= 0) {
                ops.add(expr.charAt(i));
                i++;
            } else {
                int j = i;
                while (j < expr.length() && Character.isDigit(expr.charAt(j))) j++;
                nums.add(Integer.parseInt(expr.substring(i, j)));
                i = j;
            }
        }
        for (int k = 0; k < ops.size();) {
            if (ops.get(k) == '*' || ops.get(k) == '/') {
                int a = nums.remove(k), b = nums.remove(k);
                nums.add(k, ops.get(k) == '*' ? a * b : a / b);
                ops.remove(k);
            } else k++;
        }
        int res = nums.get(0);
        for (int k = 0; k < ops.size(); k++) {
            res = ops.get(k) == '+' ? res + nums.get(k + 1) : res - nums.get(k + 1);
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String expr = sc.nextLine();
        if (validate(expr)) {
            System.out.println("Original: " + expr);
            System.out.println("Result: " + eval(expr));
        } else System.out.println("Invalid Expression");
    }
}
