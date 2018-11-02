package com.project.pentacode.pomestaff;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

public class ProjectTaskListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_task_list);

        RecyclerView rvTaskList = findViewById(R.id.rv_task_list);
        RecyclerView rvTaskReviewed = findViewById(R.id.rv_task_reviewed);
        RecyclerView rvTaskDone = findViewById(R.id.rv_task_done);
        rvTaskList.setNestedScrollingEnabled(false);
        rvTaskReviewed.setNestedScrollingEnabled(false);
        rvTaskDone.setNestedScrollingEnabled(false);
        rvTaskList.setAdapter(new TaskListAdapter());
        rvTaskReviewed.setAdapter(new TaskReviewedAdapter());
        rvTaskDone.setAdapter(new TaskDoneAdapter());
    }
}
