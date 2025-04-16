package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    CalendarView cal;
    Button sub;
    String selectdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Ticket Booking App");
        setContentView(R.layout.activity_main);

        cal = findViewById(R.id.c1);
        sub = findViewById(R.id.button);

        // Store the initially selected date
        // Listen for date selection changes
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "-" + (month + 1) + "-" + year;
                selectdate = date;
                Toast.makeText(MainActivity.this, "Date: "+date, Toast.LENGTH_SHORT).show();
            }
        });

        // Show the selected date on button click
        sub.setOnClickListener(v -> {
            // Convert the selected date to readable format

            // Display the formatted date in a Toast
            Toast.makeText(MainActivity.this, "Date: " + selectdate, Toast.LENGTH_SHORT).show();
        });
    }
}
