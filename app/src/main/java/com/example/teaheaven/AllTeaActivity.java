package com.example.teaheaven;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class AllTeaActivity extends AppCompatActivity {
    DBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tea);

        helper = new DBHelper(this);

//        setting the title
        TextView tvName = findViewById(R.id.tvAllTeaTitle);
        Intent intent = getIntent();
        int type = intent.getIntExtra("TeaType",-1);
        String typeForDatabaseSrch = "";
        if(type == 0) {
            tvName.setText("Black Tea");
            typeForDatabaseSrch = "Black";
        } else if(type == 1) {
            tvName.setText("Green Tea");
            typeForDatabaseSrch = "Green";
        } else if(type == 2) {
            tvName.setText("Oolong Tea");
            typeForDatabaseSrch = "Oolong";
        } else if(type == 3) {
            tvName.setText("White Tea");
            typeForDatabaseSrch = "White";
        }

//        populating recycler view
        RecyclerView recyclerView = findViewById(R.id.allTeaRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor = helper.getDataByType(typeForDatabaseSrch);
        ArrayList<TeaCardModel> teaList = new ArrayList<>();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                String name = cursor.getString(1);
                String price = cursor.getInt(4)+"";
                String weight = "2";
                int pic = getImage(name);
                TeaCardModel model = new TeaCardModel(pic,name,price,weight);
                teaList.add(model);

            } while (cursor.moveToNext());
        }

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this,teaList,1);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
    public int getImage(String name) {
        int pic = -1;
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
        }
//        green  tea
        else if(name.trim().equals("Matcha")) {
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
        }
//        oolong
        else if(name.trim().equals("TI KUAN YIN")) {
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
        }
//        white tea
        else if(name.trim().equals("Silver Needle")) {
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



        return pic;
    }
}