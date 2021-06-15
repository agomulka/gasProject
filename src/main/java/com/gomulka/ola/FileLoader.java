package com.gomulka.ola;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileLoader {
    private GasService gs;
    private int loadedData;
    private ListService gasList;

    public FileLoader(GasService gs) {
        this.gs = gs;
        this.gasList = gs.getListService();
    }

    public ListService loading() {
        loadedData = 0;
        File file = new File("C:\\Users\\Ola\\Documents\\java\\gasManagement\\src\\main\\resources\\file1.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                gasList.addToList(new GasData(Integer.valueOf(parts[1]), parts[0]));
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
