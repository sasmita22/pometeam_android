package com.project.pentacode.pomestaff;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.pentacode.pomestaff.model.Dashboard;
import com.project.pentacode.pomestaff.model.Task;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DashboardManagerAdapter extends RecyclerView.Adapter<DashboardManagerAdapter.ViewHolder>{
    Context context;
    ArrayList<Dashboard> dashboardsManager;

    public DashboardManagerAdapter(Context context, ArrayList<Dashboard> dashboardsManager) {
        this.context = context;
        this.dashboardsManager = dashboardsManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_task_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.taskName.setText(dashboardsManager.get(position).getTaskName());
        holder.taskDeadline.setText(dashboardsManager.get(position).getDeadline()+" (Deadline)");
        holder.projectName.setText("Project : "+dashboardsManager.get(position).getProjectName());
        holder.stepName.setText("Step : "+dashboardsManager.get(position).getStepName());
    }

    @Override
    public int getItemCount() {
        return dashboardsManager.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.taskName)
        TextView taskName;
        @BindView(R.id.taskDeadline)
        TextView taskDeadline;
        @BindView(R.id.projectName)
        TextView projectName;
        @BindView(R.id.stepName)
        TextView stepName;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @OnClick(R.id.project_item_detail)
        public void onClickDetail(View v){

        }
    }
}
