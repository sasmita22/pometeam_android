package com.project.pentacode.pomestaff;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StepDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_detail_activity);

        ButterKnife.bind(this);

        RecyclerView recyclerView = findViewById(R.id.step_detail_rv_team);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(new StepTeamHorizontalAdapter());
    }


    @OnClick(R.id.btn_managestep)
    void seeTeamClick(View v) { startActivity(new Intent(this,ProjectTaskListActivity.class)); }



}
