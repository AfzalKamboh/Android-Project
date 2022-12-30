package com.example.teaheaven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class TeaItemActivity extends AppCompatActivity {
    private TextView teaName;
    private TextView tempAndSleep;
    private TextView likes;
    private ImageView pic;
    private SeekBar seekBar;
    private TextView tvSeekBar;
    private TextView tvPrice;
    private RadioGroup radioGroup;
    public DBHelper helper;
//  views used for adding and subtracting the items
    private TextView tvTotal;
    int totalTea = 0;
    int price = 0;
    SharedPreferences preferences;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_item);

        Intent intent = getIntent();
        preferences = getSharedPreferences("users",MODE_PRIVATE);
        username = preferences.getString("username","null");

        helper = new DBHelper(this);

        teaName = findViewById(R.id.tvTeaName);
        tempAndSleep = findViewById(R.id.tvTempSleep);
        likes = findViewById(R.id.tvLikes);
        pic = findViewById(R.id.imTeaPic);
        seekBar = findViewById(R.id.seekBar);
        tvSeekBar = findViewById(R.id.seekValue);

        tvTotal = findViewById(R.id.tvTotalItems);
        tvPrice = findViewById(R.id.tvTotalPrice);
        radioGroup = findViewById(R.id.radioGroup);

        teaName.setText(intent.getStringExtra("name"));
        tvPrice.setText(intent.getStringExtra("price"));
        price = Integer.parseInt(intent.getStringExtra("price"));

        if(teaName.getText().toString().equals("Assam")) {
            pic.setImageResource(R.drawable.assam);
        } else if(teaName.getText().toString().equals("Darjeeling")) {
            pic.setImageResource(R.drawable.dajeel);
        } else if(teaName.getText().toString().equals("Ceylon")) {
            pic.setImageResource(R.drawable.ceylon);
        } else if(teaName.getText().toString().equals("Chai Kee Mun")) {
            pic.setImageResource(R.drawable.chaikimun);
        } else if(teaName.getText().toString().equals("Earl Gray")) {
            pic.setImageResource(R.drawable.earlgrey);
        } else if(teaName.getText().toString().equals("Lapsang Souchong")) {
            pic.setImageResource(R.drawable.lapso);
        } else if(teaName.getText().toString().equals("Yunnan Red")) {
            pic.setImageResource(R.drawable.yunnan);
        } else if(teaName.getText().toString().equals("Kenyan")) {
            pic.setImageResource(R.drawable.kenyan);
        }
//        green tea else ifs
        else if(teaName.getText().toString().equals("Matcha")) {
            pic.setImageResource(R.drawable.matcha);
        } else if(teaName.getText().toString().equals("Sencha")) {
            pic.setImageResource(R.drawable.sencha);
        } else if(teaName.getText().toString().equals("Hojicha")) {
            pic.setImageResource(R.drawable.hojicha);
        } else if(teaName.getText().toString().equals("Gyokuro")) {
            pic.setImageResource(R.drawable.gyokuro);
        } else if(teaName.getText().toString().equals("Tencha")) {
            pic.setImageResource(R.drawable.tencha);
        } else if(teaName.getText().toString().equals("Funmatsucha")) {
            pic.setImageResource(R.drawable.funmatsucha);
        } else if(teaName.getText().toString().equals("Agari")) {
            pic.setImageResource(R.drawable.agari);
        } else if(teaName.getText().toString().equals("Kabusecha")) {
            pic.setImageResource(R.drawable.kabusecha);
        } else if(teaName.getText().toString().equals("Fukamushi cha")) {
            pic.setImageResource(R.drawable.fukamushi_cha);
        } else if(teaName.getText().toString().equals("Kukicha")) {
            pic.setImageResource(R.drawable.kukicha);
        } else if(teaName.getText().toString().equals("Kamairicha")) {
            pic.setImageResource(R.drawable.kamairicha);
        } else if(teaName.getText().toString().equals("Genmaicha")) {
            pic.setImageResource(R.drawable.genmaicha);
        } else if(teaName.getText().toString().equals("Bancha")) {
            pic.setImageResource(R.drawable.bancha);
        } else if(teaName.getText().toString().equals("Shincha")) {
            pic.setImageResource(R.drawable.shincha);
        }
