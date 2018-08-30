package com.tourism.strategy.tourism_strategy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.tourism.strategy.tourism_strategy.adapter.PlanDayAdapter;
import com.tourism.strategy.tourism_strategy.model.PlanDetail;
import com.tourism.strategy.tourism_strategy.net.ApiRetrofit;
import com.tourism.strategy.tourism_strategy.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class TripDetailActivity extends BaseActivity{

    @BindView(R.id.recyclerview)
    public RecyclerView recyclerview;

    private int planId;
    private List<PlanDetail.PlanDay> list=new ArrayList<>();
    private PlanDayAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_detail);
        ButterKnife.bind(this);
        setTitle("行程");

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter=new PlanDayAdapter(this,list);
        recyclerview.setAdapter(adapter);

        planId=getIntent().getIntExtra("planId",0);
        if(NetUtils.isNetworkConnected(this)){
        ApiRetrofit.getInstance().getPlanDetail(planId)
                .compose(NetUtils.<PlanDetail>io_main())
                .subscribe(new Consumer<PlanDetail>() {
                    @Override
                    public void accept(PlanDetail planDetail) throws Exception {
                        list.addAll(planDetail.getPlan_days());
                        adapter.notifyDataSetChanged();
                    }
                });}
    }
}
