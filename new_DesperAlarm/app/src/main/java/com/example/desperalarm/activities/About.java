package com.example.desperalarm.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.desperalarm.R;

/**
* The class for help screen.
*/
public class About extends AppCompatActivity {

    private String DEVELOPER_EMAIL = "realbdotjdot@gmail.com";
    private final String SUGGESTION_SUBJECT = "DesperAlarm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        Button sentBtn = findViewById(R.id.send);
        sentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
        Button regularAlarmGuide = findViewById(R.id.regularAlarmGuide);
        regularAlarmGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegularAlarmGuide.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Compose an Email to recipent
     * @param recipient recipient of email
     * @param subject subject of email
     */
    public void composeEmail(String recipient, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        startActivity(intent);
    }

    /**
     * Send Email
     */
    public void sendEmail() {
        composeEmail(DEVELOPER_EMAIL, SUGGESTION_SUBJECT);
    }
}
