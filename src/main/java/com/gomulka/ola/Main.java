package com.gomulka.ola;

import com.gomulka.ola.dao.DataBaseDao;
import com.gomulka.ola.services.GasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private final static String filePath = "C:\\Users\\Ola\\Documents\\java\\gasManagement\\src\\main\\resources\\file1.txt";
    private final static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("loading data from file...");
        GasService gasService = new GasService();
        gasService.loadFromFile(filePath);
        int n = gasService.getNumberOfLoadedData();
        logger.info("loaded " + n + " lines.");

        logger.info("generating data base...");
        DataBaseDao database = new DataBaseDao();
        database.generateDataBase(gasService);
        logger.info("generated successfully.");
    }

}
