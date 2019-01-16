package com.project.pentacode.pomestaff;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.project.pentacode.pomestaff.model.Task;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskReviewedAdapter extends RecyclerView.Adapter<TaskReviewedAdapter.ViewHolder> implements TaskAdapterInterface{
    Context context;
    ArrayList<Task> tasks;
    int idProject;
    int idStep;
    int positionId;

    public TaskReviewedAdapter(Context context, ArrayList<Task> tasks, int idProject, int idStep) {
        this.context = context;
        this.tasks = tasks;
        this.idProject = idProject;
        this.idStep = idStep;
        SharedPreferences sharedPreferences = context.getSharedPreferences("PROJECT",Context.MODE_PRIVATE);
        positionId = sharedPreferences.getInt("position_id",-1);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.taskreviewed_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (positionId == 2){
            holder.btnRevisi.setVisibility(View.VISIBLE);
            holder.btnDone.setVisibility(View.GONE);
        } else {
            holder.btnRevisi.setVisibility(View.VISIBLE);
            holder.btnDone.setVisibility(View.VISIBLE);
        }

        holder.taskName.setText(tasks.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void addList(Task task) {
        tasks.add(task);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.task_list_name)
        TextView taskName;
        @BindView(R.id.btn_revisi)
        ImageButton btnRevisi;
        @BindView(R.id.btn_done)
        ImageButton btnDone;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            btnRevisi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Task task = tasks.get(getAdapterPosition());
                    tasks.remove(getAdapterPosition());
                    ((ProjectTaskListActivity) context).reviseTask(task);
                    notifyDataSetChanged();
                }
            });

            btnDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Task task = tasks.get(getAdapterPosition());
                    tasks.remove(getAdapterPosition());
                    ((ProjectTaskListActivity) context).doneTask(task);
                    notifyDataSetChanged();
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
