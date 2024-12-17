package org.example;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String[] words = {"КонтрСтр", "АртурПирожков", "ДжаваЛав", "КонтрСтр", "Братишкин", "АртурПирожков", "КонтрСтре", "КонтрСтре"};

        System.out.println(Arrays.stream(words)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue().equals(Collections.max(Arrays.stream(words)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .values())))
                .map(Map.Entry::getKey)
                .collect(Collectors.groupingBy(String::length))
                .entrySet().stream()
                .min(Comparator.comparingInt(Map.Entry::getKey))
                .map(Map.Entry::getValue)
                .orElse(Collections.emptyList())
                .stream()
                .collect(Collectors.joining(", ")));


        List<ne_main> contacts = new ArrayList<>(Arrays.asList(
                new ne_main("Егор", "ЕфимоВ", 12, "89127210685"),
                new ne_main("Сема", "Боршадский", 25, "89124343466"),
                new ne_main("Алексей", "Ботев", 20, "89546568659"),
                new ne_main("Алекс", "Боев", 21, "89546568659"),
                new ne_main("Ян", "Чермных", 52, "89525252525")
        ));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите подстроку для поиска по фамилии: ");
        String substring = scanner.nextLine().toLowerCase();

        System.out.println(
                contacts.stream()
                        .filter(contact -> contact.getLastName().toLowerCase().contains(substring))
                        .sorted(Comparator.comparingInt(ne_main::getAge).reversed())
                        .map(ne_main::getFirstName)
                        .collect(Collectors.collectingAndThen(
                                Collectors.toList(),
                                (List<String> list) -> list.size() + " контактов зовут: " + String.join(", ", list) + "."
                        ))
        );
    }
}
