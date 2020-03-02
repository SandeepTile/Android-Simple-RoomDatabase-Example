package com.example.simpleroomdatabaseexample.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.simpleroomdatabaseexample.Entity.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task")
    List<Task> getAllData();

    @Query("DELETE FROM task WHERE task_name = :task_name")
    abstract void deleteByUserId(String task_name);

    @Insert
    void insert(Task task);

    @Query("UPDATE task SET `task_desc ` = :task_desc WHERE task_name= :task_name")
    void update(String task_name,String task_desc);
}
