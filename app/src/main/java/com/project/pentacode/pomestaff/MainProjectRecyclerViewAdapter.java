package com.project.pentacode.pomestaff;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;
import com.project.pentacode.pomestaff.model.Project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainProjectRecyclerViewAdapter extends RecyclerView.Adapter<MainProjectRecyclerViewAdapter.ViewHolder> {
    MainProjectFragment.OnListProjectInteraction mListener;
    List<Project> projects;
    final DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");

    public MainProjectRecyclerViewAdapter(List<Project> projects, MainProjectFragment.OnListProjectInteraction mListener) {
        this.mListener = mListener;
        this.projects = projects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.project_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Project project = projects.get(position);
        holder.txtTitleInImage.setText(project.title);
        holder.txtProjectManager.setText(project.projectManager.name);
        holder.txtRange.setText(dateFormat.format(project.startAt)+" - "+dateFormat.format(project.deadlineAt));
        holder.txtPosition.setText("as "+project.userPosition);
        holder.txtTeam.setText(project.jumlahTeam+" Orang (Team)");
        holder.donutProgress.setProgress(project.percentage);
        holder.imgCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.expansion.getVisibility() == View.GONE) {
                    holder.expansion.setVisibility(View.VISIBLE);
                } else {
                    holder.expansion.setVisibility(View.GONE);
                }
            }
        });
        holder.imgCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity context = (MainActivity) mListener;
                context.startActivity(new Intent(context,ProjectDetailActivity.class));
            }
        });
    }




    @Override
    public int getItemCount() {
        return projects.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public View mView;
        @BindView(R.id.project_txt_image_title)
        public TextView txtTitleInImage;
        @BindView(R.id.project_image_company)
        public ImageView imgCompany;
        @BindView(R.id.project_txt_pm_name)
        public TextView txtProjectManager;
        @BindView(R.id.project_txt_team)
        public TextView txtTeam;
        @BindView(R.id.project_txt_range_time)
        public TextView txtRange;
        @BindView(R.id.project_txt_position)
        public TextView txtPosition;
        @BindView(R.id.project_donut_progress)
        public DonutProgress donutProgress;
        @BindView(R.id.expansion)
        public ViewGroup expansion;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
