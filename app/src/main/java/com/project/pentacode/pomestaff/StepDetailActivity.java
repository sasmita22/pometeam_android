package com.project.pentacode.pomestaff;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.pentacode.pomestaff.model.Step;
import com.project.pentacode.pomestaff.retrofit.RetrofitClientInstance;
import com.project.pentacode.pomestaff.retrofit.ServiceInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StepDetailActivity extends AppCompatActivity {
    @BindView(R.id.step_detail_title)
    TextView textTitle;
    @BindView(R.id.step_detail_deskripsi)
    TextView textDeskripsi;
    @BindView(R.id.step_detail_range_date)
    TextView textRangeDate;
    @BindView(R.id.task_detail_staff_image)
    CircleImageView imageLeader;
    @BindView(R.id.task_detail_staff_name)
    TextView textLeaderName;
    @BindView(R.id.task_detail_staff_jabatan)
    TextView textLeaderJabatan;
    int idProject;
    int idStep;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_detail_activity);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        idProject = intent.getIntExtra("id_project", 0);
        idStep = intent.getIntExtra("id_step", 0);


        SharedPreferences sharedPreferences = getSharedPreferences("LoginData",MODE_PRIVATE);

        ServiceInterface service = RetrofitClientInstance.getInstance().create(ServiceInterface.class);
        Call<Step> stepCall = service.getStep(idProject,idStep,sharedPreferences.getString("token",null));
        stepCall.enqueue(new Callback<Step>() {
            @Override
            public void onResponse(Call<Step> call, Response<Step> response) {
                setDataToView(response.body());
            }

            @Override
            public void onFailure(Call<Step> call, Throwable t) {
                Toast.makeText(StepDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void setDataToView(Step step){
        textTitle.setText(step.getName());
        textDeskripsi.setText(step.getDeskripsi());
        textRangeDate.setText(step.getDeadlineAt());

        if (step.getLeader() != null){
            textLeaderName.setText(step.getLeader().getName());
            textLeaderJabatan.setText(step.getLeader().getJabatan());
            Glide.with(this)
                    .load(RetrofitClientInstance.BASE_URL_IMAGE_PROFILE+step.getLeader().getImage())
                    .into(imageLeader);
        }


        RecyclerView recyclerView = findViewById(R.id.step_detail_rv_team);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(new StepTeamHorizontalAdapter(this,step));
    }

    @OnClick(R.id.step_detail_back)
    void backPressed(View v){
        onBackPressed();
    }

    @OnClick(R.id.btn_managestep)
    void seeTeamClick(View v) {
        Intent intent = new Intent(this,ProjectTaskListActivity.class);
        intent.putExtra("id_project",idProject);
        intent.putExtra("id_step",idStep);
        startActivity(intent);
    }

    @OnClick(R.id.step_detail_manage_team)
    void onClickManageTeam(View v){
        startActivity(new Intent(this,ShowTeamListActivity.class));
    }

    @OnClick(R.id.step_detail_change_leader)
    void onClickChangeLeader(View v){
        startActivity(new Intent(this,ChooseLeaderActivity.class));
    }


}
