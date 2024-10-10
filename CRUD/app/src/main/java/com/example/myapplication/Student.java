package com.example.myapplication;


public class Student {
    int id = 0;
    String username;
    String password;

    public Student(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Student(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
