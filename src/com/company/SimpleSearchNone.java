package com.company;

import java.util.*;

public class SimpleSearchNone implements SimpleSearch {
    @Override
    public List<Integer> search(Map<String, List<Integer>> invertedIndexMap, String data) {
        Set<Integer> invertedIndexSet = new HashSet<>();
        for (var l : invertedIndexMap.values()) {
            invertedIndexSet.addAll(l);
        }
        Set<Integer> set1 = new HashSet<>();
        for (var s : data.split(" ")) {
            set1.addAll(invertedIndexMap.getOrDefault(s.toLowerCase(), new ArrayList<>()));
        }
        invertedIndexSet.removeAll(set1);
        return List.copyOf(invertedIndexSet);
    }
}
