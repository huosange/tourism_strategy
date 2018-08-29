package com.tourism.strategy.tourism_strategy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tourism.strategy.tourism_strategy.AttractionDetailActivity;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.model.AttractionTrips;

import java.util.List;

public class TourismAdapter extends BaseQuickAdapter<AttractionTrips.Trip, BaseViewHolder> {

    private Context context;

    public TourismAdapter(Context context, List<AttractionTrips.Trip> list) {
        super(R.layout.item_tourism, list);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, AttractionTrips.Trip item) {
        TextView comment = helper.getView(R.id.comment);
        if (!TextUtils.isEmpty(item.getComment())) {
            comment.setVisibility(View.VISIBLE);
            comment.setText(item.getComment());
        } else {
            comment.setVisibility(View.GONE);
        }
        RecyclerView recyclerView = helper.getView(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        ImageAdapter adapter = new ImageAdapter(context, item.getNotes());
        recyclerView.setAdapter(adapter);
    }
}
