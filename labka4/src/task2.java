import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class task2 {
    public static List<Integer> getNumbersFromOp(List<Optional<Integer>> optionals) {
        return optionals.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Optional<Integer>> mixedList = List.of(
                Optional.of(10),
                Optional.empty(),
                Optional.of(42),
                Optional.empty(),
                Optional.of(99)
        );

        List<Integer> result = getNumbersFromOp(mixedList);

        System.out.println("Результат: " + result);
    }
}
