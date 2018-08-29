package com.tourism.strategy.tourism_strategy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.tourism.strategy.tourism_strategy.adapter.TourismAdapter;
import com.tourism.strategy.tourism_strategy.model.AttractionTrips;
import com.tourism.strategy.tourism_strategy.net.ApiRetrofit;
import com.tourism.strategy.tourism_strategy.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class TourismActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    public RecyclerView recyclerview;

    private int attractionId;
    private List<AttractionTrips.Trip> list = new ArrayList<>();
    private TourismAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourism);
        ButterKnife.bind(this);

        attractionId = getIntent().getIntExtra("attractionId", 0);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TourismAdapter(this, list);
        recyclerview.setAdapter(adapter);

        ApiRetrofit.getInstance().getTourism(attractionId, 1)
                .compose(NetUtils.<AttractionTrips>io_main())
                .subscribe(new Consumer<AttractionTrips>() {
                    @Override
                    public void accept(AttractionTrips attractionTrips) throws Exception {
                        list.addAll(attractionTrips.getAttraction_trips());
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
