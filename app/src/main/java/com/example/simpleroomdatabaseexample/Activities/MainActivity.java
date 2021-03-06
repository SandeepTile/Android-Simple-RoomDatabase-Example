package com.example.simpleroomdatabaseexample.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.simpleroomdatabaseexample.Adapters.TaskAdapter;
import com.example.simpleroomdatabaseexample.Entity.Task;
import com.example.simpleroomdatabaseexample.Interface.RecyclerViewClickListener;
import com.example.simpleroomdatabaseexample.R;
import com.example.simpleroomdatabaseexample.Utils.DataBaseClient;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnAddTask;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddTask = findViewById(R.id.btnAddTask);
        recyclerView = findViewById(R.id.recyclerView);

        linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setLayoutManager(linearLayoutManager);

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddTaskActivty.class));
            }
        });

        new getTask().execute();

    }

    class getTask extends AsyncTask<Void , Void , List<Task>>{

        @Override
        protected List<Task> doInBackground(Void... voids) {
            List<Task> taskList = DataBaseClient
                    .getInstance(getApplicationContext())
                    .getAppDataBase()
                    .taskDao()
                    .getAllData();
            return taskList;
        }

        @Override
        protected void onPostExecute(List<Task> tasks) {
            super.onPostExecute(tasks);
//            Log.e("taskDetails: " , tasks.get(tasks.size()-1).getTask_name());
            TaskAdapter adapter = new TaskAdapter(MainActivity.this , tasks,recyclerViewClickListener);
            recyclerView.setAdapter(adapter);
        }
    }

    class deleteTask extends AsyncTask<String , Void , Void>{
        @Override
        protected Void doInBackground(String... strings) {

            DataBaseClient
                    .getInstance(getApplicationContext())
                    .getAppDataBase()
                    .taskDao()
                    .deleteByUserId(strings[0]);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            new getTask().execute();

        }
    }


    RecyclerViewClickListener recyclerViewClickListener=new RecyclerViewClickListener() {
        @Override
        public void recyclerViewListClicked(int position,String taskName) {

            Toast.makeText(MainActivity.this, "Delete button clicked", Toast.LENGTH_SHORT).show();

            new deleteTask().execute(taskName);

            /*TaskAdapter adapter = new TaskAdapter(MainActivity.this , taskList,recyclerViewClickListener);
            recyclerView.setAdapter(adapter);*/
        }
    };

}
