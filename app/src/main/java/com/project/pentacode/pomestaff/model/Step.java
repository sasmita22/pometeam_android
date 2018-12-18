package com.project.pentacode.pomestaff.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Step {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("deadline_at")
    @Expose
    private String deadlineAt;
    @SerializedName("ended_at")
    @Expose
    private String endedAt;
    @SerializedName("team")
    @Expose
    private List<Staff> team = null;
    @SerializedName("task")
    @Expose
    private List<Task> task = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeadlineAt() {
        return deadlineAt;
    }

    public void setDeadlineAt(String deadlineAt) {
        this.deadlineAt = deadlineAt;
    }

    public String getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(String endedAt) {
        this.endedAt = endedAt;
    }

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }

    public List<Staff> getTeam() {
        return team;
    }

    public void setTeam(List<Staff> team) {
        this.team = team;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}