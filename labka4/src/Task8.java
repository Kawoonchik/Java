import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Task8 {
    public record Product(String name, double price) {}

    public static Optional<String> getSecondMostExpensive(List<Product> products) {
        return products.stream()

                .sorted(Comparator.comparingDouble(Product::price).reversed())

                .skip(1)

                .map(Product::name)

                .findFirst();
    }

    public static void main(String[] args) {
        List<Product> store = List.of(
                new Product("Хліб", 25.0),
                new Product("Молоко", 40.0),
                new Product("Сир", 200.0), // Найдорожчий
                new Product("Ковбаса", 150.0), // Другий найдорожчий
                new Product("Яблука", 35.0)
        );

        Optional<String> secondExpensive = getSecondMostExpensive(store);

        System.out.println("Другий найдорожчий продукт: " + secondExpensive.orElse("Не знайдено"));
    }
}