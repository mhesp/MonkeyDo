package com.kompstudio.service;

import com.kompstudio.dao.DAOManager;
import com.kompstudio.entities.ListToTask;
import com.kompstudio.entities.UserLists;
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

    @RequestMapping(value = "/save", method = RequestMethod.PUT, produces = "application/json")
    public void save(@RequestBody UserLists userLists) throws Exception {
        daoManager.save(userLists);
    }

    @RequestMapping(value = "/load/{owner}", method = RequestMethod.GET, produces = "application/json")
    public List<ListToTask> load(@PathVariable("owner") String owner) throws Exception {
        return daoManager.loadUserList(owner);
    }
}