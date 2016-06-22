package com.kompstudio.entities;

import java.util.List;

/**
 * Created by mhesp on 22.06.2016.
 */
public class ListToTask {

    private TaskList taskList;
    private List<Task> tasks;

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
