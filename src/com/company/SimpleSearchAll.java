package com.company;

import java.util.*;

public class SimpleSearchAll implements SimpleSearch {
    @Override
    public List<Integer> search(Map<String, List<Integer>> invertedIndexMap, String data) {
        var arrayOfData = data.split(" ");
        Set<Integer> invertedIndexSet = new HashSet<>(invertedIndexMap.getOrDefault(arrayOfData[0].toLowerCase(), new ArrayList<>()));
        for (var s : data.split(" ")) {
            invertedIndexSet.retainAll(invertedIndexMap.getOrDefault(s.toLowerCase(), new ArrayList<>()));
        }
        return List.copyOf(invertedIndexSet);
    }
}
