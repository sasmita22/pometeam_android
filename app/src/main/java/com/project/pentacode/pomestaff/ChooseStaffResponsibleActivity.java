package com.project.pentacode.pomestaff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseStaffResponsibleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_staff_responsibe_task_activity);

        ButterKnife.bind(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_choose_staff_taken);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ChooseStaffResponsibleAdapter(this));
    }

    @OnClick(R.id.staff_choice_back)
    void onClickBack(View v){
        onBackPressed();
    }
}
