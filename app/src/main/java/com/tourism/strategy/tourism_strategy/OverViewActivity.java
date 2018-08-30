package com.tourism.strategy.tourism_strategy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tourism.strategy.tourism_strategy.adapter.OverViewAdapter;
import com.tourism.strategy.tourism_strategy.model.Wiki;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 概览详情
 */
public class OverViewActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    public RecyclerView recyclerview;

    private Wiki.Page page;
    private List<Wiki.Page.Child> list = new ArrayList<>();
    private OverViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        ButterKnife.bind(this);
        setTitle(getIntent().getStringExtra("name"));
        showDialog();

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        page = (Wiki.Page) getIntent().getSerializableExtra("page");
        list.addAll(page.getChildren());
        adapter = new OverViewAdapter(this, list);
        recyclerview.setAdapter(adapter);
        hideDialog();
    }
}
