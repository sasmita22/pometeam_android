package com.project.pentacode.pomestaff.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Task {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("project_structure")
    @Expose
    private Integer projectStructure;
    @SerializedName("deadline_at")
    @Expose
    private Object deadlineAt;
    @SerializedName("finished_at")
    @Expose
    private Object finishedAt;
    @SerializedName("handled_by")
    @Expose
    private String handledBy;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Task withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Task withName(String name) {
        this.name = name;
        return this;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Task withStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Task withType(Integer type) {
        this.type = type;
        return this;
    }

    public Integer getProjectStructure() {
        return projectStructure;
    }

    public void setProjectStructure(Integer projectStructure) {
        this.projectStructure = projectStructure;
    }

    public Task withProjectStructure(Integer projectStructure) {
        this.projectStructure = projectStructure;
        return this;
    }

    public Object getDeadlineAt() {
        return deadlineAt;
    }

    public void setDeadlineAt(Object deadlineAt) {
        this.deadlineAt = deadlineAt;
    }

    public Task withDeadlineAt(Object deadlineAt) {
        this.deadlineAt = deadlineAt;
        return this;
    }

    public Object getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Object finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Task withFinishedAt(Object finishedAt) {
        this.finishedAt = finishedAt;
        return this;
    }

    public String getHandledBy() {
        return handledBy;
    }

    public void setHandledBy(String handledBy) {
        this.handledBy = handledBy;
    }

    public Task withHandledBy(String handledBy) {
        this.handledBy = handledBy;
        return this;
    }

}