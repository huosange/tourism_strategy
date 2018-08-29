package com.tourism.strategy.tourism_strategy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tourism.strategy.tourism_strategy.adapter.PlanAdapter;
import com.tourism.strategy.tourism_strategy.model.Plan;
import com.tourism.strategy.tourism_strategy.net.ApiRetrofit;
import com.tourism.strategy.tourism_strategy.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

/**
 * 行程界面
 */
public class TripActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    public RecyclerView recyclerview;

    private int cid;
    private List<Plan> list = new ArrayList<>();
    private PlanAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
        ButterKnife.bind(this);

        cid=getIntent().getIntExtra("cid",0);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PlanAdapter(this, list);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(TripActivity.this,TripDetailActivity.class);
                intent.putExtra("planId",list.get(position).getId());
                startActivity(intent);
            }
        });
        recyclerview.setAdapter(adapter);
        ApiRetrofit.getInstance().getPlans(cid, 1)
                .compose(NetUtils.<List<Plan>>io_main())
                .subscribe(new Consumer<List<Plan>>() {
                    @Override
                    public void accept(List<Plan> plans) throws Exception {
                        list.addAll(plans);
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
