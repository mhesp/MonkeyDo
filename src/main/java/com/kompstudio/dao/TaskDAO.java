package com.kompstudio.dao;

import com.kompstudio.entities.Task;
import com.kompstudio.mappers.TaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.List;

/**
 * Created by mhesp on 22.06.2016.
 */
public class TaskDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private Logger logger = LoggerFactory.getLogger(TaskDAO.class);

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

    public int add(Task task) throws Exception {
        try {
            String SQL = "INSERT INTO TASKS VALUES (NULL, ?, ?, NULL, CURRENT_TIMESTAMP, ?)";
            Object[] params = { task.getListId(), task.getTaskName(), task.isDone() };
            int[] types = { Types.INTEGER, Types.VARCHAR, Types.BOOLEAN };
            int resInsert = jdbcTemplate.update(SQL, params, types);
            if (resInsert > 0) {
                return jdbcTemplate.queryForObject("SELECT last_insert_rowid()", Integer.class);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return -1;
        /*Connection conn = null;
        PreparedStatement statement = null;
        PreparedStatement getId = null;
        ResultSet generatedKeys = null;
        try {
            String SQL = "INSERT INTO TASKS (TASK_LIST_ID, TASK_NAME, DONE, TASK_CREATED_DATE) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
            conn = jdbcTemplate.getDataSource().getConnection();
            statement = conn.prepareStatement(SQL, new String[]{ "task_id" });
            statement.setInt(1, task.getListId());
            statement.setString(2, task.getTaskName());
            statement.setBoolean(3, task.isDone());
            statement.executeUpdate();

            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return (int)generatedKeys.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed to add Task to DB: " + task.toString());
        } finally {
            if (generatedKeys != null) {
                generatedKeys.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return -1;*/
    }

    public void delete(Task task) throws Exception {
        try {
            String SQL = "DELETE FROM TASKS WHERE TASK_ID = ?";
            Object[] params = {task.getId()};
            int[] types = {Types.INTEGER};
            int res = jdbcTemplate.update(SQL, params, types);
            logger.info("DELETE. Rows updated [" + res + "]");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed to delete task [" + task.toString() + "]");
        }
    }
}
