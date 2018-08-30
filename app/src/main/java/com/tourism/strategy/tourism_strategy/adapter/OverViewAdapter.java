package com.tourism.strategy.tourism_strategy.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.model.Wiki;

import java.util.List;

public class OverViewAdapter extends BaseQuickAdapter<Wiki.Page.Child, BaseViewHolder> {

    private Context context;

    public OverViewAdapter(Context context, List<Wiki.Page.Child> list) {
        super(R.layout.item_overview, list);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Wiki.Page.Child item) {
        helper.setText(R.id.textview, item.getTitle());
        RecyclerView recyclerview = helper.getView(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        SectionAdapter adapter = new SectionAdapter(context, item.getSections());
        recyclerview.setAdapter(adapter);
    }
}
