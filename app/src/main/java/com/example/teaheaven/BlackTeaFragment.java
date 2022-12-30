package com.example.teaheaven;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BlackTeaFragment extends Fragment {

    private RecyclerView recyclerView;
    private  RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<TeaCardModel> contactArrayList;
    private DBHelper helper;
    View v;
    static Context context = null;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        Toast.makeText(getContext(), "Bingo", Toast.LENGTH_SHORT).show();
    }

    //    private ArrayAdapter
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_black_tea, container, false);
        context = v.getContext();
//        Toast.makeText(v.getContext(), "Bingo A", Toast.LENGTH_SHORT).show();
        setup();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
//        Toast.makeText(v.getContext(), "Bingo B", Toast.LENGTH_SHORT).show();
//        setup();
    }
    public void setup() {
//        Toast.makeText(v.getContext(), "Bingo", Toast.LENGTH_SHORT).show();
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.HORIZONTAL,false));

//        data is here for tea cards
        helper = new DBHelper(v.getContext());
        helper.loadDatabase();
        Cursor cursor = helper.getDataByType("Black");
        cursor.moveToFirst();
        contactArrayList = new ArrayList<>();
        do {
            String name = cursor.getString(1);
            String type = cursor.getString(2);
            String region = cursor.getString(3);
            System.out.println(name);
            System.out.println(type);
            System.out.println(region);
            int price = cursor.getInt(4);
            int pic = -1;
//            conditions for black tea

            if(name.trim().equals("Assam")) {
                Log.v("in","Insied");
                pic = R.drawable.assam;
            } else if (name.trim().equals("Darjeeling")) {
                pic = R.drawable.dajeel;
            } else if(name.trim().equals("Ceylon")) {
                pic = R.drawable.ceylon;
            } else if(name.trim().equals("Chai Kee Mun")) {
                pic = R.drawable.chaikimun;
            } else if(name.trim().equals("English")) {
                pic = R.drawable.english;
            } else if(name.trim().equals("Earl Gray")) {
                pic = R.drawable.earlgrey;
            } else if(name.trim().equals("Lapsang Souchong")) {
                pic = R.drawable.lapso;
            } else if(name.trim().equals("Yunnan Red")) {
                pic = R.drawable.yunnan;
            } else if (name.trim().equals("Kenyan")) {
                pic = R.drawable.kenyan;
            } else {
                pic = R.drawable.kenyan;
            }

            TeaCardModel model = new TeaCardModel(pic,name,price+"","10");
            contactArrayList.add(model);
        } while (cursor.moveToNext());

        recyclerViewAdapter = new RecyclerViewAdapter(v.getContext(),contactArrayList,0);
        recyclerView.setAdapter(recyclerViewAdapter);

    }
}