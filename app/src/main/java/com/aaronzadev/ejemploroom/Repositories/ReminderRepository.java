package com.aaronzadev.ejemploroom.Repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.aaronzadev.ejemploroom.Dao.ReminderDao;
import com.aaronzadev.ejemploroom.Entities.Reminder;
import com.aaronzadev.ejemploroom.RoomDb.RemindersDB;

import java.util.List;

public class ReminderRepository {

    private final ReminderDao reminderDao;
    private final LiveData<List<Reminder>> allReminders;

    public ReminderRepository(Application application) {

        RemindersDB database = RemindersDB.getDatabase(application);
        reminderDao = database.reminderDao();
        allReminders = reminderDao.getAllReminders();

    }

    public LiveData<List<Reminder>> getAllReminders() {
        return allReminders;
    }

    public void insertReminder(Reminder reminder){

        new InsertAsync(reminderDao).execute(reminder);

    }

    private class InsertAsync extends AsyncTask<Reminder, Void, Void>{

        private ReminderDao reminderDao;

        public InsertAsync(ReminderDao reminderDao) {
            this.reminderDao = reminderDao;
        }

        @Override
        protected Void doInBackground(Reminder... reminders) {

            reminderDao.addNewReminder(reminders[0]);

            return null;
        }
    }

}
