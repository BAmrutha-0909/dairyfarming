package com.example.dairyfarming.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "milk_records")
public class MilkRecord {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private double liters;
    private long timestamp;
    private String type;

    public MilkRecord(String name, double liters, long timestamp, String type) {
        this.name = name;
        this.liters = liters;
        this.timestamp = timestamp;
        this.type = type;
    }

    // Getters and setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getLiters() { return liters; }
    public void setLiters(double liters) { this.liters = liters; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
