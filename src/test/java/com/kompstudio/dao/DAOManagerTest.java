package com.kompstudio.dao;

import com.kompstudio.TestConfiguration;
import com.kompstudio.entities.User;
import com.kompstudio.mappers.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by mhesp on 22.06.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestConfiguration.class)
@WebAppConfiguration
public class DAOManagerTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void testGetAllUsers() {
        String SQL = "SELECT * FROM USERS";
        UserMapper userMapper = new UserMapper();
        List<User> users = jdbcTemplate.query(SQL, userMapper);

        for (User user: users) {
            System.out.println(user);
        }
    }
}
