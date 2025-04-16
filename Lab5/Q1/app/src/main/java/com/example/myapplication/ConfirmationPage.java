package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class ConfirmationPage extends AppCompatActivity {

    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;
    Button edit;
    Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirmation_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        t1 = findViewById(R.id.VehicleNo);
        t2 = findViewById(R.id.rc);
        t3 = findViewById(R.id.type);
        edit = findViewById(R.id.Edit);
        confirm = findViewById(R.id.confirm_button);

        Intent intent = getIntent();
        String s1 = intent.getStringExtra("VehicleNo");
        String s2 = intent.getStringExtra("RC");
        String s3 = intent.getStringExtra("type");

        t1.setText(s1);
        t2.setText(s2);
        t3.setText(s3);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int serialNumber = new Random().nextInt(100000) + 10000;
                Toast.makeText(ConfirmationPage.this, "Parking Allotted! Serial No: " + serialNumber, Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }
}