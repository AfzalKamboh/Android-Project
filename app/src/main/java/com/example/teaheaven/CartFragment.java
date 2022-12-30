package com.example.teaheaven;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CartFragment extends Fragment {
    private RecyclerView recyclerView;
    private  RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<TeaCardModel> teaList;
    DBHelper helper;
    TextView tvTotalPrice;
    int totalPrice = 0;
    SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        preferences = getActivity().getSharedPreferences("users", Context.MODE_PRIVATE);
        String username = preferences.getString("username","null");

        recyclerView = view.findViewById(R.id.cartTeaRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        helper = new DBHelper(getContext());

//        these lines set the total price of all items in cart
        totalPrice = helper.getTotalPriceFormCart(username);
        tvTotalPrice = view.findViewById(R.id.tvTotalPrice);
        tvTotalPrice.setText("$"+totalPrice);

//        pass the user name here that you get from shared preference
        Cursor cursor = helper.getCartData(username);
        teaList = new ArrayList<>();
        cursor.moveToFirst();
        if(cursor.getCount()>0) {
            do {
                String name = cursor.getString(2);
                String weight = cursor.getInt(3) + "";
                String counts = cursor.getInt(4) + "";
                //            String type = cursor.getString(2);
                //            String region = cursor.getString(3);
                int price = cursor.getInt(5);
                Log.v("Username", cursor.getString(1));
                Log.v("name", name);
                Log.v("weight", weight);
                Log.v("price", price + "");
                int pic = -1;
                //            Log.v("name",name)
                //            conditions for black tea
                if (name.trim().equals("Assam")) {
                    Log.v("in", "Insied");
                    pic = R.drawable.assam;
                } else if (name.trim().equals("Darjeeling")) {
                    pic = R.drawable.dajeel;
                } else if (name.trim().equals("Ceylon")) {
                    pic = R.drawable.ceylon;
                } else if (name.trim().equals("Chai Kee Mun")) {
                    pic = R.drawable.chaikimun;
                } else if (name.trim().equals("English")) {
                    pic = R.drawable.english;
                } else if (name.trim().equals("Earl Gray")) {
                    pic = R.drawable.earlgrey;
                } else if (name.trim().equals("Lapsang Souchong")) {
                    pic = R.drawable.lapso;
                } else if (name.trim().equals("Yunnan Red")) {
                    pic = R.drawable.yunnan;
                } else if (name.trim().equals("Kenyan")) {
                    pic = R.drawable.kenyan;
                }
                //            green tea conditions
                else if (name.trim().equals("Matcha")) {
                    pic = R.drawable.matcha;
                } else if (name.trim().equals("Sencha")) {
                    pic = R.drawable.sencha;
                } else if (name.trim().equals("Hojicha")) {
                    pic = R.drawable.hojicha;
                } else if (name.trim().equals("Gyokuro")) {
                    pic = R.drawable.gyokuro;
                } else if (name.trim().equals("Tencha")) {
                    pic = R.drawable.tencha;
                } else if (name.trim().equals("Funmatsucha")) {
                    pic = R.drawable.funmatsucha;
                } else if (name.trim().equals("Agari")) {
                    pic = R.drawable.agari;
                } else if (name.trim().equals("Kabusecha")) {
                    pic = R.drawable.kabusecha;
                } else if (name.trim().equals("Fukamushi cha")) {
                    pic = R.drawable.fukamushi_cha;
                } else if (name.trim().equals("Kukicha")) {
                    pic = R.drawable.kukicha;
                } else if (name.trim().equals("Kamairicha")) {
                    pic = R.drawable.kamairicha;
                } else if (name.trim().equals("Genmaicha")) {
                    pic = R.drawable.genmaicha;
                } else if (name.trim().equals("Bancha")) {
                    pic = R.drawable.bancha;
                } else if (name.trim().equals("Shincha")) {
                    pic = R.drawable.shincha;
                }
                //            oolong tea conditions
                else if (name.trim().equals("TI KUAN YIN")) {
                    pic = R.drawable.tikuanyin;
                } else if (name.trim().equals("DAN CONG")) {
                    pic = R.drawable.dancong;
                } else if (name.trim().equals("DA HONG PAO")) {
                    pic = R.drawable.dahongpao;
                } else if (name.trim().equals("JIN XUAN")) {
                    pic = R.drawable.jinxuan;
                } else if (name.trim().equals("SI JI CHUN")) {
                    pic = R.drawable.sijichun;
                } else if (name.trim().equals("ALI SHAN")) {
                    pic = R.drawable.alishan;
                }
                //            white tea conditions
                else if (name.trim().equals("Silver Needle")) {
                    Log.v("Inside", "1");
                    pic = R.drawable.silverneedle;
                } else if (name.trim().equals("White Peony")) {
                    Log.v("Inside", "2");
                    pic = R.drawable.whitepeony;
                } else if (name.trim().equals("Gong Mei")) {
                    Log.v("Inside", "3");
                    pic = R.drawable.gongmei;
                } else if (name.trim().equals("Shou Mei")) {
                    Log.v("Inside", "4");
                    pic = R.drawable.shoumei;
                } else if (name.trim().equals("Ceylon White")) {
                    Log.v("Inside", "5");
                    pic = R.drawable.ceylonwhite;
                } else if (name.trim().equals("African White")) {
                    Log.v("Inside", "6");
                    pic = R.drawable.africanwhite;
                } else {
                    pic = R.drawable.kenyan;
                }

                TeaCardModel model = new TeaCardModel(pic, name, price + "", weight, counts);
                teaList.add(model);
            } while (cursor.moveToNext());
//            now use recycler view
            recyclerViewAdapter = new RecyclerViewAdapter(getContext(), teaList, 2);
            recyclerView.setAdapter(recyclerViewAdapter);
        }
        else {
            Toast.makeText(getContext(), "Cart is empty! ", Toast.LENGTH_SHORT).show();
        }

//        setting on click on button
        Button btnContinue = view.findViewById(R.id.btnContPurchase);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), PurchaseActivity.class);
                intent.putExtra("price",totalPrice);
                startActivity(intent);
            }
        });


        return view;
    }

    public void contin(View view) {

//        SharedPreferences.Editor editor = preferences.edit();
//        String name = findViewById(R.id.)
//        editor.putString("")
//        Intent intent = new Intent(this, PurchaseActivity.class);
//        startActivity(intent);
    }
    public void btnMinu(View view) {

    }
}