package com.project.pentacode.pomestaff;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StepDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_detail_activity);

        ButterKnife.bind(this);
    }

    @OnClick (R.id.btn_seeteam)
    void manageStepClick(View v){
        startActivity(new Intent(this,StepTeamActivity.class));
    }

    @OnClick(R.id.btn_managestep)
    void seeTeamClick(View v) { startActivity(new Intent(this,ProjectTaskListActivity.class)); }



}
