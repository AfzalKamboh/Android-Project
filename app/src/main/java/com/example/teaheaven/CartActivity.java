package com.example.teaheaven;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private  RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<TeaCardModel> teaList;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

//        preferences =  getSharedPreferences("MyPrefs",MODE_PRIVATE);
//        recyclerView = findViewById(R.id.cartTeaRecycler);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
////        data is here for tea cards
//        TeaCardModel m1 = new TeaCardModel(R.drawable.assam,"assam","12","2");
//        TeaCardModel m2 = new TeaCardModel(R.drawable.dajeel,"darjeel","12","2");
//        TeaCardModel m3 = new TeaCardModel(R.drawable.assam,"assam","12","2");
//        TeaCardModel m4 = new TeaCardModel(R.drawable.dajeel,"darjeel","12","2");
//        TeaCardModel m5 = new TeaCardModel(R.drawable.assam,"assam","12","2");
//        TeaCardModel m6 = new TeaCardModel(R.drawable.dajeel,"darjeel","12","2");
//        teaList = new ArrayList<>();
//        teaList.add(m1);
//        teaList.add(m2);
//        teaList.add(m3);
//        teaList.add(m4);
//        teaList.add(m5);
//        teaList.add(m6);
////        data ends here
//
////        now use recycler view
//        recyclerViewAdapter = new RecyclerViewAdapter(this,teaList,2);
//        recyclerView.setAdapter(recyclerViewAdapter);
    }
//    public void contPurchase(View view) {
////        SharedPreferences.Editor editor = preferences.edit();
////        String name = findViewById(R.id.)
////        editor.putString("")
////        Intent intent = new Intent(this, PurchaseActivity.class);
////        startActivity(intent);
//    }
//    public void btnMinu(View view) {
//
//    }
}