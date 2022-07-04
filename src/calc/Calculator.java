package calc;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {
    static Scanner scanner = new Scanner(System.in);
    static int a, b;
    static char op;
    static int result;
    static String input;
    private static String convertRoman (int num) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII",
                    "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII",
                    "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII",
                    "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII",
                    "XLV", "XLV", "XLV", "XVII", "XVIII", "XLSX", "L", "LI", "LII", "LIII", "LIV", "LV",
                    "LVI", "LVII", "LVII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII",
                    "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII",
                    "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI",
                    "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
                    "XCVIII", "XCIX", "C"
        };
        return roman[num];
    }
    private static int romNum(String roman) {
        try {
            switch (roman) {
                case "I":
                    return 1;
                case "II":
                    return 2;
                case "III":
                    return 3;
                case "IV":
                    return 4;
                case "V":
                    return 5;
                case "VI":
                    return 6;
                case "VII":
                    return 7;
                case "VIII":
                    return 8;
                case "IX":
                    return 9;
                case "X":
                    return 10;
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Не корректный ввод данных!");
        }
        return -1;
    }
    public static void stringProcessing() {
        System.out.print("Input: ");
        input = scanner.nextLine().replaceAll("\\s+", "");
        char[] arrChar = new char[input.length()];
        try {
            for (int i = 0; i < input.length(); i++) {
                arrChar[i] = input.charAt(i);
                if (arrChar[i] == '+') {
                    op = '+';
                }
                if (arrChar[i] == '-') {
                    op = '-';
                }
                if (arrChar[i] == '*') {
                    op = '*';
                }
                if (arrChar[i] == '/') {
                    op = '/';
                }
            }
        } catch (Exception e) {
            return;
        }
        input = String.valueOf(arrChar);
    }
    public static String calc() {
        stringProcessing();
        int replaceAll = input.replaceAll("\\d|[a-zA-Z]", "").length();
        if (replaceAll > 1) {
            System.out.println("Введено больше одного операнда!");
        }
        String[] strSplit = input.split("[+-/*]");
        a = romNum(strSplit[0]);
        b = romNum(strSplit[1].trim());
        if (a < 0 & b < 0) {
            result = 0;
        } else {
            result = math(a, b, op);
            if (result <= 0) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    return "В римской системе нет отрицательных чисел и ноля!";
                }
            }
            if (a < 0 || b < 0) {
                return "Используются одновременно разные системы счисления!";
            }
            return convertRoman(result);
        }
        try {
            a = Integer.parseInt(strSplit[0]);
            b = Integer.parseInt(strSplit[1].trim());
        } catch (NumberFormatException e) {
            return "Не корректный ввод данных!";
        }
        if (a < 0 | a > 10 | b < 0 | b > 10) {
            return "Не корректный ввод данных!";
        }
        return Integer.toString(math(a, b, op));
    }
    public static int math(int a, int b, char op) {
        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                try {
                    yield a / b;
                } catch (Exception e) {
                    throw new RuntimeException("На ноль делить нельзя!");
                }
            }
            default -> 0;
        };
    }
    public static void main(String[] args) {
        System.out.println("Output: " + calc());
    }
}
