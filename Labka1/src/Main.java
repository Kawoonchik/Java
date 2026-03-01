import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        double celsius = 25.5;

        double fahrenheit = (celsius * 9.0 / 5.0) + 32.0;

        System.out.println(celsius + " градусів цельсія — це " + fahrenheit + " градусів Фаренгейта");

        // 2 завд
        int a = 25;
        int b = 42;

        if (a>b) {
            System.out.println("Найбільше число: " + a);
        } else if (b>a) {
            System.out.println("Найбільше число: " + b);
        } else {
            System.out.println("Одинакові: " + a);
        }

        // 3 завд


        double num1 = 4;

        char operator = '*';

        double num2 = 6;

        double result = 0;
        boolean isValid = true;

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 !=0) {
                    result = num1 / num2;
                } else {
                    System.out.println("Помилка, на 0 не ділиться");
                }
                break;
            default:
                System.out.println("невідома операція");
                isValid = false;
        }
        if (isValid) {
            System.out.println("Результат:" + result);
        }

        // завд 4



        int number = 4;

        if (number < 0) {
            System.out.println("Число відємне");
        } else {
            long factorial = 1;
            int i = 1;

            while (i <= number) {
                factorial = factorial * i;
                i++;
            }

            System.out.println("Факторіл з　" + number + "! дорівнює:" + factorial);
        }

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