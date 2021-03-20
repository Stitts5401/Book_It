package main.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * STREAMS ESSENTIALLY CREATE AN ASSEMBLY LINE
 * RATHER THAN LOOPING THROUGH EACH TIME YOU NEED TO DO SOMETHING IN THE COLLECTION
 * AFTER JAVA8 EVERY COLLECTION HAS STREAMS
 * **/
public class StreamExample1 {
/*WHATS PARALLEL PROCESSING AND HOW TO ENABLE IT
say you have an list of millions of Person's
you can assign each of the stream filters to run on different cores of the processor
  *
  */
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Carrol", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlotte", "Bronte", 45),
                new Person("Matthew", "Arnold", 38)
                );


        people.stream()
                .filter(p -> p.getAge() > 50)
                .filter(p -> p.getLastName().startsWith("C"))
                .forEach(p -> System.out.println(p.getFirstName()));

        var count= people.stream()
                .filter(p -> p.getLastName().startsWith("C"))
                .count();
        System.out.println(count);

        people.parallelStream()
                .filter(p -> p.getLastName().startsWith("B") )
                .forEach(p -> System.out.println(p.getFirstName()));


    }
}
