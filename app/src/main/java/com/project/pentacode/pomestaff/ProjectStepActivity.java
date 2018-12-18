package com.project.pentacode.pomestaff;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.project.pentacode.pomestaff.model.Step;
import com.project.pentacode.pomestaff.retrofit.RetrofitClientInstance;
import com.project.pentacode.pomestaff.retrofit.ServiceInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectStepActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_step_activity);

        int id_project = getIntent().getIntExtra("id_project",0);

        ServiceInterface service = RetrofitClientInstance.getInstance().create(ServiceInterface.class);
        Call<List<Step>> listCall = service.getSteps(id_project,getSharedPreferences("LoginData",MODE_PRIVATE).getString("token",null));
        listCall.enqueue(new Callback<List<Step>>() {
            @Override
            public void onResponse(Call<List<Step>> call, Response<List<Step>> response) {
                if (response.isSuccessful()){
                    setListSteps(response.body());
                } else {
                    Toast.makeText(ProjectStepActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Step>> call, Throwable t) {
                Toast.makeText(ProjectStepActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void setListSteps(List<Step> listSteps){
        RecyclerView recyclerView = findViewById(R.id.recyclerview_step);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Step> stepArrayList = new ArrayList<>();
        stepArrayList.addAll(listSteps);
        recyclerView.setAdapter(new ProjectStepAdapter(this,stepArrayList));
    }
}
