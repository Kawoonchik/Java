import java.util.List;
import java.util.Optional;

public class task1 {
    public static Optional<String> findFirstXString(List<String> strings) {
        return Optional.of(
                strings.stream()
                        .filter(s -> s.startsWith("X") && s.length() > 5)
                        .findFirst()
                        .orElse("Default")
        );

    }

    public static void main(String[] args) {
        List<String> list1 = List.of("Apple", "Xenon", "Xachapuri", "Kawoon");

        List<String> list2 = List.of("Apple", "Xamon", "Kawoon");

        System.out.println("Result 1:" + findFirstXString(list1));
        System.out.println("Result 2:" + findFirstXString(list2));
    }
}
