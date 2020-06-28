package com.khalilpan.autocallrecorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    ToggleButton status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        status = (ToggleButton) findViewById(R.id.toggleButton_status);

    }

    public void toggleButton(View view) {
        boolean checked = ((ToggleButton) view).isChecked();

        if (checked) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},PackageManager.PERMISSION_GRANTED);
            } else {

                Intent intent = new Intent(this, RecordingService.class);
                startService(intent);
                Toast.makeText(getApplicationContext(), "Call Recording Started", Toast.LENGTH_SHORT).show();
            }

        } else {
            Intent intent = new Intent(this, RecordingService.class);
            stopService(intent);
            Toast.makeText(getApplicationContext(), "Call Recording Stopped", Toast.LENGTH_SHORT).show();
        }
    }
}