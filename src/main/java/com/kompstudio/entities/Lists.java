package com.kompstudio.entities;

import java.util.List;

/**
 * Created by mhesp on 22.06.2016.
 */
public class Lists {

    private ToDoList taskList;
    private List<Task> tasks;

    public ToDoList getTaskList() {
        return taskList;
    }

    public void setTaskList(ToDoList taskList) {
        this.taskList = taskList;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("List: " + taskList.toString() + "\n");
        for (Task task : tasks) {
            builder.append(task.toString() + "\n");
        }
        return builder.toString();
    }
}
