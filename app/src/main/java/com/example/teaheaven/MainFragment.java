package com.example.teaheaven;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class MainFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<TeaCardModel> list;
    View view;
    static BlackTeaFragment black;
    static GreenTeaFragment green;
    OolongTeaFragment oolong;
    WhiteTeaFragment white;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Toast.makeText(getContext(), "Created", Toast.LENGTH_SHORT).show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);
        setup();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
//        setup();
    }
    private void setup() {

        TabLayout tabs;
        ViewPager pager;
        tabs = view.findViewById(R.id.teaTabs);
        pager = view.findViewById(R.id.vPager);

        tabs.setupWithViewPager(pager);
//        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                Log.v("HEEEEELLLOOO",tabs.getSelectedTabPosition()+"");
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
        FragAdapter fragAdapter = new FragAdapter(
                getActivity().
                        getSupportFragmentManager(),
                FragmentPagerAdapter.
                        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        );

//        if(fragAdapter.getCount() == 0) {
//            BlackTeaFragment black = new BlackTeaFragment();
//            Toast.makeText(getContext(), "inside", Toast.LENGTH_SHORT).show();
            fragAdapter.addFragment(new BlackTeaFragment(), "Black");
//        Toast.makeText(getContext(), "Black added", Toast.LENGTH_SHORT).show();
            fragAdapter.addFragment(new GreenTeaFragment(), "Green");
//        Toast.makeText(getContext(), "green added", Toast.LENGTH_SHORT).show();
            fragAdapter.addFragment(new OolongTeaFragment(), "Oolong");
//        Toast.makeText(getContext(), "Oolong added", Toast.LENGTH_SHORT).show();
            fragAdapter.addFragment(new WhiteTeaFragment(), "White");
//        Toast.makeText(getContext(), "white added", Toast.LENGTH_SHORT).show();

            pager.setAdapter(fragAdapter);
//            fragAdapter.notifyDataSetChanged();
//        }
//        else {
//            Toast.makeText(getContext(), "------> " + fragAdapter.getCount(), Toast.LENGTH_SHORT).show();
//        }

        Button seeAllBtn = view.findViewById(R.id.seeAll);
        seeAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tabNum = tabs.getSelectedTabPosition();
                Intent intent = new Intent(v.getContext(),AllTeaActivity.class);
                int type = 0;
                if(tabNum == 0) {
                    type = 0;
                } else if(tabNum == 1) {
                    type = 1;
                } else if(tabNum == 2) {
                    type = 2;
                } else {
                    type = 3;
                }
//                Toast.makeText(v.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                intent.putExtra("TeaType",type);
                startActivity(intent);
            }
        });
    }
}