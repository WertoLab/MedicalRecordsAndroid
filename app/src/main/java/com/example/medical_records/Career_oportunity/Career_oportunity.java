package com.example.medical_records.Career_oportunity;

import android.graphics.drawable.Drawable;

public class Career_oportunity {
    private String name;
    private Drawable icon;
    public Career_oportunity(String name, Drawable icon){
        this.name = name;
        this.icon = icon;
    }
    public String getName(){
        return name;
    }
    public Drawable getIcon(){
        return icon;
    }
}
