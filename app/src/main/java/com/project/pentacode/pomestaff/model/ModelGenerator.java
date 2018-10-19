package com.project.pentacode.pomestaff.model;

import java.util.ArrayList;

public class ModelGenerator {
    private static ArrayList<Client> clients;
    private static ArrayList<Staff> staff;
    private static ArrayList<Step> steps;
    private static ArrayList<Task> tasks;
    private static ArrayList<Project> projects;

    public static ArrayList<Client> generateClients(){
        clients.add(new Client(1,"PT. Pertamina","Jl. Soekarno Hatta","082216122637"));
        clients.add(new Client(1,"CV. Screamous","Jl. Ir. Juanda","0833312456678"));
        return clients;
    };
}
