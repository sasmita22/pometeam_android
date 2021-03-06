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

public class DashboardLeaderAdapter extends RecyclerView.Adapter<DashboardLeaderAdapter.ViewHolder>{
    Context context;
    ArrayList<Dashboard> dashboardsLeader;

    public DashboardLeaderAdapter(Context context, ArrayList<Dashboard> dashboardsLeader) {
        this.context = context;
        this.dashboardsLeader = dashboardsLeader;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_task_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.taskName.setText(dashboardsLeader.get(position).getTaskName());
        holder.taskDeadline.setText(dashboardsLeader.get(position).getDeadline()+" (Deadline)");
        holder.projectName.setText("Project : "+dashboardsLeader.get(position).getProjectName());
        holder.stepName.setText("Step : "+dashboardsLeader.get(position).getStepName());
    }

    @Override
    public int getItemCount() {
        return dashboardsLeader.size();
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
