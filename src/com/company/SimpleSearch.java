package com.company;

import java.util.*;

public interface SimpleSearch {

    private Map<String, List<Integer>> getInvertedIndexMap(List<String> people) {
        var map = new HashMap<String, List<Integer>>();
        for (var i = 0; i < people.size(); i++) {
            var arr = people.get(i).split(" ");
            for (var s : arr) {
                final var index = i;
                map.computeIfPresent(s.toLowerCase(), (key, value) -> {
                    value.add(index);
                    return List.copyOf(value);
                });
                map.computeIfAbsent(s.toLowerCase(), key -> List.of(index));
            }
        }
        return map;
    }

    List<Integer> search(Map<String, List<Integer>> map, String data);

    default void printResult(List<String> people, String data) {
        var map = getInvertedIndexMap(people);
        var listOfPeople = search(map, data);

        if (listOfPeople.size() > 0) {
            System.out.print(listOfPeople.size());
            System.out.println(" persons found:");
            listOfPeople.stream().map(people::get).forEach(System.out::println);
        } else {
            System.out.println("No matching people found.");
        }
    }

    static void printPeople(DataProvider dataProvider) {
        for (var p : dataProvider.getPeople()) {
            System.out.println(p);
        }
    }
}
