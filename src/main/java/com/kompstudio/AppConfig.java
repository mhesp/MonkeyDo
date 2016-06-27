package com.kompstudio;

import com.kompstudio.dao.DAOManager;
import com.kompstudio.dao.TaskDAO;
import com.kompstudio.dao.TaskListDAO;
import com.kompstudio.dao.UserDAO;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class AppConfig {

    @Autowired
    DataSource dataSource;

    Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    public DAOManager daoManager() {
        return new DAOManager();
    }

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:etc/db/monkeydo.db");
        return dataSource;
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
