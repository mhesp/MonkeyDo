package com.kompstudio;

import com.kompstudio.dao.DAOManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.io.File;

/**
 * Created by maria on 10.06.16.
 */
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

}
