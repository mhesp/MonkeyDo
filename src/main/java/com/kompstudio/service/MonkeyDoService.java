package com.kompstudio.service;

import com.kompstudio.dao.DAOManager;
import com.kompstudio.entities.Lists;
import com.kompstudio.entities.Task;
import com.kompstudio.entities.ToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by maria on 08.04.16.
 */

@RestController
@RequestMapping(value = "/monkeydo")
public class MonkeyDoService {

    @Autowired
    DAOManager daoManager;

    @RequestMapping(value = "/save/task", method = RequestMethod.PUT, produces = "application/json")
    public int save(@RequestBody Task task) throws Exception {
        System.out.println("Saving task!");
        return daoManager.saveTask(task);
    }

    @RequestMapping(value = "/save/list", method = RequestMethod.PUT, produces = "application/json")
    public int save(@RequestBody ToDoList list) throws Exception {
        System.out.println("Saving list!");
        return daoManager.saveList(list);
    }

    @RequestMapping(value = "/delete/task", method = RequestMethod.PUT, produces = "application/json")
    public void deleteTask(@RequestBody Task task) throws Exception {
        daoManager.deleteTask(task);
    }

    @RequestMapping(value = "/toggle/task", method = RequestMethod.PUT, produces = "application/json")
    public void toggle(@RequestBody Task task) throws Exception {
        daoManager.toggleTask(task);
    }

    @RequestMapping(value = "/load/{userId}", method = RequestMethod.GET, produces = "application/json")
    public List<Lists> load(@PathVariable("userId") int userId) throws Exception {
        return daoManager.loadUserList(userId);
    }
}