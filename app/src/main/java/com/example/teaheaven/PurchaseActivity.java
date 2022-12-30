package com.example.teaheaven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PurchaseActivity extends AppCompatActivity {
    TextView tvPrice;
    DBHelper helper;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        preferences = getSharedPreferences("users", MODE_PRIVATE);

        helper = new DBHelper(this);
        Intent intent = getIntent();

        tvPrice = findViewById(R.id.tvTotalPrice);
        int price = intent.getIntExtra("price",0);
        tvPrice.setText("$"+price);
    }

    public void contPurchase(View view) {
        String username = preferences.getString("username","null");
        helper.deleteFromCart(username);
//        Toast.makeText(this, "Thank ", Toast.LENGTH_SHORT).show();
//        take the below lines and put these into the new activity that will say thank you.
        Intent intent = new Intent(this, ThanksActivity.class);
        startActivity(intent);
    }
}