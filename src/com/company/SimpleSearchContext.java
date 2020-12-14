package com.company;

public class SimpleSearchContext {
    private SimpleSearch simpleSearch;

    private SimpleSearchContext(SimpleSearch simpleSearch) {
        this.simpleSearch = simpleSearch;
    }

    public static SimpleSearchContext getSimpleSearchContext(String strategyType) {
        if ("ALL".equals(strategyType)) {
            return new SimpleSearchContext(new SimpleSearchAll());
        } else if ("ANY".equals(strategyType)) {
            return new SimpleSearchContext(new SimpleSearchAny());
        } else if ("NONE".equals(strategyType)){
            return new SimpleSearchContext(new SimpleSearchNone());
        } else {
            throw new RuntimeException("Invalid strategy!");
        }
    }

    public void printResult(DataProvider dataProvider, String data) {
        this.simpleSearch.printResult(dataProvider.getPeople(), data);
    }
}
