package com.tourism.strategy.tourism_strategy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tourism.strategy.tourism_strategy.adapter.AttractionAdapter;
import com.tourism.strategy.tourism_strategy.model.Attraction;
import com.tourism.strategy.tourism_strategy.net.ApiRetrofit;
import com.tourism.strategy.tourism_strategy.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class TravelListActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    public RecyclerView recyclerview;

    private List<Attraction> list = new ArrayList<>();
    private AttractionAdapter adapter;
    private int cid;
    private int poi_count;
    private int totalPage;
    private int currentPage = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_list);
        ButterKnife.bind(this);
        setTitle("旅行地");

        //景点的个数
        poi_count = getIntent().getIntExtra("poi_count", 0);
        totalPage = poi_count / 10 + (poi_count % 10 == 0 ? 0 : 1);


        cid = getIntent().getIntExtra("cid", 0);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AttractionAdapter(this, list);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(TravelListActivity.this, AttractionDetailActivity.class);
                intent.putExtra("attractionId", list.get(position).getId());
                intent.putExtra("name", list.get(position).getName());
                startActivity(intent);
            }
        });
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
            ApiRetrofit.getInstance().getAttractions(cid, page)
                    .compose(NetUtils.<List<Attraction>>io_main())
                    .subscribe(new Consumer<List<Attraction>>() {
                        @Override
                        public void accept(List<Attraction> attractions) throws Exception {
                            list.addAll(attractions);
                            adapter.notifyDataSetChanged();
                            adapter.loadMoreComplete();
                        }
                    });
        }
    }
}
