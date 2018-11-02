package com.project.pentacode.pomestaff;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProjectDetailActivity extends AppCompatActivity {
    @BindView(R.id.project_back_button)
    ImageButton backButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_detail_activity);

        ButterKnife.bind(this);

        backButton.bringToFront();
    }

    @OnClick(R.id.btn_gotoworkspace)
    void workspaceClick(View v){ startActivity(new Intent(this,ProjectTaskListActivity.class));
    }

    @OnClick(R.id.btn_manageproject)
    void manageProjectClick(View v){
        startActivity(new Intent(this,ProjectStepActivity.class));
    }

    @OnClick(R.id.project_back_button)
    void backPressed(View v){
        onBackPressed();
    }
}
