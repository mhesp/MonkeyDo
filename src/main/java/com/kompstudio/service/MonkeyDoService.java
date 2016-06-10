package com.kompstudio.service;

import com.kompstudio.dao.DAOManager;
import com.kompstudio.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by maria on 08.04.16.
 */

@RestController
@RequestMapping(value = "/monkeydo")
public class MonkeyDoService {

    @Autowired
    DAOManager daoManager;

    @RequestMapping(value = "/save", method = RequestMethod.GET, produces = "application/json")
    public String save() {
        daoManager.save();
        return "\"Test of REST api - saving...\"";
    }
}