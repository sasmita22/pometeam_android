package com.project.pentacode.pomestaff.model;

import android.os.Parcelable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ModelGenerator {
//    private static final ArrayList<Client> clients = new ArrayList<>();
//    private static final ArrayList<Staff> listStaff = new ArrayList<>();
//    private static final ArrayList<Step> steps = new ArrayList<>();
//    private static final ArrayList<Task> tasks = new ArrayList<>();
//    private static final ArrayList<Parcelable> projects = new ArrayList<>();
   /* private static Step step;
    private static Task task;

    public static ArrayList<Client> getClients(){
        final ArrayList<Client> clients = new ArrayList<>();
        clients.add(new Client(1,"PT. Pertamina","Jl. Soekarno Hatta","082216122637"));
        clients.add(new Client(2,"CV. Screamous","Jl. Ir. Juanda","0833312456678"));
        clients.add(new Client(3,"CV. Aplikasi Shop","Jl. Sekeloa","0836236772229"));
        return clients;
    };

    public static ArrayList<Parcelable> getProjects(){
        final ArrayList<Parcelable> projects = new ArrayList<>();
        ArrayList<Step> steps1 = new ArrayList<>();
        ArrayList<Staff> staff1 = new ArrayList<>();

        step = getSteps().get(1);
        staff1.add(getListStaff().get(8));
        step.team = staff1;
        step.priority = 1;
        steps1.add(step);

        staff1 = new ArrayList<>();
        staff1.add(getListStaff().get(2));
        step = getSteps().get(2);
        step.team = staff1;
        step.priority = 2;
        steps1.add(step);

        staff1 = new ArrayList<>();
        staff1.add(getListStaff().get(4));
        staff1.add(getListStaff().get(0));
        step = getSteps().get(3);
        step.team = staff1;
        step.priority = 3;
        steps1.add(step);

        projects.add(new Project("Project A",getClients().get(1),-1,getListStaff().get(5),null,new Date(),new Date(),new Date(),78.9f,steps1));

        steps1 = new ArrayList<>();

        staff1 = new ArrayList<>();
        staff1.add(getListStaff().get(1));
        staff1.add(getListStaff().get(3));
        step = getSteps().get(2);
        step.team = staff1;
        step.priority = 1;
        steps1.add(step);

        staff1 = new ArrayList<>();
        staff1.add(getListStaff().get(5));
        step = getSteps().get(4);
        step.team = staff1;
        step.priority = 2;
        steps1.add(step);

        staff1 = new ArrayList<>();
        staff1.add(getListStaff().get(2));
        staff1.add(getListStaff().get(7));
        step = getSteps().get(0);
        step.team = staff1;
        step.priority = 3;
        steps1.add(step);

        projects.add(new Project("Project B",getClients().get(2),-1,getListStaff().get(0),null,new Date(),new Date(),new Date(),45.0f,steps1));

        steps1 = new ArrayList<>();

        staff1 = new ArrayList<>();
        staff1.add(getListStaff().get(1));
        staff1.add(getListStaff().get(6));
        step = getSteps().get(1);
        step.team = staff1;
        step.priority = 1;
        steps1.add(step);

        staff1 = new ArrayList<>();
        staff1.add(getListStaff().get(0));
        step = getSteps().get(3);
        step.team = staff1;
        step.priority = 2;
        steps1.add(step);

        projects.add(new Project("Project C",getClients().get(0),-1,getListStaff().get(7),null,new Date(),new Date(),new Date(),95.0f,steps1));

        return projects;
    }

    public static ArrayList<Task> getTasks(){
        final ArrayList<Task> tasks = new ArrayList<>();
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
        final ArrayList<Step> steps = new ArrayList<>();

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

        steps.add(new Step(1,"Marketing",null,tasks1,0));

        task = getTasks().get(1);
        task.priority = 1;
        tasks1.add(getTasks().get(1));

        task = getTasks().get(2);
        task.priority = 2;
        tasks1.add(getTasks().get(2));

        task = getTasks().get(3);
        task.priority = 3;
        tasks1.add(getTasks().get(3));

        steps.add(new Step(2,"Developer",null,tasks1,0));

        task = getTasks().get(3);
        task.priority = 1;
        tasks1.add(getTasks().get(3));

        task = getTasks().get(1);
        task.priority = 2;
        tasks1.add(getTasks().get(1));

        task = getTasks().get(8);
        task.priority = 3;
        tasks1.add(getTasks().get(8));
        steps.add(new Step(3,"Quality Control",null,tasks1,0));

        task = getTasks().get(4);
        task.priority = 1;
        tasks1.add(getTasks().get(4));

        task = getTasks().get(7);
        task.priority = 2;
        tasks1.add(getTasks().get(7));

        task = getTasks().get(6);
        task.priority = 3;
        tasks1.add(getTasks().get(6));
        steps.add(new Step(4,"Analysis",null,tasks1,0));

        task = getTasks().get(1);
        task.priority = 1;
        tasks1.add(getTasks().get(1));

        task = getTasks().get(2);
        task.priority = 2;
        tasks1.add(getTasks().get(2));

        task = getTasks().get(8);
        task.priority = 3;
        tasks1.add(getTasks().get(8));
        steps.add(new Step(5,"Design",null,tasks1,0));

        return steps;
    }

    public static ArrayList<Staff> getListStaff(){
        final ArrayList<Staff> listStaff = new ArrayList<>();

        listStaff.add(new Staff("102388211","Nana Sujatmiko","nanasujatmiko@gmail.com","08231238382","bitbucket.org/nanasj/","Junior Programmer",
                "https://developers.google.com/web/images/contributors/ilyagrigorik.jpg?hl=id"));
        listStaff.add(new Staff("102388212","Aris Setiyanto","arissetiyanto@gmail.com","08231238382","bitbucket.org/aris21/","Senior Programmer",
                "https://i2.wp.com/wp.lancs.ac.uk/envision/files/2017/01/Simon-Tarr-400-x-400-px.jpg?resize=400%2C400"));
        listStaff.add(new Staff("102388213","Yopi Nuno","yopinuno@gmail.com","08231238382","bitbucket.org/yopin/","Junior Programmer",
                "https://132q6j40a81047nmwg1az6v8-wpengine.netdna-ssl.com/wp-content/uploads/2017/02/kyle-400x400.jpg"));
        listStaff.add(new Staff("102388214","Rico Ceper","ricoceper@gmail.com","08231238382","bitbucket.org/ricoceper/","Senior Programmer",
                "http://ameriflux.lbl.gov/wp-content/uploads/2014/07/DarioPapale_sq400px-400x400.jpg"));
        listStaff.add(new Staff("102388215","Semi Adinda","semiadinda@gmail.com","08231238382","bitbucket.org/adinda/","Training",
                "https://pbs.twimg.com/profile_images/813065746934808576/iTLDxVWC_400x400.jpg"));
        listStaff.add(new Staff("102388216","Heru Bara","herubara@gmail.com","08231238382","bitbucket.org/herubara/","Senior Programmer",
                "https://hacktiv8.com/img/avatar/antoni-angga--md5--0c549de33ff28e5220f3e2bd6fe1b299.jpg"));
        listStaff.add(new Staff("102388217","Juna Asamiko","junaasamiko@gmail.com","08231238382","bitbucket.org/asamiko/","Training",
                "https://www.bennettphilp.com.au/wp-content/uploads/2018/07/Brian-Smith-400x400.jpg"));
        listStaff.add(new Staff("102388218","Tuna Asri","tunaasri@gmail.com","08231238382","bitbucket.org/asrituna/","Senior Programmer",
                "https://www.gsscpa.com/wp-content/uploads/2017/11/bo-brault-e1510670977988-400x400.png"));
        listStaff.add(new Staff("102388219","Jono Joni","jonojoni@gmail.com","08231238382","bitbucket.org/jononi/","Senior Programmer",
                "https://secure.gravatar.com/avatar/d84f53bd2eb3037fb605d0473ce8ddcb?s=400&d=mm&r=g"));

        return listStaff;
    }

    public static Staff getStaff(String nip){
        for(Staff s : getListStaff()){
            if (s.nip.equals(nip)){
                return s;
            }
        }
        return null;
    }*/
}
