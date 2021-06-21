package com.gomulka.ola.services;

import com.gomulka.ola.dao.FileLoaderDao;
import com.gomulka.ola.model.GasData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class GasService {
    private List<GasData> gasList;
    private Set<String> yearSet;
    private FileLoaderDao fileLoader;
    private final static Logger logger = LoggerFactory.getLogger(GasService.class);

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
            logger.info(gasData.toString());
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


    public List<GasData> loadFromFile(String path) {
        fileLoader = new FileLoaderDao(path);
        List<GasData> loadedList = fileLoader.load();
        for (GasData gasData : loadedList) {
            addToList(gasData);
        }
        return this.gasList;
    }

    public int getNumberOfLoadedData() {
        return fileLoader.getNumberLoadedData();
    }
}
