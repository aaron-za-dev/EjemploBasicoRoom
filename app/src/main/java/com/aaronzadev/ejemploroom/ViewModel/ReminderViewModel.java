package com.aaronzadev.ejemploroom.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.aaronzadev.ejemploroom.Entities.Reminder;
import com.aaronzadev.ejemploroom.Repositories.ReminderRepository;

import java.util.List;

public class ReminderViewModel extends AndroidViewModel {

    private ReminderRepository reminderRepository;
    private LiveData<List<Reminder>> reminders;

    public ReminderViewModel(@NonNull Application application) {
        super(application);
        this.reminderRepository = new ReminderRepository(application);
        this.reminders = reminderRepository.getAllReminders();
    }

    public LiveData<List<Reminder>> getReminders() {
        return reminders;
    }

    public void insertReminder(Reminder reminder){
        reminderRepository.insertReminder(reminder);
    }

}
