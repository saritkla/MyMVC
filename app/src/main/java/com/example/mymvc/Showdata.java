package com.example.mymvc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Showdata extends AppCompatActivity {
    ListView listView1;
    EditText inputext1,inputext2;
    Button getallbt;
    ArrayAdapter adapterid;
    ArrayList<String> listshowid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdata);
        listView1 = findViewById(R.id.listview1);
        getallbt = findViewById(R.id.getdatabutton);
        inputext1 = findViewById(R.id.inputtext1);
        inputext2 = findViewById(R.id.inputtext2);
        getdatatolistV();
//        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                listView1.getItemAtPosition(position);
//            }
//        });
//        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                listView2.getItemAtPosition(position);
//            }
//        });
        getallbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inp12 = String.valueOf(inputext1.getText());
//                String inp2 = String.valueOf(inputext2.getText());
                String ar[] = inp12.split("\\*\\*");
                String inp1 = ar[0];
                String inp2 = ar[1];
                TodolistDAO todolistDAO = new TodolistDAO(getApplicationContext());
                //if (inp1.isEmpty()&&inp2.isEmpty())
                if (inp1.isEmpty())
                    Toast.makeText(Showdata.this,"password or usernme is null",Toast.LENGTH_LONG).show();
                else {
                    todolistDAO.open();
                    boolean insert = todolistDAO.insertDataUser(inp1,inp2);
                    if (!insert) Toast.makeText(Showdata.this,"same user",Toast.LENGTH_LONG).show();
                    else {
                        listshowid.clear();
                        getdatatolistV();
                    }
                    inputext1.setText("");
                    todolistDAO.close();
                }
            }
        });
    }

    private void getdatatolistV(){
        TodolistDAO todolistDAO = new TodolistDAO(getApplicationContext());
        todolistDAO.open();
        listshowid = todolistDAO.getalluser();
        todolistDAO.close();
        adapterid = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listshowid);
        listView1.setAdapter(adapterid);
    }
}
