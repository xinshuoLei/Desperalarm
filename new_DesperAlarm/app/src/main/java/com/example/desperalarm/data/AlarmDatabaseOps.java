package com.example.desperalarm.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * interface for database operations
 */
@Dao
public interface AlarmDatabaseOps {
    @Insert
    void insert(Alarm alarm);

    @Query("DELETE FROM alarm_table")
    void deleteAll();

    @Query("DELETE FROM alarm_table WHERE alarmId = :id")
    void deleteById(int id);

    @Query("SELECT * FROM alarm_table ORDER BY created ASC")
    LiveData<List<Alarm>> getAlarms();

    @Query("SELECT * FROM alarm_table WHERE alarmId = :id")
    Alarm getById(int id);

    @Update
    void update(Alarm alarm);
}
