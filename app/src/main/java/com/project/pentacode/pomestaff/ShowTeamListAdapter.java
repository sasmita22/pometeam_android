package com.project.pentacode.pomestaff;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.pentacode.pomestaff.model.ApiMessage;
import com.project.pentacode.pomestaff.model.Staff;
import com.project.pentacode.pomestaff.retrofit.RetrofitClientInstance;
import com.project.pentacode.pomestaff.retrofit.ServiceInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowTeamListAdapter extends RecyclerView.Adapter<ShowTeamListAdapter.ViewHolder> {
    Context context;
    List<Staff> staffs;
    int idProject;
    int idStep;
    public ShowTeamListAdapter(Context context, List<Staff> staffs, int idProject, int idStep) {
        this.context = context;
        this.staffs = staffs;
        this.idProject = idProject;
        this.idStep = idStep;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_team_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textName.setText(staffs.get(position).getName());
        holder.textJabatan.setText(staffs.get(position).getJabatan());
        Glide.with(context)
                .load(RetrofitClientInstance.BASE_URL_IMAGE_PROFILE+staffs.get(position).getImage())
                .into(holder.imageProfile);
    }




    @Override
    public int getItemCount() {
        return staffs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.show_team_image)
        CircleImageView imageProfile;
        @BindView(R.id.show_team_name)
        TextView textName;
        @BindView(R.id.show_team_jabatan)
        TextView textJabatan;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @OnClick(R.id.show_team_item_delete)
        void onClickDelete(View v){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Delete Team Member")
                    .setMessage("Are you sure you want to delete this team member?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            ServiceInterface serviceInterface = RetrofitClientInstance.getInstance().create(ServiceInterface.class);
                            Call<ApiMessage> call = serviceInterface.deleteMember(idProject,idStep,staffs.get(getAdapterPosition()).getNip(),
                                    context.getSharedPreferences("LoginData",Context.MODE_PRIVATE).getString("token",null));
                            call.enqueue(new Callback<ApiMessage>() {
                                @Override
                                public void onResponse(Call<ApiMessage> call, Response<ApiMessage> response) {
                                    if(response.code() == 201){
                                        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ApiMessage> call, Throwable t) {
                                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
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
