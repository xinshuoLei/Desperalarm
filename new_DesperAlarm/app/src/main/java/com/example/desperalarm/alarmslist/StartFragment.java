package com.example.desperalarm.alarmslist;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.desperalarm.R;
import com.example.desperalarm.activities.DesperAlarm;
import com.example.desperalarm.activities.About;
import com.example.desperalarm.activities.Timer;

public class StartFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        Button regularAlarm = view.findViewById(R.id.regularAlarm);
        regularAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_mainActivityFragment_to_alarmsListFragment);
            }
        });
        // navigation to help
        Button helpButton = view.findViewById(R.id.about);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent helpIntent = new Intent(getActivity(), About.class);
                startActivity(helpIntent);
            }
        });
        // navigation to timer
        Button timerButton = view.findViewById(R.id.timer);
        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent timerIntent = new Intent(getActivity(), Timer.class);
                startActivity(timerIntent);
            }
        });
        // navigation to desperalarm
        Button desperButton = view.findViewById(R.id.desperAlarm);
        desperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent desperIntent = new Intent(getActivity(), DesperAlarm.class);
                startActivity(desperIntent);
            }
        });
        return view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}
