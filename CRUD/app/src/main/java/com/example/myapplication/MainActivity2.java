package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView studentListView = null;
    private ArrayList<Student> students;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        dbHelper = new DBHelper(this);

        students = dbHelper.RetrieveUsers();

        studentListView = findViewById(R.id.students_listview);
        StudentAdapter adapter = new StudentAdapter(this, R.layout.student_item_layout, students);
        studentListView.setAdapter(adapter);
        studentListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView studentIdTextView = view.findViewById(R.id.student_id);
        TextView studentUsernameTextView = view.findViewById(R.id.student_name);
        TextView studentPasswordTextView = view.findViewById(R.id.student_password);

        Intent intent = new Intent(MainActivity2.this, MainActivity.class);

        intent.putExtra("studentIdTextView", studentIdTextView.getText().toString());
        intent.putExtra("studentUsernameTextView", studentUsernameTextView.getText().toString());
        intent.putExtra("studentPasswordTextView", studentPasswordTextView.getText().toString());


        startActivity(intent);
    }
}