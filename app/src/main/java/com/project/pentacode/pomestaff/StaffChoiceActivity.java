package com.project.pentacode.pomestaff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StaffChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_choice_activity);

        ButterKnife.bind(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_choice);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new StaffChoiceAdapter());
    }

    @OnClick(R.id.staff_choice_back)
    void onClickBack(View v){
        onBackPressed();
    }
}
