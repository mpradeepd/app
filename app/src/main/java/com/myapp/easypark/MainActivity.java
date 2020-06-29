package com.myapp.easypark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    DatabaseLite mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseLite(this);
        Cursor result = mydb.getData();
        boolean isSigned = false;
        while (result.moveToNext()){
            if (result.getString(3).equals("1")){
                startActivity(new Intent(MainActivity.this,Menu.class));
                finish();
            }
        }

    }

    public void loginClick(View view){
        startActivity(new Intent(MainActivity.this,login.class));
        finish();

    }

    public void registerClick(View view){
        startActivity(new Intent(MainActivity.this,Register.class));
    }

    public void showClick(View view){
        startActivity(new Intent(MainActivity.this,slots.class));
    }
}
