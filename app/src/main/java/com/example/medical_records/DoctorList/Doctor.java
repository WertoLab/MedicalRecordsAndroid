package com.example.medical_records.DoctorList;

import java.util.ArrayList;

public class Doctor {
    private String name;
    private String company;
    private ArrayList<String> specialty;
    private SpecialtyAdapter adapter;
    public Doctor(String name, String company,ArrayList<String> specialty){
        this.name = name;
        this.company = company;
        this.specialty = specialty;
        adapter = new SpecialtyAdapter(specialty);
    }

    public String getName(){return name;}

    public String getCompany() {
        return company;
    }

    public ArrayList<String> getSpecialty(){return specialty;}

    public SpecialtyAdapter getAdapter(){
        return adapter;
    }
}
