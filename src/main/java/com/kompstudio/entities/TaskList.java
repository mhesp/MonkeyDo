package com.kompstudio.entities;

/**
 * Created by mhesp on 11.06.2016.
 */
public class TaskList {

    private String userName;
    private int userId;
    private String listName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "userName [" + userName + "] userId [" + userId  + "] listName [" + listName + "]";
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }
}
