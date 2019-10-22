package com.example.simpleroomdatabaseexample.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Task implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "task_name")
    private String task_name;

    @ColumnInfo(name = "task_desc ")
    private String task_desc ;

    @ColumnInfo(name = "task_completedate")
    private Date task_completedate;

    @ColumnInfo(name = "task_comment ")
    private String task_comment ;

    @ColumnInfo(name = "status")
    private int status;

    @ColumnInfo(name = "activate")
    private int activate ;

    @ColumnInfo(name = "task_create_date")
    private Date task_create_date ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_desc() {
        return task_desc;
    }

    public void setTask_desc(String task_desc) {
        this.task_desc = task_desc;
    }

    public Date getTask_completedate() {
        return task_completedate;
    }

    public void setTask_completedate(Date task_completedate) {
        this.task_completedate = task_completedate;
    }

    public String getTask_comment() {
        return task_comment;
    }

    public void setTask_comment(String task_comment) {
        this.task_comment = task_comment;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getActivate() {
        return activate;
    }

    public void setActivate(int activate) {
        this.activate = activate;
    }

    public Date getTask_create_date() {
        return task_create_date;
    }

    public void setTask_create_date(Date task_create_date) {
        this.task_create_date = task_create_date;
    }
}
