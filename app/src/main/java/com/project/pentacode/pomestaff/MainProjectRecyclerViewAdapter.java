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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.lzyzsd.circleprogress.DonutProgress;
import com.project.pentacode.pomestaff.model.Project;
import com.project.pentacode.pomestaff.retrofit.RetrofitClientInstance;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;

public class MainProjectRecyclerViewAdapter extends RecyclerView.Adapter<MainProjectRecyclerViewAdapter.ViewHolder> {
    MainProjectFragment.OnListProjectInteraction mListener;
    List<Project> projects;
    final DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
    Context context;

    public MainProjectRecyclerViewAdapter(List<Project> projects, MainProjectFragment.OnListProjectInteraction mListener) {
        this.mListener = mListener;
        this.projects = projects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.project_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        //getShared
        SharedPreferences sp1= context.getSharedPreferences("LoginData", MODE_PRIVATE);

        String prefNip = sp1.getString("nip", "");

        final Project project = projects.get(position);
        int teamMember = 0;

        holder.txtTitleInImage.setText(project.getName());
        holder.txtProjectManager.setText(project.getProjectManager());
        holder.txtRange.setText(project.getStartAt()+" - "+project.getDeadlineAt());
        holder.txtPosition.setText("as "+project.getJabatan());
        holder.txtStatus.setText(project.getProgress()==100?"Completed":"On Progress");
        holder.donutProgress.setProgress(project.getProgress());
        //holder.donutProgress.setProgress(project.percentage);
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


        Glide.with(context)
                .load(RetrofitClientInstance.BASE_URL_IMAGE_COMPANY+project.getImage())
                .into(holder.imgCompany);


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
        @BindView(R.id.project_txt_status)
        public TextView txtStatus;
        @BindView(R.id.project_txt_range_time)
        public TextView txtRange;
        @BindView(R.id.project_txt_position)
        public TextView txtPosition;
        @BindView(R.id.project_donut_progress)
        public DonutProgress donutProgress;
        @BindView(R.id.expansion)
        public ViewGroup expansion;
        @BindView(R.id.project_item_detail)
        public Button btn_detail;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @OnClick(R.id.project_item_detail)
        public void onClick(View v){
            Project project = projects.get(getAdapterPosition());
            MainActivity context = (MainActivity) mListener;
            SharedPreferences sp = context.getSharedPreferences("PROJECT",MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("position_id",project.getPosition_id());
            editor.putInt("step",project.getStepWorkOn());
            editor.putInt("project",project.getIdProject());
            editor.apply();
            Intent intent = new Intent(context,ProjectDetailActivity.class);
            context.startActivity(intent);
            //Toast.makeText(context, projects.get(0).getIdProject(), Toast.LENGTH_SHORT).show();
            //Log.d("dataa",projects.get(getAdapterPosition()).getIdProject()+"");
        }
    }
}
