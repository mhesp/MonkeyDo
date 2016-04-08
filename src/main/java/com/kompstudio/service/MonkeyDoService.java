package com.kompstudio.service;

import com.kompstudio.entities.Task;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by maria on 08.04.16.
 */

@RestController
@RequestMapping(value = "/monkeydo")
public class MonkeyDoService {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Task test() {
        return new Task("this is a rest-test!");
    }
}