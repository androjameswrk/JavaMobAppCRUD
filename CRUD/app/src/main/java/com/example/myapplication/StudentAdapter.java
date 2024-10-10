package com.example.myapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class StudentAdapter extends ArrayAdapter<Student> {

    private ArrayList<Student> students = null;
    private Context context = null;
    private int layoutResource = -1;

    public StudentAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Student> students) {
        super(context, resource, students);

        this.students = students;
        this.context = context;
        this.layoutResource = resource;
    }

    @Nullable
    @Override
    public Student getItem(int position) {
        return students.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Student student = getItem(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View studentItemLayout = inflater.inflate(layoutResource, parent, false);

        TextView studentIdTextView = studentItemLayout.findViewById(R.id.student_id);
        TextView studentNameTextView = studentItemLayout.findViewById(R.id.student_name);
        TextView studentPasswordTextView = studentItemLayout.findViewById(R.id.student_password);

        // set the student name
        studentIdTextView.setText(student.getId() + "");
        studentNameTextView.setText(student.getUsername());
        studentPasswordTextView.setText(student.getPassword());

        return studentItemLayout;
    }
}
