package com.example.dairyfarming;

public class BuffaloModel {
    private int id;
    private String name;
    private String milkProduction;
    private String type;

    public BuffaloModel(int id, String name, String milkProduction, String type) {
        this.id = id;
        this.name = name;
        this.milkProduction = milkProduction;
        this.type = type;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getMilkProduction() { return milkProduction; }
    public String getType() { return type; }
}
