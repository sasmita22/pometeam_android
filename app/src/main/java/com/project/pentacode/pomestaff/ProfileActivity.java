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

import com.project.pentacode.pomestaff.model.ModelGenerator;
import com.project.pentacode.pomestaff.model.Staff;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity {
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
    String prefNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        ButterKnife.bind(this);

        SharedPreferences sp1=this.getSharedPreferences("LoginData", MODE_PRIVATE);

        prefNip = sp1.getString("nip", "kosong");
        prefNama = sp1.getString("nama", "kosong");
        Staff staff = ModelGenerator.getStaff(prefNip);
        txtName.setText(staff.name);
        txtJabatan.setText(staff.jabatan);
        txtEmail.setText(staff.email);
        txtTelephone.setText(staff.telephone);
        txtGit.setText(staff.git);
    }

    @OnClick(R.id.profile_back_btn)
    void backButton(){
        onBackPressed();
    }
}
