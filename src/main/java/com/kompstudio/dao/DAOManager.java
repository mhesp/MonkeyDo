package com.kompstudio.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by maria on 10.06.16.
 */
@Component
public class DAOManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void save() {
        String SQL = "INSERT INTO lists VALUES ('maria', 1, 'newlist')";
        int res = jdbcTemplate.update(SQL);
        System.out.println("Updated table! Res [" + res + "]");
    }
}
