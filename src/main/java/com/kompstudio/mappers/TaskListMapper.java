package com.kompstudio.mappers;

import com.kompstudio.entities.TaskList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mhesp on 11.06.2016.
 */
public class TaskListMapper implements RowMapper<TaskList> {

    public TaskList mapRow(ResultSet resultSet, int i) throws SQLException {
        TaskList list = new TaskList();
        list.setUserName(resultSet.getString(1));
        list.setUserId(resultSet.getInt(2));
        list.setListName(resultSet.getString(3));
        return list;
    }
}
