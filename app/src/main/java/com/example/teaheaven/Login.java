package com.example.teaheaven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    DBHelper helper;
    EditText edUser;
    EditText edPass;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        helper = new DBHelper(this);
        edUser = findViewById(R.id.edtUser);
        edPass = findViewById(R.id.edtPass);

        preferences = getSharedPreferences("users",MODE_PRIVATE);
    }
    public void login(View view) {
        String username = edUser.getText().toString();
        String password = edPass.getText().toString();

        if(helper.checkPass(username,password)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username",username);
            editor.apply();
            Intent intent = new Intent(this, Homepage.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Wrong username or password", Toast.LENGTH_SHORT).show();
        }
    }
}