package com.kompstudio.mappers;

import com.kompstudio.entities.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mhesp on 22.06.2016.
 */
public class TaskMapper implements RowMapper<Task> {

    @Override
    public Task mapRow(ResultSet resultSet, int i) throws SQLException {
        Task task = new Task();
        task.setListId(resultSet.getInt(1));
        task.setTaskName(resultSet.getString(2));
        task.setTaskDueDate(null);
        task.setTaskCreatedDate(resultSet.getString(4));
        task.setDone(resultSet.getBoolean(5));
        return task;
    }
}
