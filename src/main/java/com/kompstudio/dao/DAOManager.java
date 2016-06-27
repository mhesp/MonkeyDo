package com.kompstudio.dao;

import com.kompstudio.AppConfig;
import com.kompstudio.entities.Lists;
import com.kompstudio.entities.Task;
import com.kompstudio.entities.ToDoList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maria on 10.06.16.
 */
@Component
public class DAOManager {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ToDoListDAO taskListDAO;

    @Autowired
    TaskDAO taskDAO;

    @Autowired
    UserDAO userDAO;

    private Logger logger = LoggerFactory.getLogger(AppConfig.class);

    public List<Lists> loadUserList(int userId) throws Exception {
        logger.info("UserID [" + userId + "]");
        List<ToDoList> lists = taskListDAO.getListsFromUserId(userId);

        List<Lists> res = new ArrayList<Lists>();
        for (ToDoList list : lists) {
            Lists userList = new Lists();
            userList.setTaskList(list);
            userList.setTasks(taskDAO.getTasksFromListId(list.getListId()));
            logger.info("Added list \n" + userList.toString());
            res.add(userList);
        }

        return res;
    }

    public int saveList(ToDoList list) throws Exception {
        logger.info("Saving list [" + list.toString() + "]");
        return taskListDAO.add(list);
    }

    public int saveTask(Task task) throws Exception {
        logger.info("Saving task [" + task.toString() + "]");
        return taskDAO.add(task);
    }

    public void deleteTask(Task task) throws Exception {
        logger.info("Deleting task [" + task.toString() + "]");
        taskDAO.delete(task);
    }

}
