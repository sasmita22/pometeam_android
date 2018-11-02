package com.project.pentacode.pomestaff;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.pentacode.pomestaff.model.ModelGenerator;
import com.project.pentacode.pomestaff.model.Staff;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.login_edt_email)
    EditText txtEmail;
    @BindView(R.id.login_edt_password)
    EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);

        //Toast.makeText(this, ModelGenerator.getListStaff().get(8).nama, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.login_submit)
    public void submitLogin(View view){
        String email = txtEmail.getText().toString();
        String pass = txtPassword.getText().toString();
        boolean verified = false;

        ArrayList<Staff> listStaff = ModelGenerator.getListStaff();

        for (int i = 0;i < listStaff.size();i++){
            Staff staff = listStaff.get(i);
            if (email.equalsIgnoreCase(staff.email) && pass.equals("123456")){
                verified = true;
                SharedPreferences sp=getSharedPreferences("LoginData", MODE_PRIVATE);
                SharedPreferences.Editor Ed=sp.edit();
                Ed.putString("nip",staff.nip );
                Ed.putString("nama",staff.name );
                Ed.apply();

                startActivity(new Intent(this,MainActivity.class));
                finish();
            }
        }

        if (!verified)
            Toast.makeText(this, "Login Error", Toast.LENGTH_SHORT).show();

    }
}
