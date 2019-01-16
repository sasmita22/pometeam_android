package com.project.pentacode.pomestaff;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.pentacode.pomestaff.model.ApiMessage;
import com.project.pentacode.pomestaff.model.Staff;
import com.project.pentacode.pomestaff.retrofit.RetrofitClientInstance;
import com.project.pentacode.pomestaff.retrofit.ServiceInterface;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseLeaderAdapter extends RecyclerView.Adapter<ChooseLeaderAdapter.ViewHolder>{
    Context context;
    ArrayList<Staff> staffs;
    int idProject;
    int idStep;

    public ChooseLeaderAdapter(Context context, ArrayList<Staff> staffs, int idProject, int idStep) {
        this.context = context;
        this.staffs = staffs;
        this.idProject = idProject;
        this.idStep = idStep;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_leader_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textStaffName.setText(staffs.get(position).getName());
        holder.textStaffJabatan.setText(staffs.get(position).getJabatan());
        Glide.with(context)
                .load(RetrofitClientInstance.BASE_URL_IMAGE_PROFILE+staffs.get(position).getImage())
                .into(holder.imageStaffProfile);
    }


    @Override
    public int getItemCount() {
        return staffs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.show_team_name)
        TextView textStaffName;
        @BindView(R.id.show_team_jabatan)
        TextView textStaffJabatan;
        @BindView(R.id.show_team_image)
        ImageView imageStaffProfile;
        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }

        @OnClick(R.id.cardview_choose_leader)
        void onClickCardView(View v){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Change Leader Step")
                    .setMessage("Are you sure you want to choose this staff?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences sharedPreferences = context.getSharedPreferences("LoginData",Context.MODE_PRIVATE);
                            ServiceInterface service = RetrofitClientInstance.getInstance().create(ServiceInterface.class);
                            //Call<ApiMessage> bodyCall = service.setNewLeader(staffs.get(getAdapterPosition()).getNip(),idProject,idStep,sharedPreferences.getString("token",null));
                            Call<ApiMessage> bodyCall = service.setNewLeader(staffs.get(getAdapterPosition()).getNip(),idProject,idStep,sharedPreferences.getString("token",null));
                            bodyCall.enqueue(new Callback<ApiMessage>() {
                                @Override
                                public void onResponse(Call<ApiMessage> call, Response<ApiMessage> response) {
                                    if (response.isSuccessful()){
                                        if (response.code() == 200){
                                            ((ChooseLeaderActivity) context).onBackPressed();
                                        }else{
                                            Toast.makeText(context, response.isSuccessful()+" : "+response.message(), Toast.LENGTH_SHORT).show();
                                        }
                                    }else{
                                        Toast.makeText(context, response.isSuccessful()+" : "+response.message(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ApiMessage> call, Throwable t) {

                                }
                            });
                        }
                    })
                    .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }
}
