package com.tourism.strategy.tourism_strategy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.tourism.strategy.tourism_strategy.adapter.HomeTripDetailAdapter;
import com.tourism.strategy.tourism_strategy.model.HomeTripDetail;
import com.tourism.strategy.tourism_strategy.net.ApiRetrofit;
import com.tourism.strategy.tourism_strategy.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class HomeTripDetailActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    public RecyclerView recyclerview;

    private HomeTripDetailAdapter adapter;
    private List<HomeTripDetail.TripDay> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hometrip_detail);
        ButterKnife.bind(this);

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HomeTripDetailAdapter(list);
        recyclerview.setAdapter(adapter);

        ApiRetrofit.getInstance().getHomeTripDetail(582490)
                .compose(NetUtils.<HomeTripDetail>io_main())
                .subscribe(new Consumer<HomeTripDetail>() {
                    @Override
                    public void accept(HomeTripDetail homeTripDetail) throws Exception {
                        list.addAll(homeTripDetail.getTrip_days());
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
