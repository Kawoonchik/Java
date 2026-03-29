import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Task7 {

    public record Transaction(String category, double amount) {}

    public static Map<String, Double> sumTransactionsByCategory(List<Transaction> transactions) {
        return transactions.stream()
                .collect(Collectors.groupingBy(

                        Transaction::category,

                        Collectors.summingDouble(Transaction::amount)
                ));
    }

    public static void main(String[] args) {

        List<Transaction> transactions = List.of(
                new Transaction("Їжа", 150.50),
                new Transaction("Транспорт", 40.00),
                new Transaction("Їжа", 320.00),
                new Transaction("Розваги", 500.00),
                new Transaction("Транспорт", 60.00)
        );


        Map<String, Double> expensesByCategory = sumTransactionsByCategory(transactions);


        expensesByCategory.forEach((category, sum) ->
                System.out.println("Категорія: " + category + " -> Загальна сума: " + sum)
        );
    }
}