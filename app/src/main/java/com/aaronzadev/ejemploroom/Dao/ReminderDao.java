package com.aaronzadev.ejemploroom.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.aaronzadev.ejemploroom.Entities.Reminder;

import java.util.List;

@Dao
public interface ReminderDao {

    @Insert
    void addNewReminder(Reminder reminder);

    /*@Delete TODO Implementar estos metodos :D
    void deleteReminder(Reminder reminder);

    @Update
    void updateReminder(Reminder reminder);*/

    @Query("Select * from Reminders order by ReminderID desc")
    LiveData<List<Reminder>> getAllReminders();
}
