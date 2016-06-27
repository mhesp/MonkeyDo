package com.kompstudio.dao;

import com.kompstudio.entities.ToDoList;
import com.kompstudio.mappers.ToDoListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

/**
 * Created by mhesp on 22.06.2016.
 */
public class TaskListDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<ToDoList> getListsFromUserId(int id) throws Exception {
        try {
            String SQL = "SELECT * FROM LISTS WHERE LIST_USER_ID = ?";
            Object[] params = {id};
            int[] types = {Types.INTEGER};
            ToDoListMapper taskListMapper = new ToDoListMapper();
            return jdbcTemplate.query(SQL, params, types, taskListMapper);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Couldn't select taskLists from list_user_id [" + id + "]");
        }
    }

    public int getListId(String name, int userId) throws Exception {
        try {
            String SQL = "SELECT LIST_ID FROM LISTS WHERE LIST_NAME = ? AND LIST_USER_ID = ?";
            Object[] params = {name, userId};
            int[] types = {Types.VARCHAR, Types.INTEGER};
            return jdbcTemplate.queryForObject(SQL, params, types, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed to get list_id where list_name [" + name + "] and user_id [" + userId + "]");
        }
    }

    public int add(ToDoList taskList) throws Exception {
        PreparedStatement statement = null;
        try {
            String SQL = "INSERT INTO lists (list_name, list_user_id) VALUES (?, ?)";
            statement = jdbcTemplate.getDataSource().getConnection()
                    .prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, taskList.getListName());
            statement.setInt(2, taskList.getUserId());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed to add TaskList to DB: " + taskList.toString());
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return -1;
    }
}
