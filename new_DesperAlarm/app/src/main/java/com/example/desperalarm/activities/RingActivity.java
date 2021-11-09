package com.example.desperalarm.activities;

import static com.example.desperalarm.broadcastreceiver.AlarmBroadcastReceiver.DESPERATE;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.desperalarm.R;
import com.example.desperalarm.service.AlarmService;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Activity class for screen that appears  when user click notification
 */
public class RingActivity extends AppCompatActivity {
    @BindView(R.id.activity_ring_dismiss) Button dismiss;
    @BindView(R.id.activity_ring_clock) ImageView clock;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring);

        ButterKnife.bind(this);

        boolean isDesperate = getIntent().getBooleanExtra(DESPERATE, false);
        if (isDesperate) {
            dismiss.setText("hard dismiss");
        } else {
            dismiss.setText("dismiss");
        }

        LayoutInflater inflater = this.getLayoutInflater();
        // onclick listener for dismiss button
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // simply stop the alarm service
                if (isDesperate) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
                    startActivity(intent);
                } else {
                    Intent intentService = new Intent(getApplicationContext(), AlarmService.class);
                    getApplicationContext().stopService(intentService);
                    finish();
                }

            }
        });
    }
}
