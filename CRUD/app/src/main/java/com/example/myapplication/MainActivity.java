package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DBHelper dbHelper;
    Button submit, cancel, next, update;
    TextView labelcreate, labelupdate , err_username, err_password;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);

        submit = findViewById(R.id.btn_submit);
        cancel = findViewById(R.id.btn_cancel);
        update = findViewById(R.id.btn_update);
        next = findViewById(R.id.btn_next);

        labelcreate = findViewById(R.id.tv_create);
        labelupdate = findViewById(R.id.tv_update);
        err_username = findViewById(R.id.tv_username);
        err_password = findViewById(R.id.tv_password);

        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);

        next.setOnClickListener(this);
        update.setOnClickListener(this);
        cancel.setOnClickListener(this);
        submit.setOnClickListener(this);


        if (getIntent().hasExtra("studentUsernameTextView") && getIntent().hasExtra("studentPasswordTextView")) {
            String Username = getIntent().getStringExtra("studentUsernameTextView");
            String Password = getIntent().getStringExtra("studentPasswordTextView");

            username.setText(Username);
            password.setText(Password);

            labelcreate.setVisibility(View.INVISIBLE);
            labelupdate.setVisibility(View.VISIBLE);
            update.setVisibility(View.VISIBLE);
            cancel.setVisibility(View.VISIBLE);
            submit.setVisibility(View.INVISIBLE);
            next.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btn_next) {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);

        }
        if (id == R.id.btn_cancel) {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        }
        if (id == R.id.btn_update) {
            String studID = getIntent().getStringExtra("studentIdTextView");
            String newusername = username.getText().toString();
            String newpassword = password.getText().toString();

            dbHelper.updateUser(studID, newusername, newpassword);
            Toast.makeText(this, "Student updated successfully", Toast.LENGTH_SHORT).show();
        }
         else if (id == R.id.btn_submit) {
            Log.d("SubmitButton", "Submitted");
            boolean isValid = true;
            String in_username = username.getText().toString();
            String in_password = password.getText().toString();

            Log.d("SubmitButton", "LENGHT: " + in_username.length());

            err_username.setVisibility(View.INVISIBLE);
            err_password.setVisibility(View.INVISIBLE);

            if ( in_username.length() < 1 ) {
                err_username.setVisibility(View.VISIBLE);
                isValid = false;
            }

            if ( in_password.length() < 1 ) {
                err_password.setVisibility(View.VISIBLE);
                isValid = false;
            }

            if ( isValid ) {
                Student student = new Student(in_username, in_password);

                long check = dbHelper.InsertUser(student);
                Log.d("Insert_User", check+"");
                if (check > 0 ) {
                    username.setText("");
                    password.setText("");
                }
            }
        }
    }
}