import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Task10 {
    private static double calculateAverageTemp(List<Integer> temperatures) {
        return temperatures.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(-273.15);
    }

    public static Optional<String> getHottestCity(Map<String, List<Integer>> cityTemperatures) {
        return cityTemperatures.entrySet().stream()
                .max(Comparator.comparingDouble(entry -> calculateAverageTemp(entry.getValue())))
                .map(Map.Entry::getKey);
    }

    public static void main(String[] args) {
        Map<String, List<Integer>> weatherData = Map.of(
                "Київ", List.of(20, 22, 19, 21),       // Середня: 20.5
                "Одеса", List.of(25, 27, 26, 28),      // Середня: 26.5
                "Львів", List.of(18, 17, 20, 19),      // Середня: 18.5
                "Херсон", List.of(28, 30, 29, 31)      // Середня: 29.5
        );

        Optional<String> hottestCity = getHottestCity(weatherData);
        System.out.println("Найспекотніше місто: " + hottestCity.orElse("Дані відсутні"));
    }
}