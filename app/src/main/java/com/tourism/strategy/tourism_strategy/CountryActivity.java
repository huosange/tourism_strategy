package com.tourism.strategy.tourism_strategy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.tourism.strategy.tourism_strategy.adapter.ZoneAdapter;
import com.tourism.strategy.tourism_strategy.model.Zone;
import com.tourism.strategy.tourism_strategy.net.ApiRetrofit;
import com.tourism.strategy.tourism_strategy.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class CountryActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    public RecyclerView recyclerview;

    private int cid;
    private ZoneAdapter adapter;
    private List<Zone> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        ButterKnife.bind(this);

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ZoneAdapter(this, list);
        recyclerview.setAdapter(adapter);

        cid = getIntent().getIntExtra("cid", 0);
        ApiRetrofit.getInstance().getCountry(cid)
                .compose(NetUtils.<List<Zone>>io_main())
                .subscribe(new Consumer<List<Zone>>() {
                    @Override
                    public void accept(List<Zone> zones) throws Exception {
                        list.addAll(zones);
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
