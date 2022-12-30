package com.example.teaheaven;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class WhiteTeaFragment extends Fragment {
    private RecyclerView recyclerView;
    private  RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<TeaCardModel> teaList;
    private DBHelper helper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_white_tea, container, false);

        recyclerView = v.findViewById(R.id.rooibosRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.HORIZONTAL,false));

//        data is here for tea cards
        helper = new DBHelper(getContext());
        teaList = new ArrayList<>();
        Cursor cursor = helper.getDataByType("White");
        cursor.moveToFirst();
        do {
            String name = cursor.getString(1);
            String type = cursor.getString(2);
            String region = cursor.getString(3);
            int price = cursor.getInt(4);
            int pic = -1;
//            conditions for oolong tea
            if(name.trim().equals("Silver Needle")) {
                Log.v("Inside","1");
                pic = R.drawable.silverneedle;
            } else if(name.trim().equals("White Peony")) {
                Log.v("Inside","2");
                pic = R.drawable.whitepeony;
            } else if(name.trim().equals("Gong Mei")) {
                Log.v("Inside","3");
                pic = R.drawable.gongmei;
            } else if(name.trim().equals("Shou Mei")) {
                Log.v("Inside","4");
                pic = R.drawable.shoumei;
            } else if(name.trim().equals("Ceylon White")) {
                Log.v("Inside","5");
                pic = R.drawable.ceylonwhite;
            } else if(name.trim().equals("African White")) {
                Log.v("Inside","6");
                pic = R.drawable.africanwhite;
            } else {
                Log.v("Inside","7");
                pic = R.drawable.kenyan;
            }

            TeaCardModel model = new TeaCardModel(pic,name,price+"","10");
            teaList.add(model);
        } while (cursor.moveToNext());
//        now use recycler view
        recyclerViewAdapter = new RecyclerViewAdapter(v.getContext(),teaList,0);
        recyclerView.setAdapter(recyclerViewAdapter);

        return v;
    }
}