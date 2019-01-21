package com.project.pentacode.pomestaff.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dashboard {
    @SerializedName("id")
    @Expose
    private String idTask;

    @SerializedName("name")
    @Expose
    private String taskName;

    @SerializedName("deadline_at")
    @Expose
    private String deadline;

    @SerializedName("project")
    @Expose
    private String projectName;

    @SerializedName("step")
    @Expose
    private String stepName;

    public String getIdTask() {
        return idTask;
    }

    public void setIdTask(String idTask) {
        this.idTask = idTask;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }
}
