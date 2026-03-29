import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class task4 {
    public record Employee(String name, double salary) {}

    public static String getSalaryRange(Employee empl) {
        if (empl.salary() < 3000) {
            return "< 3000";
        } else if (empl.salary() <= 5000) {
            return "3000-5000";
        } else {
            return "> 5000";
        }
    }

    public static Map<String, Optional<Employee>> getTopEarnersByRange(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        // 1. Вказуємо критерій групування
                        task4::getSalaryRange,
                        // 2. Вказуємо шукати максимум за зарплатою
                        Collectors.maxBy(Comparator.comparingDouble(Employee::salary))
                ));
    }

    public static void main(String[] args) {
        List<Employee> team = List.of(
                new Employee("Oleg", 2500),
                new Employee("Sanya", 2900),
                new Employee("Yegor", 4000),
                new Employee("Diana", 4800),
                new Employee("Khtos", 6000)
        );

        Map<String, Optional<Employee>> topEarners = getTopEarnersByRange(team);

        topEarners.forEach((range, empOpt) ->
                System.out.println("Діапазон " + range + " -> Найвища зарплата: " + empOpt.get().name() + " (" + empOpt.get().salary() + ")")
        );
    }
}