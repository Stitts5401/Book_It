package main.lambda;

import java.util.Arrays;
import java.util.List;

public class CollectionIterationExample {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Carrol", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlotte", "Bronte", 45),
                new Person("Matthew", "Arnold", 38)
        );

        //EXTERNAL ITERATORS
        System.out.println("Using for loop");
        for(int i = 0; i<people.size(); i++){
            System.out.println(people.get(i));
        }
        System.out.println("Using for each loop");
        for (Person p :  people) {
            System.out.println(p);
        }

        System.out.println("Using Lambda for each");
        // people.forEach( p -> System.out.println(p));
             people.forEach(System.out::println);

    }
}
