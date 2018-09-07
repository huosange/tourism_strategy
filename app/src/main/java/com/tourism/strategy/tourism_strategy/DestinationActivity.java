package com.tourism.strategy.tourism_strategy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.tourism.strategy.tourism_strategy.adapter.MyViewPagerAdapter;
import com.tourism.strategy.tourism_strategy.model.WikiDestinations;
import com.tourism.strategy.tourism_strategy.net.ApiRetrofit;
import com.tourism.strategy.tourism_strategy.utils.NetUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

/**
 * 目的地界面
 */
public class DestinationActivity extends BaseActivity {

    @BindView(R.id.tablayout)
    public XTabLayout tablayout;
    @BindView(R.id.viewpager)
    public ViewPager viewpager;

    private Map<Integer, List<WikiDestinations.Destination>> map = new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
        ButterKnife.bind(this);
        setTitle("目的地");

        if (NetUtils.isNetworkConnected(this)) {
            ApiRetrofit.getInstance().getWikiDestinations()
                    .compose(NetUtils.<List<WikiDestinations>>io_main())
                    .subscribe(new Consumer<List<WikiDestinations>>() {
                        @Override
                        public void accept(List<WikiDestinations> wikiDestinations) throws Exception {
                            List<WikiDestinations.Destination> inner = new ArrayList<>();
                            List<WikiDestinations.Destination> outer = new ArrayList<>();
                            for (WikiDestinations wds : wikiDestinations) {
                                for (WikiDestinations.Destination wd : wds.getDestinations()) {
                                    String category = wds.getCategory();
                                    if (category.equals("1") || category.equals("2") || category.equals("3")) {
                                        outer.add(wd);
                                    } else {
                                        inner.add(wd);
                                    }
                                }
                            }
                            map.put(1, inner);
                            map.put(2, outer);
                            viewpager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), map));
                            tablayout.setupWithViewPager(viewpager);
                        }
                    });
        }
    }
}
