package com.example.teaheaven;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import java.util.ArrayList;

public class Homepage extends AppCompatActivity {
//    public RecyclerView recyclerView;
//    public teaCardModel teaCard;
//    public ArrayList<teaCardModel> teaList;
    MeowBottomNavigation bottomNavigation;
    Fragment mainFragment;
    Fragment cartFragment;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        preferences = getSharedPreferences("users",MODE_PRIVATE);
//        Log.v("User name is",preferences.getString("username","Null"));
        mainFragment = new MainFragment();
        cartFragment = new CartFragment();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.mainScreen,mainFragment);
        transaction.commit();

        bottomNavigation = findViewById(R.id.bottom_nav);
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_favorite_border_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_shopping_cart_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.ic_baseline_person_24));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                switch (item.getId()) {
                    case 1:
                        transaction.replace(R.id.mainScreen,mainFragment);
                        transaction.commit();
                        System.out.println("SSSSSSSSSSSSSSSSSS");
                        break;
                    case 2:
//                        replace transaction with favorite fragment
                        break;
                    case 3:
//                        replace transaction with cart fragment
                        transaction.replace(R.id.mainScreen, cartFragment);
                        transaction.commit();
                        break;
                    case 4:
//                        replace transaction with profile fragment
                        break;
                }
//                transaction.commit();
            }
        });
        bottomNavigation.show(1,true);
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });
        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });
    }
}