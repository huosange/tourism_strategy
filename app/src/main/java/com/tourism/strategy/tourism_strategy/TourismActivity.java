package com.tourism.strategy.tourism_strategy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
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
    private int count;
    private int currentPage = 1;
    private int totalPage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourism);
        ButterKnife.bind(this);
        setTitle("游记集");
        showDialog();

        count = getIntent().getIntExtra("count", 0);
        totalPage = count / 10 + (count % 10 == 0 ? 0 : 1);
        attractionId = getIntent().getIntExtra("attractionId", 0);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TourismAdapter(this, list);
        adapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.view_empty,null));
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (currentPage >= totalPage) {
                    adapter.loadMoreEnd();
                } else {
                    currentPage++;
                    load(currentPage);
                }
            }
        }, recyclerview);
        recyclerview.setAdapter(adapter);
        load(currentPage);
    }

    private void load(int page) {
        if (NetUtils.isNetworkConnected(this)) {
            ApiRetrofit.getInstance().getTourism(attractionId, page)
                    .compose(NetUtils.<AttractionTrips>io_main())
                    .subscribe(new Consumer<AttractionTrips>() {
                        @Override
                        public void accept(AttractionTrips attractionTrips) throws Exception {
                            list.addAll(attractionTrips.getAttraction_trips());
                            adapter.notifyDataSetChanged();
                            adapter.loadMoreComplete();
                            hideDialog();
                            recyclerview.setVisibility(View.VISIBLE);
                        }
                    });
        }
    }
}
