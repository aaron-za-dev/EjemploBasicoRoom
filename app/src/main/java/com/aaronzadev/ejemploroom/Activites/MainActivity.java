package com.aaronzadev.ejemploroom.Activites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.aaronzadev.ejemploroom.Entities.Reminder;
import com.aaronzadev.ejemploroom.R;
import com.aaronzadev.ejemploroom.RecyclerviewConf.ReminderAdapter;
import com.aaronzadev.ejemploroom.ViewModel.ReminderViewModel;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ReminderViewModel reminderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomAppBar bottomAppBar = findViewById(R.id.bottomBar);
        setSupportActionBar(bottomAppBar);

        final ReminderAdapter rAdapter = new ReminderAdapter(this);

        final RecyclerView recyclerView = findViewById(R.id.rvReminders);

        recyclerView.setAdapter(rAdapter);

        reminderViewModel = ViewModelProviders.of(this).
                get(ReminderViewModel.class);

        reminderViewModel.getReminders().observe(this, new Observer<List<Reminder>>() {
            @Override
            public void onChanged(List<Reminder> reminders) {
                rAdapter.setReminderList(reminders);
            }
        });

        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showReminderDialog();
            }
        });
    }

    private void showReminderDialog(){

        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.add_reminder_dialog, null);

        final TextInputLayout iptTitle = view.findViewById(R.id.iptTitle);

        final TextInputLayout iptContent = view.findViewById(R.id.iptContent);

        final TextInputEditText txtTitle = view.findViewById(R.id.txtTitle);

        final TextInputEditText txtContent = view.findViewById(R.id.txtContent);

        final Button btnSave = view.findViewById(R.id.btnSave);

        final Button btnCancel = view.findViewById(R.id.btnCancel);

        final MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(MainActivity.this);
        dialogBuilder.setTitle("Agregar Recordatorio");
        dialogBuilder.setView(view);

        final AlertDialog dialog = dialogBuilder.create();
        dialog.show();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!checkText(txtTitle.getText())){

                    iptTitle.setError(null);

                    if(!checkText(txtContent.getText())){


                        Reminder reminder = new Reminder(0,
                                txtTitle.getText().toString(),
                                txtContent.getText().toString()
                        );

                        addReminderToDB(reminder);
                        dialog.dismiss();

                        Toast.makeText(MainActivity.this, "Recordatorio Agregado! :D",
                                Toast.LENGTH_SHORT).show();

                    }
                    else {
                        iptContent.setError("El contenido no puede estar vacío!");
                    }

                }
                else {
                    iptTitle.setError("El titulo no puede estar vacío!");
                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }

    private boolean checkText(CharSequence text){

        return text.toString().isEmpty();

    }

    private void addReminderToDB(Reminder reminder){

        if(reminder != null || reminderViewModel != null){

            reminderViewModel.insertReminder(reminder);

        }
    }

}
