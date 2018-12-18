package com.project.pentacode.pomestaff;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.project.pentacode.pomestaff.model.Step;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectStepAdapter extends RecyclerView.Adapter<ProjectStepAdapter.ViewHolder> {
    Context context;
    ArrayList<Step> steps;
    public ProjectStepAdapter(Context context, ArrayList<Step> steps) {
        this.context = context;
        this.steps = steps;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Step step = steps.get(position);

        holder.stepName.setText(step.getName());
        holder.stepDeskripsi.setText(step.getDeskripsi());
        holder.stepRangeDate.setText("Deadline : "+step.getDeadlineAt());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,StepDetailActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        @BindView(R.id.step_item_name)
        TextView stepName;
        @BindView(R.id.step_item_deskripsi)
        TextView stepDeskripsi;
        @BindView(R.id.step_item_team)
        TextView steapTeam;
        @BindView(R.id.step_item_range_date)
        TextView stepRangeDate;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;

            ButterKnife.bind(this,itemView);
        }
    }
}
