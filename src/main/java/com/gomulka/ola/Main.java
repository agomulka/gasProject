package com.gomulka.ola;

import com.gomulka.ola.dao.FileLoaderDao;
import com.gomulka.ola.dao.GeneratorExcelDao;
import com.gomulka.ola.services.GasService;

public class Main {

    public static void main(String[] args) {
        final String filePath = "C:\\Users\\Ola\\Documents\\java\\gasManagement\\src\\main\\resources\\file1.txt";
        final String destinationPath = "src/main/resources/gasData.xlsx";

        System.out.println("loading data from file...");
        GasService gasService = new GasService();
        FileLoaderDao fileLoader = new FileLoaderDao(gasService, filePath);
        fileLoader.load();
        int n = fileLoader.getNumberLoadedData();
        System.out.println("loaded " + n + " lines.");


        System.out.println("generating excel file...");
        GeneratorExcelDao generator = new GeneratorExcelDao(gasService, destinationPath);
        generator.generate();
        System.out.println("generated successfully.");
    }

}
