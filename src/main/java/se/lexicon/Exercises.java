package se.lexicon;

import se.lexicon.data.DataStorage;
import se.lexicon.model.Gender;
import se.lexicon.model.Person;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;

public class Exercises {

    private final static DataStorage storage = DataStorage.INSTANCE;

    /*
       1. Find everyone that has firstName: “Erik” using findMany().
    */
    public static void exercise1(String message) {
        System.out.println(message);
        storage.findMany(p -> p.getFirstName().equals("Erik")).forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        2. Find all females in the collection using findMany().
     */
    public static void exercise2(String message) {
        System.out.println(message);
        storage.findMany(person -> person.getGender() == Gender.FEMALE).forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        3. Find all who are born after (and including) 2000-01-01 using findMany().
     */
    public static void exercise3(String message) {
        System.out.println(message);
        storage.findMany(person -> person.getBirthDate().isAfter(LocalDate.parse("1999-12-31"))).forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        4. Find the Person that has an id of 123 using findOne().
     */
    public static void exercise4(String message) {
        System.out.println(message);
        System.out.println(storage.findOne(p -> p.getId() == 123));
        System.out.println("----------------------");
    }

    /*
        5. Find the Person that has an id of 456 and convert to String with following content:
            “Name: Nisse Nilsson born 1999-09-09”. Use findOneAndMapToString().
     */
    public static void exercise5(String message) {
        System.out.println(message);
        String result = storage.findOneAndMapToString(
                person -> person.getId() == 456,
                person -> "Name: " + person.getFirstName() + " " + person.getLastName() + " born " + person.getBirthDate()
        );
        System.out.println(result != null ? result : "No person found with ID 456");
        System.out.println("----------------------");
    }

    /*
        6. Find all male people whose names start with “E” and convert each to a String using findManyAndMapEachToString().
     */
    public static void exercise6(String message) {
        System.out.println(message);
        storage.findManyAndMapEachToString(
                person -> person.getGender() == Gender.MALE && person.getFirstName().startsWith("E"),
                Person::toString
        ).forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        7. Find all people who are below age of 10 and convert them to a String like this:
            “Olle Svensson 9 years”. Use findManyAndMapEachToString() method.
     */
    public static void exercise7(String message) {
        System.out.println(message);
        storage.findManyAndMapEachToString(
                person -> Period.between(person.getBirthDate(), LocalDate.now()).getYears() < 10,
                person -> person.getFirstName() + " " + person.getLastName() + " " + Period.between(person.getBirthDate(), LocalDate.now()).getYears() + " years"
        ).forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
      8. Using findAndDo() print out all people with firstName “Ulf”.
    */
    public static void exercise8(String message) {
        System.out.println(message);
        storage.findAndDo(person -> person.getFirstName().equals("Ulf"), System.out::println);
        System.out.println("----------------------");
    }

    /*
      9. Using findAndDo() print out everyone who have their lastName contain their firstName.
     */
    public static void exercise9(String message) {
        System.out.println(message);
        storage.findAndDo(person -> person.getLastName().contains(person.getFirstName()), System.out::println);
        System.out.println("----------------------");
    }

    /*
      10. Using findAndDo() print out the firstName and lastName of everyone whose firstName is a palindrome.
     */
    public static void exercise10(String message) {
        System.out.println(message);
        storage.findAndDo(
                person -> {
                    String firstName = person.getFirstName().toLowerCase();
                    return firstName.equals(new StringBuilder(firstName).reverse().toString());
                },
                person -> System.out.println(person.getFirstName() + " " + person.getLastName())
        );
        System.out.println("----------------------");
    }

    /*
      11. Using findAndSort() find everyone whose firstName starts with A sorted by birthDate.
     */
    public static void exercise11(String message) {
        System.out.println(message);
        Comparator<Person> byBirthDate = Comparator.comparing(Person::getBirthDate);
        storage.findAndSort(person -> person.getFirstName().startsWith("A"), byBirthDate).forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
      12. Using findAndSort() find everyone born before 1950 sorted reversed by latest to earliest.
     */
    public static void exercise12(String message) {
        System.out.println(message);
        Comparator<Person> byBirthDateReversed = Comparator.comparing(Person::getBirthDate).reversed();
        storage.findAndSort(person -> person.getBirthDate().isBefore(LocalDate.parse("1950-01-01")), byBirthDateReversed).forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
      13. Using findAndSort() find everyone sorted in following order: lastName > firstName > birthDate.
     */
    public static void exercise13(String message) {
        System.out.println(message);
        Comparator<Person> byLastNameThenFirstNameThenBirthDate = Comparator.comparing(Person::getLastName)
                .thenComparing(Person::getFirstName)
                .thenComparing(Person::getBirthDate);
        storage.findAndSort(byLastNameThenFirstNameThenBirthDate).forEach(System.out::println);
        System.out.println("----------------------");
    }
}
