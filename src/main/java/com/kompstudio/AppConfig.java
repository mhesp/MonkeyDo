package com.kompstudio;

import com.kompstudio.dao.DAOManager;
import com.kompstudio.dao.TaskDAO;
import com.kompstudio.dao.TaskListDAO;
import com.kompstudio.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
public class AppConfig {

    @Autowired
    DataSource dataSource;

    @Bean
    public DAOManager daoManager() {
        return new DAOManager();
    }

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setName("monkeyDo")
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("db/create-db.sql")
                .addScript("db/insert-db.sql")
                .build();
        return db;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public TaskDAO taskDAO() {
        return new TaskDAO();
    }

    @Bean
    public UserDAO userDAO() {
        return new UserDAO();
    }

    @Bean
    public TaskListDAO taskListDAO() {
        return new TaskListDAO();
    }

}
