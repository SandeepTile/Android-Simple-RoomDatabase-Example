package com.example.simpleroomdatabaseexample.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.simpleroomdatabaseexample.Entity.Task;
import com.example.simpleroomdatabaseexample.R;
import com.example.simpleroomdatabaseexample.Utils.DataBaseClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddTaskActivty extends AppCompatActivity {

    private EditText etTask, etTaskDesc, etTaskComment;
    private Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        etTask = findViewById(R.id.etTask);
        etTaskDesc = findViewById(R.id.etTaskDesc);
        etTaskComment = findViewById(R.id.etTaskComment);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new saveTask().execute();

            }
        });

    }

    private boolean validation() {
        if (etTask.getText().toString().trim().isEmpty()) {
            etTask.setError("please enter task");
            etTask.requestFocus();
            return false;
        } else if (etTaskDesc.getText().toString().trim().isEmpty()) {
            etTaskDesc.setError("please enter task Desc");
            etTaskDesc.requestFocus();
            return false;
        } else if (etTaskComment.getText().toString().trim().isEmpty()) {
            etTaskComment.setError("please enter task comment");
            etTaskComment.requestFocus();
            return false;
        } else {
            return true;

        }
    }

    class saveTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                new Handler(Looper.getMainLooper()){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        btnSubmit.setText("please wait...");
                        btnSubmit.setEnabled(false);
                    }
                };
                Date date = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String strDate = df.format(date);

                Date create_date = df.parse(strDate);


                Task task = new Task();
                task.setTask_name(etTask.getText().toString().trim());
                task.setTask_comment(etTaskComment.getText().toString().trim());
                task.setTask_desc(etTaskDesc.getText().toString().trim());
                task.setActivate(0);
                task.setTask_completedate(create_date);
                task.setTask_create_date(date);


                //add to Database
                DataBaseClient.getInstance(getApplicationContext()).getAppDataBase()
                        .taskDao().insert(task);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            new Handler(Looper.getMainLooper()){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    btnSubmit.setText("submit");
                    btnSubmit.setEnabled(true);
                }
            };
            Toast.makeText(getApplicationContext(), "Succesfully Saved", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }
}
