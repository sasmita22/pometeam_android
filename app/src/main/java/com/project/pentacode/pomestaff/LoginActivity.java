package com.project.pentacode.pomestaff;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.pentacode.pomestaff.model.LoginUser;
import com.project.pentacode.pomestaff.retrofit.RetrofitClientInstance;
import com.project.pentacode.pomestaff.retrofit.ServiceInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.login_edt_email)
    EditText txtEmail;
    @BindView(R.id.login_edt_password)
    EditText txtPassword;
    @BindView(R.id.text_greet)
    TextView txtGreet;

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

        ServiceInterface service = RetrofitClientInstance.getInstance().create(ServiceInterface.class);
        Call<LoginUser> loginCall = service.doLogin(email,pass);
        loginCall.enqueue(new Callback<LoginUser>() {
            @Override
            public void onResponse(Call<LoginUser> call, Response<LoginUser> response) {
                boolean verified = false;
                //Toast.makeText(LoginActivity.this, response.isSuccessful()+"", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful())
                    prosesLogin(response.body());
                else
                    if (response.code() == 401) {
                        Toast.makeText(LoginActivity.this, "Email atau password Salah", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    } else if (response.code() == 500) {
                        Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }


            }

            @Override
            public void onFailure(Call<LoginUser> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getCause()+"", Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void prosesLogin(LoginUser loginUser){

        SharedPreferences sp = getSharedPreferences("LoginData", MODE_PRIVATE);
        SharedPreferences.Editor Ed=sp.edit();
        Ed.putString("nip",loginUser.getNip());
        Ed.putString("name",loginUser.getName());
        Ed.putString("email",loginUser.getEmail());
        Ed.putString("token","Bearer "+loginUser.getToken());
        Ed.apply();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
