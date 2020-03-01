package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        final var scanner = new Scanner(System.in);
        var terminate = false;

        while (!terminate) {
            System.out.println();
            System.out.println("=== Menu ===");
            System.out.println("1. Find a person");
            System.out.println("2. Print all people");
            System.out.println("0. Exit");
            System.out.print("> ");
            var option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println();
                    System.out.println("Select a matching strategy: ALL, ANY, NONE");
                    var strategyType = scanner.nextLine();
                    SimpleSearchContext context = SimpleSearchContext.setStrategy(strategyType);
                    System.out.println();
                    System.out.println("Enter a name or email to search all suitable people.");
                    System.out.print("> ");
                    var data = scanner.nextLine();
                    context.printResult("/home/daulet/Downloads/text.txt", data);
                    break;
                case 2:
                    System.out.println();
                    System.out.println("=== List of people ===");
                    SimpleSearch.printPeople("/home/daulet/Downloads/text.txt");
                    break;
                case 0:
                    System.out.println();
                    System.out.println("Bye!");
                    terminate = true;
                    break;
                default:
                    System.out.println();
                    System.out.println("Incorrect option! Try again.");
            }
        }
    }
}

interface SimpleSearch {

    private Map<String, List<Integer>> getFilledMap(List<String> people) {
        var map = new HashMap<String, List<Integer>>();
        for (var i = 0; i < people.size(); i++) {
            var arr = people.get(i).split(" ");
            for (var s : arr) {
                var val = map.getOrDefault(s.toLowerCase(), new ArrayList<>());
                val.add(i);
                map.put(s.toLowerCase(), val);
            }
        }
        return map;
    }

    private static List<String> getAllPeople(String fileName) throws IOException {
        return Arrays.asList(new String(Files.readAllBytes(Paths.get(fileName))).split("\n"));
    }

    List<Integer> search(Map<String, List<Integer>> map, String data);

    default void printResult(String fileName, String data) {
        List<String> people = new ArrayList<>();
        try {
            people = getAllPeople(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        var map = getFilledMap(people);
        var listOfPeople = search(map, data);

        if (listOfPeople.size() > 0) {
            System.out.print(listOfPeople.size());
            System.out.println(" persons found:");
            for (var i : listOfPeople) {
                System.out.println(people.get(i));
            }
        } else {
            System.out.println("No matching people found.");
        }
    }

    static void printPeople(String fileName) {
        try {
            for (var p : getAllPeople(fileName)) {
                System.out.println(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class SimpleSearchAll implements SimpleSearch {
    @Override
    public List<Integer> search(Map<String, List<Integer>> map, String data) {
        var arrayOfData = data.split(" ");
        Set<Integer> set = new HashSet<>(map.getOrDefault(arrayOfData[0].toLowerCase(), new ArrayList<>()));
        for (var s : data.split(" ")) {
            set.retainAll(map.getOrDefault(s.toLowerCase(), new ArrayList<>()));
        }
        return List.copyOf(set);
    }
}

class SimpleSearchAny implements SimpleSearch {
    @Override
    public List<Integer> search(Map<String, List<Integer>> map, String data) {
        Set<Integer> set = new HashSet<>();
        for (var s : data.split(" ")) {
            set.addAll(map.getOrDefault(s.toLowerCase(), new ArrayList<>()));
        }
        return List.copyOf(set);
    }
}

class SimpleSearchNone implements SimpleSearch {
    @Override
    public List<Integer> search(Map<String, List<Integer>> map, String data) {
        Set<Integer> set = new HashSet<>();
        for (var l : map.values()) {
            set.addAll(l);
        }
        Set<Integer> set1 = new HashSet<>();
        for (var s : data.split(" ")) {
            set1.addAll(map.getOrDefault(s.toLowerCase(), new ArrayList<>()));
        }
        set.removeAll(set1);
        return List.copyOf(set);
    }
}

class SimpleSearchContext {
    private SimpleSearch simpleSearch;

    private SimpleSearchContext(SimpleSearch simpleSearch) {
        this.simpleSearch = simpleSearch;
    }

    public static SimpleSearchContext setStrategy(String strategyType) {
        if ("ALL".equals(strategyType)) {
            return new SimpleSearchContext(new SimpleSearchAll());
        } else if ("ANY".equals(strategyType)) {
            return new SimpleSearchContext(new SimpleSearchAny());
        } else {
            return new SimpleSearchContext(new SimpleSearchNone());
        }
    }

    public void printResult(String fileName, String data) {
        this.simpleSearch.printResult(fileName, data);
    }
}


