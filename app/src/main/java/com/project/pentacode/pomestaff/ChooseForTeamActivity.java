package com.project.pentacode.pomestaff;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.project.pentacode.pomestaff.model.Staff;
import com.project.pentacode.pomestaff.retrofit.RetrofitClientInstance;
import com.project.pentacode.pomestaff.retrofit.ServiceInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseForTeamActivity extends AppCompatActivity {
    @BindView(R.id.recyclerview_choose_for_team)
    RecyclerView rvChooseStaff;
    int idProject;
    int idStep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_for_team_activity);
        ButterKnife.bind(this);

        SharedPreferences sharedPreferences = getSharedPreferences("PROJECT",MODE_PRIVATE);

        idProject = sharedPreferences.getInt("project", 0);
        idStep = sharedPreferences.getInt("step", 0);

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
                        Toast.makeText(ChooseForTeamActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ChooseForTeamActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Staff>> call, Throwable t) {
                Toast.makeText(ChooseForTeamActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    void setDataToView(List<Staff> staffs){
        ArrayList<Staff> staffArrayList = new ArrayList<>();
        staffArrayList.addAll(staffs);
        rvChooseStaff.setAdapter(new ChooseForTeamAdapter(this,staffArrayList,idProject,idStep));
    }

    @OnClick(R.id.choose_for_team_back)
    void onClickBack(View v){
        onBackPressed();
    }
}
