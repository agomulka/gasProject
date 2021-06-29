package com.gomulka.ola;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

public class DataSourceSingleton {

    private static DataSource instance;

    private DataSourceSingleton() {
    }

    public static DataSource getInstance() {
        if(instance == null) {
            instance = getDataSource();
        }
        return instance;
    }

    private static DataSource getDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl("jdbc:mariadb://localhost:3306/gas");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("root");
        driverManagerDataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        return driverManagerDataSource;
    }


}