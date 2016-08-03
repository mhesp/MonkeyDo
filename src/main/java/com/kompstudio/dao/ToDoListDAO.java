package com.kompstudio.dao;

import com.kompstudio.entities.Task;
import com.kompstudio.entities.ToDoList;
import com.kompstudio.mappers.ToDoListMapper;
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
public class ToDoListDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private Logger logger = LoggerFactory.getLogger(ToDoListDAO.class);

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

        try {
            String SQL = "INSERT INTO lists VALUES (?, ?, NULL)";
            Object[] params = { taskList.getUserId(), taskList.getListName() };
            int[] types = { Types.INTEGER,Types.VARCHAR };
            int resInsert = jdbcTemplate.update(SQL, params, types);
            if (resInsert > 0) {
                return jdbcTemplate.queryForObject("SELECT last_insert_rowid()", Integer.class);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void delete(ToDoList list) throws Exception {
        try {
            String SQL = "DELETE FROM lists WHERE list_id = ?";
            Object[] params = {list.getListId()};
            int[] types = {Types.INTEGER};
            int res = jdbcTemplate.update(SQL, params, types);
            logger.info("DELETE. Rows updated [" + res + "]");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed to delete list [" + list.toString() + "]");
        }
    }
}
