import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class task3 {
    public static Optional<String> findLongestName(List<String> names) {
        return names.stream()
                .max(Comparator.comparingInt(String::length));
    }

    public static void main(String[] args) {
        List<String> names = List.of("Kant", "Aristotle", "Confucius", "Shevchenko");

        List<String> emptyList = List.of();

        Optional<String> longest = findLongestName(names);
        Optional<String> longestEmpty = findLongestName(emptyList);

        System.out.println("Найдовше ім'я: " + longest.orElse("Список порожній")); // Виведе: Schopenhauer
        System.out.println("З порожнього: " + longestEmpty.orElse("Список порожній")); // Виведе: Список порожній
    }
}
