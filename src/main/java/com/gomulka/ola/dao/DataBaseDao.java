package com.gomulka.ola.dao;

import com.gomulka.ola.DataSourceSingleton;
import com.gomulka.ola.GasMapper;
import com.gomulka.ola.model.GasData;
import com.gomulka.ola.services.GasService;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class DataBaseDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSourceSingleton.getInstance());

    public void addGas(GasData g) {
        jdbcTemplate.
                update("INSERT INTO gas VALUES(?,?,?);",g.getDateAsString(), g.getValue(), g.getTemperature());

    }

    public void generateDataBase(GasService gasService ){
        List<GasData> gasList = gasService.getList();
        for(GasData gasData : gasList){
            addGas(gasData);
        }
    }

    public List<GasData> getGasList(){
        return jdbcTemplate.query("SELECT * FROM gas", new GasMapper());
    }
}
