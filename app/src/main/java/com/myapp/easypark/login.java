package com.myapp.easypark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    private static final String TAG = "message";
    private EditText Email,Password;
    private DatabaseLite mydb;
    String email,password;
    private boolean emailb,passwordb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email = (EditText) findViewById(R.id.email_login);
        Password = (EditText) findViewById(R.id.pass_login);
        mydb = new DatabaseLite(this);
        Log.i(TAG,"here");
    }

    public void login_submitClick(View view){
        email = Email.getText().toString();
        password = Password.getText().toString();
        Cursor cursor = mydb.getData();
        Log.i(TAG," "+cursor);
        emailb = false;
        passwordb = false;

        while(cursor.moveToNext()){
            if (email.equals(cursor.getString(1))){
                emailb = true;
                if (password.equals(cursor.getString(2))){
                    passwordb = true;
                }
            }
        }



        if (!emailb){
            Toast.makeText(this, "Incorrect Email", Toast.LENGTH_SHORT).show();
        }
        else if(emailb && !passwordb){
            Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT).show();
        }
        else if(emailb && passwordb){
            Toast.makeText(this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
            mydb.updateSigned(email,"1");
            startActivity(new Intent(login.this,Menu.class));
            finish();
        }
        else{
            Toast.makeText(this, "Not Successfull", Toast.LENGTH_SHORT).show();
        }
    }
}
