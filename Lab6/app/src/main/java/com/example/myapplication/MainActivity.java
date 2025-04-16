package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner sp;
    EditText vno;
    EditText rcno;
    Button submit;
    String[] vehicletype = {"Car", "Bus", "Bike", "Scooter"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sp = findViewById(R.id.vty);
        vno = findViewById(R.id.VehicleNo);
        rcno = findViewById(R.id.rcno);
        submit = findViewById(R.id.sub);


        sp.setOnItemSelectedListener(this);
        ArrayAdapter<String> ad = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                vehicletype
        );

        // Set simple layout resource file for each item of spinner
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the Spinner which binds data to spinner
        sp.setAdapter(ad);
        submit.setOnClickListener(v->{
            String vehicle = sp.getSelectedItem().toString();
            String vehicleNo = vno.getText().toString();
            String rc = rcno.getText().toString();

            Intent intent = new Intent(this, ConfirmationPage.class);
            intent.putExtra("vehicle",vehicle);
            intent.putExtra("vehicleNo",vehicleNo);
            intent.putExtra("rc",rc);

            startActivity(intent);
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, vehicletype[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}