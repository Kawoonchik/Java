import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Task9 {

    public static List<String> getUppercaseProductNames(Map<Integer, Optional<String>> productMap) {
        return productMap.values().stream()

                .filter(Optional::isPresent)

                .map(Optional::get)

                .map(String::toUpperCase)

                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Map<Integer, Optional<String>> products = Map.of(
                1, Optional.of("Laptop"),
                2, Optional.empty(), // без назви
                3, Optional.of("Mouse"),
                4, Optional.of("keyboard"),
                5, Optional.empty()
        );

        List<String> validNames = getUppercaseProductNames(products);

        // [LAPTOP, MOUSE, KEYBOARD]
        System.out.println("Назви продуктів: " + validNames);
    }
}