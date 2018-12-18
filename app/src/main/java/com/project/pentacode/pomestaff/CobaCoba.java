package com.project.pentacode.pomestaff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.project.pentacode.pomestaff.model.Project;
import com.project.pentacode.pomestaff.retrofit.RetrofitClientInstance;
import com.project.pentacode.pomestaff.retrofit.ServiceInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CobaCoba extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coba_coba);

        textView = findViewById(R.id.coba);

        ServiceInterface service = RetrofitClientInstance.getInstance().create(ServiceInterface.class);
        /*Call<List<Project>> listCall = service.getProjectList("2");
        listCall.enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                textView.setText(response.body().size()+"");
                Toast.makeText(CobaCoba.this, "Berhasil", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                textView.setText(t.getMessage());
                Toast.makeText(CobaCoba.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}
