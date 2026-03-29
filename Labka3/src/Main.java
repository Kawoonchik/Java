import java.util.*;

// Zavd 1
class Student {
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {return id;}
    public String getName() {return name;}

    @Override public String toString() {
        return "Student [id=" + id + ", name=" + name + "]";
    }
}

class StudentRegistry {
    private Map<Integer,Student> registry = new HashMap<>();

    public void addStudent(Student student) {
        registry.put(student.getId(), student);
        System.out.println("Added " + student.getName());
    }

    public void removeStudent(int id) {
        Student removed = registry.remove(id);
        if (removed != null) {
            System.out.println("Deleted " + removed.getName());
        } else {
            System.out.println("Student " + id + "was not found");
        }
    }
    public Student findStudent(int id) {
        return registry.get(id);
    }

    public void displayAll() {
        System.out.println("Student list");
        for (Student student : registry.values()) {
            System.out.println(student);
        }
    }
}
//zavd3
class Box<T> {
    private T item;

    public void put(T item) {
        this.item = item;
    }

    public T get() {
        return item;
    }
}

//zavd 5
class Pair<K,V> {
    private K first;
    private V second;

    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public K getFirst() { return first;}
    public V getSecond() { return second;}

    public boolean comparePairs(Pair<K,V> otherPair) {
        return this.first.equals(otherPair.getFirst()) && this.second.equals(otherPair.getSecond());
    }

    @Override
    public String toString() {
        return "Pair{" + first + ", " + second + "}";
    }
}

//zavd 6
abstract class Shape {
    public abstract double getArea();
}

class Circle extends Shape {
    private double radius;
    public Circle(double redius) { this.radius = radius;}
    @Override
    public double getArea() {return Math.PI * radius * radius;}
}

class Rectangle extends Shape {
    private double width, height;
    public Rectangle(double width, double height) { this.width = width; this.height = height; }
    @Override
    public double getArea() { return width * height; }
}

//zavd8
abstract class Animal {
    public abstract void makeSound();
}
class Cat extends Animal {
    @Override
    public void makeSound() { System.out.println("Myau!"); }
}
class Dog extends Animal {
    @Override
    public void makeSound() { System.out.println("Gav!"); }
}
class Labrador extends Dog {
    @Override
    public void makeSound() { System.out.println("Gav (im labrador)!"); }
}

class AnimalShelter {
    private List<Dog> dogs = new ArrayList<>();
    private List<Animal> otherAnimals = new ArrayList<>();

    public void addAnimals(List<? extends Dog> newDogs) {
        dogs.addAll(newDogs);
    }
    public void addOtherAnimal(Animal animal) {
        otherAnimals.add(animal);
    }
    public void printAnimalSounds(List<? extends Animal> animals) {
        for (Animal animal : animals) {
            animal.makeSound();
        }
    }

    public List<Dog> getDogs() {return dogs;}
    public List<Animal> getOtherAnimals() {return otherAnimals;}
}

public class Main {

    public static <T> Set<T> getUniqueElements(List<T> list) {
        return new HashSet<>(list);
    }

    public static <T> Map<T, Integer> countOccurrences(List<T> list) {
        Map<T, Integer> counts = new HashMap<>();
        for (T item : list) {
            counts.put(item, counts.getOrDefault(item,0) + 1);
        }
        return counts;
    }

    //zavd4
    public static <T extends Comparable<T>> T findMax(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }

        T max = array[0];
        for (int i =1; i < array.length; i++) {
            if (array[i].compareTo(max) > 0) {
                max = array[i];
            }
        }
        return max;
    }

    //zavd6
    public static double calculateTotalArea(List<? extends Shape> shapes) {
        double totalArea = 0;
        for (Shape shape : shapes) {
            totalArea += shape.getArea();
        }
        return totalArea;
    }
    //zavd7
    public static void addToList(List<? super Integer> list) {
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
    }

    public static void main (String[] args) {
        System.out.println("Task 1");
        StudentRegistry registry = new StudentRegistry();
        registry.addStudent(new Student(101, "Petro"));
        registry.addStudent(new Student(102, "Oleg"));

        registry.displayAll();
        System.out.println("Search for 101 " + registry.findStudent(101));
        registry.removeStudent(102);
        registry.displayAll();


        System.out.println("Task 2");
        List<String> words = Arrays.asList("kawoon", "mango", "tango", "ferie", "kawoon", "mango");

        System.out.println("List " + words);

        Set<String> uniqueWords = getUniqueElements(words);
        System.out.println("Unique list " + uniqueWords);

        Map<String, Integer> wordCounts = countOccurrences(words);
        System.out.println("Number of entries" + wordCounts);


        System.out.println("Task 3");
        Box<Integer> intBox = new Box<>();
        intBox.put(100);
        System.out.println("There is in box: " + intBox.get());

        Box<String> strBox = new Box<>();
        strBox.put("Kawoon");
        System.out.println("There is in box: " + strBox.get());


        System.out.println("Task 4");
        Integer[] intArray = {1, 5, 3, 9, 2};
        System.out.println("Max Integer: " + findMax(intArray)); // 9

        Double[] doubleArray = {3.14, 2.71, 9.81, 1.41};
        System.out.println("Max Double: " + findMax(doubleArray)); //9.81

        Character[] charArray = {'a', 'z', 'k', 'b'};
        System.out.println("Max Character: " + findMax(charArray)); // z

        String[] strArray = {"Kawoon", "Zebra", "Tango"};
        System.out.println("Max String: " + findMax(strArray));


        System.out.println("Task 5");

        Pair<Integer, String> pair1 = new Pair<>(1, "One");
        Pair<Integer, String> pair2 = new Pair<>(1, "One");
        Pair<Integer, String> pair3 = new Pair<>(2, "Two");

        System.out.println("Pair 1: " + pair1);
        System.out.println("Pair 1 = pair 2? " + pair1.comparePairs(pair2)); // true
        System.out.println("Pair 1 = pair 3? " + pair1.comparePairs(pair3)); // false

        Pair<String, List<Integer>> complexPair = new Pair<>("Grades", Arrays.asList(5,4,5,3));
        System.out.println("Complex pair: " + complexPair);


        System.out.println("Task 6");
        List<Circle> circles = Arrays.asList(new Circle(2.0), new Circle(3.0));
        List<Rectangle> rectangles = Arrays.asList(new Rectangle(2, 4), new Rectangle(5, 5));

        System.out.println("Circle ploshcha: " + calculateTotalArea(circles));
        System.out.println("Rectangle ploshcha: " + calculateTotalArea(rectangles));

        System.out.println("Task 7");
        List<Integer> intList = new ArrayList<>();
        List<Number> numList = new ArrayList<>();

        addToList(intList);
        addToList(numList);

        System.out.println("List of Integer: " + intList);
        System.out.println("List of Number: " + numList);

        System.out.println("Task 8");
        AnimalShelter shelter = new AnimalShelter();

        Cat myCat = new Cat();
        shelter.addOtherAnimal(myCat);

        List<Dog> myDogs = Arrays.asList(new Dog(), new Dog());
        List<Labrador> myLabradors = Arrays.asList(new Labrador());

        shelter.addAnimals(myDogs);
        shelter.addAnimals(myLabradors);

        System.out.println("Sounds of dogs in shelter:");
        shelter.printAnimalSounds(shelter.getDogs());

        System.out.println("Sounds of other animals:");
        shelter.printAnimalSounds(shelter.getOtherAnimals());
    }
}