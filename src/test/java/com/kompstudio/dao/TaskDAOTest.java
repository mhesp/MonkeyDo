package com.kompstudio.dao;

import com.kompstudio.TestConfiguration;
import com.kompstudio.entities.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by mhesp on 22.06.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestConfiguration.class)
@WebAppConfiguration
public class TaskDAOTest {

    @Autowired
    TaskDAO taskDAO;

    @Test
    public void testGetTasksFromId() throws Exception {
        List<Task> tasks = taskDAO.getTasksFromListId(0);

        for (Task task: tasks) {
            System.out.println(task);
        }
    }

}
