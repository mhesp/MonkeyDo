package com.kompstudio.dao;

import com.kompstudio.entities.TaskList;
import com.kompstudio.mappers.TaskListMapper;
import org.springframework.asm.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Types;
import java.util.List;

/**
 * Created by mhesp on 22.06.2016.
 */
public class TaskListDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<TaskList> getListsFromUserId(int id) throws Exception {
        try {
            String SQL = "SELECT * FROM LISTS WHERE LIST_USER_ID = ?";
            Object[] params = { id };
            int[] types = { Types.INTEGER };
            TaskListMapper taskListMapper = new TaskListMapper();
            return jdbcTemplate.query(SQL, params, types, taskListMapper);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Couldn't select taskLists from list_user_id [" + id + "]");
        }
    }

    public int getListId(String name, int userId) throws Exception {
        try {
            String SQL = "SELECT LIST_ID FROM LISTS WHERE LIST_NAME = ? AND LIST_USER_ID = ?";
            Object[] params = { name, userId };
            int[] types = { Types.VARCHAR, Types.INTEGER };
            return jdbcTemplate.queryForObject(SQL, params, types, Integer.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed to get list_id where list_name [" + name + "] and user_id [" + userId + "]");
        }
    }

    public void add(TaskList taskList) throws Exception {
        try {
            String SQL = "INSERT INTO lists (list_name, list_user_id) VALUES (?, ?)";
            Object[] params = { taskList.getListName(), taskList.getUserId() };
            int[] types = { Types.INTEGER, Types.VARCHAR };
            int res = jdbcTemplate.update(SQL, params, types);
            System.out.println("Result of update [" + res + "]");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed to add TaskList to DB: " + taskList.toString());
        }
    }
}
