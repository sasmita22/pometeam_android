package com.project.pentacode.pomestaff;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.project.pentacode.pomestaff.model.Task;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskDoneAdapter extends RecyclerView.Adapter<TaskDoneAdapter.ViewHolder> implements TaskAdapterInterface{
    Context context;
    ArrayList<Task> tasks;
    int idProject;
    int idStep;

    public TaskDoneAdapter(Context context, ArrayList<Task> tasks, int idProject, int idStep) {
        this.context = context;
        this.tasks = tasks;
        this.idProject = idProject;
        this.idStep = idStep;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.taskdone_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.taskName.setText(tasks.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    @Override
    public void addList(Task task) {
        tasks.add(task);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.task_list_name)
        TextView taskName;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @OnClick(R.id.btn_info)
        void onClickInfo(View v){
            Intent intent = new Intent(context,TaskDetailActivity.class);
            intent.putExtra("id_task",tasks.get(getAdapterPosition()).getId());
            intent.putExtra("id_project",idProject);
            intent.putExtra("id_step",idStep);
            context.startActivity(intent);
        }
    }
}
