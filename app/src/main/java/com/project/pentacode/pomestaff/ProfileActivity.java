package com.project.pentacode.pomestaff;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.pentacode.pomestaff.model.ModelGenerator;
import com.project.pentacode.pomestaff.model.Staff;
import com.project.pentacode.pomestaff.retrofit.RetrofitClientInstance;
import com.project.pentacode.pomestaff.retrofit.ServiceInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    @BindView(R.id.profile_pict)
    CircleImageView profileImage;
    @BindView(R.id.profile_name)
    TextView txtName;
    @BindView(R.id.profile_jabatan)
    TextView txtJabatan;
    @BindView(R.id.profile_email)
    TextView txtEmail;
    @BindView(R.id.profile_telephone)
    TextView txtTelephone;
    @BindView(R.id.profile_git)
    TextView txtGit;

    String prefNip;
    String prefToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        ButterKnife.bind(this);

        SharedPreferences sp1=this.getSharedPreferences("LoginData", MODE_PRIVATE);

        prefNip = sp1.getString("nip", "kosong");
        prefToken = sp1.getString("token", "kosong");
        /*Staff staff = ModelGenerator.getStaff(prefNip);
        txtName.setText(staff.name);
        txtJabatan.setText(staff.jabatan);
        txtEmail.setText(staff.email);
        txtTelephone.setText(staff.telephone);
        txtGit.setText(staff.git);*/

        ServiceInterface service = RetrofitClientInstance.getInstance().create(ServiceInterface.class);
        Call<Staff> staffCall = service.getStaff(prefNip,prefToken);
        staffCall.enqueue(new Callback<Staff>() {
            @Override
            public void onResponse(Call<Staff> call, Response<Staff> response) {
                setDataToView(response.body());
            }

            @Override
            public void onFailure(Call<Staff> call, Throwable t) {

            }
        });
    }

    private void setDataToView(Staff staff){
        Glide.with(getApplicationContext())
                .load(RetrofitClientInstance.BASE_URL_IMAGE_PROFILE+staff.getImage())
                .into(profileImage);
        txtName.setText(staff.getName());
        txtJabatan.setText(staff.getJabatan());
        txtEmail.setText(staff.getEmail());
        txtTelephone.setText(staff.getTelephone());
    }

    @OnClick(R.id.profile_back_btn)
    void backButton(){
        onBackPressed();
    }
}
