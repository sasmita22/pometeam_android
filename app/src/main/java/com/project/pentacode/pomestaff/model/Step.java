package com.project.pentacode.pomestaff.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Step implements Serializable {
    public int id;
    public String name;
    public ArrayList<Task> tasks;

    public Step(int id, String name, ArrayList<Task> tasks) {
        this.id = id;
        this.name = name;
        this.tasks = tasks;
    }
}
