package com.kompstudio.entities;

/**
 * Created by mhesp on 11.06.2016.
 */
public class ToDoList {

    private int userId;
    private int listId;
    private String listName;

    public int getUserId() {
        return userId;
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

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    @Override
    public String toString() {
        return "listId [" + listId + "] userId [" + userId + "] listName [" + listName + "]";
    }
}
