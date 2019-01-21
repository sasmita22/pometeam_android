package com.project.pentacode.pomestaff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.project.pentacode.pomestaff.model.Project;
import com.project.pentacode.pomestaff.retrofit.RetrofitClientInstance;
import com.project.pentacode.pomestaff.retrofit.ServiceInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Coba extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coba_activity);

        ServiceInterface serviceInterface = RetrofitClientInstance.getInstance().create(ServiceInterface.class);
        Call<Project> call = serviceInterface.getProjectQRCode("1",1,"asdhkasdhkjashd");
        call.enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                if (response.isSuccessful()){
                    //showProjectByQRCode(response.body());
                    Toast.makeText(Coba.this, response.body().getName(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Coba.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {
                Toast.makeText(Coba.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
