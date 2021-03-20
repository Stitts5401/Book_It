package main.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import java.util.function.Predicate;

public class U1PracticeUsingJava8 {
        public static void main(String[] args) {

            List<Person> people = Arrays.asList(
                    new Person("Charles", "Dickens", 60),
                    new Person("Lewis", "Carrol", 42),
                    new Person("Thomas", "Carlyle", 51),
                    new Person("Charlotte", "Bronte", 45),
                    new Person("Matthew", "Arnold", 38)
            );

            //Step 1: S-ort list by last name
            Collections.sort(people, (p1,p2)-> p1.getLastName().compareTo(p2.getLastName()));

            //Step 2: create a method that prints all elements in the list
            System.out.println("PRINT ALL");
            printConditional(people, p -> true);
            //Step 3: Create a method that prints all people that have last name beginning  with C
            System.out.println("PRINTING LN STARTING WITH C");
            printConditional(people, p -> p.getLastName().startsWith("C"));

            System.out.println("PRINTING FN STARTING WITH C");

            printConditional(people, p -> p.getFirstName().startsWith("C"));


            System.out.println("PRINTING FN STARTING WITH C");
            printConditional(people, p -> p.getFirstName().startsWith("C"));

        }
        private static void printAll (List < Person > people) {
            for (Person p : people) {
                System.out.println(p);
            }
        }
        /*
        private static void printLastNameBeginningWithC(List<Person> people){
            for (Person p : people) {
                if(p.getLastName().startsWith("c")) {
                    System.out.println(p);
                }
            }
        }*/
        private static void printConditional (List < Person > people, Predicate<Person> predicate){
            for (Person p : people) {
                if (predicate.test(p)) {
                    System.out.println(p);
                }

            }


        }
    }


    interface Condition {
        boolean test(Person p);
    }
