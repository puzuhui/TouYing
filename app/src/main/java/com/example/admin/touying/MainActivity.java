package com.example.admin.touying;


import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.admin.touying.base.BaseActivity;
import com.example.admin.touying.base.BaseFragment;
import com.example.admin.touying.view.fragment.AddFragment;
import com.example.admin.touying.view.fragment.AttentionFragment;
import com.example.admin.touying.view.fragment.HomePagerFragment;
import com.example.admin.touying.view.fragment.MineFragment;
import com.example.admin.touying.view.fragment.NewsFragment;

import java.util.ArrayList;


public class MainActivity extends BaseActivity {
    ViewPager viewPager;
    RadioButton item1;
    RadioButton item2;
    RadioButton item3;
    RadioButton item4;
    RadioButton item5;
    private ArrayList<BaseFragment> fragmentArrayList;
    private MineFragment mineFragment;
    private HomePagerFragment homePagerFragment;
    private AttentionFragment attentionFragment;
    private NewsFragment newsFragment;
    private AddFragment addFragment;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        fragmentArrayList = new ArrayList<>();
        viewPager = findViewById(R.id.viewpager);
        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        item4 = findViewById(R.id.item4);
        item5 = findViewById(R.id.item5);

        homePagerFragment = new HomePagerFragment();
        attentionFragment = new AttentionFragment();
        newsFragment = new NewsFragment();
        mineFragment = new MineFragment();
        addFragment = new AddFragment();
        fragmentArrayList.add(homePagerFragment);
        fragmentArrayList.add(attentionFragment);
        fragmentArrayList.add(addFragment);
        fragmentArrayList.add(newsFragment);
        fragmentArrayList.add(mineFragment);


        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        // 给ViewPager设置适配器
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        item1.setChecked(true);
                        break;
                    case 1:
                        item2.setChecked(true);
                        break;
                    case 2:
                        item3.setChecked(true);
                        break;
                    case 3:
                        item4.setChecked(true);
                        break;
                    case 4:
                        item5.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        item1.setOnClickListener(onClickListener);
        item2.setOnClickListener(onClickListener);
        item3.setOnClickListener(onClickListener);
        item4.setOnClickListener(onClickListener);
        item5.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.item1:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.item2:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.item3:
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.item4:
                    viewPager.setCurrentItem(3);
                    break;
                case R.id.item5:
                    viewPager.setCurrentItem(4);
                    break;
            }
        }
    };

    //    重写FragmentPagerAdapter方法
    public class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

    }
}
