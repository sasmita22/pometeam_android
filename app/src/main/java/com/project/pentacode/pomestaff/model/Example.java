
package com.project.pentacode.pomestaff.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("project")
    @Expose
    private Project2 project;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Example withMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Project2 getProject() {
        return project;
    }

    public void setProject(Project2 project) {
        this.project = project;
    }

    public Example withProject2(Project2 project2) {
        this.project = project2;
        return this;
    }

    @Override
    public String toString() {
        String s = "";
        s += this.msg+"\n";
        s += this.project.toString();
        return s;
    }
}
