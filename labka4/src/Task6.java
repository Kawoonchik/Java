import java.util.List;
import java.util.stream.Collectors;

public class Task6 {
    public record Person(String name, List<Person> friends) {}

    public static List<String> getUniqueFriendsNamesUppercase(List<Person> people) {
        return people.stream()

                .flatMap(person -> person.friends().stream())

                .map(Person::name)

                .map(String::toUpperCase)

                .distinct()

                .collect(Collectors.toList());
    }

    public static void main(String[] args) {

        Person friend1 = new Person("Mykola", List.of());
        Person friend2 = new Person("Oksana", List.of());
        Person friend3 = new Person("mykola", List.of());


        Person person1 = new Person("Anton", List.of(friend1, friend2));
        Person person2 = new Person("Borys", List.of(friend2, friend3));

        List<Person> people = List.of(person1, person2);

        List<String> uniqueUppercaseFriends = getUniqueFriendsNamesUppercase(people);

        //[MYKOLA, OKSANA]
        System.out.println("Унікальні імена друзів: " + uniqueUppercaseFriends);
    }
}