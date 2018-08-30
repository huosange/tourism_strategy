package com.tourism.strategy.tourism_strategy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.tourism.strategy.tourism_strategy.adapter.WikiAdapter;
import com.tourism.strategy.tourism_strategy.model.Wiki;
import com.tourism.strategy.tourism_strategy.net.ApiRetrofit;
import com.tourism.strategy.tourism_strategy.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.b.V;
import io.reactivex.functions.Consumer;

public class StrategyActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    public RecyclerView recyclerview;

    private int id;
    private List<Wiki> list = new ArrayList<>();
    private WikiAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strategy);
        ButterKnife.bind(this);
        setTitle("攻略");
        showDialog();

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WikiAdapter(this, list);
        adapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.view_empty, null));
        recyclerview.setAdapter(adapter);
        id = getIntent().getIntExtra("id", 0);

        //判断网络是否可用
        if (NetUtils.isNetworkConnected(this)) {
            ApiRetrofit.getInstance().getWiki(id)
                    .compose(NetUtils.<List<Wiki>>io_main())
                    .subscribe(new Consumer<List<Wiki>>() {
                        @Override
                        public void accept(List<Wiki> wikis) throws Exception {
                            for (Wiki wk : wikis) {
                                int type = wk.getCategory_type();
                                if (type == 0 || type == 1 || type == 2 || type == 3 || type == 7 || type == 8) {
                                    list.add(wk);
                                }
                            }
                            adapter.notifyDataSetChanged();
                            hideDialog();
                            recyclerview.setVisibility(View.VISIBLE);
                        }
                    });
        }
    }
}
