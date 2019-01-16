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
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseStaffResponsibleAdapter extends RecyclerView.Adapter<ChooseStaffResponsibleAdapter.ViewHolder> {
    Context context;
    ArrayList<Staff> staffs;
    int idTask;

    public ChooseStaffResponsibleAdapter(Context context, ArrayList<Staff> staffs,int idTask) {
        this.context = context;
        this.staffs = staffs;
        this.idTask = idTask;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_staff_responsible_task_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textName.setText(staffs.get(position).getName());
        holder.textJabatan.setText(staffs.get(position).getJabatan());
        Glide.with(context)
                .load(RetrofitClientInstance.BASE_URL_IMAGE_PROFILE+staffs.get(position).getImage())
                .into(holder.imageStaff);
    }

    @Override
    public int getItemCount() {
        return staffs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.show_team_image)
        CircleImageView imageStaff;
        @BindView(R.id.show_team_name)
        TextView textName;
        @BindView(R.id.show_team_jabatan)
        TextView textJabatan;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }

        @OnClick(R.id.cardview_choose_staff_responsible)
        void onClickCardView(View v){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Change Staff Responsibility")
                    .setMessage("Are you sure you want to choose this staff?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            String token = context.getSharedPreferences("LoginData",Context.MODE_PRIVATE).getString("token",null);
                            ServiceInterface serviceInterface = RetrofitClientInstance.getInstance().create(ServiceInterface.class);
                            Call<ApiMessage> call = serviceInterface.setPenanggungJawabTask(idTask,staffs.get(getAdapterPosition()).nip,token);
                            call.enqueue(new Callback<ApiMessage>() {
                                @Override
                                public void onResponse(Call<ApiMessage> call, Response<ApiMessage> response) {
                                    if (response.code()==201){
                                        Toast.makeText(context, "Berhasil diubah", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(context, "Cek Lagii : "+response.message()+"("+response.code()+")", Toast.LENGTH_SHORT).show();
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
