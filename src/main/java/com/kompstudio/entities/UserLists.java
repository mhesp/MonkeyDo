package com.kompstudio.entities;

import java.util.List;

/**
 * Created by mhesp on 22.06.2016.
 */
public class UserLists {

    private String user;
    private List<ListToTask> lists;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<ListToTask> getLists() {
        return lists;
    }

    public void setLists(List<ListToTask> lists) {
        this.lists = lists;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [" + user + "]:\n");
        for (ListToTask list: lists) {
            builder.append(list.toString() + "\n");
        }
        return builder.toString();
    }
}
