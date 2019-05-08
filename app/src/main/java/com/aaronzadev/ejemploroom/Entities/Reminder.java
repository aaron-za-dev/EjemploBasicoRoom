package com.aaronzadev.ejemploroom.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Reminders")
public class Reminder {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "ReminderID")
    private final int reminderId;

    @NonNull
    @ColumnInfo(name = "ReminderTitle")
    private final String reminderTitle;

    @NonNull
    @ColumnInfo(name = "ReminderContent")
    private final String reminderContent;

    public Reminder(int reminderId, @NonNull String reminderTitle, @NonNull String reminderContent) {
        this.reminderId = reminderId;
        this.reminderTitle = reminderTitle;
        this.reminderContent = reminderContent;
    }

    public int getReminderId() {
        return reminderId;
    }

    @NonNull
    public String getReminderTitle() {
        return reminderTitle;
    }

    @NonNull
    public String getReminderContent() {
        return reminderContent;
    }
}