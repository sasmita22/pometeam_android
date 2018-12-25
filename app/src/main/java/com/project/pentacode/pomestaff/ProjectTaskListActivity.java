package com.project.pentacode.pomestaff;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.project.pentacode.pomestaff.model.Task;
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

public class ProjectTaskListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_task_list);
        ButterKnife.bind(this);

        int idProject = getIntent().getIntExtra("id_project",0);
        int idStep = getIntent().getIntExtra("id_step",0);
        SharedPreferences s = getSharedPreferences("LoginData",MODE_PRIVATE);

        ServiceInterface service = RetrofitClientInstance.getInstance().create(ServiceInterface.class);
        //Call<List<Task>> listCall = service.getTasks(idProject,idStep,s.getString("token",null));
        Call<List<Task>> listCall = service.getTasks(idProject,idStep,s.getString("token",null));

        listCall.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                if (response.isSuccessful()){
                    setDataToView(response.body());
                } else {
                    response.message();
                }
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                t.getMessage();
            }
        });


    }

    private void setDataToView(List<Task> tasks){
        ArrayList<Task> taskArrayList = new ArrayList<>();
        taskArrayList.addAll(tasks);

        ArrayList<Task> taskList = new ArrayList<>();
        ArrayList<Task> taskReview =  new ArrayList<>();
        ArrayList<Task> taskDone = new ArrayList<>();

        for (Task task : taskArrayList) {
            if (task.getStatus() == 0) {
                taskList.add(task);
            } else if (task.getStatus() == 1) {
                taskDone.add(task);
            } else {
                taskReview.add(task);
            }
        }

        RecyclerView rvTaskList = findViewById(R.id.rv_task_list);
        RecyclerView rvTaskReviewed = findViewById(R.id.rv_task_reviewed);
        RecyclerView rvTaskDone = findViewById(R.id.rv_task_done);
        rvTaskList.setNestedScrollingEnabled(false);
        rvTaskReviewed.setNestedScrollingEnabled(false);
        rvTaskDone.setNestedScrollingEnabled(false);
        rvTaskList.setAdapter(new TaskListAdapter(this,taskList));
        rvTaskReviewed.setAdapter(new TaskReviewedAdapter(this,taskReview));
        rvTaskDone.setAdapter(new TaskDoneAdapter(this,taskDone));

    }

    @OnClick(R.id.task_list_back)
    void onClickBack(View v){
        onBackPressed();
    }
}
