package com.example.simpleroomdatabaseexample.DataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.simpleroomdatabaseexample.Dao.TaskDao;
import com.example.simpleroomdatabaseexample.Entity.Task;
import com.example.simpleroomdatabaseexample.Utils.Converter;

@Database(entities = {Task.class},version = 1)
@TypeConverters({Converter.class})
public abstract class AppDataBase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
