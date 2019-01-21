package com.project.pentacode.pomestaff.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notifikasi {
    @SerializedName("id")
    @Expose
    private String idTask;

    @SerializedName("project")
    @Expose
    private String project;

    @SerializedName("step")
    @Expose
    private String step;

    @SerializedName("task")
    @Expose
    private String task;

    @SerializedName("deadline_at")
    @Expose
    private String deadline;

    public String getIdTask() {
        return idTask;
    }

    public void setIdTask(String idTask) {
        this.idTask = idTask;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
