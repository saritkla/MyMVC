package com.example.mymvc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText usernameregis,passwordregit;
    Button donregis;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernameregis = findViewById(R.id.usernameregis);
        passwordregit = findViewById(R.id.passwordregis);
        donregis = findViewById(R.id.doneregis);
        db = new DBHelper(this);
        donregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = String.valueOf(usernameregis.getText());
                String password = String.valueOf(passwordregit.getText());
                TodolistDAO todolistDAO = new TodolistDAO(getApplicationContext());
                if (username.isEmpty()&&password.isEmpty())
                    Toast.makeText(Register.this,"password or usernme is null",Toast.LENGTH_LONG).show();
                else {
                    todolistDAO.open();
                    boolean insert = todolistDAO.insertDataUser(username,password);
                    if (!insert) Toast.makeText(Register.this,"same user",Toast.LENGTH_LONG).show();
                    todolistDAO.close();

                }


            }
        });
//
    }
}
