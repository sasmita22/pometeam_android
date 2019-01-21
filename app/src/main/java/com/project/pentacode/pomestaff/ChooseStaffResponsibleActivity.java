package com.project.pentacode.pomestaff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.project.pentacode.pomestaff.model.Staff;
import com.project.pentacode.pomestaff.retrofit.RetrofitClientInstance;
import com.project.pentacode.pomestaff.retrofit.ServiceInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseStaffResponsibleActivity extends AppCompatActivity {
    int idTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_staff_responsibe_task_activity);

        ButterKnife.bind(this);

        final int idProject = getIntent().getIntExtra("id_project",0);
        final int idStep = getIntent().getIntExtra("id_step",0);
        idTask = getIntent().getIntExtra("id_task",0);
        String token = getSharedPreferences("LoginData",MODE_PRIVATE).getString("token",null);

        ServiceInterface service = RetrofitClientInstance.getInstance().create(ServiceInterface.class);
        Call<List<Staff>> listCall = service.getPenanggungJawabTask(idProject,idStep,idTask,token);
        listCall.enqueue(new Callback<List<Staff>>() {
            @Override
            public void onResponse(Call<List<Staff>> call, Response<List<Staff>> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200){
                        setDataToView(response.body());
                        Toast.makeText(ChooseStaffResponsibleActivity.this, "Project : "+idProject+", Step : "+idStep, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ChooseStaffResponsibleActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ChooseStaffResponsibleActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Staff>> call, Throwable t) {

            }
        });


    }

    void setDataToView(List<Staff> staffs){
        ArrayList<Staff> staffArrayList = new ArrayList<>();
        staffArrayList.addAll(staffs);
        RecyclerView recyclerView = findViewById(R.id.recyclerview_choose_staff_taken);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ChooseStaffResponsibleAdapter(this,staffArrayList,idTask));
    }

    @OnClick(R.id.staff_choice_back)
    void onClickBack(View v){
        onBackPressed();
    }
}
