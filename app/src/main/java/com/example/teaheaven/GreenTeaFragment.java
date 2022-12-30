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


public class GreenTeaFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<TeaCardModel> contactArrayList;
    DBHelper helper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_green_tea, container, false);

        recyclerView = v.findViewById(R.id.greenTeaRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.HORIZONTAL,false));

        //        data is here for tea cards
        helper = new DBHelper(getContext());
        Cursor cursor = helper.getDataByType("Green");
        cursor.moveToFirst();
        contactArrayList = new ArrayList<>();

        do {
            String name = cursor.getString(1);
            String type = cursor.getString(2);
            String region = cursor.getString(3);
            int price = cursor.getInt(4);
            int pic = -1;
//            conditions for green tea
            if(name.trim().equals("Matcha")) {
                pic = R.drawable.matcha;
            } else if(name.trim().equals("Sencha")) {
                pic = R.drawable.sencha;
            } else if(name.trim().equals("Hojicha")) {
                pic = R.drawable.hojicha;
            } else if(name.trim().equals("Gyokuro")) {
                pic = R.drawable.gyokuro;
            } else if(name.trim().equals("Tencha")) {
                pic = R.drawable.tencha;
            } else if(name.trim().equals("Funmatsucha")) {
                pic = R.drawable.funmatsucha;
            } else if(name.trim().equals("Agari")) {
                pic = R.drawable.agari;
            } else if(name.trim().equals("Kabusecha")) {
                pic = R.drawable.kabusecha;
            } else if(name.trim().equals("Fukamushi cha")) {
                pic = R.drawable.fukamushi_cha;
            } else if(name.trim().equals("Kukicha")) {
                pic = R.drawable.kukicha;
            } else if(name.trim().equals("Kamairicha")) {
                pic = R.drawable.kamairicha;
            } else if(name.trim().equals("Genmaicha")) {
                pic = R.drawable.genmaicha;
            } else if(name.trim().equals("Bancha")) {
                pic = R.drawable.bancha;
            } else if(name.trim().equals("Shincha")) {
                pic= R.drawable.shincha;
            } else {
                pic = R.drawable.kenyan;
            }

            TeaCardModel model = new TeaCardModel(pic,name,price+"","10");
            contactArrayList.add(model);
        } while (cursor.moveToNext());

//        data ends here

        //        now use recycler view
        recyclerViewAdapter = new RecyclerViewAdapter(v.getContext(),contactArrayList,0);
        recyclerView.setAdapter(recyclerViewAdapter);


        return v;
    }
}