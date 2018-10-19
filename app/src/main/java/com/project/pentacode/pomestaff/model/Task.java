package com.project.pentacode.pomestaff.model;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
    public int id;
    public String name;
    public String description;
    public Date startAt;
    public Date deadlineAt;
    public Date finishedAt;

    public Task(int id, String name, String description, Date startAt, Date deadlineAt, Date finishedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startAt = startAt;
        this.deadlineAt = deadlineAt;
        this.finishedAt = finishedAt;
    }
}
