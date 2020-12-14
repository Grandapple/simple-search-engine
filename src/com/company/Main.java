package com.company;

import java.io.IOException;
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
            var dataProvider = new DataProvider("/home/daulet/Downloads/text.txt");

            switch (option) {
                case 1:
                    System.out.println();
                    System.out.println("Select a matching strategy: ALL, ANY, NONE");
                    var strategyType = scanner.nextLine();
                    SimpleSearchContext context = SimpleSearchContext.getSimpleSearchContext(strategyType);
                    System.out.println();
                    System.out.println("Enter a name or email to search all suitable people.");
                    System.out.print("> ");
                    var data = scanner.nextLine();
                    context.printResult(dataProvider, data);
                    break;
                case 2:
                    System.out.println();
                    System.out.println("=== List of people ===");
                    SimpleSearch.printPeople(dataProvider);
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


