package com.project.pentacode.pomestaff.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Step implements Serializable {
    public int id;
    public String name;
    public ArrayList<Task> tasks;
    public int priority;

    public Step(int id, String name, ArrayList<Task> tasks, int priority) {
        this.id = id;
        this.name = name;
        this.tasks = tasks;
        this.priority = priority;
    }
}