//        oolong tea ifs
        else if(teaName.getText().toString().equals("TI KUAN YIN")) {
            pic.setImageResource(R.drawable.tikuanyin);
        } else if(teaName.getText().toString().equals("DAN CONG")) {
            pic.setImageResource(R.drawable.dancong);
        } else if(teaName.getText().toString().equals("DA HONG PAO")) {
            pic.setImageResource(R.drawable.dahongpao);
        } else if(teaName.getText().toString().equals("JIN XUAN")) {
            pic.setImageResource(R.drawable.jinxuan);
        } else if(teaName.getText().toString().equals("SI JI CHUN")) {
            pic.setImageResource(R.drawable.sijichun);
        } else if(teaName.getText().toString().equals("ALI SHAN")) {
            pic.setImageResource(R.drawable.alishan);
        }
//        white tea ifs
        else if(teaName.getText().toString().equals("Silver Needle")) {
            pic.setImageResource(R.drawable.silverneedle);
        } else if(teaName.getText().toString().equals("White Peony")) {
            pic.setImageResource(R.drawable.whitepeony);
        } else if(teaName.getText().toString().equals("Gong Mei")) {
            pic.setImageResource(R.drawable.gongmei);
        } else if(teaName.getText().toString().equals("Shou Mei")) {
            pic.setImageResource(R.drawable.shoumei);
        } else if(teaName.getText().toString().equals("Ceylon White")) {
            pic.setImageResource(R.drawable.ceylonwhite);
        } else if(teaName.getText().toString().equals("African White")) {
            pic.setImageResource(R.drawable.africanwhite);
        }

//        pic.setImageResource(R.drawable.assam);
//        pic.setImageBitmap(((Bitmap)intent.getParcelableExtra("bitmap")));


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSeekBar.setText(progress+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public void plus(View view) {
        totalTea++;
        tvTotal.setText(totalTea+"");
        tvPrice.setText((price*totalTea)+"");
    }
    public void minus(View view) {
        if(totalTea>0) {
            totalTea--;
            tvTotal.setText(totalTea+"");
            tvPrice.setText((price*totalTea)+"");
        }
    }
    public void addToCart(View view) {
        int counts = totalTea;
        int radioID = radioGroup.getCheckedRadioButtonId();
        String itemName = teaName.getText().toString();

        if(radioID != -1) {
            RadioButton radioButton = findViewById(radioID);
            String gramText = radioButton.getText().toString();
            String t = trimGrams(gramText);
            int grams = Integer.parseInt(t);

//            Toast.makeText(this, grams + "", Toast.LENGTH_SHORT).show();
//            get the username from shared preference
            Log.v("price",price+"");
            helper.insertItemIntoCart(username,itemName,grams,counts,price);

            finish();
//            Intent intent = new Intent(this, CartActivity.class);
//            startActivity(intent);
        } else {
            Toast.makeText(this, "Quantity not selected", Toast.LENGTH_SHORT).show();
        }
//        show();
    }
    public String trimGrams(String s) {
        StringBuilder builder = new StringBuilder();
        char[] arr = s.toCharArray();
        for(char c: arr) {
            if((int) c >= 48 && (int) c <= 57) {
                builder.append(c);
            }
        }
        return builder.toString();
    }
    public void sh(View view) {
        helper.deleteFromCart(username);
//        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
//        Cursor cursor = helper.getDataByType("Black");
//        cursor.moveToFirst();
//        do {
//            Log.v("name", cursor.getString(1));
//            Log.v("type",cursor.getString(2));
//            Log.v("region",cursor.getString(3));
//            Log.v("price",cursor.getInt(4)+"");
//        } while (cursor.moveToNext());
//        cursor.close();
    }
//    test method
//    public void show() {
//        helper.del("usama");
//        Cursor cursor = helper.getData("usama");
//        cursor.moveToFirst();
//        do {
//            Log.v("username",cursor.getString(1));
//            Log.v("name",cursor.getString(2));
//            Log.v("grams",cursor.getInt(3)+"");
//            Log.v("count",cursor.getInt(4)+"");
//            Log.v("price",cursor.getInt(5)+"");
//        } while (cursor.moveToNext());
//        cursor.close();
//    }
}