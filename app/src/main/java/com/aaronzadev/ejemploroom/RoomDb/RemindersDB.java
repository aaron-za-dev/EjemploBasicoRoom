package com.aaronzadev.ejemploroom.RoomDb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.aaronzadev.ejemploroom.Dao.ReminderDao;
import com.aaronzadev.ejemploroom.Entities.Reminder;

@Database(entities = {Reminder.class}, version = 1)
public abstract class RemindersDB extends RoomDatabase {

    private static final String DATABASE_NAME = "RemindersDb";

    private static volatile RemindersDB instance;

    public abstract ReminderDao reminderDao();

    public static synchronized RemindersDB getDatabase(final Context context){

        if(instance == null){

            instance = Room.databaseBuilder(context.getApplicationContext(),
                    RemindersDB.class, DATABASE_NAME)
                    .build();

        }

        return instance;

    }

}
