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
        task.setId(resultSet.getInt(1));
        task.setListId(resultSet.getInt(2));
        task.setTaskName(resultSet.getString(3));
        task.setTaskDueDate(null);
        task.setTaskCreatedDate(resultSet.getString(5));
        task.setDone(resultSet.getBoolean(6));
        return task;
    }
}
