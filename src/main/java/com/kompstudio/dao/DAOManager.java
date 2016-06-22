package com.kompstudio.dao;

import com.kompstudio.entities.Task;
import com.kompstudio.entities.TaskList;
import com.kompstudio.entities.ListToTask;
import com.kompstudio.entities.UserLists;
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
    TaskListDAO taskListDAO;

    @Autowired
    TaskDAO taskDAO;

    @Autowired
    UserDAO userDAO;

    public List<ListToTask> loadUserList(String owner) throws Exception {
        int userId = userDAO.getIdFromName(owner);
        List<TaskList> lists = taskListDAO.getListsFromUserId(userId);

        List<ListToTask> res = new ArrayList<ListToTask>();
        for (TaskList taskList: lists) {
            ListToTask userList = new ListToTask();
            userList.setTaskList(taskList);
            userList.setTasks(taskDAO.getTasksFromListId(taskList.getListId()));
            res.add(userList);
        }

        return res;
    }

    public void save(UserLists userLists) throws Exception {
        String name = userLists.getUser();
        int userId = userDAO.getIdFromName(name);

        List<ListToTask> list = userLists.getLists();
        for (ListToTask ltt: list) {
            TaskList taskList = ltt.getTaskList();
            taskList.setUserId(userId);
            if (taskList.getListId() == -1)  {
                taskListDAO.add(taskList);
                taskList.setListId(taskListDAO.getListId(taskList.getListName(), userId));
            }

            List<Task> tasks = ltt.getTasks();
            for (Task task: tasks) {
                task.setListId(taskList.getListId());
                if (null == task.getTaskCreatedDate()) {
                    taskDAO.add(task);
                }
            }
        }
    }
}
