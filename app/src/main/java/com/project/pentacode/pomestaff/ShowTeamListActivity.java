package com.project.pentacode.pomestaff;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.project.pentacode.pomestaff.model.Staff;
import com.project.pentacode.pomestaff.model.Step;
import com.project.pentacode.pomestaff.retrofit.RetrofitClientInstance;
import com.project.pentacode.pomestaff.retrofit.ServiceInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowTeamListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    int idProject;
    int idStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_team_activity);

        ButterKnife.bind(this);

        SharedPreferences sharedPreferences = getSharedPreferences("PROJECT",MODE_PRIVATE);
        idProject = sharedPreferences.getInt("project", 0);
        idStep = sharedPreferences.getInt("step", 0);

        //ArrayList<Staff> staffs = getIntent().getParcelableArrayListExtra("TEAM");
        recyclerView = findViewById(R.id.recyclerview_show_team);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //setDataRV(staffs);
    }

    void setDataRV(ArrayList<Staff> staffs){

        recyclerView.setAdapter(new ShowTeamListAdapter(this,staffs,idProject,idStep));
    }

    @OnClick(R.id.show_team_back)
    void onClickBack(View v){
        onBackPressed();
    }

    @OnClick(R.id.show_team_add)
    void onClickAdd(View v){
        Intent intent = new Intent(this,ChooseForTeamActivity.class);
        startActivity(intent);
    }

    private void getListTeam(){
        SharedPreferences sharedPreferences = getSharedPreferences("LoginData",MODE_PRIVATE);

        ServiceInterface service = RetrofitClientInstance.getInstance().create(ServiceInterface.class);
        Call<List<Staff>> call = service.getTeam(idProject,idStep,sharedPreferences.getString("token",null));
        call.enqueue(new Callback<List<Staff>>() {
            @Override
            public void onResponse(Call<List<Staff>> call, Response<List<Staff>> response) {
                if (response.code()==200){
                    ArrayList arrayList = new ArrayList();
                    Toast.makeText(ShowTeamListActivity.this, response.body().size()+"", Toast.LENGTH_SHORT).show();
                    arrayList.addAll(response.body());
                    setDataRV(arrayList);
                }else{
                    Toast.makeText(ShowTeamListActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Staff>> call, Throwable t) {
                Toast.makeText(ShowTeamListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        getListTeam();
        super.onResume();
    }
}
