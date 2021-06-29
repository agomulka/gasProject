package com.gomulka.ola;

import com.gomulka.ola.model.GasData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GasMapper implements RowMapper<GasData> {

    @Override
    public GasData mapRow(ResultSet resultSet, int i) throws SQLException {
        String date = resultSet.getString("date");
        String value = resultSet.getString("value");
        return new GasData(Integer.parseInt(value), date);
    }
}