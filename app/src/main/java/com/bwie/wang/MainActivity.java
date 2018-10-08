package com.bwie.wang;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bwie.fragment.FragmentOne;
import com.bwie.fragment.FragmentThree;
import com.bwie.fragment.FragmentTwo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private ViewPager viewPag;
    private RadioButton but1,but2,but3;
    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取id
        radioGroup = findViewById(R.id.radioGroup);
        but1 = findViewById(R.id.but1);
        but2 = findViewById(R.id.but2);
        but3 = findViewById(R.id.but3);
        viewPag = findViewById(R.id.viewPag);

       /* */
       //集合fragment
       list = new ArrayList<>();
       list.add(new FragmentOne());
       list.add(new FragmentTwo());
       list.add(new FragmentThree());
//适配器的创建
       viewPag.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
           @Override
           public Fragment getItem(int position) {
               return list.get(position);
           }

           @Override
           public int getCount() {
               return list.size();
           }
       });

       //fragment
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.but1:
                        viewPag.setCurrentItem(0);
                        break;
                    case R.id.but2:
                        viewPag.setCurrentItem(1);
                        break;
                    case R.id.but3:
                        viewPag.setCurrentItem(2);
                        break;
                }
            }
        });
    }
}