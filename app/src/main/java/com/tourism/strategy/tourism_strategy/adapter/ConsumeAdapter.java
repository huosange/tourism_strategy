package com.tourism.strategy.tourism_strategy.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.model.Consume;

import java.util.List;

public class ConsumeAdapter extends BaseQuickAdapter<Consume, BaseViewHolder> {

    public ConsumeAdapter(List<Consume> list) {
        super(R.layout.item_consume, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, Consume item) {
        helper.setText(R.id.time, item.getTime())
                .setText(R.id.money, item.getMoney() + "韩元")
                .setText(R.id.summary, item.getSummary());
    }
}
