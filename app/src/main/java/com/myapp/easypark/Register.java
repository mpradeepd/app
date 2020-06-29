package com.myapp.easypark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    private EditText Email,Password,Repass;
    private DatabaseLite mydb;
    private String email,password,repass;
    private boolean dbResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.pass);
        Repass = (EditText) findViewById(R.id.repass);
        mydb = new DatabaseLite(this);
    }

    public void submitClick(View view){
        email = Email.getText().toString();
        password = Password.getText().toString();
        repass = Repass.getText().toString();



        if (!isEmailValid(email)){
            Toast.makeText(this, "Enter a valid email address", Toast.LENGTH_SHORT).show();
        }
        else if (isRegistered(email)){
            Toast.makeText(this, "This email is already registered", Toast.LENGTH_SHORT).show();
        }
        else if (password.length()<5){
            Toast.makeText(this, "Pleas Enter a password more than 5 charactors", Toast.LENGTH_SHORT).show();
        }
        else if (!password.equals(repass)){
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        }

        else{
            dbResult = mydb.insertUser(email,password);
            if (dbResult){
                Toast.makeText(this, "Registered Succesfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Register.this,login.class));
                finish();
            }
            else{
                Toast.makeText(this, "Not Succesfull. Try Again", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public boolean isRegistered(String email){
        Cursor result = mydb.getData();
        boolean res = false;
        while(result.moveToNext()){
            if (email.equals(result.getString(1))){
                res = true;
            }
        }
        return res;
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
