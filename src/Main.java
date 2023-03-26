import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
// 1.
        System.out.println("1.Find the number of minors (i.e. people under 18).");
        long littleAge = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Total found: " + littleAge + " people)");
// 2.
        System.out.println("\n2. Get a list of names of conscripts (i.e. men from 18 to 27 years old).");
        List<String> LuckyFamilies = persons.stream()
                .filter(person -> person.getSex().equals(Sex.MAN))
                .filter(person -> person.getAge() >= 18 & person.getAge() < 27)
                .map(person -> person.getFamily())
                .collect(Collectors.toList());
        System.out.printf("Received a list of the names of the lucky ones in quantity: %s\n",
                LuckyFamilies.size());
// 3.
        System.out.println("\n3. Get a list sorted by last name " +
                "potentially able-bodied people with higher education in the sample ");
        System.out.println("(those. people with higher education " +
                "18 to 60 years for women and up to 65 years for men).");
        List<String> DoomedToPlow = persons.stream()
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> (person.getSex().equals(Sex.MAN)) ?
                        (person.getAge() >= 18 & person.getAge() < 65) :
                        (person.getAge() >= 18 & person.getAge() < 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .map(Person::toString)
                .toList();
        System.out.printf("who learned: %s person ;)\n", DoomedToPlow.size());

//        if (!DoomedToPlow.isEmpty()) {// для контроля
//            System.out.println("\nFirst two in list:");
//            DoomedToPlow.stream()
//                    .limit(2)
//                    .forEach(System.out::println);
//        }
    }
}
