package com.example.simpleroomdatabaseexample.Utils;

import android.content.Context;

import androidx.room.Room;

import com.example.simpleroomdatabaseexample.DataBase.AppDataBase;

public class DataBaseClient {
    private Context mContext;
    private static DataBaseClient mInstance;

    private AppDataBase appDataBase;

    public DataBaseClient(Context mContext) {
        this.mContext = mContext;
        appDataBase = Room.databaseBuilder(mContext , AppDataBase.class , "MyDailyTask").build();
    }

    public static synchronized DataBaseClient getInstance(Context mContext){
        if (mInstance == null)
            mInstance = new DataBaseClient(mContext);
        return mInstance;
    }

    public AppDataBase getAppDataBase(){
        return appDataBase;
    }
}
