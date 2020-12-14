package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataProvider {

    private final List<String> people;

    public DataProvider(String fileName) throws IOException {
        this.people = getAllPeople(fileName);
    }

    private static List<String> getAllPeople(String fileName) throws IOException {
        return Files.lines(Paths.get(fileName)).collect(Collectors.toList());
    }

    public List<String> getPeople() {
        return people;
    }
}
