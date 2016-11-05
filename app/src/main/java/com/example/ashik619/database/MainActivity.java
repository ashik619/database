package com.example.ashik619.database;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DBHelper mydb;
    EditText ed1;
    EditText ed2;
    EditText ed3;
    String ip1;
    String ip2;
    String ip3;
    TextView tb1;
    TextView tb2;
    TextView tb3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        tb1 =(TextView) findViewById(R.id.tb1);
        tb2 =(TextView) findViewById(R.id.tb2);
        tb3 =(TextView) findViewById(R.id.tb3);
        mydb = new DBHelper(this);
        ip1 = ed1.getText().toString();
        ip2 = ed2.getText().toString();
        ip3 = ed3.getText().toString();
    }
    public void create(View v){
        ip1 = ed1.getText().toString();
        ip2 = ed2.getText().toString();
        ip3 = ed3.getText().toString();
        System.out.println(ip1);
        System.out.println(ip2);
        boolean flag = mydb.insertInput(ip1,ip2,ip3);
        if(flag != true){
            Toast.makeText(MainActivity.this, "cannot insert", Toast.LENGTH_SHORT).show();
        }
        display();
    }
    void display(){
        int noOfRows= mydb.numberOfRows();
        //System.out.println(noOfRows);
        if(noOfRows == 0){
            tb1.setText("");
            tb2.setText("");
            tb3.setText("");
        }
          else {
            Cursor res = mydb.getData(noOfRows);
            res.moveToFirst();
            tb1.append(res.getString(res.getColumnIndex("ip1")) + "\n");
            tb2.append(res.getString(res.getColumnIndex("ip2")) + "\n");
            tb3.append(res.getString(res.getColumnIndex("ip3")) + "\n");
            String tos = res.getString(res.getColumnIndex("ip1"));
            //System.out.println("data :" + tos);
        }

    }
    public void deleteall(View v){
        mydb.deleteall();
        display();
    }
}
