package com.example.desperalarm.alarmslist;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desperalarm.data.Alarm;
import com.example.desperalarm.R;
import com.example.desperalarm.data.AlarmDatabase;
import com.example.desperalarm.data.AlarmDatabaseOps;
import com.example.desperalarm.data.AlarmRepository;

/**
 * View holder for the recycler view
 */
public class AlarmViewHolder extends RecyclerView.ViewHolder {
    private TextView alarmTime;
    private ImageView alarmRecurring;
    private TextView alarmRecurringDays;
    private TextView alarmTitle;
    private TextView alarmMode;
    private Button alarmDelete;

    Switch alarmStarted;

    private OnToggleAlarmListener listener;

    public AlarmViewHolder(@NonNull View itemView, OnToggleAlarmListener listener) {
        super(itemView);

        alarmTime = itemView.findViewById(R.id.item_alarm_time);
        alarmStarted = itemView.findViewById(R.id.item_alarm_started);
        alarmRecurring = itemView.findViewById(R.id.item_alarm_recurring);
        alarmRecurringDays = itemView.findViewById(R.id.item_alarm_recurringDays);
        alarmTitle = itemView.findViewById(R.id.item_alarm_title);
        alarmMode = itemView.findViewById(R.id.item_alarm_mode);
        alarmDelete = itemView.findViewById(R.id.delete_alarm);

        this.listener = listener;
    }

    /**
     * set information about single alarm
     * @param alarm
     */
    public void bind(Alarm alarm) {
        String alarmText = String.format("%02d:%02d", alarm.getHour(), alarm.getMinute());

        alarmTime.setText(alarmText);
        alarmStarted.setChecked(alarm.isStarted());

        if (alarm.isRecurring()) {
            alarmRecurring.setImageResource(R.drawable.ic_repeat_black_24dp);
            alarmRecurringDays.setText(alarm.getRecurringDaysText());
        } else {
            alarmRecurring.setImageResource(R.drawable.ic_looks_one_black_24dp);
            alarmRecurringDays.setText("Once Off");
        }

        if (alarm.getTitle().length() != 0) {
            alarmTitle.setText(String.format("%s", alarm.getTitle()));
        } else {
            alarmTitle.setText(String.format("%s", "Alarm"));
        }

        if (alarm.isDesperate()) {
            alarmMode.setText("Desperate");
            alarmMode.setTextColor(Color.RED);
        } else {
            alarmMode.setText("Regular");
        }

        alarmStarted.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listener.onToggle(alarm);
            }
        });

        alarmDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // cancel the alarm first
                alarmStarted.setChecked(false);
                // delete from database
                AlarmRepository.deleteById(alarm.getAlarmId());
            }
        });
    }
}
