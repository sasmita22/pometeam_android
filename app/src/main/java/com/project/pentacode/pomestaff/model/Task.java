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
    public int priority;

    public Task(int id, String name, String description, Date startAt, Date deadlineAt, Date finishedAt, int priority) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startAt = startAt;
        this.deadlineAt = deadlineAt;
        this.finishedAt = finishedAt;
        this.priority = priority;
    }
}
