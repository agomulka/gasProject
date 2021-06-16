package com.gomulka.ola.dao;

import com.gomulka.ola.model.GasData;
import com.gomulka.ola.services.GasService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FileLoaderDao {
    private final GasService gasService;
    private int loadedData;
    private List<GasData> gasList;
    private final String filePath;

    public FileLoaderDao(GasService gasService, String filePath) {
        this.gasService = gasService;
        this.gasList = gasService.getList();
        this.filePath = filePath;
    }

    public List<GasData> load() {
        loadedData = 0;
        File file = new File(filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                gasList = gasService.addToList(new GasData(Integer.valueOf(parts[1]), parts[0]));
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
