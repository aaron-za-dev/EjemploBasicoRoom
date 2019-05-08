package com.aaronzadev.ejemploroom.RecyclerviewConf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aaronzadev.ejemploroom.Entities.Reminder;
import com.aaronzadev.ejemploroom.R;

import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderVH> {

    private final LayoutInflater inflater;
    private List<Reminder> reminderList;

    public ReminderAdapter(Context context) {

        inflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public ReminderVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.reminder_item, parent, false);

        return new ReminderVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderVH holder, int position) {

        if(reminderList != null){

            holder.txtReminderId.setText("RECORDATORIO N° "+reminderList.get(position).getReminderId());
            holder.txtReminderTitle.setText(reminderList.get(position).getReminderTitle());
            holder.txtReminderContent.setText(reminderList.get(position).getReminderContent());

        }

    }

    @Override
    public int getItemCount() {
        return (reminderList != null) ? reminderList.size():0;
    }

    public void setReminderList(List<Reminder> reminderList) {
        this.reminderList = reminderList;
        notifyDataSetChanged();
    }

    class ReminderVH extends RecyclerView.ViewHolder{

        private TextView txtReminderId, txtReminderTitle, txtReminderContent;

        public ReminderVH(@NonNull View itemView) {
            super(itemView);

            txtReminderId = itemView.findViewById(R.id.txtReminderId);
            txtReminderTitle = itemView.findViewById(R.id.txtReminderTitle);
            txtReminderContent = itemView.findViewById(R.id.txtReminderContent);

        }
    }

}
