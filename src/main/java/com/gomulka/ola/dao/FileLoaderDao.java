package com.gomulka.ola.dao;

import com.gomulka.ola.model.GasData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLoaderDao {
    private int loadedData;
    private List<GasData> gasList;
    private final String filePath;


    public FileLoaderDao(String filePath) {
        this.filePath = filePath;
        this.gasList = new ArrayList<>();
    }

    public List<GasData> load() {
        loadedData = 0;
        File file = new File(filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                gasList.add(new GasData(Integer.valueOf(parts[1]), parts[0]));
                loadedData++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gasList;
    }

    public int getNumberLoadedData() {
        return loadedData;
    }
}
