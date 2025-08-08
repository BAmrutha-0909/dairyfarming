package com.example.dairyfarming;

public class BuffaloModel {
    private String id;
    private String name;
    private String milkProduction;

    public BuffaloModel(String id, String name, String milkProduction) {
        this.id = id;
        this.name = name;
        this.milkProduction = milkProduction;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMilkProduction() {
        return milkProduction;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMilkProduction(String milkProduction) {
        this.milkProduction = milkProduction;
    }
}
