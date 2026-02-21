import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        int intLegth =5;
//        int intWidth = 10;
//
//        int intArea = intLegth * intWidth;
//        int intPerimeter = 2 * (intLegth + intWidth);
//
//        System.out.println("Площа прямокутника(інт): " + intArea);
//        System.out.println("Периметр прямокутника(інт): " + intPerimeter);
//
//        double dLegth = 5.5;
//        double dWidth = 10.2;
//
//        double dArea = dLegth * dWidth;
//        double dPerimeter = 2 * (dLegth + dWidth);
//
//        System.out.println("Площа прямокутника(d): " + dArea);
//        System.out.println("Периметр прямокутника(d): " + dPerimeter);
//
//        // 2 завд
//        int a = 25;
//        int b = 42;
//
//        if (a>b) {
//            System.out.println("Найбільше число: " + a);
//        } else if (b>a) {
//            System.out.println("Найбільше число: " + b);
//        } else {
//            System.out.println("Одинакові: " + a);
//        }

        // 3 завд
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Введіть 1 число:");
//        double num1 = scanner.nextDouble();
//
//        System.out.println("Введіть операцію(+ - * /)");
//        char operator = scanner.next().charAt(0);
//
//        System.out.println("Введіть 2 число:");
//        double num2 = scanner.nextDouble();
//
//        double result = 0;
//        boolean isValid = true;
//
//        switch (operator) {
//            case '+':
//                result = num1 + num2;
//                break;
//            case '-':
//                result = num1 - num2;
//                break;
//            case '*':
//                result = num1 * num2;
//                break;
//            case '/':
//                if (num2 !=0) {
//                    result = num1 / num2;
//                } else {
//                    System.out.println("Помилка, на 0 не ділиться");
//                }
//                break;
//            default:
//                System.out.println("невідома операція");
//                isValid = false;
//        }
//        if (isValid) {
//            System.out.println("Результат:" + result);
//        }

        // завд 4
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Введіть число");
//        int number = scanner.nextInt();
//
//        if (number < 0) {
//            System.out.println("Число відємне");
//        } else {
//            long factorial = 1;
//            int i = 1;
//
//            while (i <= number) {
//                factorial = factorial * i;
//                i++;
//            }
//
//            System.out.println("Факторіл з　" + number + "! дорівнює:" + factorial);
//        }

        // 任务 5

        int[] numbers = {15, 42, 8, 99, 23, 4, -5, -23, 76};

        int max = numbers[0];

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }

        System.out.println("Масив містить " + numbers.length +", найбільший елемент:" + max);
    }
}