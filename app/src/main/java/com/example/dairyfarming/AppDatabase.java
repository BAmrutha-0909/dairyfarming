package com.example.dairyfarming.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MilkRecord.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MilkRecordDao milkRecordDao();
}
