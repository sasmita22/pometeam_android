package com.project.pentacode.pomestaff.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ModelGenerator {
    private static final ArrayList<Client> clients = new ArrayList<>();
    private static final ArrayList<Staff> listStaff = new ArrayList<>();
    private static final ArrayList<Step> steps = new ArrayList<>();
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final ArrayList<Project> projects = new ArrayList<>();
    private static Step step;
    private static Task task;

    public static ArrayList<Client> getClients(){
        clients.add(new Client(1,"PT. Pertamina","Jl. Soekarno Hatta","082216122637"));
        clients.add(new Client(2,"CV. Screamous","Jl. Ir. Juanda","0833312456678"));
        clients.add(new Client(3,"CV. Aplikasi Shop","Jl. Sekeloa","0836236772229"));
        return clients;
    };

    public static ArrayList<Project> generateProject(){
        ArrayList<Step> steps1 = new ArrayList<>();
        step = getSteps().get(1);
        step.priority = 1;
        steps1.add(step);

        step = getSteps().get(2);
        step.priority = 2;
        steps1.add(step);

        step = getSteps().get(3);
        step.priority = 3;
        steps1.add(step);

        projects.add(new Project("Project A",getClients().get(1),getListStaff().get(3),new Date(),78.9f,steps1));

        step = getSteps().get(2);
        step.priority = 1;
        steps1.add(step);

        step = getSteps().get(4);
        step.priority = 2;
        steps1.add(step);

        step = getSteps().get(5);
        step.priority = 3;
        steps1.add(step);

        projects.add(new Project("Project B",getClients().get(2),getListStaff().get(1),new Date(),45.0f,steps1));

        step = getSteps().get(1);
        step.priority = 1;
        steps1.add(step);

        step = getSteps().get(3);
        step.priority = 2;
        steps1.add(step);

        step = getSteps().get(5);
        step.priority = 3;
        steps1.add(step);

        projects.add(new Project("Project C",getClients().get(3),getListStaff().get(5),new Date(),95.0f,steps1));

        return projects;
    }

    public static ArrayList<Task> getTasks(){
        tasks.add(new Task(1,"Task A", "Task About A",new Date(),new Date(),new Date(),0));
        tasks.add(new Task(2,"Task B", "Task About B",new Date(),new Date(),new Date(),0));
        tasks.add(new Task(3,"Task C", "Task About C",new Date(),new Date(),new Date(),0));
        tasks.add(new Task(4,"Task D", "Task About D",new Date(),new Date(),new Date(),0));
        tasks.add(new Task(5,"Task E", "Task About E",new Date(),new Date(),new Date(),0));
        tasks.add(new Task(6,"Task F", "Task About F",new Date(),new Date(),new Date(),0));
        tasks.add(new Task(7,"Task G", "Task About G",new Date(),new Date(),new Date(),0));
        tasks.add(new Task(8,"Task H", "Task About H",new Date(),new Date(),new Date(),0));
        tasks.add(new Task(9,"Task I", "Task About I",new Date(),new Date(),new Date(),0));

        return tasks;
    }

    public static ArrayList<Step> getSteps(){
        ArrayList<Task> tasks1 = new ArrayList<>();
        task = getTasks().get(1);
        task.priority = 1;
        tasks1.add(getTasks().get(1));

        task = getTasks().get(5);
        task.priority = 2;
        tasks1.add(getTasks().get(5));

        task = getTasks().get(8);
        task.priority = 3;
        tasks1.add(getTasks().get(8));

        steps.add(new Step(1,"Marketing",tasks1,0));

        task = getTasks().get(1);
        task.priority = 1;
        tasks1.add(getTasks().get(1));

        task = getTasks().get(2);
        task.priority = 2;
        tasks1.add(getTasks().get(2));

        task = getTasks().get(3);
        task.priority = 3;
        tasks1.add(getTasks().get(3));

        steps.add(new Step(2,"Developer",tasks1,0));

        task = getTasks().get(3);
        task.priority = 1;
        tasks1.add(getTasks().get(3));

        task = getTasks().get(1);
        task.priority = 2;
        tasks1.add(getTasks().get(1));

        task = getTasks().get(8);
        task.priority = 3;
        tasks1.add(getTasks().get(8));
        steps.add(new Step(3,"Quality Control",tasks1,0));

        task = getTasks().get(4);
        task.priority = 1;
        tasks1.add(getTasks().get(4));

        task = getTasks().get(7);
        task.priority = 2;
        tasks1.add(getTasks().get(7));

        task = getTasks().get(6);
        task.priority = 3;
        tasks1.add(getTasks().get(6));
        steps.add(new Step(4,"Analisis",tasks1,0));

        task = getTasks().get(1);
        task.priority = 1;
        tasks1.add(getTasks().get(1));

        task = getTasks().get(2);
        task.priority = 2;
        tasks1.add(getTasks().get(2));

        task = getTasks().get(8);
        task.priority = 3;
        tasks1.add(getTasks().get(8));
        steps.add(new Step(5,"Design",tasks1,0));

        return steps;
    }

    public static ArrayList<Staff> getListStaff(){
        listStaff.add(new Staff("102388211","Nana Sujatmiko","Junior Programmer",null));
        listStaff.add(new Staff("102388212","Aris Setiyanto","Senior Programmer", null));
        listStaff.add(new Staff("102388213","Yopi Nuno","Junior Programmer", null));
        listStaff.add(new Staff("102388214","Rico Ceper","Senior Programmer", null));
        listStaff.add(new Staff("102388215","Semi adinda","Training", null));
        listStaff.add(new Staff("102388216","Heru Bara","Senior Programmer", null));
        listStaff.add(new Staff("102388217","Juna Asamiko","Training", null));

        return listStaff;
    }
}
