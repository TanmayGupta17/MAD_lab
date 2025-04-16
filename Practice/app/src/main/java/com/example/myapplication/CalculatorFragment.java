package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CalculatorFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        EditText num1 = view.findViewById(R.id.num1);
        EditText num2 = view.findViewById(R.id.num2);
        Button addButton = view.findViewById(R.id.add_button);
        TextView resultText = view.findViewById(R.id.result);

        addButton.setOnClickListener(v -> {
            String str1 = num1.getText().toString();
            String str2 = num2.getText().toString();
            int sum;
            if (!str1.isEmpty() && !str2.isEmpty()) {
                sum = Integer.parseInt(str1) + Integer.parseInt(str2);
//                Intent intent = new Intent(getActivity(),CalaculatorResult.class);
//                String s = String.valueOf(sum);
//                intent.putExtra(s,sum);
                resultText.setText("Result: " + sum);
            } else {
                resultText.setText("Enter numbers!");
            }
        });

        return view;
    }
}
