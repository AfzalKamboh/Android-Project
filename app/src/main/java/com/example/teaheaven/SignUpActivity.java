package com.example.teaheaven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    EditText edEmail;
    EditText edUsername;
    EditText edPass;
    EditText edRepeatPass;
    DBHelper helper;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edEmail = findViewById(R.id.edtSignEmail);
        edUsername = findViewById(R.id.edtSignUser);
        edPass = findViewById(R.id.edtSignPass);
        edRepeatPass = findViewById(R.id.edtSignRptPass);

        helper = new DBHelper(this);
        preferences = getSharedPreferences("users", MODE_PRIVATE);
    }

    public void checkSign(View view) {
        boolean flag = true;
        String username = edUsername.getText().toString();
        String email = edEmail.getText().toString();
        String password = edPass.getText().toString();
        String repeatPass = edRepeatPass.getText().toString();

        if(flag && email.length() == 0) {
            Toast.makeText(this, "Fill in Email", Toast.LENGTH_SHORT).show();
            flag = false;
        }
        if(flag && username.length() == 0) {
            Toast.makeText(this, "Fill in Username", Toast.LENGTH_SHORT).show();
            flag = false;
        }
        if(flag && password.length() == 0) {
            Toast.makeText(this, "Fill in Password", Toast.LENGTH_SHORT).show();
            flag = false;
        }
        if(flag && repeatPass.length() == 0) {
            Toast.makeText(this, "Fill in  Repeat Password", Toast.LENGTH_SHORT).show();
            flag = false;
        }
        if(flag && !(repeatPass.equals(password))) {
            Toast.makeText(this, "Password not matching", Toast.LENGTH_SHORT).show();
            flag = false;
        }
        if(flag && !(helper.checkIfUserNameExists(username))) {

            helper.insertUser(username,password,email);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username",username);
            editor.apply();

            Intent intent = new Intent(this, Homepage.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else if(flag && helper.checkIfUserNameExists(username)) {
            Toast.makeText(this, "Username Already exists", Toast.LENGTH_SHORT).show();
        }
    }

}