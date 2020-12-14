package com.company;

import java.util.*;

public class SimpleSearchAny implements SimpleSearch {
    @Override
    public List<Integer> search(Map<String, List<Integer>> map, String data) {
        Set<Integer> invertedIndexSet = new HashSet<>();
        for (var s : data.split(" ")) {
            invertedIndexSet.addAll(map.getOrDefault(s.toLowerCase(), new ArrayList<>()));
        }
        return List.copyOf(invertedIndexSet);
    }
}
