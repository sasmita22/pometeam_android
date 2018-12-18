package com.project.pentacode.pomestaff.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Project{

    @SerializedName("id_project")
    @Expose
    private Integer idProject;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("client")
    @Expose
    private String client;
    @SerializedName("pm")
    @Expose
    private Staff projectManagerObject;
    @SerializedName("project_manager")
    @Expose
    private String projectManager;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("start_at")
    @Expose
    private String startAt;
    @SerializedName("deadline_at")
    @Expose
    private String deadlineAt;
    @SerializedName("ended_at")
    @Expose
    private String endedAt;
    @SerializedName("step")
    @Expose
    private List<Step> step = null;


    public Integer getIdProject() {
        return idProject;
    }

    public void setIdProject(Integer idProject) {
        this.idProject = idProject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<Step> getStep() {
        return step;
    }

    public void setStep(List<Step> step) {
        this.step = step;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
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

    public Staff getProjectManagerObject() {
        return projectManagerObject;
    }

    public void setProjectManagerObject(Staff projectManagerObject) {
        this.projectManagerObject = projectManagerObject;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}