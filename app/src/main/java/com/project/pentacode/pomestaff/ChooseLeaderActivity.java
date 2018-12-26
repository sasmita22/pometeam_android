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

public class ChooseLeaderActivity extends AppCompatActivity {
    int idProject, idStep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_leader_activity);

        ButterKnife.bind(this);

        idProject = getIntent().getIntExtra("id_project",0);
        idStep = getIntent().getIntExtra("id_step",0);

        ServiceInterface service = RetrofitClientInstance.getInstance().create(ServiceInterface.class);
        Call<List<Staff>> listCall = service.getStaffForLeaderOrTeam(
                idProject,getSharedPreferences("LoginData",MODE_PRIVATE).getString("token",null));

        listCall.enqueue(new Callback<List<Staff>>() {
            @Override
            public void onResponse(Call<List<Staff>> call, Response<List<Staff>> response) {
                if (response.isSuccessful()){
                    if(response.code() == 200){
                        setDataToView(response.body());
                    }else{
                        Toast.makeText(ChooseLeaderActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ChooseLeaderActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Staff>> call, Throwable t) {
                Toast.makeText(ChooseLeaderActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void setDataToView(List<Staff> staffs){
        ArrayList<Staff> staffList = new ArrayList<>();
        staffList.addAll(staffs);
        RecyclerView recyclerView = findViewById(R.id.recyclerview_choose_leader);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ChooseLeaderAdapter(this,staffList,idProject,idStep));
    }

    @OnClick(R.id.choose_leader_back)
    void onClickBack(View v){
        onBackPressed();
    }
}
