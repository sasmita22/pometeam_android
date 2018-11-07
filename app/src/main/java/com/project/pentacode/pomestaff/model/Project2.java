
package com.project.pentacode.pomestaff.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Project2 {

    @SerializedName("id_project")
    @Expose
    private Integer idProject;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("project_manager")
    @Expose
    private String projectManager;
    @SerializedName("start_at")
    @Expose
    private String startAt;
    @SerializedName("ended_at")
    @Expose
    private String endedAt;
    @SerializedName("deadline_at")
    @Expose
    private String deadlineAt;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("view_project")
    @Expose
    private ViewProject viewProject;

    public Integer getIdProject() {
        return idProject;
    }

    public void setIdProject(Integer idProject) {
        this.idProject = idProject;
    }

    public Project2 withIdProject(Integer idProject) {
        this.idProject = idProject;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project2 withName(String name) {
        this.name = name;
        return this;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public Project2 withProjectManager(String projectManager) {
        this.projectManager = projectManager;
        return this;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public Project2 withStartAt(String startAt) {
        this.startAt = startAt;
        return this;
    }

    public String getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(String endedAt) {
        this.endedAt = endedAt;
    }

    public Project2 withEndedAt(String endedAt) {
        this.endedAt = endedAt;
        return this;
    }

    public String getDeadlineAt() {
        return deadlineAt;
    }

    public void setDeadlineAt(String deadlineAt) {
        this.deadlineAt = deadlineAt;
    }

    public Project2 withDeadlineAt(String deadlineAt) {
        this.deadlineAt = deadlineAt;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Project2 withStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Project2 withPrice(Integer price) {
        this.price = price;
        return this;
    }

    public ViewProject getViewProject() {
        return viewProject;
    }

    public void setViewProject(ViewProject viewProject) {
        this.viewProject = viewProject;
    }

    public Project2 withViewProject(ViewProject viewProject) {
        this.viewProject = viewProject;
        return this;
    }
}
