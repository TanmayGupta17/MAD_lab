package com.example.taskmanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText name,due_date;
    RadioButton high,low,medium;
    Button sub;
    ListView listView;
    ActionMode actionMode;
    DBHelper db;
    String p;
    int selectedposition = -1;
    ArrayList<Integer> taskIds = new ArrayList<>();
    GridView gridView;
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

        name = findViewById(R.id.taskname);
        due_date = findViewById(R.id.duedate);
        sub = findViewById(R.id.button);
//        listView = findViewById(R.id.tasklist);
        high = findViewById(R.id.high);
        medium = findViewById(R.id.medium);
        low = findViewById(R.id.low);
        gridView = findViewById(R.id.taskGrid);
        db = new DBHelper(this);

        loadTasksIntoListView();
        gridView.setOnItemLongClickListener(((parent, view, position, id) -> {
            if (actionMode != null) {
                return false;
            }

            // Start the CAB using the ActionMode.Callback defined earlier.
            selectedposition = position;
            actionMode = startActionMode(actionModeCallback);
            view.setSelected(true);
            return true;
        }));

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametxt = name.getText().toString();
                String due = due_date.getText().toString();
                String priority = new String();
                if(high.isChecked()){
                    priority = "high";
                }
                else if(medium.isChecked()){
                    priority = "medium";
                }
                else if(low.isChecked()){
                    priority = "low";
                }
                boolean checkInsert = db.InsertTask(nametxt,due,priority);
                if(checkInsert == true){
                    Toast.makeText(MainActivity.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
                    loadTasksIntoListView();
                }
                else {
                    Toast.makeText(MainActivity.this, "Failed to INsert data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadTasksIntoListView() {
        Cursor cursor = db.getAllTasks();
        ArrayList<String> tasks = new ArrayList<>();
        taskIds.clear();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String taskName = cursor.getString(1); // Adjust indexes if your table has more columns
                String dueDate = cursor.getString(2);
                String priority = cursor.getString(3);
                tasks.add("Task: " + taskName + "\nDue: " + dueDate + "\nPriority: " + priority);
                taskIds.add(id);
            } while (cursor.moveToNext());
        }

        GridView gridView = findViewById(R.id.taskGrid);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        gridView.setAdapter(adapter);
    }


    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {

        // Called when the action mode is created. startActionMode() is called.
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items.
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.context_menu, menu);
            return true;
        }

        // Called each time the action mode is shown. Always called after
        // onCreateActionMode, and might be called multiple times if the mode
        // is invalidated.
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done.
        }

        // Called when the user selects a contextual menu item.
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int id = item.getItemId();
            if(id == R.id.edit){
                Toast.makeText(MainActivity.this, "Edit button clicked", Toast.LENGTH_SHORT).show();
                mode.finish();
                return true;
            } else if (id == R.id.delete) {
                int taskId = taskIds.get(selectedposition);
                db.deleteTask(taskId);
                Toast.makeText(MainActivity.this, "Task Deleted", Toast.LENGTH_SHORT).show();
                mode.finish();
                return true;
            }
            return false;
        }



        // Called when the user exits the action mode.
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
        }
    };

}