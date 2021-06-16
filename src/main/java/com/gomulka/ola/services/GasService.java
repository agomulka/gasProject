package com.gomulka.ola.services;

import com.gomulka.ola.model.GasData;

import java.util.*;
import java.util.stream.Collectors;

public class GasService {
    private List<GasData> gasList;
    private Set<String> yearSet;

    public GasService() {
        this.gasList = new ArrayList<>();
        this.yearSet = new HashSet<>();
    }

    public List<GasData> getList() {
        return gasList;
    }

    public List<GasData> addToList(GasData gasData) {
        yearSet.add(gasData.getYear());
        gasList.add(gasData);
        return gasList;
    }

    public void printList() {
        Collections.sort(gasList);
        for (GasData gasData : gasList) {
            System.out.println(gasData);
        }
    }

    public List<GasData> getYearList(int year) {
        String yearAsString = String.valueOf(year);
        List<GasData> stream = gasList.stream().filter(gasData -> gasData.getYear().equals(yearAsString))
                .collect(Collectors.toList());

        return stream;
    }

    public Set<String> getYearSet() {
        return yearSet;
    }

}
