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

public class OolongTeaFragment extends Fragment {
    private RecyclerView recyclerView;
    private  RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<TeaCardModel> teaList;
    private DBHelper helper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_oolong_tea, container, false);

        recyclerView = v.findViewById(R.id.oolongRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.HORIZONTAL,false));

//        data is here for tea cards
        helper = new DBHelper(getContext());
        teaList = new ArrayList<>();
        Cursor cursor = helper.getDataByType("Oolong");
        cursor.moveToFirst();
        do {
            String name = cursor.getString(1);
            String type = cursor.getString(2);
            String region = cursor.getString(3);
            int price = cursor.getInt(4);
            int pic = -1;
//            conditions for oolong tea
            if(name.trim().equals("TI KUAN YIN")) {
                pic = R.drawable.tikuanyin;
            } else if(name.trim().equals("DAN CONG")) {
                pic = R.drawable.dancong;
            } else if(name.trim().equals("DA HONG PAO")) {
                pic = R.drawable.dahongpao;
            } else if(name.trim().equals("JIN XUAN")) {
                pic = R.drawable.jinxuan;
            } else if(name.trim().equals("SI JI CHUN")) {
                pic = R.drawable.sijichun;
            } else if(name.trim().equals("ALI SHAN")) {
                pic = R.drawable.alishan;
            } else {
                pic = R.drawable.kenyan;
            }

            TeaCardModel model = new TeaCardModel(pic,name,price+"","10");
            teaList.add(model);
        } while (cursor.moveToNext());
//        data ends here

//        now use recycler view
        recyclerViewAdapter = new RecyclerViewAdapter(v.getContext(),teaList,0);
        recyclerView.setAdapter(recyclerViewAdapter);

        return v;
    }
}