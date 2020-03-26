package com.example.mymvc;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    TextView textshow;
    Button login,register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.Username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.Register);
        textshow = findViewById(R.id.textshow);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String us = String.valueOf(username.getText());
                String pw = String.valueOf(password.getText());
                textshow.setText(us);
                String user = "kla";
                String pwd= "pa";
                if( user.equals(us) && pwd.equals(pw)){
                    Intent toshowdata = new Intent(MainActivity.this,Showdata.class);
                    startActivity(toshowdata);
                }
                else
                    Toast.makeText(MainActivity.this,"Error wrong username or password",Toast.LENGTH_SHORT).show();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toregister = new  Intent(MainActivity.this,Register.class);
                startActivity(toregister);
            }
        });

    }
}
