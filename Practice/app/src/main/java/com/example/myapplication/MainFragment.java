package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // Assign variable
        textView = view.findViewById(R.id.text_view);

        // Get title from arguments (if available)
        Bundle args = getArguments();
        if (args != null) {
            String sTitle = args.getString("title");
            // Set title on TextView
            textView.setText(sTitle);
        }

        // Return the view
        return view;
    }
}
