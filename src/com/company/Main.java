package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>(Arrays.asList(
                create("Ieva", 40),
                create("Ieva", 20),
                create("Ilze", 22),
                create("Marta", 29),
                create("Markus", 35),
                create("Ivars", 25),
                create("Miks", 31),
                create("Iveta", 51),
                create("Lauris", 49)));

        var list = persons.stream()
                .filter(p -> p.getAge() > 30)
                .collect(Collectors.toList());

        //printPersons(list);

        var personsWithNameContainingA = persons.stream()
                .filter(p -> p.getName().contains("a") || p.getName().contains("A"))
                .collect(Collectors.toList());

        printPersons(personsWithNameContainingA);

        var personMarta = persons.stream()
                .filter(person -> person.getName().equalsIgnoreCase("marta"))
                .collect(Collectors.toList())
                .stream();

        var marta = persons.stream()
                .filter(p -> p.getName().equalsIgnoreCase("marta"))
                .findFirst();

        if(marta.isPresent()) {
            var m = marta.get();
            System.out.println(m.getName() +" "+ m.getAge());
        }

        var result = persons.stream()
                .filter(p -> p.getName().equalsIgnoreCase("ieva") && p.getAge() > 30)
                .findFirst();

        if(result.isPresent()) {
            var ieva = result.get();
            System.out.println(ieva.getAge() + " "+ieva.getName());
        }

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        Integer sum = integers.stream()
                .reduce(0, (a, b) -> a + b);

        System.out.println(sum);


        var totalAge = persons.stream()
                .map(a -> a.getAge())
                .reduce(0, (a, b) -> a + b);

        var spersons = persons.stream()
                .map(a -> new SimplePerson(a.getName() + " "+ a.getSurname()))
                .filter(b -> b.getName().contains("a"))
                .map(b -> b.getName())
                .reduce("", (a, b)-> a +","+ b);

        var ws = spersons.substring(1);

        System.out.println(ws);

        System.out.println(spersons);

        List<Integer> integers2 = Arrays.asList(1, 2, 3, 4, 5);
        Integer sum2 = integers.stream()
                .collect(Collectors.summingInt(Integer::intValue));

    }

    private static void printPersons(List<Person> list) {
        for (var person :
                list) {
            System.out.println(person.getAge() + " " + person.getName());
        }
    }

    public static Person create(String name, int age) {
        return new Person(name, "Surname", age);
    }
}
