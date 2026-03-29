import java.util.List;
import java.util.Optional;

public class Task5 {
    public static Optional<Integer> getProductOfOdds(List<Integer> numbers) {
        return numbers.stream()
                // Фільтруємо: залишаємо лише непарні числа
                .filter(n -> n % 2 != 0)
                // Згортаємо: множимо всі залишені числа між собою
                .reduce((a, b) -> a * b);
    }

    public static void main(String[] args) {
        //  3, 5, 7 -> 3 * 5 * 7 = 105
        List<Integer> numbers = List.of(2, 3, 4, 5, 6, 7);

        List<Integer> onlyEvens = List.of(2, 4, 6, 8);

        Optional<Integer> result1 = getProductOfOdds(numbers);
        Optional<Integer> result2 = getProductOfOdds(onlyEvens);

        System.out.println("Добуток непарних (1): " + result1.orElse(0)); // 105
        System.out.println("Добуток непарних (2): " + result2.orElse(0)); // 0
    }
}