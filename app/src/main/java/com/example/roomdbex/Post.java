package com.example.roomdbex;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "posts_table")
public class Post {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    private  User user;
    private String title;
    private String body;

    public Post( User user, String title, String body) {

        this.user = user;
        this.title = title;
        this.body = body;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
