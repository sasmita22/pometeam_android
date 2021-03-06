package com.project.pentacode.pomestaff;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.project.pentacode.pomestaff.model.ApiMessage;
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

public class ProjectTaskListActivity extends AppCompatActivity implements TaskListInterface{
    int idProject;
    int idStep;
    RecyclerView.Adapter adapterList;
    RecyclerView.Adapter adapterReview;
    RecyclerView.Adapter adapterDone;
    RecyclerView rvTaskList;
    RecyclerView rvTaskReviewed;
    RecyclerView rvTaskDone;
    ArrayList<Task> taskList;
    ArrayList<Task> taskReview;
    ArrayList<Task> taskDone;
    SharedPreferences s;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_task_list);
        ButterKnife.bind(this);

        SharedPreferences sharedPreferences = getSharedPreferences("PROJECT",MODE_PRIVATE);
        idProject = sharedPreferences.getInt("project",0);
        idStep = sharedPreferences.getInt("step",0);
        s = getSharedPreferences("LoginData",MODE_PRIVATE);

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

        taskList = new ArrayList<>();
        taskReview =  new ArrayList<>();
        taskDone = new ArrayList<>();

        for (Task task : taskArrayList) {
            if (task.getStatus() == 0) {
                taskList.add(task);
            } else if (task.getStatus() == 1) {
                taskDone.add(task);
            } else {
                taskReview.add(task);
            }
        }

        rvTaskList = findViewById(R.id.rv_task_list);
        rvTaskReviewed = findViewById(R.id.rv_task_reviewed);
        rvTaskDone = findViewById(R.id.rv_task_done);
        rvTaskList.setNestedScrollingEnabled(false);
        rvTaskReviewed.setNestedScrollingEnabled(false);
        rvTaskDone.setNestedScrollingEnabled(false);

        adapterList = new TaskListAdapter(this,taskList,idProject,idStep);
        adapterReview = new TaskReviewedAdapter(this,taskReview,idProject,idStep);
        adapterDone = new TaskDoneAdapter(this,taskDone,idProject,idStep);

        rvTaskList.setAdapter(adapterList);
        rvTaskReviewed.setAdapter(adapterReview);
        rvTaskDone.setAdapter(adapterDone);


    }



    @OnClick(R.id.task_list_back)
    void onClickBack(View v){
        onBackPressed();
    }

    @Override
    public void reviewTask(Task task) {
        final Task temp = task;
        ServiceInterface serviceInterface = RetrofitClientInstance.getInstance().create(ServiceInterface.class);
        Call<ApiMessage> messageCall = serviceInterface.setTaskPreview(task.getId(),s.getString("token",null));
        messageCall.enqueue(new Callback<ApiMessage>() {
            @Override
            public void onResponse(Call<ApiMessage> call, Response<ApiMessage> response) {
                ((TaskReviewedAdapter) adapterReview).addList(temp);
            }

            @Override
            public void onFailure(Call<ApiMessage> call, Throwable t) {
                Toast.makeText(ProjectTaskListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void doneTask(Task task) {
        final Task temp = task;
        ServiceInterface serviceInterface = RetrofitClientInstance.getInstance().create(ServiceInterface.class);
        Call<ApiMessage> messageCall = serviceInterface.setTaskDone(task.getId(),s.getString("token",null));
        messageCall.enqueue(new Callback<ApiMessage>() {
            @Override
            public void onResponse(Call<ApiMessage> call, Response<ApiMessage> response) {
                ((TaskDoneAdapter) adapterDone).addList(temp);
                Toast.makeText(ProjectTaskListActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ApiMessage> call, Throwable t) {
                Toast.makeText(ProjectTaskListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void reviseTask(Task task) {
        final Task temp = task;
        ServiceInterface serviceInterface = RetrofitClientInstance.getInstance().create(ServiceInterface.class);
        Call<ApiMessage> messageCall = serviceInterface.setTaskUndone(task.getId(),s.getString("token",null));
        messageCall.enqueue(new Callback<ApiMessage>() {
            @Override
            public void onResponse(Call<ApiMessage> call, Response<ApiMessage> response) {
                ((TaskListAdapter) adapterList).addList(temp);
            }

            @Override
            public void onFailure(Call<ApiMessage> call, Throwable t) {
                Toast.makeText(ProjectTaskListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
