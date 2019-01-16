package com.project.pentacode.pomestaff;

import com.project.pentacode.pomestaff.model.Task;

public interface TaskListInterface {

    public void reviewTask(Task task);
    public void doneTask(Task task);
    public void reviseTask(Task task);
}
