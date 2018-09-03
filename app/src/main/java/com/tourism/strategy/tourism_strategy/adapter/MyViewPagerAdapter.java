package com.tourism.strategy.tourism_strategy.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.tourism.strategy.tourism_strategy.fragment.DestinationFragment;
import com.tourism.strategy.tourism_strategy.model.WikiDestinations;

import java.util.List;
import java.util.Map;

public class MyViewPagerAdapter extends FragmentStatePagerAdapter{

    private Map<Integer,List<WikiDestinations.Destination>> map;

    public MyViewPagerAdapter(FragmentManager fm, Map<Integer,List<WikiDestinations.Destination>> map){
        super(fm);
        this.map=map;
    }

    @Override
    public Fragment getItem(int position) {
        DestinationFragment df=new DestinationFragment();
        if(position==0){
            df.setList(map.get(1));
        }else if(position==1){
            df.setList(map.get(2));
        }
        return df;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "国内";
        }
        return "国外";
    }
}
