package com.project.pentacode.pomestaff;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_detail_activity);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.task_detail_taken_by)
    void onClicktakenBy(View v){
        startActivity(new Intent(this,StaffChoiceActivity.class));
    }

    @OnClick(R.id.task_detail_back)
    void onClickback(View v){
        onBackPressed();
    }
}
