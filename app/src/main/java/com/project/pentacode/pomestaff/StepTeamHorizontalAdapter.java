package com.project.pentacode.pomestaff;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.pentacode.pomestaff.model.Staff;
import com.project.pentacode.pomestaff.model.Step;
import com.project.pentacode.pomestaff.retrofit.RetrofitClientInstance;
import com.project.pentacode.pomestaff.retrofit.ServiceInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class StepTeamHorizontalAdapter extends RecyclerView.Adapter<StepTeamHorizontalAdapter.ViewHolder>{
    Context context;
    Step step;

    public StepTeamHorizontalAdapter(Context context, Step step) {
        this.context = context;
        this.step = step;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.staff_horizontal_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Staff staff = step.getTeam().get(position);
        holder.textNama.setText(staff.getName());
        holder.textJabatan.setText(staff.getJabatan());
        Glide.with(context)
                .load(RetrofitClientInstance.BASE_URL_IMAGE_PROFILE+staff.getImage())
                .into(holder.imageTeam);
    }

    @Override
    public int getItemCount() {
        return step.getTeam().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.horizontal_staff_image)
        CircleImageView imageTeam;
        @BindView(R.id.horizontal_staff_nama)
        TextView textNama;
        @BindView(R.id.horizontal_staff_jabatan)
        TextView textJabatan;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
