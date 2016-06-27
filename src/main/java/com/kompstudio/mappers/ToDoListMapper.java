package com.kompstudio.mappers;

import com.kompstudio.entities.ToDoList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mhesp on 11.06.2016.
 */
public class ToDoListMapper implements RowMapper<ToDoList> {

    public ToDoList mapRow(ResultSet resultSet, int i) throws SQLException {
        ToDoList list = new ToDoList();
        list.setUserId(resultSet.getInt(1));
        list.setListName(resultSet.getString(2));
        list.setListId(resultSet.getInt(3));
        return list;
    }
}
