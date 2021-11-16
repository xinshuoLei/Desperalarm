package com.example.desperalarm.createalarm;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.desperalarm.R;
import com.example.desperalarm.data.Alarm;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * class used for creating an instance of Alarm
 */
public class CreateAlarmFragment extends Fragment {
    /**
     * all components in view
     */
    @BindView(R.id.fragment_createalarm_timePicker) TimePicker timePicker;
    @BindView(R.id.fragment_createalarm_title) EditText title;
    @BindView(R.id.fragment_createalarm_scheduleAlarm) Button scheduleAlarm;
    @BindView(R.id.fragment_createalarm_recurring) CheckBox recurring;
    @BindView(R.id.fragment_createalarm_desperate) CheckBox desperate;
    @BindView(R.id.fragment_createalarm_checkMon) CheckBox mon;
    @BindView(R.id.fragment_createalarm_checkTue) CheckBox tue;
    @BindView(R.id.fragment_createalarm_checkWed) CheckBox wed;
    @BindView(R.id.fragment_createalarm_checkThu) CheckBox thu;
    @BindView(R.id.fragment_createalarm_checkFri) CheckBox fri;
    @BindView(R.id.fragment_createalarm_checkSat) CheckBox sat;
    @BindView(R.id.fragment_createalarm_checkSun) CheckBox sun;
    @BindView(R.id.fragment_createalarm_recurring_options) LinearLayout recurringOptions;

    private CreateAlarmViewModel createAlarmViewModel;

    /**
     * constants for different alarm sound types
     */
    public static final String NO_SOUND = "no sound";
    public static final String SOFT_SOUND = "soft sound";
    public static final String NORMAL_SOUND = "normal sound";
    public static final String IRRITATING_SOUND = "irritating sound";
    public static final String[] SOUND_TYPES = {NO_SOUND, SOFT_SOUND, NORMAL_SOUND, IRRITATING_SOUND};
    /**
     * Spinner for sound selection
     */
    private Spinner sound;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createAlarmViewModel = ViewModelProviders.of(this).get(CreateAlarmViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_createalarm, container, false);

        ButterKnife.bind(this, view);
        // show recurring days option if recurring is checked
        recurring.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    recurringOptions.setVisibility(View.VISIBLE);
                } else {
                    recurringOptions.setVisibility(View.GONE);
                }
            }
        });
        // set options for sound selection
        sound = (Spinner) view.findViewById(R.id.fragment_createalarm_sound);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, SOUND_TYPES);
        sound.setAdapter(adapter);
        // set on event change to play sound selected
        sound.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // when user select an item, play a clip
                String selection = SOUND_TYPES[position];
                previewSound(selection);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }

        });
        // listener for the schedule alarm button
        scheduleAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scheduleAlarm();
                Navigation.findNavController(v).navigate(R.id.action_createAlarmFragment_to_alarmsListFragment);
            }
        });


        return view;
    }

    /**
     * function for scheduling an alarm. create an Alarm instance
     */
    private void scheduleAlarm() {
        int alarmId = new Random().nextInt(Integer.MAX_VALUE);

        Alarm alarm = new Alarm(
                alarmId,
                TimePickerUtil.getTimePickerHour(timePicker),
                TimePickerUtil.getTimePickerMinute(timePicker),
                title.getText().toString(),
                System.currentTimeMillis(),
                true,
                recurring.isChecked(),
                mon.isChecked(),
                tue.isChecked(),
                wed.isChecked(),
                thu.isChecked(),
                fri.isChecked(),
                sat.isChecked(),
                sun.isChecked(),
                desperate.isChecked(),
                SOUND_TYPES[sound.getSelectedItemPosition()]
        );

        createAlarmViewModel.insert(alarm);
        alarm.schedule(getContext());
    }

    /**
     * play a sound clip of the selected type for user to preview alarm sound
     */
    public void previewSound(String selection) {
        if (selection.equals(NO_SOUND)) {
            return;
        }
        int clip = R.raw.normal_clip;
        if (selection.equals(IRRITATING_SOUND)) {
            clip = R.raw.irritating_clip;
        } else if (selection.equals(SOFT_SOUND)) {
            clip = R.raw.soft_clip;
        }
        MediaPlayer player = MediaPlayer.create(getActivity(), clip);
        player.start();
    }
}
