package com.example.dairyfarming.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MilkRecordDao {

    @Insert
    void insert(MilkRecord milkRecord);

    @Update
    void update(MilkRecord milkRecord);

    @Delete
    void delete(MilkRecord milkRecord);

    @Query("SELECT * FROM milk_records")
    List<MilkRecord> getAllRecords();

    @Query("SELECT * FROM milk_records WHERE id = :id LIMIT 1")
    MilkRecord getRecordById(int id);
}
