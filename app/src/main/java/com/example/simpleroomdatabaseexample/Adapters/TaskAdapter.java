package com.example.simpleroomdatabaseexample.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpleroomdatabaseexample.Activities.MainActivity;
import com.example.simpleroomdatabaseexample.Entity.Task;
import com.example.simpleroomdatabaseexample.R;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {
    private Context mContext;
    private List<Task> tasks;
    public TaskAdapter(MainActivity mainActivity, List<Task> tasks) {
        this.mContext = mainActivity;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_task,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.tvTask.setText(tasks.get(i).getTask_name());
        myViewHolder.tvTaskDesc.setText(tasks.get(i).getTask_desc());
        myViewHolder.tvTaskDate.setText(""+tasks.get(i).getTask_completedate());

        myViewHolder.BtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTask , tvTaskDesc , tvTaskDate;
        private Button BtnDelete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTask=itemView.findViewById(R.id.tvTask);
            tvTaskDesc=itemView.findViewById(R.id.tvTaskDesc);
            tvTaskDate=itemView.findViewById(R.id.tvTaskDate);
            BtnDelete=itemView.findViewById(R.id.BtnDelete);
        }


    }
}
