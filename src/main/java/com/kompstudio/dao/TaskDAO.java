package com.kompstudio.dao;

import com.kompstudio.entities.Task;
import com.kompstudio.mappers.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;

/**
 * Created by mhesp on 22.06.2016.
 */
public class TaskDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Task> getTasksFromListId(int id) throws Exception {
        try {
            String SQL = "SELECT * FROM TASKS WHERE task_list_id = ?";
            Object[] params = {id};
            int[] types = {Types.INTEGER};
            return jdbcTemplate.query(SQL, params, types, new TaskMapper());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed to get tasks with task_list_id [" + id + "]");
        }
    }

    public boolean exists(Task task) throws Exception {
        try {
            String SQL = "SELECT * FROM TASKS WHERE TASK_LIST_ID = ? AND TASK_NAME = ? AND TASK_CREATED_DATE = ?";
            Timestamp timestamp = Timestamp.valueOf(task.getTaskCreatedDate());
            System.out.println("Insert timestamp [" + timestamp + "]");
            Object[] params = {task.getListId(), task.getTaskName(), timestamp};
            int[] types = {Types.INTEGER, Types.VARCHAR, Types.TIMESTAMP};
            TaskMapper taskMapper = new TaskMapper();
            jdbcTemplate.query(SQL, params, types, taskMapper);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public void add(Task task) throws Exception {
        try {
            String SQL = "INSERT INTO TASKS (TASK_LIST_ID, TASK_NAME, DONE, TASK_CREATED_DATE) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
            Object[] params = {task.getListId(), task.getTaskName(), task.isDone()};
            int[] types = {Types.INTEGER, Types.VARCHAR, Types.BOOLEAN};
            int res = jdbcTemplate.update(SQL, params, types);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed to add Task to DB: " + task.toString());
        }
    }
}
