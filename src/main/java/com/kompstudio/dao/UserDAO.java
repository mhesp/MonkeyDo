package com.kompstudio.dao;

import com.kompstudio.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Types;

/**
 * Created by mhesp on 22.06.2016.
 */
public class UserDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int getIdFromName(String name) throws Exception {
        try {
            String SQL = "SELECT user_id FROM users WHERE user_name = ?";
            Object[] params = { name };
            int[] types = { Types.VARCHAR };
            return jdbcTemplate.queryForObject(SQL, params, types, Integer.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Couldn't get userId from userName [" + name + "]");
        }
    }
}
